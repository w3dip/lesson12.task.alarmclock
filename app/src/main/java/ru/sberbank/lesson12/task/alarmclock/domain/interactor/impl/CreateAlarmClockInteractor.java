package ru.sberbank.lesson12.task.alarmclock.domain.interactor.impl;

import javax.inject.Inject;

import io.reactivex.schedulers.Schedulers;
import ru.sberbank.lesson12.task.alarmclock.domain.interactor.Interactor;
import ru.sberbank.lesson12.task.alarmclock.domain.model.AlarmClockItem;
import ru.sberbank.lesson12.task.alarmclock.domain.repository.AlarmClockRepository;

import static ru.sberbank.lesson12.task.alarmclock.domain.util.AlarmClockSheduler.shedule;

public class CreateAlarmClockInteractor implements Interactor<Void> {
    public static final String NOTIFICATION_WORK_TAG = "notificationWork";

    private AlarmClockRepository repository;
    private AlarmClockItem item;

    @Inject
    CreateAlarmClockInteractor(AlarmClockRepository repository) {
        this.repository = repository;
    }

    public void setItem(AlarmClockItem item) {
        this.item = item;
    }

    @Override
    public Void execute() {
        shedule(item);
        repository.create(item).subscribeOn(Schedulers.io()).subscribe();
        return null;
    }
}
