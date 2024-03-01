package ru.gonalex.prog.lab5.commands;

import ru.gonalex.prog.lab5.exceptions.ScriptLoopException;
import ru.gonalex.prog.lab5.exceptions.TemporaryDataFileException;
import ru.gonalex.prog.lab5.manage.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Класс, реализующий команду [execute_script] - считать и исполнить скрипт из указанного файла
 * @author gonalex
 * @version 1.1
 */
public class ExecuteScript extends Command {
    /** наименование (имя файла) текущего выполняемого скрипта */
    private String currentScriptName = "";

    public ExecuteScript() {
        super("execute_script", "считать и исполнить скрипт из указанного файла");
        setHelp("В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме");
        params.isSubjectManipulation = true;
    }

    /**
     * @see ru.gonalex.prog.lab5.manage.Command#execute(CommandParams params)
     * */
    @Override
    public CommandResult execute(CommandParams params) {
        TroubleWatcher.clear();
        if(params.params == null || params.params.isEmpty()) {
            TroubleWatcher.putProblem("Файл со скриптом не указан в качестве параметра. Повторите ввод команды.");
            return new CommandResult(TroubleWatcher.getProblem());
        }

        if (TroubleWatcher.isCurrentScriptName(params.params)) {
            TroubleWatcher.putProblem(ScriptLoopException.defaultErrorMessage);
            return new CommandResult(TroubleWatcher.getProblem());
        }
        else {
            TroubleWatcher.setCurrentScriptName(params.params);
            this.currentScriptName = TroubleWatcher.getCurrentScriptName();
        }

        // проверка на зацикливание скрипта, чтобы скрипт не вызывал сам себя
        try {
            TroubleWatcher.addScriptFileName(params.params);
        }
        catch (ScriptLoopException ex) {
            TroubleWatcher.putProblem(ex.getMessage());
            return new CommandResult(TroubleWatcher.getProblem());
        }

        Path path = Paths.get(params.params);
        var file = path.toFile();

        if (!file.exists()) TroubleWatcher.putProblem(String.format("Файла [%s] не существует", params.params));
        else if (!file.isFile()) TroubleWatcher.putProblem("Вы указали путь к файлу, а не имя файла");
        else if (!file.canRead()) TroubleWatcher.putProblem("Нет доступа к файлу");
        if (TroubleWatcher.hasProblem()) return new CommandResult(TroubleWatcher.getProblem());

        // читаем файл согласно ТЗ через Scanner
        // файл читаем сразу весь
        ArrayList<String> commandsList = new ArrayList<String>();
        try {
            path = Paths.get(params.params);
            Scanner scanner = new Scanner(path);
            scanner.useDelimiter(System.getProperty("line.separator"));
            while (scanner.hasNext()) { //построчно считываем файл
                String text = scanner.next().trim();
                commandsList.add(text);
            }
            scanner.close();
        }
        catch (IOException ex) {
            TroubleWatcher.putProblem("Ошибка чтения файла: " + ex.getMessage());
            return new CommandResult(TroubleWatcher.getProblem());
        }
        catch (Exception ex) {
            TroubleWatcher.putProblem("Ошибка чтения файла: " + ex.getMessage());
            return new CommandResult(TroubleWatcher.getProblem());
        }

        // выполняем команды из файла
        var orchestrator = new OrchestratorScript(params.realtor, params.fileNameJson);
        var line = 1;
        for(String cmd : commandsList) {
            orchestrator.run(cmd);
            if (TroubleWatcher.hasProblem()) break;
            TroubleWatcher.setCurrentScriptName(params.params); // при рекурсивном вызове скриптов, при выходе на уровень вверх, текущее имя файла исполняемого скрипта должно поменяться в TroubleWatcher
            line++;
        }
        // если проблем не было при выполнении скрипта, то сохраняем временную копию файла коллекции
        if (!TroubleWatcher.hasProblem()) {
            var rcp = new CommandParams();
            rcp.realtor = params.realtor;
            try {
                orchestrator.localTempCopy.save(rcp);
            }
            catch (TemporaryDataFileException ex) {
                TroubleWatcher.putProblem(ex.getMessage());
            }
        }

        return new CommandResult(TroubleWatcher.hasProblem()
                ? "Выполнение скрипта прервано: " + TroubleWatcher.getProblem() + String.format(" [Строка %s]", line)
                : "Скрипт выполнен.");
    }
}
