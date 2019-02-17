package ru.sberbank.lesson12.task.alarmclock.data.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import lombok.Builder;
import lombok.Data;

import static ru.sberbank.lesson12.task.alarmclock.data.entity.AlarmClockEntity.TABLE_NAME;

@Data
@Builder
@Entity(tableName = TABLE_NAME)
public class AlarmClockEntity {
    @Ignore
    public static final String TABLE_NAME = "alarm_clocks";

    /*@Ignore
    public static final String COLUMN_DATE = "date";*/

    /*@Ignore
    public static final String FORECAST_DATE = "ru.sberbank.lesson9.task.network.domain.entity.FORECAST_DATE";*/

    @PrimaryKey
    @NonNull
    private Long id;
    private String time;

    public AlarmClockEntity(@NonNull Long id, String time) {
        this.id = id;
        this.time = time;
    }
}
