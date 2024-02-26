package ru.gonalex.prog.lab5.manage;

import ru.gonalex.prog.lab5.exceptions.ScriptLoopException;
import java.util.HashSet;

/**
 * Класс предназначен для мониторинга выполнения скрипта с командами.
 * Фиксирует проблемы с данными и проблему с бесконечным зацикливанием скриптов, когда скрипты вызывают друг друга
 * @author gonalex
 * @version 1.0
 */
public class TroubleWatcher {

    /** произошла ошибка */
    private static boolean errorOccurred = false;

    /** описание проблемы */
    private static String description = "";

    /** список файлов с исполняемыми скриптами */
    private static HashSet<String> scriptFileNames = new HashSet<>();

    /** добавить описание проблемы
     * @param description текст с описанием проблемы */
    public static void putProblem(String description) {
        errorOccurred = !(description == null | description.isEmpty());
        TroubleWatcher.description = description;
    }

    /** получить описание пробелмы
     * @return текст с описанием проблемы */
    public static String getProblem() {
        return description;
    }

    /** есть ли проблема
     * @return true|false */
    public static boolean hasProblem() {
        return errorOccurred;
    }

    /** очистить/сбросить описание проблемы */
    public static void clear() {
        errorOccurred = false;
        description = "";
    }

    /** очистить список вызываемых скриптов */
    public static void clearScriptList() {
        scriptFileNames.clear();
    }

    /** добавить имя файла вызываемого скрипта
     * @param fileName имя файла скрипта
     * @exception  ScriptLoopException вызывается, если указанный файл скрипта уже вызывался ранее. Необходимо прервать выполнение основного скрипта, т.к. игнорирование этого приведет к зависанию программы */
    public static void addScriptFileName(String fileName) throws ScriptLoopException {
        fileName = fileName.toLowerCase().trim();
        int count = scriptFileNames.size();
        scriptFileNames.add(fileName);
        if (count == scriptFileNames.size()) // если после добавления размер не изменился, значит такое значение уже было в справочнике
            throw new ScriptLoopException("Файл со скриптом уже выполнялся в рамках данной команды. Во избежание зацикливания, процесс выполнения скрипта был прерван в месте зацикливания.");
    }
}
