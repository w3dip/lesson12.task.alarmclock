package ru.sberbank.lesson12.task.alarmclock.domain.interactor.usecase;

import ru.sberbank.lesson12.task.alarmclock.domain.interactor.Callback;
import ru.sberbank.lesson12.task.alarmclock.domain.interactor.Interactor;
import ru.sberbank.lesson12.task.alarmclock.domain.model.AlarmClockItem;
import ru.sberbank.lesson12.task.alarmclock.domain.repository.AlarmClockRepository;

import static ru.sberbank.lesson12.task.alarmclock.domain.util.AlarmClockSheduler.shedule;

public class SheduleAlarmClockInteractor implements Interactor {
    //public static final String NOTIFICATION_WORK_TAG = "notificationWork";

    private AlarmClockRepository repository;
    private AlarmClockItem item;
    //private Callback<Long> callback;

    public SheduleAlarmClockInteractor(AlarmClockRepository repository, AlarmClockItem item) {
        this.repository = repository;
        this.item = item;
        //this.callback = callback;
    }

    @Override
    public void execute() {
        shedule(item);
        repository.create(item);
        //callback.handle(repository.create(item));
    }
}
