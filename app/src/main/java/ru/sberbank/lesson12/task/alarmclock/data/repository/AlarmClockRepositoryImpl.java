package ru.sberbank.lesson12.task.alarmclock.data.repository;

import android.content.Context;

import java.util.Collections;
import java.util.List;

import ru.sberbank.lesson12.task.alarmclock.domain.repository.AlarmClockRepository;

public class AlarmClockRepositoryImpl implements AlarmClockRepository {

    @Override
    public List<String> getAll() {
        return Collections.emptyList();
    }
}
