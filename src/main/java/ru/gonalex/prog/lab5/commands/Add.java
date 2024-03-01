package ru.gonalex.prog.lab5.commands;

import ru.gonalex.prog.lab5.manage.CommandParams;
import ru.gonalex.prog.lab5.manage.CommandResult;
import ru.gonalex.prog.lab5.manage.CommandManipulation;
import ru.gonalex.prog.lab5.models.Flat;

/**
 * Класс, реализующий команду [add] - добавить новый элемент в коллекцию
 * @author gonalex
 * @version 1.0
 */
public class Add extends CommandManipulation {
    public Add() {
        super("add", "добавить новый элемент в коллекцию", "должны вводиться по одному полю в строку.");
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

        params.realtor.addFlat(flat);
        return new CommandResult("Объект добавлен в коллекцию.");
    }
}