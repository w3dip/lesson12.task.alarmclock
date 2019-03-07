package ru.sberbank.lesson12.task.alarmclock;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import ru.sberbank.lesson12.task.alarmclock.di.components.AlarmClockComponent;
import ru.sberbank.lesson12.task.alarmclock.di.components.DaggerAlarmClockComponent;

public class AlarmClockApplication extends DaggerApplication {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AlarmClockComponent component = DaggerAlarmClockComponent.builder().application(this).build();
        component.inject(this);
        return component;
    }
}
