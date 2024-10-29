package com.aadproject.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("NotificationReceiver", "Alarm triggered!");
        String title = intent.getStringExtra("title");
        String message = intent.getStringExtra("message");
        NotificationHelper.showNotification(context, title, message);
    }
}
