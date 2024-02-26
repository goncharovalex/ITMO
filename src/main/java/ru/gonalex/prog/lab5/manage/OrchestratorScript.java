package ru.gonalex.prog.lab5.manage;

import ru.gonalex.prog.lab5.exceptions.UnknownOrchestratorScannerException;
import ru.gonalex.prog.lab5.models.Realtor;

/**
 * Оркестратор для управления коллекцией квартир с использованием файла скрипта (с перечнем команд)
 * и без интерактивного взаимодействия с пользователем
 * @author gonalex
 * @version 1.0
 */
public class OrchestratorScript extends Orchestrator {

    /** Имя файла с данными в формате JSON, в котором хранится коллекция риэлтора */
    private String fileNameJson;

    /** Ридер, который обрабатываем команды из скрипта */
    private OrchestratorReader reader;

    /** Конструктор оркестратора
     * @param realtor Объект Realtor с перечнем квартир, который будет обрабатываться данным оркестратором
     * @param fileNameJson - Имя файла с данными в формате JSON, в котором хранится коллекция риэлтора */
    public OrchestratorScript(Realtor realtor, String fileNameJson) {
        super(realtor);
        this.fileNameJson = fileNameJson;
        reader = new OrchestratorReader(commands, realtor, fileNameJson);
    }

    /** Передача команды оркестратору на исполнение.
     * @param  command - команда на выполнение */
    public void run(String command) {
        try {
            reader.read(command);
        } catch (UnknownOrchestratorScannerException e) {
            TroubleWatcher.putProblem(e.getMessage());
        }
    }

    /** Передача команды оркестратору на исполнение.
     * @param  command - команда на выполнение */
    /*
    public void putCommand(String command) {
        try {
            reader.read(command);
        } catch (UnknownOrchestratorScannerException e) {
            TroubleWatcher.putProblem(e.getMessage());
        }
    }
   */
}
