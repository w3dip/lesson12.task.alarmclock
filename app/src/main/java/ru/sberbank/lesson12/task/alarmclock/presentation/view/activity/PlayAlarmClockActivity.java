package ru.sberbank.lesson12.task.alarmclock.presentation.view.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;
import butterknife.BindView;
import butterknife.ButterKnife;
import ru.sberbank.lesson12.task.alarmclock.R;
import ru.sberbank.lesson12.task.alarmclock.domain.util.AudioPlayer;

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
        player.stop();
        finish();
        return true;
    }

    @Override
    public void onBackPressed() {
        player.stop();
        super.onBackPressed();
    }
}
