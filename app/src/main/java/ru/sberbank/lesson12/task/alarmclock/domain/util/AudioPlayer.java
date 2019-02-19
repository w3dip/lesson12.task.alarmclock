package ru.sberbank.lesson12.task.alarmclock.domain.util;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;

public class AudioPlayer {

    private MediaPlayer mediaPlayer;

    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public void play(Context context, int rid) {
        stop();

        int maxCount = 3;
        mediaPlayer = MediaPlayer.create(context, rid);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            int count = 1;

            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                if(count < maxCount) {
                    count++;
                    mediaPlayer.seekTo(0);
                    mediaPlayer.start();
                } else {
                    stop();
                    ((Activity) context).finish();
                }
            }
        });

        mediaPlayer.start();
    }

}