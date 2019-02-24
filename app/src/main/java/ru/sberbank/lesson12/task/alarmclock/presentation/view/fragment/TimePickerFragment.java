package ru.sberbank.lesson12.task.alarmclock.presentation.view.fragment;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.LiveData;
import ru.sberbank.lesson12.task.alarmclock.data.repository.AlarmClockRepositoryImpl;
import ru.sberbank.lesson12.task.alarmclock.data.repository.dao.AlarmClockDao;
import ru.sberbank.lesson12.task.alarmclock.data.repository.database.AlarmClockDatabase;
import ru.sberbank.lesson12.task.alarmclock.domain.interactor.Callback;
import ru.sberbank.lesson12.task.alarmclock.domain.interactor.usecase.CreateAlarmClockInteractor;
import ru.sberbank.lesson12.task.alarmclock.domain.interactor.usecase.SheduleAlarmClockInteractor;
import ru.sberbank.lesson12.task.alarmclock.domain.model.AlarmClockItem;
import ru.sberbank.lesson12.task.alarmclock.domain.repository.AlarmClockRepository;

import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MINUTE;
import static ru.sberbank.lesson12.task.alarmclock.domain.model.AlarmClockItem.ALARM_CLOCK_ITEM;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener, Callback<LiveData<Long>> {
    private static final DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm");
    private AlarmClockRepository repository;
    private AlarmClockItem value = AlarmClockItem.builder().build();

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlarmClockDao dao = AlarmClockDatabase.getDatabase(getContext().getApplicationContext()).alarmClockDao();
        repository = new AlarmClockRepositoryImpl(dao);
        Bundle args = getArguments();
        if (args != null ) {
            value = (AlarmClockItem)args.getSerializable(ALARM_CLOCK_ITEM);
            if (value != null) {
                LocalTime time = formatter.parseDateTime(value.getTime()).toLocalTime();
                return new TimePickerDialog(getActivity(), this, time.getHourOfDay(), time.getMinuteOfHour(),
                        DateFormat.is24HourFormat(getActivity()));
            }

        }
        final Calendar c = Calendar.getInstance();
        return new TimePickerDialog(getActivity(), this, c.get(HOUR_OF_DAY), c.get(MINUTE),
                DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar c = Calendar.getInstance();
        c.set(HOUR_OF_DAY, hourOfDay);
        c.set(MINUTE, minute);
        value.setTime(formatter.print(c.getTimeInMillis()));
        new CreateAlarmClockInteractor(repository, value, this).execute();
    }

    @Override
    public void handle(LiveData<Long> data) {
        data.observe(this, id -> {
            value.setId(id);
            new SheduleAlarmClockInteractor(repository, value).execute();
        });
    }
}
