package xyz.fpointzero.android.utils.activity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;

import xyz.fpointzero.android.R;
import xyz.fpointzero.android.activities.ChatActivity;

public class NoticeUtil {
    public static void newMessageNotice(Context context, String title, String text, String userID) {
        NotificationManager notificationManager;
        NotificationChannel channel = null;
        
        Intent intent = new Intent(context, ChatActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("userID", userID);
        intent.putExtras(bundle);
        
        PendingIntent pi = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel = new NotificationChannel("MyChannel", "MyChannel", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder = new NotificationCompat.Builder(context, "MyChannel");
        } else {
            builder = new NotificationCompat.Builder(context, "default");
        }
        builder.setDefaults(NotificationCompat.DEFAULT_ALL)
                .setSmallIcon(R.drawable.ic_user_24)
                .setWhen(System.currentTimeMillis())
                .setContentTitle(title)
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pi)
                .setAutoCancel(true);
        notificationManager.notify(1, builder.build());
    }
}
