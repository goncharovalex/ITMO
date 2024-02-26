package ru.gonalex.prog.lab5.exceptions;

/**
 * Исключение, вызываемое если в OrchestratorReader указан неприменимый объект для получения данных для команд и полей данных
 * @author gonalex
 * @version 1.0
 */
public class UnknownOrchestratorScannerException extends Exception {
    private static final long serialVersionUID = 7L;

    public UnknownOrchestratorScannerException(String message) {
        super(message);
    }
}
