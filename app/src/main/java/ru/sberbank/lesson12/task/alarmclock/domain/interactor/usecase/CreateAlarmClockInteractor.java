package ru.sberbank.lesson12.task.alarmclock.domain.interactor.usecase;

import ru.sberbank.lesson12.task.alarmclock.domain.interactor.Interactor;
import ru.sberbank.lesson12.task.alarmclock.domain.model.AlarmClockItem;
import ru.sberbank.lesson12.task.alarmclock.domain.repository.AlarmClockRepository;

import static ru.sberbank.lesson12.task.alarmclock.domain.util.AlarmClockSheduler.shedule;

public class CreateAlarmClockInteractor implements Interactor {
    public static final String NOTIFICATION_WORK_TAG = "notificationWork";

    private AlarmClockRepository repository;
    private AlarmClockItem item;

    public CreateAlarmClockInteractor(AlarmClockRepository repository, AlarmClockItem item) {
        this.repository = repository;
        this.item = item;
    }

    @Override
    public void execute() {
        shedule(item);
        repository.create(item);
    }
}
