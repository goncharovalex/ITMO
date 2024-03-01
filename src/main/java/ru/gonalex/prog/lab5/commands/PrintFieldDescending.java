package ru.gonalex.prog.lab5.commands;

import ru.gonalex.prog.lab5.manage.Command;
import ru.gonalex.prog.lab5.manage.CommandParams;
import ru.gonalex.prog.lab5.manage.CommandResult;
import ru.gonalex.prog.lab5.manage.CommandManipulation;
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
        setHelp(String.format("Параметр команды - элемент перечисления Transport [%s]", CommandManipulation.getTransportValuesAsString()));
        params.isSubjectManipulation = true;
    }

    /**
     * @see ru.gonalex.prog.lab5.manage.Command#execute(CommandParams params)
     * */
    @Override
    public CommandResult execute(CommandParams params) {
        if(params.realtor == null || params.realtor.getCollectionSize() == 0)
            return new CommandResult("Нет данных для обработки");

        ArrayList<Flat> sortedData = params.realtor.sortByTransportDescending();
        ArrayList<String> lst = new ArrayList<>();
        for(Flat flat : sortedData) {
            lst.add(flat.getTransport() == null ? "" : flat.getTransport().toString()); // выводим пустые строки, если Transport не указан
            //if(flat.getTransport() != null) lst.add(flat.getTransport().toString()); // не выводим пустые строки, если Transport не указан
        }
        return new CommandResult(lst);
    }
}
