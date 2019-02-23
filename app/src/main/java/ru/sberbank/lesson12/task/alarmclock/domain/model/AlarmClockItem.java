package ru.sberbank.lesson12.task.alarmclock.domain.model;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AlarmClockItem implements Serializable {
    public static final String ALARM_CLOCK_ITEM = "alarmClockItem";
    public static final String ALARM_CLOCK_TAG = "timePicker";

    private Long id;
    private String time;
}
