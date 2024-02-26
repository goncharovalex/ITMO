package ru.gonalex.prog.lab5.commands;

import ru.gonalex.prog.lab5.manage.Command;
import ru.gonalex.prog.lab5.manage.CommandExecuteResult;
import ru.gonalex.prog.lab5.manage.RealtorCommandParams;
import ru.gonalex.prog.lab5.models.Flat;

import java.util.ArrayList;

/**
 * Класс, реализующий команду [print_field_descending] - вывести значения поля transport всех элементов в порядке убывания
 * @author gonalex
 * @version 1.0
 */
public class PrintFieldDescending extends Command {
    public PrintFieldDescending() {
        super("print_field_descending", "вывести значения поля transport всех элементов в порядке убывания");
        setHelp(String.format("Параметр команды - элемент перечисления Transport [%s]", Manipulation.getTransportValuesAsString()));
        params.isSubjectManipulation = true;
    }

    /**
     * @see ru.gonalex.prog.lab5.manage.Command#execute(RealtorCommandParams params)
     * */
    @Override
    public CommandExecuteResult execute(RealtorCommandParams params) {
        if(params.realtor == null || params.realtor.getCollectionSize() == 0)
            return new CommandExecuteResult("Нет данных для обработки");

        ArrayList<Flat> sortedData = params.realtor.sortByTransportDescending();
        ArrayList<String> lst = new ArrayList<>();
        for(Flat flat : sortedData) {
            lst.add(flat.getTransport() == null ? "" : flat.getTransport().toString()); // выводим пустые строки, если Transport не указан
            //if(flat.getTransport() != null) lst.add(flat.getTransport().toString()); // не выводим пустые строки, если Transport не указан
        }
        return new CommandExecuteResult(lst);
    }
}
