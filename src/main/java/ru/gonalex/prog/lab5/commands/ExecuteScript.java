package ru.gonalex.prog.lab5.commands;

import ru.gonalex.prog.lab5.exceptions.ScriptLoopException;
import ru.gonalex.prog.lab5.manage.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Класс, реализующий команду [execute_script] - считать и исполнить скрипт из указанного файла
 * @author gonalex
 * @version 1.0
 */
public class ExecuteScript extends Command {
    public ExecuteScript() {
        super("execute_script", "считать и исполнить скрипт из указанного файла");
        setHelp("В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме");
        params.isSubjectManipulation = true;
    }

    /**
     * @see ru.gonalex.prog.lab5.manage.Command#execute(RealtorCommandParams params)
     * */
    @Override
    public CommandExecuteResult execute(RealtorCommandParams params) {
        TroubleWatcher.clear();
        if(params.params == null || params.params.isEmpty()) {
            TroubleWatcher.putProblem("Файл со скриптом не указан в качестве параметра. Повторите ввод команды.");
            return new CommandExecuteResult(TroubleWatcher.getProblem());
        }

        // проверка на зацикливание скрипта, чтобы скрипт не вызывал сам себя
        try {
            TroubleWatcher.addScriptFileName(params.params);
        }
        catch (ScriptLoopException ex) {
            TroubleWatcher.putProblem(ex.getMessage());
            return new CommandExecuteResult(TroubleWatcher.getProblem());
        }

        Path path = Paths.get(params.params);
        var file = path.toFile();

        //File file = new File(params.params);
        if (!file.exists()) TroubleWatcher.putProblem(String.format("Файла [%s] не существует", params.params));
        else if (!file.isFile()) TroubleWatcher.putProblem("Вы указали путь к файлу, а не имя файла");
        else if (!file.canRead()) TroubleWatcher.putProblem("Нет доступа к файлу");
        if (TroubleWatcher.hasProblem()) return new CommandExecuteResult(TroubleWatcher.getProblem());

        // читаем файл согласно ТЗ через Scanner
        // файл читаем сразу весь
        ArrayList<String> commandsList = new ArrayList<String>();
        try {
            path = Paths.get(params.params);
            Scanner scanner = new Scanner(path);
            scanner.useDelimiter(System.getProperty("line.separator"));
            while (scanner.hasNext()) { //построчно считываем файл
                String text = scanner.next().trim();
                //if (!text.equals(""))
                    commandsList.add(text);
            }
            scanner.close();
        }
        catch (IOException ex) {
            TroubleWatcher.putProblem("Ошибка чтения файла: " + ex.getMessage());
            return new CommandExecuteResult(TroubleWatcher.getProblem());
        }
        catch (Exception ex) {
            TroubleWatcher.putProblem("Ошибка чтения файла: " + ex.getMessage());
            return new CommandExecuteResult(TroubleWatcher.getProblem());
        }

        // выполняем команды из файла
        var orchestrator = new OrchestratorScript(params.realtor, params.fileNameJson);
        var line = 1;
        for(String cmd : commandsList) {
            orchestrator.run(cmd);
            if (TroubleWatcher.hasProblem()) break;
            line++;
        }

        return new CommandExecuteResult(TroubleWatcher.hasProblem()
                ? "Выполнение скрипта прервано: " + TroubleWatcher.getProblem() + String.format(" [Строка %s]", line)
                : "Скрипт выполнен.");
    }
}
