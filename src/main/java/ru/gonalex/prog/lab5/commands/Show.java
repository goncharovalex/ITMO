package ru.gonalex.prog.lab5.commands;

import ru.gonalex.prog.lab5.manage.Command;
import ru.gonalex.prog.lab5.manage.CommandExecuteResult;
import ru.gonalex.prog.lab5.manage.RealtorCommandParams;

/**
 * Класс, реализующий команду [show] - вывести все элементы коллекции
 * @author gonalex
 * @version 1.0
 */
public class Show extends Command {
    public Show() {
        super("show", "вывести все элементы коллекции");
        setHelp(super.get_MSG_NO_PARAMS_COMMAND());
        params.isSubjectManipulation = true;
    }

    /**
     * @see ru.gonalex.prog.lab5.manage.Command#execute(RealtorCommandParams params)
     * */
    @Override
    public CommandExecuteResult execute(RealtorCommandParams params) {
        return new CommandExecuteResult(params.realtor.allFlatsInfo());
    }
}
