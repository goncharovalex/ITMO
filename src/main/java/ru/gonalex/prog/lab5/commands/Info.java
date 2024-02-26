package ru.gonalex.prog.lab5.commands;

import ru.gonalex.prog.lab5.manage.Command;
import ru.gonalex.prog.lab5.manage.CommandExecuteResult;
import ru.gonalex.prog.lab5.manage.RealtorCommandParams;
import ru.gonalex.prog.lab5.manage.Utils;

import java.util.ArrayList;

/**
 * Класс, реализующий команду [info] - вывести информацию о коллекции
 * @author gonalex
 * @version 1.0
 */
public class Info extends Command {
    public Info() {
        super("info", "вывести информацию о коллекции");
        setHelp(super.get_MSG_NO_PARAMS_COMMAND());
        params.isSubjectManipulation = true;
    }

    /**
     * @see ru.gonalex.prog.lab5.manage.Command#execute(RealtorCommandParams params)
     * */
    @Override
    public CommandExecuteResult execute(RealtorCommandParams params) {
        var lst = new ArrayList<String>();

        if(params.realtor == null || params.realtor.getCollectionSize() == 0)
            lst.add("Коллекция не инициализирована");
        else {
            lst.add("Наименование: " + params.realtor.getCollectionName());
            lst.add("Тип: " + params.realtor.getCollectionType());
            lst.add("Количество элементов: " + params.realtor.getCollectionSize());
            lst.add("Дата инициализации: " + Utils.dateToString(params.realtor.getCreationDate()));
        }
        return new CommandExecuteResult(lst);
    }
}
