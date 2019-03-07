package ru.sberbank.lesson12.task.alarmclock.di.modules;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import ru.sberbank.lesson12.task.alarmclock.presentation.view.activity.MainActivity;
import ru.sberbank.lesson12.task.alarmclock.presentation.view.activity.PlayAlarmClockActivity;
import ru.sberbank.lesson12.task.alarmclock.presentation.view.fragment.DeleteAlarmClockFragment;
import ru.sberbank.lesson12.task.alarmclock.presentation.view.fragment.TimePickerFragment;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector
    abstract PlayAlarmClockActivity bindDetailForecastActivity();

    @ContributesAndroidInjector
    abstract DeleteAlarmClockFragment bindDeleteAlarmClockFragment();

    @ContributesAndroidInjector
    abstract TimePickerFragment bindTimePickerFragment();
}
