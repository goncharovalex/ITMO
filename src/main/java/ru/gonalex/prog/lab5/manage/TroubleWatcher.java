package ru.gonalex.prog.lab5.manage;

import ru.gonalex.prog.lab5.exceptions.ScriptLoopException;

import java.io.File;
import java.util.ArrayList;

/**
 * Класс предназначен для мониторинга выполнения скрипта с командами.
 * Фиксирует проблемы с данными и проблему с бесконечным зацикливанием скриптов, когда скрипты вызывают друг друга
 * @author gonalex
 * @version 1.1
 */
public class TroubleWatcher {

    /** произошла ошибка */
    private static boolean errorOccurred = false;

    /** описание проблемы */
    private static String description = "";

    /** наименование (имя файла) текущего выполняемого скрипта */
    private static String currentScriptName = "";

    /** список файлов с исполняемыми скриптами */
    private static ArrayList<String> scriptFileNames = new ArrayList<>();

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

    /** Указать наименование текущего исполняемого скрипта
     *
     * @param currentScriptFileName имя файла скрипта
     */
    public static void setCurrentScriptName(String currentScriptFileName) {
        File file = new File(currentScriptFileName);
        currentScriptFileName = file.getAbsolutePath().toLowerCase().trim();
        currentScriptName = currentScriptFileName;
    }

    /** Возворащает наименование текущего исполняемого скрипта
     *
     * @return полное имя файла скрипта
     */
    public static String getCurrentScriptName() {
        return currentScriptName;
    }

    /** Является ли переданное аргументом наименование скрипта ныне исполняющимся
     *
     * @param currentScriptFileName наименование файла скрипта
     * @return совпадают полные имена файлов (вместе с путем к ним) или нет
     */
    public static boolean isCurrentScriptName(String currentScriptFileName) {
        File file = new File(currentScriptFileName);
        currentScriptFileName = file.getAbsolutePath().toLowerCase().trim();
        return currentScriptName.equals(currentScriptFileName);
    }

    /** очистить список вызываемых скриптов */
    public static void clearScriptList() {
        currentScriptName = "";
        scriptFileNames.clear();
    }

    /** добавить имя файла вызываемого скрипта
     * @param fileName имя файла скрипта
     * @exception  ScriptLoopException вызывается, если указанный файл скрипта уже вызывался ранее. Необходимо прервать выполнение основного скрипта, т.к. игнорирование этого приведет к зависанию программы */
    public static void addScriptFileName(String fileName) throws ScriptLoopException {
        int count = scriptFileNames.size();

        File file = new File(fileName);
        fileName = file.getAbsolutePath().toLowerCase().trim();

        // если последний добавленный скрипт совпадает с указанным в качестве параметра метода, то это не зацикливание и вызов последовательными командами одного и того же скрипта
        if (count > 0) {
            if(scriptFileNames.get(count-1).equals(fileName)) return;
            if(scriptFileNames.contains(fileName))
                throw new ScriptLoopException(ScriptLoopException.defaultErrorMessage);
        }

        scriptFileNames.add(fileName);
    }
}
