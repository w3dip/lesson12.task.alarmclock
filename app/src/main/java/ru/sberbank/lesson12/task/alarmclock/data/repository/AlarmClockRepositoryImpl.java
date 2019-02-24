package ru.sberbank.lesson12.task.alarmclock.data.repository;

import android.os.AsyncTask;

import com.google.common.collect.Lists;

import java.util.List;

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

public class AlarmClockRepositoryImpl implements AlarmClockRepository {
    private static Mapper<List<AlarmClockEntity>, List<AlarmClockItem>> alarmClockEntityToItemMapper = new AlarmClockEntityToItemMapper();
    private static Mapper<AlarmClockItem, AlarmClockEntity> alarmClockItemToEntityMapper = new AlarmClockItemToEntityMapper();

    private static AlarmClockDao dao;

    private static final MediatorLiveData<List<AlarmClockEntity>> result = new MediatorLiveData<>();
    private static final MediatorLiveData<Long> resultCreate = new MediatorLiveData<>();
    private static final MediatorLiveData<AlarmClockEntity> resultById = new MediatorLiveData<>();

    public AlarmClockRepositoryImpl(AlarmClockDao dao) {
        AlarmClockRepositoryImpl.dao = dao;
    }

    @Override
    public LiveData<List<AlarmClockItem>> getAll() {
        new LoadFromDbAsyncTask().execute();
        return Transformations.map(result, input -> alarmClockEntityToItemMapper.map(input));
    }

    @Override
    public LiveData<AlarmClockItem> getById(long id) {
        new LoadFromDbByIdAsyncTask().execute(id);
        return Transformations.map(result, input -> alarmClockEntityToItemMapper.map(Lists.newArrayList(input)).get(0));
    }

    @Override
    public LiveData<Long> create(AlarmClockItem item) {
        new CreateAlarmAsyncTask().execute(alarmClockItemToEntityMapper.map(item));
        //return resultCreate.getValue();
        return resultCreate;
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

    private static class LoadFromDbByIdAsyncTask extends AsyncTask<Long, Void, LiveData<AlarmClockEntity>> {
        @Override
        protected LiveData<AlarmClockEntity> doInBackground(Long... ids) {
            return dao.getById(ids[0]);
        }

        @Override
        protected void onPostExecute(LiveData<AlarmClockEntity> alarmData) {
            result.addSource(alarmData, alarmClockEntity -> resultById.setValue(alarmClockEntity));
        }
    }

    private static class CreateAlarmAsyncTask extends AsyncTask<AlarmClockEntity, Void, Long[]> {

        @Override
        protected Long[] doInBackground(AlarmClockEntity... alarmClockEntities) {
            return dao.insertAll(alarmClockEntities);
        }

        @Override
        protected void onPostExecute(Long[] ids) {
            //resultCreate.addSource(ids, id -> resultCreate.postValue(id));
            resultCreate.postValue(ids[0]);
            //resultCreate.setValue(ids);
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
