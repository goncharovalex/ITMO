package ru.gonalex.prog.lab5.manage;

import ru.gonalex.prog.lab5.models.Flat;
import java.util.HashSet;

/**
 * Используется при чтении коллекции с информацией о квартирах из источника данных.
 * Может содержать коллекцию или же сообщение об ошибке, если данные из источника данных получить не удалось
 * @author gonalex
 * @version 1.0
 */
public class DataWrapper {
    public HashSet<Flat> flats;
    public String message;
    public DataWrapper() {
        flats = null;
        message = "";
    }
}
