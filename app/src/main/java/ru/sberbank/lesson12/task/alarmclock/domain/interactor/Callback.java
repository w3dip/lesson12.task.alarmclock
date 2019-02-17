package ru.sberbank.lesson12.task.alarmclock.domain.interactor;

public interface Callback<T> {
    void handle(T value);
}
