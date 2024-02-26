package ru.gonalex.prog.lab5.manage;

import ru.gonalex.prog.lab5.commands.*;
import ru.gonalex.prog.lab5.exceptions.NoCommandParamException;
import ru.gonalex.prog.lab5.models.Realtor;

import java.util.HashMap;

/**
 * Абстрактный класс Оркестратор, осуществляющий управлением исполнения команд и взаимодействием с пользователем
 * или с другим вызывающим сервисом
 * @author gonalex
 * @version 1.0
 */
public abstract class Orchestrator {

    public Realtor realtor;
    public HashMap<String, Command> commands = new HashMap<>();

    public Orchestrator(Realtor realtor) {
        this.realtor = realtor;
        init();
    }

    /** Производит регистрацию команды
     * @param command - "объект команда" */
    protected void registerCommand(Command command) {
        commands.put(command.getName(), command);
    }

    /** Инициализация всех команд */
    protected void init() {
        registerCommand(new Exit());
        registerCommand(new Add());
        registerCommand(new AddIfMin());
        registerCommand(new Info());
        registerCommand(new Show());
        registerCommand(new Update());
        registerCommand(new RemoveById());
        registerCommand(new RemoveGreater());
        registerCommand(new RemoveLower());
        registerCommand(new CountGreaterThanTransport());
        registerCommand(new FilterLessThanView());
        registerCommand(new PrintFieldDescending());
        registerCommand(new ExecuteScript());
        registerCommand(new Clear());
        registerCommand(new Save());
        registerCommand(new Help());

        Command command = new Help(); // эту команду добавляем последней
        commands.put(command.getName(), command);
        ((Help)commands.get(command.getName())).putAllCommands(commands);
    }

    /** запуск оркестратора */
    public void run() { }

    /** распарсить команду из строки
     * @param command текст с командой
     * @return обнаруженная команда или пусто, если команду распознать не удалось
     * */
    public String parseCommand(String command) {
        if(command == null || command.trim().isEmpty()) return "";
        var parsedCmd = new Object() {
            String text = "";
        };

        String command0 = command.trim().split(" ")[0];
        commands.forEach((key, value) -> {
            if (command0.toLowerCase().startsWith(key) && command0.length() == key.length()) {
                parsedCmd.text = key;
            }
        });
        return parsedCmd.text;
    }

    /** распарсить команду из строки на основании переданного списка команд в качестве параметра
     * @param command текст с командой
     * @param commands перечень команд
     * @return обнаруженная команда или пусто, если команду распознать не удалось
     * */
    public static String parseCommand(HashMap<String, Command> commands, String command) {
        if(command == null || command.trim().isEmpty()) return "";
        var parsedCmd = new Object() {
            String text = "";
        };

        String command0 = command.trim().split(" ")[0];
        commands.forEach((key, value) -> {
            if (command0.toLowerCase().startsWith(key) && command0.length() == key.length()) {
                parsedCmd.text = key;
            }
        });
        return parsedCmd.text;
    }

    /** распарсить параметры команды
     * @param command текст с командой
     * @return обнаруженные параметры или пусто, если параметров нет
     * */
    public static String parseParams(String command) {
        try {
            return Command.getCommandParam(command);
        } catch (NoCommandParamException e) {
            return "";
        }
    }
}
