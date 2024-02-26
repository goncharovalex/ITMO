package ru.gonalex.prog.lab5.exceptions;

/**
 * Исключение, вызываемое если команда подразумевает параметр, а он не был указан
 * @author gonalex
 * @version 1.0
 */
public class NoCommandParamException extends Exception {
    private static final long serialVersionUID = 6L;

    public NoCommandParamException(String message) {
        super(message);
    }
}
