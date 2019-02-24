package ru.sberbank.lesson12.task.alarmclock.domain.util;

import com.google.common.collect.Lists;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import ru.sberbank.lesson12.task.alarmclock.domain.model.AlarmClockItem;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.MINUTES;
import static ru.sberbank.lesson12.task.alarmclock.domain.interactor.usecase.CreateAlarmClockInteractor.NOTIFICATION_WORK_TAG;

public class AlarmClockSheduler {
    private static DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm");

    public static void shedule(AlarmClockItem item) {
        LocalTime targetTime = formatter.parseDateTime(item.getTime()).toLocalTime();
        DateTime now = DateTime.now();
        DateTime targetDate = new DateTime(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), targetTime.getHourOfDay(), targetTime.getMinuteOfHour());
        if (!targetDate.isAfter(now)) {
            targetDate = targetDate.plusDays(1);
        }
        item.setDate(targetDate.toDate());

        List<OneTimeWorkRequest> workRequests = Lists.newArrayList();
        workRequests.add(new OneTimeWorkRequest.Builder(AlarmClockWorker.class)
                .setInitialDelay(calculateDelay(now, targetDate), MILLISECONDS)
                .build());

        /*for (int i = 0; i < 5; i++) {
            workRequests.add(new OneTimeWorkRequest.Builder(AlarmClockWorker.class)
                    .setInitialDelay(1, MINUTES)
                    .build());
        }*/
        WorkManager.getInstance().enqueueUniqueWork(NOTIFICATION_WORK_TAG, ExistingWorkPolicy.REPLACE, workRequests);
        //WorkManager.getInstance().enqueue(workRequests);
    }

    private static long calculateDelay(DateTime now, DateTime targetDate) {
        return targetDate.getMillis() - now.getMillis();
    }
}
