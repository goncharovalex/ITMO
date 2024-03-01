package ru.gonalex.prog.lab5.interfaces;

import ru.gonalex.prog.lab5.manage.CommandResult;
import ru.gonalex.prog.lab5.manage.CommandParams;

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
     * @param commandParams параметры для выполнения команды
     * @return Объект-оболочка с результатом выполнения команды
     */
    CommandResult execute(CommandParams commandParams);
}
