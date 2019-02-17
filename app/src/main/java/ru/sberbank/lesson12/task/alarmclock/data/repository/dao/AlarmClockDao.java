package ru.sberbank.lesson12.task.alarmclock.data.repository.dao;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import ru.sberbank.lesson12.task.alarmclock.data.entity.AlarmClockEntity;

import static ru.sberbank.lesson12.task.alarmclock.data.entity.AlarmClockEntity.TABLE_NAME;

@Dao
public interface AlarmClockDao {

    @Query("SELECT * FROM " + TABLE_NAME)
    LiveData<List<AlarmClockEntity>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(AlarmClockEntity... entities);

    @Query("DELETE FROM " + TABLE_NAME)
    void deleteAll();
}
