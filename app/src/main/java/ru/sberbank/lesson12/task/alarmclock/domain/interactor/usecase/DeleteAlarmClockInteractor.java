package ru.sberbank.lesson12.task.alarmclock.domain.interactor.usecase;

import javax.inject.Inject;

import ru.sberbank.lesson12.task.alarmclock.domain.interactor.Interactor;
import ru.sberbank.lesson12.task.alarmclock.domain.model.AlarmClockItem;
import ru.sberbank.lesson12.task.alarmclock.domain.repository.AlarmClockRepository;

public class DeleteAlarmClockInteractor implements Interactor {
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
    public void execute() {
        repository.delete(item);
    }
}
