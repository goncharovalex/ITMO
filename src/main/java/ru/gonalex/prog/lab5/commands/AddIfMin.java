package ru.gonalex.prog.lab5.commands;

import ru.gonalex.prog.lab5.manage.CommandResult;
import ru.gonalex.prog.lab5.manage.CommandManipulation;
import ru.gonalex.prog.lab5.manage.CommandParams;
import ru.gonalex.prog.lab5.models.Flat;

/**
 * Класс, реализующий команду [add_if_min] - добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции
 * @author gonalex
 * @version 1.0
 */
public class AddIfMin extends CommandManipulation {
    public AddIfMin() {
        super("add_if_min",
                "добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции",
                "Сначала вводим инфу по объекту, затем он добавляется в коллекцию по критерию house.numberOfFloors");
        params.isSubjectManipulation = true;
        params.isFieldsManipulation = true;
        params.isCompleteCommandNeeds = true;
    }

    /**
     * @see ru.gonalex.prog.lab5.manage.Command#execute(CommandParams params)
     * */
    public CommandResult execute(CommandParams params) { // параметр хоть и не используется, но так надо
        counter = 1;
        flat = new Flat();
        return new CommandResult();
    }

    /**
     * @see ru.gonalex.prog.lab5.manage.Command#executeComplete(CommandParams params)
     * */
    @Override
    public CommandResult executeComplete(CommandParams params) {
        if (flat == null) return new CommandResult("Нет объекта для добавления в коллекцию");

        boolean result = params.realtor.addIfMinFlat(flat);
        return new CommandResult(result ? "Объект добавлен в коллекцию." : "Объект не был добавлен в коллекцию, т.к. не удовлетворяет требованию");
    }
}
