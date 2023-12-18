package ru.gonalex.suncitytale2.interfaces;

import ru.gonalex.suncitytale2.enums.ETime;

public interface IWordTime {
    void addTime(String Word, ETime Time);
    String getTime(ETime Time);
}
