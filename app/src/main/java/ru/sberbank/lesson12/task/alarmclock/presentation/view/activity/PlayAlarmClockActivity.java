package ru.sberbank.lesson12.task.alarmclock.presentation.view.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;
import androidx.work.WorkManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import ru.sberbank.lesson12.task.alarmclock.R;
import ru.sberbank.lesson12.task.alarmclock.domain.model.AlarmClockItem;
import ru.sberbank.lesson12.task.alarmclock.domain.util.AudioPlayer;
import ru.sberbank.lesson12.task.alarmclock.presentation.viewmodel.AlarmClockViewModel;

import static ru.sberbank.lesson12.task.alarmclock.domain.interactor.usecase.CreateAlarmClockInteractor.NOTIFICATION_WORK_TAG;
import static ru.sberbank.lesson12.task.alarmclock.domain.util.AlarmClockSheduler.shedule;

public class PlayAlarmClockActivity extends AppCompatActivity {
    @BindView(R.id.clockImageView)
    ImageView clockImageView;

    private AudioPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_alarm_clock);
        ButterKnife.bind(this);

        AnimatedVectorDrawableCompat animatedVectorDrawable = ((AnimatedVectorDrawableCompat)clockImageView.getDrawable());
        animatedVectorDrawable.registerAnimationCallback(new Animatable2Compat.AnimationCallback() {
            @Override
            public void onAnimationEnd(Drawable drawable) {
                clockImageView.post(() -> animatedVectorDrawable.start());
            }
        });
        animatedVectorDrawable.start();

        if (savedInstanceState == null) {
            player = new AudioPlayer();
            player.play(this, R.raw.music);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        closeAlarmClock();
        finish();
        return true;
    }

    @Override
    public void onBackPressed() {
        closeAlarmClock();
        super.onBackPressed();
    }

    private void closeAlarmClock() {
        player.stop();
        NotificationManagerCompat.from(getApplicationContext()).cancel(1);
        //WorkManager.getInstance().cancelUniqueWork(NOTIFICATION_WORK_TAG);
        WorkManager.getInstance().cancelAllWork();
        AlarmClockViewModel viewModel = ViewModelProviders.of(this).get(AlarmClockViewModel.class);
        viewModel.getClocks().observe(this, alarmClockItems -> {
            for (AlarmClockItem item : alarmClockItems) {
                viewModel.resheduleAlarmClock(item);
            }
        });
    }
}
