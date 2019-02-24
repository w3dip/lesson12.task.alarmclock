package ru.sberbank.lesson12.task.alarmclock.domain.interactor.usecase;

import androidx.lifecycle.LiveData;
import ru.sberbank.lesson12.task.alarmclock.domain.interactor.Callback;
import ru.sberbank.lesson12.task.alarmclock.domain.interactor.Interactor;
import ru.sberbank.lesson12.task.alarmclock.domain.model.AlarmClockItem;
import ru.sberbank.lesson12.task.alarmclock.domain.repository.AlarmClockRepository;

import static ru.sberbank.lesson12.task.alarmclock.domain.util.AlarmClockSheduler.shedule;

public class CreateAlarmClockInteractor implements Interactor {
    public static final String NOTIFICATION_WORK_TAG = "notificationWork";

    private AlarmClockRepository repository;
    private AlarmClockItem item;
    private Callback<LiveData<Long>> callback;

    public CreateAlarmClockInteractor(AlarmClockRepository repository, AlarmClockItem item, Callback<LiveData<Long>> callback) {
        this.repository = repository;
        this.item = item;
        this.callback = callback;
    }

    @Override
    public void execute() {
        //shedule(item);
        callback.handle(repository.create(item));
    }
}
