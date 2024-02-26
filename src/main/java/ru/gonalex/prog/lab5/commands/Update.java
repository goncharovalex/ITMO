package ru.gonalex.prog.lab5.commands;

import ru.gonalex.prog.lab5.manage.Command;
import ru.gonalex.prog.lab5.manage.CommandExecuteResult;
import ru.gonalex.prog.lab5.manage.RealtorCommandParams;
import ru.gonalex.prog.lab5.manage.TroubleWatcher;

import static java.lang.Long.parseLong;

/**
 * Класс, реализующий команду [update] - обновить значение элемента коллекции
 * @author gonalex
 * @version 1.0
 */
public class Update extends Manipulation {
    public Update() {
        super("update", "обновить значение элемента коллекции", "update id, где id - уникальный номер элемента коллекции");
        params.isSubjectManipulation = true;
        params.isFieldsManipulation = true;
        params.isCompleteCommandNeeds = true;
    }

    /**
     * @see ru.gonalex.prog.lab5.manage.Command#execute(RealtorCommandParams params)
     * */
    public CommandExecuteResult execute(RealtorCommandParams params) {
        TroubleWatcher.clear();
        if(params.params.isEmpty()) {
            TroubleWatcher.putProblem("id записи не указан");
            return new CommandExecuteResult(TroubleWatcher.getProblem());
        }
        CommandExecuteResult cer = new CommandExecuteResult();
        long id;
        try {
            id = parseLong(params.params);

            flat = params.realtor.getFlatById(id);
            if (flat == null) {
                cer.add(String.format("Элемента с id=%s не существует", id));
                return cer;
            }
            counter = 1;
        }
        catch (NumberFormatException ex) {
            TroubleWatcher.putProblem(Command.get_MSG_NO_NUMERIC_VALUE());
            cer.add(TroubleWatcher.getProblem());
        }
        return cer;
    }

    /**
     * @see ru.gonalex.prog.lab5.manage.Command#executeComplete(RealtorCommandParams params)
     * */
    @Override
    public CommandExecuteResult executeComplete(RealtorCommandParams params) {
        if (flat == null) return new CommandExecuteResult("Нет объекта для обновления");

        params.realtor.postFlat(flat);
        return new CommandExecuteResult("Исправления внесены");
    }
}
