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
import ru.sberbank.lesson12.task.alarmclock.domain.interactor.usecase.GetAllAlarmClocksInteractor;
import ru.sberbank.lesson12.task.alarmclock.domain.model.AlarmClockItem;
import ru.sberbank.lesson12.task.alarmclock.domain.repository.AlarmClockRepository;

public class AlarmClockViewModel extends AndroidViewModel implements Callback<LiveData<List<AlarmClockItem>>> {
    private LiveData<List<AlarmClockItem>> clocks;

    public AlarmClockViewModel(@NonNull Application application) {
        super(application);
        AlarmClockDao dao = AlarmClockDatabase.getDatabase(application).alarmClockDao();
        AlarmClockRepository repository = new AlarmClockRepositoryImpl(dao);

        GetAllAlarmClocksInteractor interactor = new GetAllAlarmClocksInteractor(repository, this);
        interactor.execute();
    }

    public LiveData<List<AlarmClockItem>> getClocks() {
        return clocks;
    }

    @Override
    public void handle(LiveData<List<AlarmClockItem>> clocks) {
        this.clocks  = clocks;
    }
}
