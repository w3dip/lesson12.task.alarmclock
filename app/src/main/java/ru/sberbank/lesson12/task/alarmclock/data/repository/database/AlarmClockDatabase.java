package ru.sberbank.lesson12.task.alarmclock.data.repository.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import ru.sberbank.lesson12.task.alarmclock.data.entity.AlarmClockEntity;
import ru.sberbank.lesson12.task.alarmclock.data.repository.dao.AlarmClockDao;

@Database(entities = {AlarmClockEntity.class}, version = 3, exportSchema = false)
public abstract class AlarmClockDatabase extends RoomDatabase {
    public abstract AlarmClockDao alarmClockDao();

    private static volatile AlarmClockDatabase INSTANCE;

    public static AlarmClockDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AlarmClockDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AlarmClockDatabase.class, "alarm_clock_db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
