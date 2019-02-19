package ru.sberbank.lesson12.task.alarmclock.domain.util;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import ru.sberbank.lesson12.task.alarmclock.R;
import ru.sberbank.lesson12.task.alarmclock.presentation.view.activity.PlayAlarmClockActivity;

import static android.content.Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static androidx.work.ListenableWorker.Result.success;

public class AlarmClockWorker extends Worker {
    private static final String CHANNEL_ID = "ALARM_CLOCK_CHANNEL";
    private Context context;
    private Intent intent;

    public AlarmClockWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context = context;
    }

    @NonNull
    @Override
    public Result doWork() {
        intent = getAlarmIntent();
        triggerAlarm();
        showNotification();
        return success();
    }

    private void showNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_notification)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_notification))
                .setContentTitle(context.getResources().getString(R.string.notification_title))
                .setContentText(context.getResources().getString(R.string.notification_message))
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setContentIntent(PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT))
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(1, builder.build());
    }

    private void triggerAlarm() {
        context.startActivity(intent);
    }

    private Intent getAlarmIntent() {
        Intent intent = new Intent(context, PlayAlarmClockActivity.class);
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY);
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        return intent;
    }
}