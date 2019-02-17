package ru.sberbank.lesson12.task.alarmclock.presentation.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.List;

import ru.sberbank.lesson12.task.alarmclock.data.repository.AlarmClockRepositoryImpl;
import ru.sberbank.lesson12.task.alarmclock.data.repository.dao.AlarmClockDao;
import ru.sberbank.lesson12.task.alarmclock.data.repository.database.AlarmClockDatabase;
import ru.sberbank.lesson12.task.alarmclock.domain.interactor.Callback;
import ru.sberbank.lesson12.task.alarmclock.domain.interactor.usecase.GetAllAlarmClocksInteractor;
import ru.sberbank.lesson12.task.alarmclock.domain.model.AlarmClockItem;
import ru.sberbank.lesson12.task.alarmclock.domain.repository.AlarmClockRepository;

public class AlarmClockViewModel extends AndroidViewModel implements Callback<List<AlarmClockItem>> {
    private List<AlarmClockItem> clocks;

    public AlarmClockViewModel(@NonNull Application application) {
        super(application);
        AlarmClockDao dao = AlarmClockDatabase.getDatabase(application).alarmClockDao();
        AlarmClockRepository repository = new AlarmClockRepositoryImpl(dao);

        GetAllAlarmClocksInteractor interactor = new GetAllAlarmClocksInteractor(repository, this);
        interactor.execute();
        /*AlarmClockRepository repository = new AlarmClockRepositoryImpl(application.getApplicationContext());
        GetAllAlarmClocksInteractor allImagesInteractor = new GetAllAlarmClocksInteractor(repository, this);
        allImagesInteractor.execute();*/
    }

    public List<AlarmClockItem> getClocks() {
        return clocks;
    }

    @Override
    public void handle(List<AlarmClockItem> clocks) {
        this.clocks  = clocks;
    }
}
