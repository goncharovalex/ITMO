package ru.gonalex.suncitytale2.interfaces;

import ru.gonalex.suncitytale2.enums.ECase;

public interface IWordCase {
    void addCase(String Word, ECase Case); // добавить слово в падеже
    String getCase(ECase Case); // получить слово в падеже
}
