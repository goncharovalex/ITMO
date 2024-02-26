package ru.gonalex.prog.lab5.exceptions;

/**
 * Исключение, вызываемое если значение поля координаты X указано неверно
 * @author gonalex
 * @version 1.0
 */
public class CoordinatesXException extends Exception {
    private static final long serialVersionUID = 1L;

    public CoordinatesXException(String message) {
        super(message);
    }
}