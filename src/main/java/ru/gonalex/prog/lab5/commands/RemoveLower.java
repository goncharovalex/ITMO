package ru.gonalex.prog.lab5.commands;

import ru.gonalex.prog.lab5.manage.CommandExecuteResult;
import ru.gonalex.prog.lab5.manage.RealtorCommandParams;
import ru.gonalex.prog.lab5.models.Flat;

/**
 * Класс, реализующий команду [remove_lower] - удалить из коллекции все элементы, меньшие, чем заданный
 * @author gonalex
 * @version 1.0
 */
public class RemoveLower extends Manipulation {
    public RemoveLower() {
        super("remove_lower",
                "удалить из коллекции все элементы, меньшие, чем заданный",
                "Сначала вводим инфу по объекту, затем на основании house.numberOfFloors выполняется действие над коллекцией");
        params.isSubjectManipulation = true;
        params.isFieldsManipulation = true;
        params.isCompleteCommandNeeds = true;
    }

    /**
     * @see ru.gonalex.prog.lab5.manage.Command#execute(RealtorCommandParams params)
     * */
    public CommandExecuteResult execute(RealtorCommandParams params) { // параметр хоть и не используется, но так надо
        counter = 1;
        flat = new Flat();
        return new CommandExecuteResult();
    }

    /**
     * @see ru.gonalex.prog.lab5.manage.Command#executeComplete(RealtorCommandParams params)
     * */
    @Override
    public CommandExecuteResult executeComplete(RealtorCommandParams params) {
        if (flat == null) return new CommandExecuteResult("Нет объекта для сравнения по критерию");

        boolean result = params.realtor.removeLower(flat);
        return new CommandExecuteResult(result ? "Из коллекции удалены элементы согласно критерию" : "Коллекция не изменилась, т.к. нет элементов, удовлетворяющих критерию");
    }
}
