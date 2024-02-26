package ru.gonalex.prog.lab5.commands;

import ru.gonalex.prog.lab5.manage.*;

import java.io.IOException;

/**
 * Класс, реализующий команду [save] - сохранить коллекцию в файл
 * @author gonalex
 * @version 1.0
 */
public class Save extends Manipulation {
    public Save() {
        super("save", "сохранить коллекцию в файл", Command.get_MSG_NO_PARAMS_COMMAND());
        params.isSubjectManipulation = true;
        params.isFileName = true;
    }

    /**
     * @see ru.gonalex.prog.lab5.manage.Command#execute(RealtorCommandParams params)
     * */
    @Override
    public CommandExecuteResult execute(RealtorCommandParams params) {
        TroubleWatcher.clear();
        if (params.params.isEmpty()) {
            TroubleWatcher.putProblem("Не указано имя файла");
            return new CommandExecuteResult(TroubleWatcher.getProblem());
        }
        DataProviderJSON provider = new DataProviderJSON(params.params);
        try {
            provider.write(params.realtor.getFlats());
        } catch (IOException ex) {
            TroubleWatcher.putProblem("Ошибка записи коллекции в файл: " + ex.getMessage());
            return new CommandExecuteResult(TroubleWatcher.getProblem());
        }
        return new CommandExecuteResult("Коллекция сохранена");
    }
}
