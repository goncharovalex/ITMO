package ru.gonalex.prog.lab5.commands;

import ru.gonalex.prog.lab5.manage.CommandExecuteResult;
import ru.gonalex.prog.lab5.manage.RealtorCommandParams;
import ru.gonalex.prog.lab5.models.Flat;

/**
 * Класс, реализующий команду [add_if_min] - добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции
 * @author gonalex
 * @version 1.0
 */
public class AddIfMin extends Manipulation {
    public AddIfMin() {
        super("add_if_min",
                "добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции",
                "Сначала вводим инфу по объекту, затем он добавляется в коллекцию по критерию house.numberOfFloors");
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
        if (flat == null) return new CommandExecuteResult("Нет объекта для добавления в коллекцию");

        boolean result = params.realtor.addIfMinFlat(flat);
        return new CommandExecuteResult(result ? "Объект добавлен в коллекцию." : "Объект не был добавлен в коллекцию, т.к. не удовлетворяет требованию");
    }
}
