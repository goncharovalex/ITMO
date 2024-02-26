package ru.gonalex.prog.lab5.commands;

import ru.gonalex.prog.lab5.manage.Command;
import ru.gonalex.prog.lab5.manage.CommandExecuteResult;
import ru.gonalex.prog.lab5.manage.RealtorCommandParams;

/**
 * Класс, реализующий команду [clear] - очистить коллекцию
 * @author gonalex
 * @version 1.0
 */
public class Clear extends Manipulation {
    public Clear() {
        super("clear", "очистить коллекцию", Command.get_MSG_NO_PARAMS_COMMAND());
        params.isSubjectManipulation = true;
    }

    /**
     * @see ru.gonalex.prog.lab5.manage.Command#execute(RealtorCommandParams params)
     * */
    @Override
    public CommandExecuteResult execute(RealtorCommandParams params) {
        params.realtor.clearAll();
        return new CommandExecuteResult("Удалены все элементы коллекции");
    }
}
