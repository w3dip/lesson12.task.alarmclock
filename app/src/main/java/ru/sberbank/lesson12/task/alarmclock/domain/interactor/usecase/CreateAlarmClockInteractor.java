package ru.sberbank.lesson12.task.alarmclock.domain.interactor.usecase;

import javax.inject.Inject;

import ru.sberbank.lesson12.task.alarmclock.domain.interactor.Interactor;
import ru.sberbank.lesson12.task.alarmclock.domain.model.AlarmClockItem;
import ru.sberbank.lesson12.task.alarmclock.domain.repository.AlarmClockRepository;

import static ru.sberbank.lesson12.task.alarmclock.domain.util.AlarmClockSheduler.shedule;

public class CreateAlarmClockInteractor implements Interactor {
    public static final String NOTIFICATION_WORK_TAG = "notificationWork";

    private AlarmClockRepository repository;
    private AlarmClockItem item;

    @Inject
    public CreateAlarmClockInteractor(AlarmClockRepository repository) {
        this.repository = repository;
    }

    public void setItem(AlarmClockItem item) {
        this.item = item;
    }

    @Override
    public void execute() {
        shedule(item);
        repository.create(item);
    }
}
