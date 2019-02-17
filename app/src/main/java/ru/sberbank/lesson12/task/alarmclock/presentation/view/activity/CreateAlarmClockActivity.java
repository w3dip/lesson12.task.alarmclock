package ru.sberbank.lesson12.task.alarmclock.presentation.view.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import ru.sberbank.lesson12.task.alarmclock.R;
import ru.sberbank.lesson12.task.alarmclock.data.repository.AlarmClockRepositoryImpl;
import ru.sberbank.lesson12.task.alarmclock.data.repository.dao.AlarmClockDao;
import ru.sberbank.lesson12.task.alarmclock.data.repository.database.AlarmClockDatabase;
import ru.sberbank.lesson12.task.alarmclock.domain.interactor.usecase.CreateAlarmClockInteractor;
import ru.sberbank.lesson12.task.alarmclock.domain.model.AlarmClockItem;
import ru.sberbank.lesson12.task.alarmclock.domain.repository.AlarmClockRepository;

public class CreateAlarmClockActivity extends AppCompatActivity {
    @BindView(R.id.newAlarmTime)
    EditText newAlarmTime;

    @BindView(R.id.newAlarmBtn)
    Button newAlarmBtn;

    @BindView(R.id.cancelNewAlarmBtn)
    Button cancelNewAlarmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_alarm_clock);
        ButterKnife.bind(this);

        AlarmClockDao dao = AlarmClockDatabase.getDatabase(getApplicationContext()).alarmClockDao();
        AlarmClockRepository repository = new AlarmClockRepositoryImpl(dao);

        newAlarmBtn.setOnClickListener(v -> {
            String time = newAlarmTime.getText().toString();
            if (time.length() > 0) {
                CreateAlarmClockInteractor interactor = new CreateAlarmClockInteractor(repository, AlarmClockItem.builder().time(time).build());
                interactor.execute();
                /*Intent replyIntent = new Intent();
                replyIntent.putExtra(NOTE_CONTENT, text);
                setResult(RESULT_OK, replyIntent);*/
                finish();
            } else {
                Toast.makeText(v.getContext(), R.string.empty_text, Toast.LENGTH_SHORT).show();
            }
        });

        cancelNewAlarmBtn.setOnClickListener(v -> {
            finish();
        });
    }
}
