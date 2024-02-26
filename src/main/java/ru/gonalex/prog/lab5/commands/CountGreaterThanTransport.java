package ru.gonalex.prog.lab5.commands;

import ru.gonalex.prog.lab5.enums.Transport;
import ru.gonalex.prog.lab5.manage.Command;
import ru.gonalex.prog.lab5.manage.CommandExecuteResult;
import ru.gonalex.prog.lab5.manage.RealtorCommandParams;
import ru.gonalex.prog.lab5.manage.TroubleWatcher;

/**
 * Класс, реализующий команду [count_greater_than_transport] - вывести количество элементов, значение поля transport которых больше заданного
 * @author gonalex
 * @version 1.0
 */
public class CountGreaterThanTransport extends Command {
    public CountGreaterThanTransport() {
        super("count_greater_than_transport", "вывести количество элементов, значение поля transport которых больше заданного");
        setHelp(String.format("Параметр команды - элемент перечисления Transport [%s]", Manipulation.getTransportValuesAsString()));
        params.isSubjectManipulation = true;
    }

    /**
     * @see ru.gonalex.prog.lab5.manage.Command#execute(RealtorCommandParams params)
     * */
    @Override
    public CommandExecuteResult execute(RealtorCommandParams params) {
        TroubleWatcher.clear();
        if(params.params == null || params.params.isEmpty()) {
            TroubleWatcher.putProblem(String.format("Параметр не указан. Требуется одно из значений [%s]", Manipulation.getTransportValuesAsString()));
            return new CommandExecuteResult(TroubleWatcher.getProblem());
        }

        Transport t;
        try {
            t = Transport.valueOf(params.params.toUpperCase());
        }
        catch(Exception ex) {
            TroubleWatcher.putProblem(String.format("Параметр неверный. Ожидается [%s]", Manipulation.getTransportValuesAsString()));
            return new CommandExecuteResult(TroubleWatcher.getProblem());
        }

        var filterData = params.realtor.filterGreaterThanTransport(t);
        return new CommandExecuteResult(String.valueOf(filterData.size()));
    }
}
