package ru.sberbank.lesson12.task.alarmclock.data.mapper;

import ru.sberbank.lesson12.task.alarmclock.data.entity.AlarmClockEntity;
import ru.sberbank.lesson12.task.alarmclock.domain.mapper.Mapper;
import ru.sberbank.lesson12.task.alarmclock.domain.model.AlarmClockItem;

public class AlarmClockItemToEntityMapper implements Mapper<AlarmClockItem, AlarmClockEntity> {
    /*private static final String DATE_FORMAT_INPUT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_OUTPUT = "dd.MM.yyyy HH:mm";*/

    @Override
    public AlarmClockEntity map(AlarmClockItem from) {
        return AlarmClockEntity
                .builder()
                .id(from.getId())
                .time(from.getTime())
                .build();
    }
}
