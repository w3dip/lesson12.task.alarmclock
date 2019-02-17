package ru.sberbank.lesson12.task.alarmclock.data.repository.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

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
