package ru.sberbank.lesson12.task.alarmclock.data.repository;

import android.arch.lifecycle.MediatorLiveData;
import android.content.Context;

import java.util.Collections;
import java.util.List;

import ru.sberbank.lesson12.task.alarmclock.data.entity.AlarmClockEntity;
import ru.sberbank.lesson12.task.alarmclock.data.repository.dao.AlarmClockDao;
import ru.sberbank.lesson12.task.alarmclock.domain.model.AlarmClockItem;
import ru.sberbank.lesson12.task.alarmclock.domain.repository.AlarmClockRepository;

public class AlarmClockRepositoryImpl implements AlarmClockRepository {
    private AlarmClockDao dao;

    private final MediatorLiveData<List<AlarmClockEntity>> result = new MediatorLiveData<>();

    public AlarmClockRepositoryImpl(AlarmClockDao dao) {
        this.dao = dao;
    }

    @Override
    public List<AlarmClockItem> getAll() {
        return Collections.emptyList();
    }

    @Override
    public void create(AlarmClockItem item) {

    }
}
