package ru.sberbank.lesson12.task.alarmclock.data.mapper;

import ru.sberbank.lesson12.task.alarmclock.data.entity.AlarmClockEntity;
import ru.sberbank.lesson12.task.alarmclock.domain.mapper.Mapper;
import ru.sberbank.lesson12.task.alarmclock.domain.model.AlarmClockItem;

public class AlarmClockEntityToItemMapper implements Mapper<AlarmClockEntity, AlarmClockItem> {
    @Override
    public AlarmClockItem map(AlarmClockEntity from) {
        return AlarmClockItem.builder()
                .id(from.getId())
                .time(from.getTime())
                .build();
    }
}
