package ru.sberbank.lesson12.task.alarmclock.data.repository.dao;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Flowable;
import io.reactivex.Single;
import ru.sberbank.lesson12.task.alarmclock.data.entity.AlarmClockEntity;

import static ru.sberbank.lesson12.task.alarmclock.data.entity.AlarmClockEntity.TABLE_NAME;

@Dao
public interface AlarmClockDao {

    @Query("SELECT * FROM " + TABLE_NAME)
    Flowable<List<AlarmClockEntity>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Single<List<Long>> insertAll(AlarmClockEntity... entities);

    @Delete
    Single<Integer> delete(AlarmClockEntity... entities);
}
