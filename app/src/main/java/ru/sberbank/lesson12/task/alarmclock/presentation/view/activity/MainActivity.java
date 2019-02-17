package ru.sberbank.lesson12.task.alarmclock.presentation.view.activity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import ru.sberbank.lesson12.task.alarmclock.R;
import ru.sberbank.lesson12.task.alarmclock.presentation.view.adapter.AlarmClockAdapter;
import ru.sberbank.lesson12.task.alarmclock.presentation.viewmodel.AlarmClockViewModel;

import static android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN;

public class MainActivity extends AppCompatActivity {
    public static final int NEW_ALARM_CLOCK_ACTIVITY_REQUEST_CODE = 1;
    public static final int EDIT_ALARM_CLOCK_ACTIVITY_REQUEST_CODE = 2;

    @BindView(R.id.alarm_clocks)
    RecyclerView recyclerAlarmClocks;

    @BindView(R.id.create_alarm_clock)
    FloatingActionButton createAlarmClockBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        createAlarmClockBtn.setOnClickListener(v -> {
            //startActivityForResult(new Intent(this, CreateAlarmClockActivity.class), NEW_ALARM_CLOCK_ACTIVITY_REQUEST_CODE);
            startActivity(new Intent(this, CreateAlarmClockActivity.class));
        });

        AlarmClockViewModel viewModel = ViewModelProviders.of(this).get(AlarmClockViewModel.class);
        viewModel.getClocks().observe(this, alarmClockItems -> {
            AlarmClockAdapter adapter = new AlarmClockAdapter(this);
            adapter.setClocks(alarmClockItems);
            recyclerAlarmClocks.setAdapter(adapter);
            recyclerAlarmClocks.setLayoutManager(new LinearLayoutManager(this));
        });
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == NEW_ALARM_CLOCK_ACTIVITY_REQUEST_CODE) {
                //
            }
        }
    }*/
}
