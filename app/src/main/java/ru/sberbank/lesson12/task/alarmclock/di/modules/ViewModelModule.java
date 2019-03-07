package ru.sberbank.lesson12.task.alarmclock.di.modules;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import ru.sberbank.lesson12.task.alarmclock.di.util.ViewModelKey;
import ru.sberbank.lesson12.task.alarmclock.presentation.viewmodel.AlarmClockViewModel;
import ru.sberbank.lesson12.task.alarmclock.di.util.ViewModelFactory;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AlarmClockViewModel.class)
    abstract ViewModel bindAlarmClockViewModel(AlarmClockViewModel forecastViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
