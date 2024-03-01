package ru.gonalex.prog.lab5.exceptions;

/**
 * Исключение, вызываемое если обнаружено условие для зацикливания выполнения скриптов
 * @author gonalex
 * @version 1.0
 */
public class ScriptLoopException extends Exception {
    private static final long serialVersionUID = 9L;

    public static String defaultErrorMessage = "В процессе выполнения скрипта была обнаружена команда циклического вызова скрипта, что приведет к зацикливанию выполнения команд скрипта. Во избежание зацикливания, процесс выполнения скрипта был прерван в месте зацикливания.";

    public ScriptLoopException(String message) {
        super(message);
    }
}