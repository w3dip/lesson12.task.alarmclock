package ru.sberbank.lesson12.task.alarmclock.di.modules;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.sberbank.lesson12.task.alarmclock.data.repository.dao.AlarmClockDao;
import ru.sberbank.lesson12.task.alarmclock.data.repository.database.AlarmClockDatabase;

@Module(includes = {ViewModelModule.class, AlarmClockModule.class})
public class ApplicationModule {
    @Provides
    @Singleton
    AlarmClockDao provideAlarmClockDao(Application application) {
        return AlarmClockDatabase.getDatabase(application).alarmClockDao();
    }
}
