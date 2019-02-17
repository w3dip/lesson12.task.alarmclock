package ru.sberbank.lesson12.task.alarmclock.domain.interactor.usecase;

import java.util.List;

import androidx.lifecycle.LiveData;
import ru.sberbank.lesson12.task.alarmclock.domain.interactor.Callback;
import ru.sberbank.lesson12.task.alarmclock.domain.interactor.Interactor;
import ru.sberbank.lesson12.task.alarmclock.domain.model.AlarmClockItem;
import ru.sberbank.lesson12.task.alarmclock.domain.repository.AlarmClockRepository;

public class GetAllAlarmClocksInteractor implements Interactor {
    private AlarmClockRepository repository;
    private Callback<LiveData<List<AlarmClockItem>>> callback;

    public GetAllAlarmClocksInteractor(AlarmClockRepository repository, Callback<LiveData<List<AlarmClockItem>>> callback) {
        this.repository = repository;
        this.callback = callback;
    }

    @Override
    public void execute() {
        callback.handle(repository.getAll());
    }
}
