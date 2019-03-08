package ru.sberbank.lesson12.task.alarmclock.domain.repository;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import ru.sberbank.lesson12.task.alarmclock.domain.model.AlarmClockItem;

public interface AlarmClockRepository {
    Flowable<List<AlarmClockItem>> getAll();
    Single<List<Long>> create(AlarmClockItem item);
    Single<Integer> delete(AlarmClockItem item);
}
