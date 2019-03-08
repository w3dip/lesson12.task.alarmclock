package ru.sberbank.lesson12.task.alarmclock.domain.interactor.impl;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.sberbank.lesson12.task.alarmclock.domain.interactor.Interactor;
import ru.sberbank.lesson12.task.alarmclock.domain.model.AlarmClockItem;
import ru.sberbank.lesson12.task.alarmclock.domain.repository.AlarmClockRepository;

public class GetAllAlarmClocksInteractor implements Interactor<Flowable<List<AlarmClockItem>>> {
    private AlarmClockRepository repository;

    @Inject
    GetAllAlarmClocksInteractor(AlarmClockRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flowable<List<AlarmClockItem>> execute() {
        return repository.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
