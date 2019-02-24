package ru.sberbank.lesson12.task.alarmclock.domain.util;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.concurrent.TimeUnit;

import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import ru.sberbank.lesson12.task.alarmclock.domain.model.AlarmClockItem;

import static ru.sberbank.lesson12.task.alarmclock.domain.interactor.usecase.CreateAlarmClockInteractor.NOTIFICATION_WORK_TAG;
import static ru.sberbank.lesson12.task.alarmclock.domain.model.AlarmClockItem.ALARM_CLOCK_ITEM_ID;

public class AlarmClockSheduler {
    public static void shedule(AlarmClockItem item) {
        OneTimeWorkRequest notificationWork = new OneTimeWorkRequest.Builder(AlarmClockWorker.class)
                .setInitialDelay(calculateDelay(item.getTime()), TimeUnit.MILLISECONDS)
                .addTag(NOTIFICATION_WORK_TAG)
                .setInputData(new Data.Builder()
                        .putLong(ALARM_CLOCK_ITEM_ID, item.getId())
                        .build())
                .build();
        //WorkManager.getInstance().beginUniqueWork(NOTIFICATION_WORK_TAG, ExistingWorkPolicy.REPLACE, notificationWork).enqueue();
        item.setWorkId(notificationWork.getId().toString());
        WorkManager.getInstance().enqueue(notificationWork);
    }

    private static long calculateDelay(String time) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm");
        LocalTime targetTime = formatter.parseDateTime(time).toLocalTime();
        DateTime now = DateTime.now();
        DateTime targetDate = new DateTime(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), targetTime.getHourOfDay(), targetTime.getMinuteOfHour());
        if (targetDate.isAfter(now)) {
            return targetDate.getMillis() - now.getMillis();
        }
        return targetDate.plusDays(1).getMillis() - now.getMillis();
    }
}
