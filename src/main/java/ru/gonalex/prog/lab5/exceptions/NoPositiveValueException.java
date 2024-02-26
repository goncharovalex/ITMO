package ru.gonalex.prog.lab5.exceptions;

/**
 * Исключение, вызываемое если значение поля меньше нуля, а ожидается >= 0
 * @author gonalex
 * @version 1.0
 */
public class NoPositiveValueException extends Exception  {
    private static final long serialVersionUID = 4L;

    public NoPositiveValueException(String message) {
        super(message);
    }
}
