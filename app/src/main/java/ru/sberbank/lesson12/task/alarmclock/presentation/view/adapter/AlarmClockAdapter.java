package ru.sberbank.lesson12.task.alarmclock.presentation.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.File;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.sberbank.lesson12.task.alarmclock.R;

public class AlarmClockAdapter extends RecyclerView.Adapter<AlarmClockAdapter.AlarmClockViewHolder> {

    private final LayoutInflater inflater;
    private List<String> clocks = Collections.emptyList();
    private Context context;

    public static class AlarmClockViewHolder extends RecyclerView.ViewHolder {
        /*@BindView(R.id.image) ImageView image;
        String url;*/
        AlarmClockViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
            /*image.setOnClickListener((item) -> {
                Context context = item.getRootView().getContext();
                Intent intent = new Intent(context, ImagePreviewActivity.class);
                intent.putExtra(IMAGE_URL, url);
                context.startActivity(intent);
            });*/
        }
    }

    public AlarmClockAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public AlarmClockViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View view = inflater.inflate(R.layout.image_layout, parent, false);
        /*DisplayMetrics metrics = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(metrics.widthPixels / 3, metrics.widthPixels / 3);
        view.setLayoutParams(layoutParams);*/
        return new AlarmClockViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AlarmClockViewHolder holder, int position) {
        /*String url = clocks.get(position);
        holder.url = url;*/
    }

    public void setClocks(List<String> clocks) {
        this.clocks = clocks;
    }

    @Override
    public int getItemCount() {
        return clocks != null ? clocks.size() : 0;
    }
}
