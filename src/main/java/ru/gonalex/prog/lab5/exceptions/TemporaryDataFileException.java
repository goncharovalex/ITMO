package ru.gonalex.prog.lab5.exceptions;

/**
 * Исключение, вызываемое при работе с временным файлом данных коллекции
 * @author gonalex
 * @version 1.0
 */
public class TemporaryDataFileException extends Exception {
    private static final long serialVersionUID = 10L;

    public TemporaryDataFileException(String message) {
        super(message);
    }
}
