package ru.sberbank.lesson12.task.alarmclock.data.repository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.Single;
import ru.sberbank.lesson12.task.alarmclock.data.entity.AlarmClockEntity;
import ru.sberbank.lesson12.task.alarmclock.data.repository.dao.AlarmClockDao;
import ru.sberbank.lesson12.task.alarmclock.domain.mapper.Mapper;
import ru.sberbank.lesson12.task.alarmclock.domain.model.AlarmClockItem;
import ru.sberbank.lesson12.task.alarmclock.domain.repository.AlarmClockRepository;

@Singleton
public class AlarmClockRepositoryImpl implements AlarmClockRepository {
    private AlarmClockDao dao;
    private Mapper<AlarmClockEntity, AlarmClockItem> alarmClockEntityToItemMapper;
    private Mapper<AlarmClockItem, AlarmClockEntity> alarmClockItemToEntityMapper;


    @Inject
    AlarmClockRepositoryImpl(AlarmClockDao dao,
                             Mapper<AlarmClockEntity, AlarmClockItem> alarmClockEntityToItemMapper,
                             Mapper<AlarmClockItem, AlarmClockEntity> alarmClockItemToEntityMapper) {
        this.dao = dao;
        this.alarmClockEntityToItemMapper = alarmClockEntityToItemMapper;
        this.alarmClockItemToEntityMapper = alarmClockItemToEntityMapper;
    }

    @Override
    public Flowable<List<AlarmClockItem>> getAll() {
        return dao.getAll()
                .flatMapSingle(alarmClockEntities -> Flowable.fromIterable(alarmClockEntities)
                        .map(alarmClockEntityToItemMapper::map)
                        .toList());
    }

    @Override
    public Single<List<Long>> create(AlarmClockItem item) {
        return dao.insertAll(alarmClockItemToEntityMapper.map(item));
    }

    @Override
    public Single<Integer> delete(AlarmClockItem item) {
        return dao.delete(alarmClockItemToEntityMapper.map(item));
    }
}
