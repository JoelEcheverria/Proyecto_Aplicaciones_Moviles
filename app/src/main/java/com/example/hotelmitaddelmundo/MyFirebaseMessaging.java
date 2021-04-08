package com.example.hotelmitaddelmundo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


public class MyFirebaseMessaging extends FirebaseMessagingService {
    private static final String TAG="MyFirebaseMessagin";
    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.d(TAG,"onNewToken"+token);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d(TAG,"Mensaje recibido");
        RemoteMessage.Notification notification = remoteMessage.getNotification();
        String title = notification.getTitle();
        String msg= notification.getBody();
        sendNotification(title,msg);
    }

    public void sendNotification(String title, String msg){
        Intent intent = new Intent(this,login.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,MyNotification.NOTIFICATION_ID, intent, PendingIntent.FLAG_ONE_SHOT);
        MyNotification notification = new MyNotification(this,MyNotification.CHANNEL_ID_NOTIFICATIONS);
        notification.build(R.drawable.ic_launcher_foreground, title, msg, pendingIntent);
        notification.addChannel("Notificacioens", NotificationManager.IMPORTANCE_DEFAULT);
        notification.createChannelGroup(MyNotification.CHANNEL_GROUP_GENERAL, R.string.notification_channel_group_general);

       notification.show(MyNotification.NOTIFICATION_ID);

    }
}
