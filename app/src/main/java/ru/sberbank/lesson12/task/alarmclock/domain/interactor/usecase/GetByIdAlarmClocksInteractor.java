package ru.sberbank.lesson12.task.alarmclock.domain.interactor.usecase;

import java.util.List;

import androidx.lifecycle.LiveData;
import ru.sberbank.lesson12.task.alarmclock.domain.interactor.Callback;
import ru.sberbank.lesson12.task.alarmclock.domain.interactor.Interactor;
import ru.sberbank.lesson12.task.alarmclock.domain.model.AlarmClockItem;
import ru.sberbank.lesson12.task.alarmclock.domain.repository.AlarmClockRepository;

public class GetByIdAlarmClocksInteractor implements Interactor {
    private AlarmClockRepository repository;
    private Callback<LiveData<AlarmClockItem>> callback;
    private long id;

    public GetByIdAlarmClocksInteractor(AlarmClockRepository repository, Callback<LiveData<AlarmClockItem>> callback) {
        this.repository = repository;
        this.callback = callback;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public void execute() {
        callback.handle(repository.getById(id));
    }
}
