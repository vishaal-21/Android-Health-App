package com.aadproject.test;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class Login extends AppCompatActivity implements View.OnClickListener {

    EditText email, password;
    Button login;
    FirebaseFirestore db; // Declare Firestore instance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email_field);
        password = findViewById(R.id.password_field);
        login = findViewById(R.id.login_button);
        login.setOnClickListener(this);

        db = FirebaseFirestore.getInstance(); // Initialize Firestore instance
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login_button) {
            String u = email.getText().toString().trim();
            String p = password.getText().toString().trim();

            if (u.isEmpty() || p.isEmpty()) {
                Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_LONG).show();
                return;
            }

            checkUserCredentials(u, p); // Check the credentials against Firestore
        }
    }

    private void checkUserCredentials(String email, String password) {
        // Query Firestore for the user with the entered email
        db.collection("Users")
                .whereEqualTo("email", email)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (!task.getResult().isEmpty()) {
                                // User found
                                for (DocumentSnapshot document : task.getResult()) {
                                    // Retrieve the stored password for the user
                                    String storedPassword = document.getString("password");
                                    String name = document.getString("name");
                                    if (storedPassword != null && storedPassword.equals(password)) {
                                        // Password matches, login successful

                                        SharedPreferences sharedPreferences = getSharedPreferences("userPref", MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putBoolean("isLoggedIn", true);
                                        editor.putString("name", name);
                                        editor.putString("email", email);
                                        editor.apply();

                                        Intent intent = new Intent(Login.this, HomePageActivity.class);
                                        startActivity(intent);
                                        finish(); // Optional: finish this activity
                                    } else {
                                        // Password does not match
                                        Toast.makeText(Login.this, "Incorrect password", Toast.LENGTH_LONG).show();
                                    }
                                }
                            } else {
                                // No user found with that email
                                Toast.makeText(Login.this, "No account found with that email", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            // Handle errors while querying Firestore
                            Toast.makeText(Login.this, "Error checking credentials: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
