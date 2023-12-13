package main.java.ru.gonalex.suncitytale.interfaces;

import main.java.ru.gonalex.suncitytale.enums.ECase;

public interface IWordCase {
    void addCase(String Word, ECase Case); // добавить слово в падеже
    String getCase(ECase Case); // получить слово в падеже
}
