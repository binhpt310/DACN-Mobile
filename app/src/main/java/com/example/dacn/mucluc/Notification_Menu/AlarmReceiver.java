package com.example.dacn.mucluc.Notification_Menu;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.SystemClock;

import com.example.dacn.R;
import com.example.dacn.mucluc.Notification_Menu.Reminder.Reminder;
import com.example.dacn.mucluc.Notification_Menu.Reminder.ReminderDatabase;
import com.example.dacn.mucluc.Notification_Menu.Reminder.ReminderEditActivity;

import java.util.Calendar;

public class AlarmReceiver extends BroadcastReceiver {
    AlarmManager mAlarmManager;
    PendingIntent mPendingIntent;

    @Override
    public void onReceive(Context context, Intent intent) {
        int mReceivedID = Integer.parseInt(intent.getStringExtra(ReminderEditActivity.EXTRA_REMINDER_ID));

        // Get notification title from Reminder Database
        ReminderDatabase rb = new ReminderDatabase(context);
        Reminder reminder = rb.getReminder(mReceivedID);
        String mTitle = reminder.getTitle();

        // Create intent to open ReminderEditActivity on notification click
        Intent editIntent = new Intent(context, ReminderEditActivity.class);
        editIntent.putExtra(ReminderEditActivity.EXTRA_REMINDER_ID, Integer.toString(mReceivedID));
        PendingIntent mClick = PendingIntent.getActivity(context, mReceivedID, editIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Create Notification
        Notification.Builder mBuilder = new Notification.Builder(context.getApplicationContext(), "notify")
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                .setSmallIcon(R.drawable.ic_alarm_on_white_24dp)
                .setContentTitle(context.getResources().getString(R.string.app_name))
                .setTicker(mTitle)
                .setContentText(mTitle)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentIntent(mClick)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true);

        NotificationManager nManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            String channelId = "Your_channel_id";
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_HIGH);
            nManager.createNotificationChannel(channel);
            mBuilder.setChannelId(channelId);
        }
        nManager.notify(mReceivedID, mBuilder.build());
    }

    public void setAlarm(Context context, Calendar calendar, int ID) {
        mAlarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        // Put Reminder ID in Intent Extra
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra(ReminderEditActivity.EXTRA_REMINDER_ID, Integer.toString(ID));
        mPendingIntent = PendingIntent.getBroadcast(context, ID, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        // Calculate notification time
        Calendar c = Calendar.getInstance();
        long currentTime = c.getTimeInMillis();
        long diffTime = calendar.getTimeInMillis() - currentTime;

        // Start alarm using notification time
        mAlarmManager.set(AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime() + diffTime,
                mPendingIntent);

        // Restart alarm if device is rebooted
        ComponentName receiver = new ComponentName(context, BootReceiver.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }

    public void setRepeatAlarm(Context context, Calendar calendar, int ID, long RepeatTime) {
        mAlarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        // Put Reminder ID in Intent Extra
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra(ReminderEditActivity.EXTRA_REMINDER_ID, Integer.toString(ID));
        mPendingIntent = PendingIntent.getBroadcast(context, ID, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        // Calculate notification timein
        Calendar c = Calendar.getInstance();
        long currentTime = c.getTimeInMillis();
        long diffTime = calendar.getTimeInMillis() - currentTime;

        // Start alarm using initial notification time and repeat interval time
        mAlarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime() + diffTime,
                RepeatTime , mPendingIntent);

        // Restart alarm if device is rebooted
        ComponentName receiver = new ComponentName(context, BootReceiver.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }

    public void cancelAlarm(Context context, int ID) {
        mAlarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        // Cancel Alarm using Reminder ID
        mPendingIntent = PendingIntent.getBroadcast(context, ID, new Intent(context, AlarmReceiver.class), 0);
        mAlarmManager.cancel(mPendingIntent);

        // Disable alarm
        ComponentName receiver = new ComponentName(context, BootReceiver.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }
}

