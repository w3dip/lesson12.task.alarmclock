package ru.sberbank.lesson12.task.alarmclock.domain.repository;

import java.util.List;

import ru.sberbank.lesson12.task.alarmclock.domain.model.AlarmClockItem;

public interface AlarmClockRepository {
    List<AlarmClockItem> getAll();
    void create(AlarmClockItem item);
}
