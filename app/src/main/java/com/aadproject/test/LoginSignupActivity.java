package com.aadproject.test;

import static android.app.PendingIntent.getActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LoginSignupActivity extends AppCompatActivity {

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_logout);

        EditText emailField = findViewById(R.id.email_field);
        EditText passwordField = findViewById(R.id.password_field);
        EditText nameField = findViewById(R.id.name_field);
        Button signUpButton = findViewById(R.id.signup_button);
        TextView loginText = findViewById(R.id.redirect_text);

        signUpButton.setOnClickListener(v -> {
            String email = emailField.getText().toString().trim();
            String password = passwordField.getText().toString().trim();
            String name = nameField.getText().toString().trim();

            // Check if any field is empty
            if (email.isEmpty() || password.isEmpty() || name.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please fill in all fields", Toast.LENGTH_LONG).show();
            } else {
                Log.d("test", email + " + " + password);
                signUpUser(email, password, name);
            }
        });

        loginText.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        });
    }

    private void signUpUser(String email, String password, String name) {
        // Check if a user with this email already exists
        db.collection("Users")
                .whereEqualTo("email", email) // Query to find documents with the same email
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && !task.getResult().isEmpty()) {
                        // Email already exists, show a message
                        Toast.makeText(getApplicationContext(), "Account with this email already exists", Toast.LENGTH_LONG).show();
                    } else {
                        // No existing account, proceed with sign-up
                        createNewUser(email, password, name);
                    }
                })
                .addOnFailureListener(e -> {
                    // Handle potential errors while querying Firestore
                    Toast.makeText(getApplicationContext(), "Error checking account: " + e.getMessage(), Toast.LENGTH_LONG).show();
                });
    }

    private void createNewUser(String email, String password, String name) {
        String id = UUID.randomUUID().toString();

        Map<String, Object> doc = new HashMap<>();
        doc.put("email", email);
        doc.put("password", password);
        doc.put("name", name);

        db.collection("Users").document(id).set(doc)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Successfully signed up the user, redirect to main activity
                        Toast.makeText(getApplicationContext(), "Sign-up Success", Toast.LENGTH_LONG).show();

                        SharedPreferences sharedPreferences = getSharedPreferences("userPref", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("isLoggedIn", true);
                        editor.putString("name", name);
                        editor.putString("email", email);
                        editor.apply();

                        Intent intent = new Intent(LoginSignupActivity.this, HomePageActivity.class);
                        startActivity(intent);
                        finish(); // Optionally finish this activity so the user can't go back
                    }
                })
                .addOnFailureListener(e -> {
                    // Handle failure to add user
                    Toast.makeText(getApplicationContext(), "Sign-up Failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
                });
    }

}
