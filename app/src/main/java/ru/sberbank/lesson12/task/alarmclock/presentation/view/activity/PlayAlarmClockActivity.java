package ru.sberbank.lesson12.task.alarmclock.presentation.view.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;

import java.util.UUID;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;
import androidx.work.WorkManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import ru.sberbank.lesson12.task.alarmclock.R;
import ru.sberbank.lesson12.task.alarmclock.domain.interactor.Callback;
import ru.sberbank.lesson12.task.alarmclock.domain.interactor.usecase.SheduleAlarmClockInteractor;
import ru.sberbank.lesson12.task.alarmclock.domain.model.AlarmClockItem;
import ru.sberbank.lesson12.task.alarmclock.domain.util.AudioPlayer;
import ru.sberbank.lesson12.task.alarmclock.presentation.viewmodel.PlayAlarmClockViewModel;

import static ru.sberbank.lesson12.task.alarmclock.domain.model.AlarmClockItem.ALARM_CLOCK_ITEM_ID;
import static ru.sberbank.lesson12.task.alarmclock.domain.util.AlarmClockSheduler.shedule;

public class PlayAlarmClockActivity extends AppCompatActivity /*implements Callback<Long>*/ {
    @BindView(R.id.clockImageView)
    ImageView clockImageView;

    private PlayAlarmClockViewModel viewModel;
    private AudioPlayer player;
    private AlarmClockItem item;

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

        viewModel = ViewModelProviders.of(this).get(PlayAlarmClockViewModel.class);
        viewModel.getAlarmClock(getIntent().getLongExtra(ALARM_CLOCK_ITEM_ID, 0));
        viewModel.getClock().observe(this, item -> {
            this.item = item;
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        WorkManager.getInstance().cancelWorkById(UUID.fromString(item.getWorkId()));
        //viewModel.recreateAlarmClock(item);
        new SheduleAlarmClockInteractor(viewModel.getRepository(), item).execute();
        player.stop();
        finish();
        return true;
    }

    @Override
    public void onBackPressed() {
        //TODO сделать пересоздание шедуллера
        player.stop();
        super.onBackPressed();
    }

    /*@Override
    public void handle(Long value) {
        shedule(value);
    }*/
}
