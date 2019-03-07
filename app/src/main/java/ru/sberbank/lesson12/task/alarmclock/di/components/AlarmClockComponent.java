package ru.sberbank.lesson12.task.alarmclock.di.components;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;
import ru.sberbank.lesson12.task.alarmclock.AlarmClockApplication;
import ru.sberbank.lesson12.task.alarmclock.di.modules.ActivityBindingModule;
import ru.sberbank.lesson12.task.alarmclock.di.modules.ApplicationModule;

@Singleton
@Component(modules = {ApplicationModule.class, AndroidSupportInjectionModule.class, ActivityBindingModule.class})
public interface AlarmClockComponent extends AndroidInjector<DaggerApplication> {
  void inject(AlarmClockApplication application);

  @Component.Builder
  interface Builder {
    @BindsInstance
    Builder application(Application application);
    AlarmClockComponent build();
  }
}
