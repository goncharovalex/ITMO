package ru.gonalex.prog.lab5.interfaces;

import ru.gonalex.prog.lab5.manage.CommandExecuteResult;
import ru.gonalex.prog.lab5.manage.RealtorCommandParams;

/**
 * Интерфейс описывает необходимые методы взаимодействия с командой
 * @author gonalex
 * @version 1.0
 */
public interface CommandDescriptionInterface {
    /** Получить имя команды */
    String getName();

    /** Получить описание команды */
    String getDescription();

    /** Получить справку по команде */
    String getHelp();


    /**
     * Выполнить команду
     * @param realtorCommandParams параметры для выполнения команды
     * @return Объект-оболочка с результатом выполнения команды
     */
    CommandExecuteResult execute(RealtorCommandParams realtorCommandParams);
}
