package ru.sberbank.lesson12.task.alarmclock.data.repository.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import ru.sberbank.lesson12.task.alarmclock.data.entity.AlarmClockEntity;
import ru.sberbank.lesson12.task.alarmclock.data.repository.dao.AlarmClockDao;

@Database(entities = {AlarmClockEntity.class}, version = 4, exportSchema = false)
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
