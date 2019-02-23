package ru.sberbank.lesson12.task.alarmclock.domain.interactor.usecase;

import ru.sberbank.lesson12.task.alarmclock.domain.interactor.Interactor;
import ru.sberbank.lesson12.task.alarmclock.domain.model.AlarmClockItem;
import ru.sberbank.lesson12.task.alarmclock.domain.repository.AlarmClockRepository;

public class DeleteAlarmClockInteractor implements Interactor {
    private AlarmClockRepository repository;
    private AlarmClockItem item;

    public DeleteAlarmClockInteractor(AlarmClockRepository repository, AlarmClockItem item) {
        this.repository = repository;
        this.item = item;
    }

    @Override
    public void execute() {
        repository.delete(item);
    }
}
