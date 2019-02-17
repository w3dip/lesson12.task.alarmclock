package ru.sberbank.lesson12.task.alarmclock.domain.repository;

import java.util.List;

import androidx.lifecycle.LiveData;
import ru.sberbank.lesson12.task.alarmclock.domain.model.AlarmClockItem;

public interface AlarmClockRepository {
    LiveData<List<AlarmClockItem>> getAll();
    void create(AlarmClockItem item);
}
