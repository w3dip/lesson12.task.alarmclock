package ru.sberbank.lesson12.task.alarmclock.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AlarmClockItem {
    private String time;
}
