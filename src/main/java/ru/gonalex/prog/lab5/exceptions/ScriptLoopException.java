package ru.gonalex.prog.lab5.exceptions;

/**
 * Исключение, вызываемое если обнаружено условие для зацикливания выполнения скриптов
 * @author gonalex
 * @version 1.0
 */
public class ScriptLoopException extends Exception {
    private static final long serialVersionUID = 9L;

    public ScriptLoopException(String message) {
        super(message);
    }
}