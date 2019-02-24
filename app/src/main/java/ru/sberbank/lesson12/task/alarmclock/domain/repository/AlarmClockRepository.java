package ru.sberbank.lesson12.task.alarmclock.domain.repository;

import java.util.List;

import androidx.lifecycle.LiveData;
import ru.sberbank.lesson12.task.alarmclock.domain.model.AlarmClockItem;

public interface AlarmClockRepository {
    LiveData<List<AlarmClockItem>> getAll();
    LiveData<AlarmClockItem> getById(long id);
    LiveData<Long> create(AlarmClockItem item);
    void delete(AlarmClockItem item);
}
