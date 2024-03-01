package ru.gonalex.prog.lab5.manage;

import ru.gonalex.prog.lab5.models.Realtor;

/**
 * Класс описывает параметры команды, которые используются или могут использоваться при выполнении команды
 * @author gonalex
 * @version 1.0
 */
public class CommandParams {
    /** Объект Риэлтор */
    public Realtor realtor;

    /** параметры команды */
    public String params;

    /** Имя файла с данными в формате JSON, в котором хранится коллекция риэлтора */
    public String fileNameJson;

    public CommandParams() {
        realtor = null;
        params = fileNameJson = "";
    }
}
