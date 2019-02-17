package ru.sberbank.lesson12.task.alarmclock.domain.interactor.usecase;

import java.util.List;

import ru.sberbank.lesson12.task.alarmclock.domain.interactor.Callback;
import ru.sberbank.lesson12.task.alarmclock.domain.interactor.Interactor;
import ru.sberbank.lesson12.task.alarmclock.domain.repository.AlarmClockRepository;

public class GelAllImagesInteractor implements Interactor {
    private AlarmClockRepository repository;
    private Callback<List<String>> callback;

    public GelAllImagesInteractor(AlarmClockRepository repository, Callback<List<String>> callback) {
        this.repository = repository;
        this.callback = callback;
    }

    @Override
    public void execute() {
        callback.handle(repository.getAll());
    }
}
