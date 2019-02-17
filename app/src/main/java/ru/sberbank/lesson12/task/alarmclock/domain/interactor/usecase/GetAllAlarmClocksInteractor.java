package ru.sberbank.lesson12.task.alarmclock.domain.interactor.usecase;

import java.util.List;

import ru.sberbank.lesson12.task.alarmclock.domain.interactor.Callback;
import ru.sberbank.lesson12.task.alarmclock.domain.interactor.Interactor;
import ru.sberbank.lesson12.task.alarmclock.domain.model.AlarmClockItem;
import ru.sberbank.lesson12.task.alarmclock.domain.repository.AlarmClockRepository;

public class GetAllAlarmClocksInteractor implements Interactor {
    private AlarmClockRepository repository;
    private Callback<List<AlarmClockItem>> callback;

    public GetAllAlarmClocksInteractor(AlarmClockRepository repository, Callback<List<AlarmClockItem>> callback) {
        this.repository = repository;
        this.callback = callback;
    }

    @Override
    public void execute() {
        callback.handle(repository.getAll());
    }
}
