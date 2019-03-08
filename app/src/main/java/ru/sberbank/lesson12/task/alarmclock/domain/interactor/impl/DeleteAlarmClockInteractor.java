package ru.sberbank.lesson12.task.alarmclock.domain.interactor.impl;

import javax.inject.Inject;

import io.reactivex.schedulers.Schedulers;
import ru.sberbank.lesson12.task.alarmclock.domain.interactor.Interactor;
import ru.sberbank.lesson12.task.alarmclock.domain.model.AlarmClockItem;
import ru.sberbank.lesson12.task.alarmclock.domain.repository.AlarmClockRepository;

public class DeleteAlarmClockInteractor implements Interactor<Void> {
    private AlarmClockRepository repository;
    private AlarmClockItem item;

    @Inject
    public DeleteAlarmClockInteractor(AlarmClockRepository repository) {
        this.repository = repository;
    }

    public void setItem(AlarmClockItem item) {
        this.item = item;
    }

    @Override
    public Void execute() {
        repository.delete(item).subscribeOn(Schedulers.io()).subscribe();
        return null;
    }
}
