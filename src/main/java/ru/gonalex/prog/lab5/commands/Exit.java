package ru.gonalex.prog.lab5.commands;

import ru.gonalex.prog.lab5.manage.Command;

/**
 * Класс, реализующий команду [exit] - завершить программу (без сохранения в файл)
 * @author gonalex
 * @version 1.0
 */
public class Exit extends Command {

    public Exit() {
        super("exit", "завершить программу (без сохранения в файл)");
        setHelp(Command.get_MSG_NO_PARAMS_COMMAND());
        params.isExit = true;
        params.isNoParams = true;
    }
}
