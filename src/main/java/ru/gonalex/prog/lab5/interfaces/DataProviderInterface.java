package ru.gonalex.prog.lab5.interfaces;

import ru.gonalex.prog.lab5.manage.DataWrapper;
import ru.gonalex.prog.lab5.models.Flat;
import java.io.IOException;
import java.util.HashSet;

/**
 * Интерфейс описывает необходимые методы для чтения и записи данных коллекции
 * @author gonalex
 * @version 1.0
 */
public interface DataProviderInterface {

    /**
     * Запись данных
     * @param flats - коллекция квартир
     * @exception IOException если что-то пошло не так при записи данных
     * */
    void write(HashSet<Flat> flats) throws IOException;

    /**
     * Чтение данных
     * @exception IOException если что-то пошло не так при чтении данных
     * */
    DataWrapper read() throws IOException;
}
