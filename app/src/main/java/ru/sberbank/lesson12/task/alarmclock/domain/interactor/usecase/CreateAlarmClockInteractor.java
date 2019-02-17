package ru.sberbank.lesson12.task.alarmclock.domain.interactor.usecase;

import java.util.List;

import ru.sberbank.lesson12.task.alarmclock.domain.interactor.Callback;
import ru.sberbank.lesson12.task.alarmclock.domain.interactor.Interactor;
import ru.sberbank.lesson12.task.alarmclock.domain.model.AlarmClockItem;
import ru.sberbank.lesson12.task.alarmclock.domain.repository.AlarmClockRepository;

public class CreateAlarmClockInteractor implements Interactor {
    private AlarmClockRepository repository;
    private AlarmClockItem item;
    //private Callback<List<String>> callback;

    public CreateAlarmClockInteractor(AlarmClockRepository repository, AlarmClockItem item/*, Callback<List<String>> callback*/) {
        this.repository = repository;
        this.item = item;
        //this.callback = callback;
    }

    @Override
    public void execute() {
        /*callback.handle(repository.getAll());*/
        repository.create(item);
    }
}
