package ru.sberbank.lesson12.task.alarmclock.presentation.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.List;

import ru.sberbank.lesson12.task.alarmclock.domain.interactor.Callback;

public class AlarmClockViewModel extends AndroidViewModel implements Callback<List<String>> {
    private List<String> clocks;

    public AlarmClockViewModel(@NonNull Application application) {
        super(application);
        /*AlarmClockRepository repository = new AlarmClockRepositoryImpl(application.getApplicationContext());
        GelAllImagesInteractor allImagesInteractor = new GelAllImagesInteractor(repository, this);
        allImagesInteractor.execute();*/
    }

    public List<String> getClocks() {
        return clocks;
    }

    @Override
    public void handle(List<String> images) {
        this.clocks = images;
    }
}
