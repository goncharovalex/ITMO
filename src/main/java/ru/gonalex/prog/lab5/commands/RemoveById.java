package ru.gonalex.prog.lab5.commands;

import ru.gonalex.prog.lab5.manage.Command;
import ru.gonalex.prog.lab5.manage.CommandExecuteResult;
import ru.gonalex.prog.lab5.manage.RealtorCommandParams;
import ru.gonalex.prog.lab5.manage.TroubleWatcher;

import static java.lang.Long.parseLong;

/**
 * Класс, реализующий команду [remove_by_id] - удалить элемент из коллекции
 * @author gonalex
 * @version 1.0
 */
public class RemoveById extends Manipulation {
    public RemoveById() {
        super("remove_by_id", "удалить элемент из коллекции", "remove_by_id id, где id - уникальный номер элемента коллекции");
        params.isSubjectManipulation = true;
    }

    /**
     * @see ru.gonalex.prog.lab5.manage.Command#execute(RealtorCommandParams params)
     * */
    @Override
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
            params.realtor.removeFlat(flat);
            cer.add(String.format("Элемент с id=%s удален из коллекции", id));
        }
        catch (NumberFormatException ex) {
            TroubleWatcher.putProblem(Command.get_MSG_NO_NUMERIC_VALUE());
            cer.add(TroubleWatcher.getProblem());
        }
        return cer;
    }
}
