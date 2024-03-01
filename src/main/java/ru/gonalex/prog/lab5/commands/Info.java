package ru.gonalex.prog.lab5.commands;

import ru.gonalex.prog.lab5.manage.Command;
import ru.gonalex.prog.lab5.manage.CommandParams;
import ru.gonalex.prog.lab5.manage.CommandResult;
import ru.gonalex.prog.lab5.manage.Utils;

import java.util.ArrayList;

/**
 * Класс, реализующий команду [info] - вывести информацию о коллекции
 * @author gonalex
 * @version 1.1
 */
public class Info extends Command {
    public Info() {
        super("info", "вывести информацию о коллекции");
        setHelp(super.get_MSG_NO_PARAMS_COMMAND());
        params.isSubjectManipulation = true;
    }

    /**
     * @see ru.gonalex.prog.lab5.manage.Command#execute(CommandParams params)
     * */
    @Override
    public CommandResult execute(CommandParams params) {
        var lst = new ArrayList<String>();

        if(params.realtor == null || params.realtor.getCollectionSize() == 0)
            lst.add("Коллекция не инициализирована");
        else {
            lst.add("Наименование: " + params.realtor.getCollectionName());
            lst.add("Тип: " + params.realtor.getCollectionType());
            lst.add("Количество элементов: " + params.realtor.getCollectionSize());
            lst.add("Дата инициализации: " + Utils.dateToString(params.realtor.getCreationDate()));
            lst.add("Файл данных: " + params.fileNameJson);
        }
        return new CommandResult(lst);
    }
}
