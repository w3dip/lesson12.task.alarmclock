package ru.sberbank.lesson12.task.alarmclock.presentation.viewmodel;

import android.app.Application;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import ru.sberbank.lesson12.task.alarmclock.domain.interactor.impl.GetAllAlarmClocksInteractor;
import ru.sberbank.lesson12.task.alarmclock.domain.model.AlarmClockItem;

public class AlarmClockViewModel extends AndroidViewModel {
    private GetAllAlarmClocksInteractor interactor;

    @Inject
    AlarmClockViewModel(@NonNull Application application, GetAllAlarmClocksInteractor interactor) {
        super(application);
        this.interactor = interactor;
    }

    public LiveData<List<AlarmClockItem>> getClocks() {
        return LiveDataReactiveStreams.fromPublisher(interactor.execute());
    }
}
