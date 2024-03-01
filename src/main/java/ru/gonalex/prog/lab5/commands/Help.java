package ru.gonalex.prog.lab5.commands;

import ru.gonalex.prog.lab5.manage.Command;
import ru.gonalex.prog.lab5.manage.CommandParams;
import ru.gonalex.prog.lab5.manage.CommandResult;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Класс, реализующий команду [help] - вывести справку по доступным командам
 * @author gonalex
 * @version 1.0
 */
public class Help extends Command {
    private HashMap<String, Command> commands;
    public Help() {
        super("help", "вывести справку по доступным командам");
        setHelp(super.get_MSG_NO_PARAMS_COMMAND());
        params.isNoParams = true;
    }

    /**
     * для формирования справки по переданным командам в качестве параметра
     * @param commands - список команд
     * */
    public void putAllCommands(HashMap<String, Command> commands) {
        this.commands = commands;
    }

    /**
     * @see ru.gonalex.prog.lab5.manage.Command#execute(CommandParams params)
     * */
    @Override
    public CommandResult execute(CommandParams params) {
        ArrayList<String> hlp = new ArrayList<String>();
        commands.forEach((key, value) -> hlp.add(String.format("%s - %s [%s]", value.getName(), value.getDescription(), value.getHelp())));
        return new CommandResult(hlp);
    }
}
