package ru.gonalex.prog.lab5.commands;

import ru.gonalex.prog.lab5.enums.View;
import ru.gonalex.prog.lab5.manage.*;
import ru.gonalex.prog.lab5.models.Realtor;

/**
 * Класс, реализующий команду [filter_less_than_view] - вывести элементы, значение поля view которых меньше заданного
 * @author gonalex
 * @version 1.0
 */
public class FilterLessThanView extends Command {
    public FilterLessThanView() {
        super("filter_less_than_view", "вывести элементы, значение поля view которых меньше заданного");
        setHelp(String.format("Параметр команды - элемент перечисления View [%s]", CommandManipulation.getViewValuesAsString()));
        params.isSubjectManipulation = true;
    }

    /**
     * @see ru.gonalex.prog.lab5.manage.Command#execute(CommandParams params)
     * */
    @Override
    public CommandResult execute(CommandParams params) {
        TroubleWatcher.clear();
        if(params.params == null || params.params.isEmpty()) {
            TroubleWatcher.putProblem(String.format("Параметр не указан. Требуется одно из значений [%s]", CommandManipulation.getViewValuesAsString()));
            return new CommandResult(TroubleWatcher.getProblem());
        }

        View v;
        try {
            v = View.valueOf(params.params.toUpperCase());
        }
        catch(Exception ex) {
            TroubleWatcher.putProblem(String.format("Параметр неверный. Ожидается [%s]", CommandManipulation.getViewValuesAsString()));
            return new CommandResult(TroubleWatcher.getProblem());
        }

        var filterData = params.realtor.filterLessThanView(v);
        var result = Realtor.allFlatsInfo(filterData);
        return new CommandResult(result);
    }
}
