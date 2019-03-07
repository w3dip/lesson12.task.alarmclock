package ru.sberbank.lesson12.task.alarmclock.presentation.viewmodel;

import android.app.Application;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import ru.sberbank.lesson12.task.alarmclock.domain.interactor.Callback;
import ru.sberbank.lesson12.task.alarmclock.domain.interactor.usecase.GetAllAlarmClocksInteractor;
import ru.sberbank.lesson12.task.alarmclock.domain.model.AlarmClockItem;

public class AlarmClockViewModel extends AndroidViewModel implements Callback<LiveData<List<AlarmClockItem>>> {
    private LiveData<List<AlarmClockItem>> clocks;
    private GetAllAlarmClocksInteractor interactor;

    @Inject
    public AlarmClockViewModel(@NonNull Application application, GetAllAlarmClocksInteractor interactor) {
        super(application);
        this.interactor = interactor;
        this.interactor.setCallback(this);
        this.interactor.execute();
    }

    public LiveData<List<AlarmClockItem>> getClocks() {
        return clocks;
    }

    @Override
    public void handle(LiveData<List<AlarmClockItem>> clocks) {
        this.clocks  = clocks;
    }
}
