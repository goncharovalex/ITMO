package ru.gonalex.prog.lab5.exceptions;

/**
 * Исключение, вызываемое если значение поля Area указано неверно
 * @author gonalex
 * @version 1.0
 */
public class AreaValueException extends Exception {
    private static final long serialVersionUID = 3L;

    public AreaValueException(String message) {
        super(message);
    }
}