package com.example.waterooo;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class ReminderReceiver extends BroadcastReceiver {

    private static final String CHANNEL_ID = "water_reminders";

    @Override
    public void onReceive(Context context, Intent intent) {

        createNotificationChannel(context);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context, CHANNEL_ID)
                        .setSmallIcon(R.drawable.waterooo_logo)
                        .setContentTitle("Time to drink water 💧")
                        .setContentText(
                                "Stay hydrated! Take a few sips of water."
                        )
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setAutoCancel(true);

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(
                        Context.NOTIFICATION_SERVICE
                );

        notificationManager.notify(
                1001,
                builder.build()
        );
    }

    private void createNotificationChannel(Context context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel =
                    new NotificationChannel(
                            CHANNEL_ID,
                            "Water Reminders",
                            NotificationManager.IMPORTANCE_HIGH
                    );

            channel.setDescription(
                    "Notifications reminding you to drink water"
            );

            NotificationManager manager =
                    context.getSystemService(
                            NotificationManager.class
                    );

            manager.createNotificationChannel(channel);
        }
    }
}