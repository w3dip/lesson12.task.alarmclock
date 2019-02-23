package ru.sberbank.lesson12.task.alarmclock.presentation.view.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import ru.sberbank.lesson12.task.alarmclock.R;
import ru.sberbank.lesson12.task.alarmclock.domain.model.AlarmClockItem;
import ru.sberbank.lesson12.task.alarmclock.presentation.view.fragment.DeleteAlarmClockFragment;
import ru.sberbank.lesson12.task.alarmclock.presentation.view.fragment.TimePickerFragment;

import static ru.sberbank.lesson12.task.alarmclock.domain.model.AlarmClockItem.ALARM_CLOCK_ITEM;
import static ru.sberbank.lesson12.task.alarmclock.domain.model.AlarmClockItem.ALARM_CLOCK_TAG;

public class AlarmClockAdapter extends RecyclerView.Adapter<AlarmClockAdapter.AlarmClockViewHolder> {

    private final LayoutInflater inflater;
    private List<AlarmClockItem> clocks = Collections.emptyList();

    public static class AlarmClockViewHolder extends RecyclerView.ViewHolder {
        AlarmClockItem item;
        @BindView(R.id.time) TextView time;
        @BindView(R.id.alarm_clock_item) View view;
        AlarmClockViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
            view.setOnClickListener(i -> {
                DialogFragment newFragment = new TimePickerFragment();
                Bundle args = new Bundle();
                args.putSerializable(ALARM_CLOCK_ITEM, item);
                newFragment.setArguments(args);
                newFragment.show(((FragmentActivity)v.getContext()).getSupportFragmentManager(), ALARM_CLOCK_TAG);
            });
            view.setOnLongClickListener(i -> {
                DialogFragment newFragment = new DeleteAlarmClockFragment();
                Bundle args = new Bundle();
                args.putSerializable(ALARM_CLOCK_ITEM, item);
                newFragment.setArguments(args);
                newFragment.show(((FragmentActivity)v.getContext()).getSupportFragmentManager(), null);
                return true;
            });
        }
    }

    public AlarmClockAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public AlarmClockViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View view = inflater.inflate(R.layout.alarm_clock_item_layout, parent, false);
        return new AlarmClockViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AlarmClockViewHolder holder, int position) {
        AlarmClockItem item = clocks.get(position);
        holder.time.setText(item.getTime());
        holder.item = item;
    }

    public void setClocks(List<AlarmClockItem> clocks) {
        this.clocks = clocks;
    }

    @Override
    public int getItemCount() {
        return clocks != null ? clocks.size() : 0;
    }
}
