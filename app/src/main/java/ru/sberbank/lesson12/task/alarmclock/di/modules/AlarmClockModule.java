package ru.sberbank.lesson12.task.alarmclock.di.modules;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;
import ru.sberbank.lesson12.task.alarmclock.data.repository.AlarmClockRepositoryImpl;
import ru.sberbank.lesson12.task.alarmclock.domain.interactor.Interactor;
import ru.sberbank.lesson12.task.alarmclock.domain.interactor.usecase.CreateAlarmClockInteractor;
import ru.sberbank.lesson12.task.alarmclock.domain.interactor.usecase.DeleteAlarmClockInteractor;
import ru.sberbank.lesson12.task.alarmclock.domain.interactor.usecase.GetAllAlarmClocksInteractor;
import ru.sberbank.lesson12.task.alarmclock.domain.repository.AlarmClockRepository;

@Module
public abstract class AlarmClockModule {
    @Binds
    public abstract AlarmClockRepository bindAlarmClockRepository(AlarmClockRepositoryImpl repository);

    @Binds
    abstract Context bindContext(Application application);

    @Binds
    abstract Interactor bindGetAllAlarmClocksInteractor(GetAllAlarmClocksInteractor interactor);

    @Binds
    abstract Interactor bindDeleteAlarmClockInteractor(DeleteAlarmClockInteractor interactor);

    @Binds
    abstract Interactor bindCreateAlarmClockInteractor(CreateAlarmClockInteractor interactor);
}
