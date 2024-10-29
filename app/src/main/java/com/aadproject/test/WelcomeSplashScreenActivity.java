package com.aadproject.test;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Calendar;

@SuppressLint("CustomSplashScreen")
public class WelcomeSplashScreenActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_splashscreen);

        sharedPreferences = getSharedPreferences("userPref", MODE_PRIVATE);
        SharedPreferences.Editor ed = sharedPreferences.edit();
        ed.putBoolean("areRemindersSet",false);
        ed.apply();

        // Create notification channel
        NotificationHelper.createNotificationChannel(this);

        // Check if reminders are already set (this can be a flag in SharedPreferences)
        boolean areRemindersSet = sharedPreferences.getBoolean("areRemindersSet", false);
        Log.d("notifs", String.valueOf(areRemindersSet));
        if (!areRemindersSet) {
            Log.d("new notifs", "entered if");

            setWaterReminder();
            setExerciseReminder();
            setHealthTipReminder();

            // Update SharedPreferences to indicate reminders are set
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("areRemindersSet", true);
            editor.apply();
        }

        checkLoginStatus();
    }

    private void setWaterReminder() {
        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.HOUR_OF_DAY, 0); // Set time for water reminder
//        calendar.set(Calendar.MINUTE, 12);
//        calendar.add(Calendar.SECOND, 10);

        Intent intent = new Intent(this, NotificationReceiver.class);
        intent.putExtra("title", "Hydration Reminder");
        intent.putExtra("message", "Time to drink water!");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Log.d("setWaterReminder", "Setting water reminder alarm");
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }

    private void setExerciseReminder() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0); // Set time for exercise reminder
        calendar.set(Calendar.MINUTE, 57);
//        calendar.add(Calendar.SECOND, 15);

        if (calendar.getTimeInMillis() < System.currentTimeMillis()) {
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }

        Intent intent = new Intent(this, NotificationReceiver.class);
        intent.putExtra("title", "Exercise Reminder");
        intent.putExtra("message", "Time to get moving!");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }

    private void setHealthTipReminder() {
        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.HOUR_OF_DAY, 18); // Set time for health tips reminder
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.add(Calendar.SECOND, 5);

        Intent intent = new Intent(this, NotificationReceiver.class);
        intent.putExtra("title", "Health Tip");
        intent.putExtra("message", "Stay healthy with balanced nutrition!");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 2, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }


    private void checkLoginStatus() {
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

        if (isLoggedIn) {
            Toast.makeText(WelcomeSplashScreenActivity.this, "Welcome back! Going to Main Activity.", Toast.LENGTH_SHORT).show();
            goToMainActivity();
        } else {
            Toast.makeText(WelcomeSplashScreenActivity.this, "New User! Going to Sign Up Activity.", Toast.LENGTH_SHORT).show();
            goToSignUpActivity();
        }
    }

    private void goToMainActivity() {
        Intent intent = new Intent(WelcomeSplashScreenActivity.this, HomePageActivity.class);
        startActivity(intent);
        finish();
    }

    private void goToSignUpActivity() {
        Intent intent = new Intent(WelcomeSplashScreenActivity.this, LoginSignupActivity.class);
        startActivity(intent);
        finish();
    }
}
