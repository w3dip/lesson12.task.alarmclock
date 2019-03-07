package ru.sberbank.lesson12.task.alarmclock.presentation.view.fragment;

import android.app.Dialog;
import android.os.Bundle;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import dagger.android.support.DaggerDialogFragment;
import ru.sberbank.lesson12.task.alarmclock.R;
import ru.sberbank.lesson12.task.alarmclock.domain.interactor.usecase.DeleteAlarmClockInteractor;
import ru.sberbank.lesson12.task.alarmclock.domain.model.AlarmClockItem;

import static android.content.DialogInterface.BUTTON_NEGATIVE;
import static android.content.DialogInterface.BUTTON_POSITIVE;
import static ru.sberbank.lesson12.task.alarmclock.domain.model.AlarmClockItem.ALARM_CLOCK_ITEM;

public class DeleteAlarmClockFragment extends DaggerDialogFragment {
    @Inject
    DeleteAlarmClockInteractor interactor;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
        dialog.setCancelable(false);
        dialog.setTitle(R.string.alarm_clock_delete_title);
        dialog.setMessage(getResources().getString(R.string.alarm_clock_delete_message));
        dialog.setButton(BUTTON_POSITIVE, "OK", (alertDialog, which) -> {
            Bundle args = getArguments();
            if (args != null ) {
                AlarmClockItem value = (AlarmClockItem)args.getSerializable(ALARM_CLOCK_ITEM);
                if (value != null) {
                    interactor.setItem(value);
                    interactor.execute();
                    dismiss();
                }

            }
        });
        dialog.setButton(BUTTON_NEGATIVE, "Cancel", (alertDialog, which) -> {
            alertDialog.dismiss();
        });
        return dialog;
    }
}
