package ru.sberbank.lesson12.task.alarmclock.domain.interactor.usecase;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.joda.time.Period;
import org.joda.time.Seconds;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import ru.sberbank.lesson12.task.alarmclock.domain.interactor.Callback;
import ru.sberbank.lesson12.task.alarmclock.domain.interactor.Interactor;
import ru.sberbank.lesson12.task.alarmclock.domain.model.AlarmClockItem;
import ru.sberbank.lesson12.task.alarmclock.domain.repository.AlarmClockRepository;
import ru.sberbank.lesson12.task.alarmclock.domain.util.AlarmClockWorker;

public class CreateAlarmClockInteractor implements Interactor {
    public static final String NOTIFICATION_WORK_TAG = "notificationWork";

    private AlarmClockRepository repository;
    private AlarmClockItem item;
    //private Callback<List<String>> callback;

    public CreateAlarmClockInteractor(AlarmClockRepository repository, AlarmClockItem item/*, Callback<List<String>> callback*/) {
        this.repository = repository;
        this.item = item;
        //this.callback = callback;
    }

    @Override
    public void execute() {
        /*callback.handle(repository.getAll());*/
        shedule(item);
        repository.create(item);
    }

    private void shedule(AlarmClockItem item) {
        //Data inputData = new Data.Builder().putInt(DBEventIDTag, DBEventID).build();
        OneTimeWorkRequest notificationWork = new OneTimeWorkRequest.Builder(AlarmClockWorker.class)
                .setInitialDelay(calculateDelay(item.getTime()), TimeUnit.MILLISECONDS)
                //.setInputData(inputData)
                .addTag(NOTIFICATION_WORK_TAG)
                .build();
        //WorkManager.getInstance().beginUniqueWork(NOTIFICATION_WORK_TAG, ExistingWorkPolicy.REPLACE, notificationWork).enqueue();
        WorkManager.getInstance().enqueue(notificationWork);
    }

    private long calculateDelay(String time) {
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
