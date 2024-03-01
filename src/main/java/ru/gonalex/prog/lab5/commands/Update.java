package ru.gonalex.prog.lab5.commands;

import ru.gonalex.prog.lab5.manage.*;

import static java.lang.Long.parseLong;

/**
 * Класс, реализующий команду [update] - обновить значение элемента коллекции
 * @author gonalex
 * @version 1.0
 */
public class Update extends CommandManipulation {
    public Update() {
        super("update", "обновить значение элемента коллекции", "update id, где id - уникальный номер элемента коллекции");
        params.isSubjectManipulation = true;
        params.isFieldsManipulation = true;
        params.isCompleteCommandNeeds = true;
    }

    /**
     * @see ru.gonalex.prog.lab5.manage.Command#execute(CommandParams params)
     * */
    public CommandResult execute(CommandParams params) {
        TroubleWatcher.clear();
        if(params.params.isEmpty()) {
            TroubleWatcher.putProblem("id записи не указан");
            return new CommandResult(TroubleWatcher.getProblem());
        }
        CommandResult cer = new CommandResult();
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
     * @see ru.gonalex.prog.lab5.manage.Command#executeComplete(CommandParams params)
     * */
    @Override
    public CommandResult executeComplete(CommandParams params) {
        if (flat == null) return new CommandResult("Нет объекта для обновления");

        params.realtor.postFlat(flat);
        return new CommandResult("Исправления внесены");
    }
}
