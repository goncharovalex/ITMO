package ru.gonalex.prog.lab5.exceptions;

/**
 * Исключение, вызываемое если значение поля координаты Y указано неверно
 * @author gonalex
 * @version 1.0
 */
public class CoordinatesYException extends Exception {
    private static final long serialVersionUID = 2L;

    public CoordinatesYException(String message) {
        super(message);
    }
}