package ru.sberbank.lesson12.task.alarmclock.data.mapper;

import com.google.common.collect.FluentIterable;

import java.util.Collections;
import java.util.List;

import ru.sberbank.lesson12.task.alarmclock.data.entity.AlarmClockEntity;
import ru.sberbank.lesson12.task.alarmclock.domain.mapper.Mapper;
import ru.sberbank.lesson12.task.alarmclock.domain.model.AlarmClockItem;

public class AlarmClockEntityToItemMapper implements Mapper<List<AlarmClockEntity>, List<AlarmClockItem>> {
    @Override
    public List<AlarmClockItem> map(List<AlarmClockEntity> source) {
        if (source == null || source.isEmpty()) {
            return Collections.emptyList();
        }
        return FluentIterable.from(source)
                .transform(item -> AlarmClockItem.builder()
                        .time(item.getTime())
                        .build())
                .toList();

    }
}
