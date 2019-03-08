package ru.sberbank.lesson12.task.alarmclock.di.modules;

import android.app.Application;
import android.content.Context;

import java.util.List;

import dagger.Binds;
import dagger.Module;
import io.reactivex.Flowable;
import ru.sberbank.lesson12.task.alarmclock.data.repository.AlarmClockRepositoryImpl;
import ru.sberbank.lesson12.task.alarmclock.domain.interactor.Interactor;
import ru.sberbank.lesson12.task.alarmclock.domain.interactor.impl.CreateAlarmClockInteractor;
import ru.sberbank.lesson12.task.alarmclock.domain.interactor.impl.DeleteAlarmClockInteractor;
import ru.sberbank.lesson12.task.alarmclock.domain.interactor.impl.GetAllAlarmClocksInteractor;
import ru.sberbank.lesson12.task.alarmclock.domain.model.AlarmClockItem;
import ru.sberbank.lesson12.task.alarmclock.domain.repository.AlarmClockRepository;

@Module
public abstract class AlarmClockModule {
    @Binds
    public abstract AlarmClockRepository bindAlarmClockRepository(AlarmClockRepositoryImpl repository);

    @Binds
    abstract Context bindContext(Application application);

    @Binds
    abstract Interactor<Flowable<List<AlarmClockItem>>> bindGetAllAlarmClocksInteractor(GetAllAlarmClocksInteractor interactor);

    @Binds
    abstract Interactor<Void> bindDeleteAlarmClockInteractor(DeleteAlarmClockInteractor interactor);

    @Binds
    abstract Interactor<Void> bindCreateAlarmClockInteractor(CreateAlarmClockInteractor interactor);
}
