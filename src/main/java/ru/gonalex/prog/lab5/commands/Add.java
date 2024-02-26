package ru.gonalex.prog.lab5.commands;

import ru.gonalex.prog.lab5.manage.CommandExecuteResult;
import ru.gonalex.prog.lab5.manage.RealtorCommandParams;
import ru.gonalex.prog.lab5.models.Flat;

/**
 * Класс, реализующий команду [add] - добавить новый элемент в коллекцию
 * @author gonalex
 * @version 1.0
 */
public class Add extends Manipulation {
    public Add() {
        super("add", "добавить новый элемент в коллекцию", "должны вводиться по одному полю в строку.");
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

        params.realtor.addFlat(flat);
        return new CommandExecuteResult("Объект добавлен в коллекцию.");
    }
}