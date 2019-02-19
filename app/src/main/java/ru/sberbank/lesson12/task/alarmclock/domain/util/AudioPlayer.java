package ru.sberbank.lesson12.task.alarmclock.domain.util;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;

public class AudioPlayer {

    private MediaPlayer mMediaPlayer;

    public void stop() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    public void play(Context context, int rid) {
        stop();

        mMediaPlayer = MediaPlayer.create(context, rid);
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                stop();
                ((Activity)context).finish();
            }
        });

        mMediaPlayer.start();
    }

}