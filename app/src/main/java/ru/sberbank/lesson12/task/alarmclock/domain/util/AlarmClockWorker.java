package ru.sberbank.lesson12.task.alarmclock.domain.util;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import ru.sberbank.lesson12.task.alarmclock.R;
import ru.sberbank.lesson12.task.alarmclock.presentation.view.activity.PlayAlarmClockActivity;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static androidx.work.ListenableWorker.Result.success;

public class AlarmClockWorker extends Worker {

    public AlarmClockWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        //triggerAlarmAndNotification();
        //Toast.makeText(getApplicationContext(), R.string.alarm_clock_triggered, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), PlayAlarmClockActivity.class);
        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(intent);
        return success();
    }
}