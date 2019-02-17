package ru.sberbank.lesson12.task.alarmclock.domain.mapper;

public interface Mapper<T,V> {
    V map(T from);
}
