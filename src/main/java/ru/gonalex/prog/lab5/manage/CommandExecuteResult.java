package ru.gonalex.prog.lab5.manage;

import java.util.ArrayList;

/**
 * Класс, описывающий результат выполнения команды
 * @author gonalex
 * @version 1.0
 */
public class CommandExecuteResult {

    /** Текст с результатом выполнения команды */
    public ArrayList<String> multilineText;

    /** Конструктор по умолчанию */
    public CommandExecuteResult() {
        multilineText = new ArrayList<String>();
    }

    /** Конструктор, в качестве параметра которого передается ArrayList<String>  */
    public CommandExecuteResult(ArrayList<String> arrayList) {
        multilineText = arrayList;
    }

    /** Конструктор, в качестве параметра которого передается String  */
    public CommandExecuteResult(String text) {
        this();
        multilineText.add(text);
    }

    /** Есть ли текст
     * @return true|false  */
    public boolean isEmpty() { return multilineText.isEmpty(); }

    /** Добавить текст
     * @param text Текст */
    public void add(String text) { multilineText.add(text); }
}
