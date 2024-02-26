package ru.gonalex.prog.lab5.exceptions;

/**
 * Исключение, вызываемое если значение поля [год дома] указано неверно
 * @author gonalex
 * @version 1.0
 */
public class HouseYearException extends Exception {
    private static final long serialVersionUID = 5L;

    public HouseYearException(String message) {
        super(message);
    }
}
