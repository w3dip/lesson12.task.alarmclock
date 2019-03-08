package ru.sberbank.lesson12.task.alarmclock.di.modules;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.sberbank.lesson12.task.alarmclock.data.entity.AlarmClockEntity;
import ru.sberbank.lesson12.task.alarmclock.data.mapper.AlarmClockEntityToItemMapper;
import ru.sberbank.lesson12.task.alarmclock.data.mapper.AlarmClockItemToEntityMapper;
import ru.sberbank.lesson12.task.alarmclock.data.repository.dao.AlarmClockDao;
import ru.sberbank.lesson12.task.alarmclock.data.repository.database.AlarmClockDatabase;
import ru.sberbank.lesson12.task.alarmclock.domain.mapper.Mapper;
import ru.sberbank.lesson12.task.alarmclock.domain.model.AlarmClockItem;

@Module(includes = {ViewModelModule.class, AlarmClockModule.class})
public class ApplicationModule {
    @Provides
    @Singleton
    AlarmClockDao provideAlarmClockDao(Application application) {
        return AlarmClockDatabase.getDatabase(application).alarmClockDao();
    }

    @Provides
    @Singleton
    Mapper<AlarmClockEntity, AlarmClockItem> provideAlarmClockEntityToItemMapper() {
        return new AlarmClockEntityToItemMapper();
    }

    @Provides
    @Singleton
    Mapper<AlarmClockItem, AlarmClockEntity> provideAlarmClockItemToEntityMapper() {
        return  new AlarmClockItemToEntityMapper();
    }
}
