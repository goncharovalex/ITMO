package main.java.ru.gonalex.suncitytale.interfaces;

import main.java.ru.gonalex.suncitytale.enums.ETime;

public interface IWordTime {
    void addTime(String Word, ETime Time);
    String getTime(ETime Time);
}
