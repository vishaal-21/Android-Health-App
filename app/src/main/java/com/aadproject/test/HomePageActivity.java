//package com.aadproject.test;
//
//import android.app.AlarmManager;
//import android.app.PendingIntent;
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.os.Build;
//import android.os.Bundle;
//
//import androidx.activity.EdgeToEdge;
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//import android.os.Bundle;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.drawerlayout.widget.DrawerLayout;
//import androidx.cardview.widget.CardView;
//import androidx.core.view.GravityCompat;
//import androidx.appcompat.app.ActionBarDrawerToggle;
////import androidx.navigation.ui.AppBarConfiguration;
//import com.google.android.material.navigation.NavigationView;
//
//import android.os.PowerManager;
//import android.provider.Settings;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.content.Intent;
//import android.net.Uri;
//import android.widget.TextView;
//
//import java.util.Calendar;
//
//public class HomePageActivity extends AppCompatActivity implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener {
//    DrawerLayout drawerLayout;
//    ActionBarDrawerToggle toogle;
//
//    private CardView healthTips, exerciseTips, foodTips, medicineKit;
//    private CardView bmiCalculator, nearbyHospitals, routine, aboutUs;
//    private CardView calorieCalculator, settings;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.homepage_activity);
//        drawerLayout = findViewById(R.id.drawerId);
//
//        toogle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
//        drawerLayout.addDrawerListener(toogle);
//        toogle.syncState();
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        SharedPreferences sharedPreferences = getSharedPreferences("userPref", MODE_PRIVATE);
//        String name = sharedPreferences.getString("name","No name");
//        String email = sharedPreferences.getString("email","No email");
//
////        ---------------------------------------------
//        // Get the NavigationView
//        NavigationView navigationView = findViewById(R.id.navigationId);
//
//        // Access the header layout of the NavigationView
//        View headerView = navigationView.getHeaderView(0);
//
//        // Get references to the TextView elements in the header layout
//        TextView nameTextView = headerView.findViewById(R.id.changeName);
//        TextView emailTextView = headerView.findViewById(R.id.changeEmail);
//
//        nameTextView.setText(name);
//        emailTextView.setText(email);
//
////                --------------------------------------
//
//        healthTips = findViewById(R.id.HealthTips);
//        healthTips.setOnClickListener(this);
//
//        exerciseTips = findViewById(R.id.ExerciseTips);
//        exerciseTips.setOnClickListener(this);
//
//        foodTips = findViewById(R.id.FoodTips);
//        foodTips.setOnClickListener(this);
//
//
//        medicineKit = findViewById(R.id.MedKit);
//        medicineKit.setOnClickListener(this);
//
//        calorieCalculator = findViewById(R.id.calorieCalc);
//        calorieCalculator.setOnClickListener(this);
//
//
//        bmiCalculator = findViewById(R.id.BMI);
//        bmiCalculator.setOnClickListener(this);
//
////        nearbyHospitals=findViewById(R.id.Hospital);
////        nearbyHospitals.setOnClickListener(this);
////
////        routine=findViewById(R.id.Routine);
////        routine.setOnClickListener(this);
////
////        settings=findViewById(R.id.Settings);
////        settings.setOnClickListener(this);
////
////        aboutUs=findViewById(R.id.About);
////        aboutUs.setOnClickListener(this);
//    }
//
//    public void onClick(View v) {
//
//        if (v.getId() == R.id.HealthTips) {
//            Intent intent = new Intent(HomePageActivity.this, HealthTipsActivity.class);
//            startActivity(intent);
//        }
//
//        if (v.getId() == R.id.ExerciseTips) {
//            Intent intent = new Intent(HomePageActivity.this, ExerciseTipsActivity.class);
//            startActivity(intent);
//        }
//
//        if (v.getId() == R.id.FoodTips) {
//            Intent intent = new Intent(HomePageActivity.this, FoodTipsActivity.class);
//            startActivity(intent);
//        }
//
//        if (v.getId() == R.id.MedKit) {
//            Intent intent = new Intent(HomePageActivity.this, MediKitActivity.class);
//            startActivity(intent);
//        }
//
//        if (v.getId() == R.id.calorieCalc) {
//            Intent intent = new Intent(HomePageActivity.this, CalorieCalculatorActivity.class);
//            startActivity(intent);
//        }
//
//
//        if (v.getId() == R.id.BMI) {
//            Intent intent = new Intent(HomePageActivity.this, BmiCalculatorActivity.class);
//            startActivity(intent);
//        }
//
////        if(v.getId()==R.id.Hospital)
////        {
////            // Intent intent=new Intent(HomePageActivity.this,NearByHospitalsActivity.class);
////            // startActivity(intent);
////            //Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("geo:24.896837,91.902739"));
////            Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.google.com/maps/search/hospitals/@23.8213522,90.3747209,14z"));
////            startActivity(intent);
////        }
////
////        if(v.getId()==R.id.Routine)
////        {
////            Intent intent=new Intent(HomePageActivity.this,LoginSignupActivity.class);
////            startActivity(intent);
////        }
////
////        if(v.getId()==R.id.Settings)
////        {
////            Intent intent=new Intent(HomePageActivity.this,LoginSignUpActivityForDietPlan.class);
////            startActivity(intent);
////        }
////
////        if(v.getId()==R.id.About)
////        {
////            //Intent intent=new Intent(HomePageActivity.this,AboutUsActivity.class);
////            //startActivity(intent);
////            Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.google.com/maps/search/gym/@23.8213522,90.3747209,14z"));
////            startActivity(intent);
////        }
//    }
//
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.mnuitemon_actionbar, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (toogle.onOptionsItemSelected(item)) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//        if(menuItem.getItemId() == R.id.logOutId) {
//            SharedPreferences sharedPreferences = getSharedPreferences("userPref", MODE_PRIVATE);
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putBoolean("isLoggedIn", false);
//            editor.apply();
//
//            startActivity(new Intent(HomePageActivity.this, LoginSignupActivity.class));
//            finish();
//            return true;
//        }
//        return false;
//    }
//}

package com.aadproject.test;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

public class HomePageActivity extends AppCompatActivity implements View.OnClickListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private CardView healthTips, exerciseTips, foodTips, medicineKit, bmiCalculator, calorieCalculator;
    private NavigationView navigationView;

    // Shared Preferences key constants
    private static final String PREFS_NAME = "userPref";
    private static final String PREF_NAME_KEY = "name";
    private static final String PREF_EMAIL_KEY = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage_activity);

        // Initialize UI elements
        setupDrawerAndNavigationView();
        setupCardViews();
        updateNavigationHeader();
    }

    // Modularized method to setup Drawer and NavigationView
    private void setupDrawerAndNavigationView() {
        drawerLayout = findViewById(R.id.drawerId);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Set up the navigation view listener
        navigationView = findViewById(R.id.navigationId);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                if (id == R.id.homeMenuId) {
                    // Navigate to Home Activity
                    Intent homeIntent = new Intent(getApplicationContext(), HomePageActivity.class);
                    startActivity(homeIntent);
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                } else if (id == R.id.settingsMenuId) {
                    // Navigate to Settings Activity
                    Intent settingsIntent = new Intent(getApplicationContext(), SettingsActivity.class);
                    startActivity(settingsIntent);
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                } else if (id == R.id.logOutId) {
                    // Handle Logout
                    logoutUser();
                    drawerLayout.closeDrawer(GravityCompat.START); // Close the drawer after logout
                    return true;
                }

                // Close the drawer after selection
                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }

        });
    }

    // Method to setup the CardViews
    private void setupCardViews() {
        healthTips = findViewById(R.id.HealthTips);
        exerciseTips = findViewById(R.id.ExerciseTips);
        foodTips = findViewById(R.id.FoodTips);
        medicineKit = findViewById(R.id.MedKit);
        calorieCalculator = findViewById(R.id.calorieCalc);
        bmiCalculator = findViewById(R.id.BMI);

        healthTips.setOnClickListener(this);
        exerciseTips.setOnClickListener(this);
        foodTips.setOnClickListener(this);
        medicineKit.setOnClickListener(this);
        calorieCalculator.setOnClickListener(this);
        bmiCalculator.setOnClickListener(this);
    }

    // Update Navigation Header TextViews with SharedPreferences values
    private void updateNavigationHeader() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String name = sharedPreferences.getString(PREF_NAME_KEY, "No name");
        String email = sharedPreferences.getString(PREF_EMAIL_KEY, "No email");

        // Get the NavigationView
        NavigationView navigationView = findViewById(R.id.navigationId);

        // Access the header layout of the NavigationView
        View headerView = navigationView.getHeaderView(0);

        // Get references to the TextView elements in the header layout
        TextView nameTextView = headerView.findViewById(R.id.changeName);
        TextView emailTextView = headerView.findViewById(R.id.changeEmail);

        // Check for null before setting values
        if (nameTextView != null && emailTextView != null) {
            nameTextView.setText(name);
            emailTextView.setText(email);
        }
    }

    // CardView onClick listener
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.HealthTips) {
            Intent intent = new Intent(this, HealthTipsActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.ExerciseTips) {
            Intent intent = new Intent(this, ExerciseTipsActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.FoodTips) {
            Intent intent = new Intent(this, FoodTipsActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.MedKit) {
            Intent intent = new Intent(this, MediKitActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.calorieCalc) {
            Intent intent = new Intent(this, CalorieCalculatorActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.BMI) {
            Intent intent = new Intent(this, BmiCalculatorActivity.class);
            startActivity(intent);
        }
    }

    // Logout logic
    private void logoutUser() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLoggedIn", false);
        editor.apply();

        Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show();

        // Redirect to Login/Signup Activity
        Intent intent = new Intent(getApplicationContext(), LoginSignupActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.mnuitemon_actionbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
