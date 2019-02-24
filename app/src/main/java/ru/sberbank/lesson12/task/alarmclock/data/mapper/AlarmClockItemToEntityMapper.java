package ru.sberbank.lesson12.task.alarmclock.data.mapper;

import ru.sberbank.lesson12.task.alarmclock.data.entity.AlarmClockEntity;
import ru.sberbank.lesson12.task.alarmclock.domain.mapper.Mapper;
import ru.sberbank.lesson12.task.alarmclock.domain.model.AlarmClockItem;

public class AlarmClockItemToEntityMapper implements Mapper<AlarmClockItem, AlarmClockEntity> {
    @Override
    public AlarmClockEntity map(AlarmClockItem from) {
        return AlarmClockEntity
                .builder()
                .id(from.getId())
                .time(from.getTime())
                .date(from.getDate())
                .build();
    }
}
