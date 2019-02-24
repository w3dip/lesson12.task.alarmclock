package ru.sberbank.lesson12.task.alarmclock.presentation.viewmodel;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import ru.sberbank.lesson12.task.alarmclock.data.repository.AlarmClockRepositoryImpl;
import ru.sberbank.lesson12.task.alarmclock.data.repository.dao.AlarmClockDao;
import ru.sberbank.lesson12.task.alarmclock.data.repository.database.AlarmClockDatabase;
import ru.sberbank.lesson12.task.alarmclock.domain.interactor.Callback;
import ru.sberbank.lesson12.task.alarmclock.domain.interactor.usecase.CreateAlarmClockInteractor;
import ru.sberbank.lesson12.task.alarmclock.domain.interactor.usecase.GetAllAlarmClocksInteractor;
import ru.sberbank.lesson12.task.alarmclock.domain.interactor.usecase.GetByIdAlarmClocksInteractor;
import ru.sberbank.lesson12.task.alarmclock.domain.model.AlarmClockItem;
import ru.sberbank.lesson12.task.alarmclock.domain.repository.AlarmClockRepository;

public class PlayAlarmClockViewModel extends AndroidViewModel implements Callback<LiveData<AlarmClockItem>> {
    private LiveData<AlarmClockItem> clock;
    private AlarmClockRepository repository;

    public PlayAlarmClockViewModel(@NonNull Application application) {
        super(application);
        AlarmClockDao dao = AlarmClockDatabase.getDatabase(application).alarmClockDao();
        repository = new AlarmClockRepositoryImpl(dao);
    }

    public void getAlarmClock(long id) {
        GetByIdAlarmClocksInteractor interactor = new GetByIdAlarmClocksInteractor(repository, this);
        interactor.setId(id);
        interactor.execute();
    }

    /*public void recreateAlarmClock(AlarmClockItem item) {
        new CreateAlarmClockInteractor(repository, item).execute();
    }*/

    public LiveData<AlarmClockItem> getClock() {
        return clock;
    }

    public AlarmClockRepository getRepository() {
        return repository;
    }

    @Override
    public void handle(LiveData<AlarmClockItem> clock) {
        this.clock  = clock;
    }
}
