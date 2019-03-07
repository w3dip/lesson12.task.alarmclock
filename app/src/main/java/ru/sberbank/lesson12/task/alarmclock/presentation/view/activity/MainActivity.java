package ru.sberbank.lesson12.task.alarmclock.presentation.view.activity;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import javax.inject.Inject;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.WorkManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;
import ru.sberbank.lesson12.task.alarmclock.R;
import ru.sberbank.lesson12.task.alarmclock.domain.model.AlarmClockItem;
import ru.sberbank.lesson12.task.alarmclock.presentation.view.adapter.AlarmClockAdapter;
import ru.sberbank.lesson12.task.alarmclock.presentation.view.fragment.TimePickerFragment;
import ru.sberbank.lesson12.task.alarmclock.presentation.viewmodel.AlarmClockViewModel;
import ru.sberbank.lesson12.task.alarmclock.di.util.ViewModelFactory;

import static ru.sberbank.lesson12.task.alarmclock.domain.interactor.usecase.CreateAlarmClockInteractor.NOTIFICATION_WORK_TAG;
import static ru.sberbank.lesson12.task.alarmclock.domain.model.AlarmClockItem.ALARM_CLOCK_TAG;
import static ru.sberbank.lesson12.task.alarmclock.domain.util.AlarmClockSheduler.shedule;

public class MainActivity extends DaggerAppCompatActivity {
    @BindView(R.id.alarm_clocks)
    RecyclerView recyclerAlarmClocks;

    @BindView(R.id.create_alarm_clock)
    FloatingActionButton createAlarmClockBtn;

    @Inject
    ViewModelFactory viewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            WorkManager.getInstance().cancelAllWorkByTag(NOTIFICATION_WORK_TAG);
        }

        createAlarmClockBtn.setOnClickListener(v -> {
            new TimePickerFragment().show(getSupportFragmentManager(), ALARM_CLOCK_TAG);
        });

        AlarmClockViewModel viewModel = ViewModelProviders.of(this, viewModelFactory).get(AlarmClockViewModel.class);
        AlarmClockAdapter adapter = new AlarmClockAdapter(this);
        viewModel.getClocks().observe(this, alarmClockItems -> {
            adapter.setClocks(alarmClockItems);
            recyclerAlarmClocks.setAdapter(adapter);
            recyclerAlarmClocks.setLayoutManager(new LinearLayoutManager(this));

            if (savedInstanceState == null) {
                for (AlarmClockItem item : alarmClockItems) {
                    shedule(item);
                }
            }
        });
    }
}
