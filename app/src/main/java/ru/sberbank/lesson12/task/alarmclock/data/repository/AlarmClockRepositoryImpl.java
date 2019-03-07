package ru.sberbank.lesson12.task.alarmclock.data.repository;

import android.os.AsyncTask;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Transformations;
import ru.sberbank.lesson12.task.alarmclock.data.entity.AlarmClockEntity;
import ru.sberbank.lesson12.task.alarmclock.data.mapper.AlarmClockEntityToItemMapper;
import ru.sberbank.lesson12.task.alarmclock.data.mapper.AlarmClockItemToEntityMapper;
import ru.sberbank.lesson12.task.alarmclock.data.repository.dao.AlarmClockDao;
import ru.sberbank.lesson12.task.alarmclock.domain.mapper.Mapper;
import ru.sberbank.lesson12.task.alarmclock.domain.model.AlarmClockItem;
import ru.sberbank.lesson12.task.alarmclock.domain.repository.AlarmClockRepository;

@Singleton
public class AlarmClockRepositoryImpl implements AlarmClockRepository {
    private static Mapper<List<AlarmClockEntity>, List<AlarmClockItem>> alarmClockEntityToItemMapper = new AlarmClockEntityToItemMapper();
    private static Mapper<AlarmClockItem, AlarmClockEntity> alarmClockItemToEntityMapper = new AlarmClockItemToEntityMapper();

    private static AlarmClockDao dao;

    private static final MediatorLiveData<List<AlarmClockEntity>> result = new MediatorLiveData<>();

    @Inject
    public AlarmClockRepositoryImpl(AlarmClockDao dao) {
        AlarmClockRepositoryImpl.dao = dao;
    }

    @Override
    public LiveData<List<AlarmClockItem>> getAll() {
        new LoadFromDbAsyncTask().execute();
        return Transformations.map(result, input -> alarmClockEntityToItemMapper.map(input));
    }

    @Override
    public void create(AlarmClockItem item) {
        new CreateAlarmAsyncTask().execute(alarmClockItemToEntityMapper.map(item));
    }

    @Override
    public void delete(AlarmClockItem item) {
        new DeleteAlarmAsyncTask().execute(alarmClockItemToEntityMapper.map(item));
    }

    private static class LoadFromDbAsyncTask extends AsyncTask<Void, Void, LiveData<List<AlarmClockEntity>>> {
        @Override
        protected LiveData<List<AlarmClockEntity>> doInBackground(Void... voids) {
            return dao.getAll();
        }

        @Override
        protected void onPostExecute(LiveData<List<AlarmClockEntity>> alarmData) {
            result.addSource(alarmData, alarmClockEntities -> result.setValue(alarmClockEntities));
        }
    }

    private static class CreateAlarmAsyncTask extends AsyncTask<AlarmClockEntity, Void, Void> {

        @Override
        protected Void doInBackground(AlarmClockEntity... alarmClockEntities) {
            dao.insertAll(alarmClockEntities);
            return null;
        }
    }

    private static class DeleteAlarmAsyncTask extends AsyncTask<AlarmClockEntity, Void, Void> {

        @Override
        protected Void doInBackground(AlarmClockEntity... alarmClockEntities) {
            dao.delete(alarmClockEntities[0]);
            return null;
        }
    }
}
