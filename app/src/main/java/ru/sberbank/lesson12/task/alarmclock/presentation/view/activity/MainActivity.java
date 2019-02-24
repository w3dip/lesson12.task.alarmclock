package ru.sberbank.lesson12.task.alarmclock.presentation.view.activity;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.WorkManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import ru.sberbank.lesson12.task.alarmclock.R;
import ru.sberbank.lesson12.task.alarmclock.domain.model.AlarmClockItem;
import ru.sberbank.lesson12.task.alarmclock.presentation.view.adapter.AlarmClockAdapter;
import ru.sberbank.lesson12.task.alarmclock.presentation.view.fragment.TimePickerFragment;
import ru.sberbank.lesson12.task.alarmclock.presentation.viewmodel.AlarmClockViewModel;

import static ru.sberbank.lesson12.task.alarmclock.domain.interactor.usecase.CreateAlarmClockInteractor.NOTIFICATION_WORK_TAG;
import static ru.sberbank.lesson12.task.alarmclock.domain.model.AlarmClockItem.ALARM_CLOCK_TAG;
import static ru.sberbank.lesson12.task.alarmclock.domain.util.AlarmClockSheduler.shedule;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.alarm_clocks)
    RecyclerView recyclerAlarmClocks;

    @BindView(R.id.create_alarm_clock)
    FloatingActionButton createAlarmClockBtn;

    private AlarmClockViewModel viewModel;
    private AlarmClockAdapter adapter;

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

        viewModel = ViewModelProviders.of(this).get(AlarmClockViewModel.class);
        adapter = new AlarmClockAdapter(this);
        viewModel.getClocks().observe(this, alarmClockItems -> {
            adapter.setClocks(alarmClockItems);
            recyclerAlarmClocks.setAdapter(adapter);
            recyclerAlarmClocks.setLayoutManager(new LinearLayoutManager(this));

            //WorkManager.getInstance().cancelUniqueWork(NOTIFICATION_WORK_TAG);
            //WorkManager.getInstance().cancelAllWork();
            /*if (savedInstanceState == null) {
                for (AlarmClockItem item : alarmClockItems) {
                    viewModel.resheduleAlarmClock(item);
                }
            }*/
        });
    }

    @Override
    protected void onResume() {
        /*viewModel.getClocks().observe(this, alarmClockItems -> {
            adapter.setClocks(alarmClockItems);
            recyclerAlarmClocks.setAdapter(adapter);
            recyclerAlarmClocks.setLayoutManager(new LinearLayoutManager(this));

            //WorkManager.getInstance().cancelUniqueWork(NOTIFICATION_WORK_TAG);
            WorkManager.getInstance().cancelAllWork();
            //if (savedInstanceState == null) {
                for (AlarmClockItem item : alarmClockItems) {
                    viewModel.resheduleAlarmClock(item);
                }
            //}
        });*/
        super.onResume();
        WorkManager.getInstance().cancelAllWork();
        viewModel.getClocks().observe(this, alarmClockItems -> {
            for (AlarmClockItem item : alarmClockItems) {
                viewModel.resheduleAlarmClock(item);
            }
        });
        //WorkManager.getInstance().cancelAllWork();
    }
}
