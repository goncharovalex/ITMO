package ru.gonalex.prog.lab5.manage;

import ru.gonalex.prog.lab5.models.Realtor;
import java.util.HashMap;
import java.util.Scanner;
import ru.gonalex.prog.lab5.exceptions.UnknownOrchestratorScannerException;

/**
 * Реализует чтение данных (команд и полей объектов) для оркестраторов как в интерактивном режиме, там и в скриптовом режиме.
 * @author gonalex
 * @version 1.0
 */
public class OrchestratorReader {

    private final HashMap<String, Command> commands;
    private final Realtor realtor;
    private final String fileNameJson;

    /** ссылка на команду, которая в данный момент предлагает ввод значений полей  */
    private Command commandFieldsManipulation;

    /** использовать ли System.out для вывода сообщений */
    private boolean useSystemOut;

    /** Конструктов класса
     * @param commands список команд для обработки данных
     * @param realtor коллекция для обработки
     * @param fileNameJson  имя файла с данными коллекции в формате Json. Требуется для выполнения команды Save */
    public OrchestratorReader(HashMap<String, Command> commands, Realtor realtor, String fileNameJson)
    {
        this.commands = commands;
        this.realtor = realtor;
        this.fileNameJson = fileNameJson;
    }

    /** Осуществляем чтение команды и данных полей объектов
     * @param scanner - откуда происходит чтение команды и данных. Объект Scanner (для консоли) или String (для скриптов)
     * @exception UnknownOrchestratorScannerException если переданный параметр scanner Не Scanner и не String  */
    public void read(Object scanner) throws UnknownOrchestratorScannerException {
        String command = "";
        String parsedCommand = "";
        useSystemOut = false;

        do {
            if(scanner.getClass().getName().endsWith("Scanner")) {
                // в режиме консоли надо почистить TroubleWatcher, чтобы накопленные ошибки по execute_script не приводили к проблеме повторного выполнения execute_script
                TroubleWatcher.clear();
                TroubleWatcher.clearScriptList();

                useSystemOut = true;
                command = ((Scanner) scanner).nextLine().trim();
            }
            else if(scanner.getClass().getName().endsWith("String"))
                command = scanner.toString();
            else {
                throw new UnknownOrchestratorScannerException("Указанный режим получения данных не поддерживается");
            }

            try {
                if (commandFieldsManipulation != null) {
                    var msg = commandFieldsManipulation.setTypingData(command);
                    if (msg.isEmpty()) {
                        // переходим к вводу следующих данных
                        String text = commandFieldsManipulation.getTypingDataDescription();
                        if (text.isEmpty()) {
                            // данные все введены, передаем данные риэтору
                            RealtorCommandParams rcp = new RealtorCommandParams();
                            rcp.realtor = realtor;
                            rcp.params = Orchestrator.parseParams(command);

                            var result = commandFieldsManipulation.executeComplete(rcp);
                            result.multilineText.forEach((str) -> {
                                if (this.useSystemOut) System.out.println(str);
                            });
                            // и переходим в режим ввода комманд
                            commandFieldsManipulation = null;
                        } else {
                            if (this.useSystemOut) System.out.print(text);
                        }
                    } else {
                        if (this.useSystemOut) System.out.print(msg);
                    }
                } else {
                    if (command.isEmpty()) {
                        if (this.useSystemOut) System.out.println("Введите команду");
                        continue;
                    }

                    parsedCommand = Orchestrator.parseCommand(commands, command);
                    if (parsedCommand.isEmpty()) {
                        if (this.useSystemOut) System.out.println(String.format("Нет такой команды [%s]", command));
                        TroubleWatcher.putProblem(String.format("Команды [%s] не существует", command));
                        continue;
                    }

                    if (commands.get(parsedCommand).params.isExit) break;

                    CommandExecuteResult result = new CommandExecuteResult();

                    if (commands.get(parsedCommand).params.isNoParams) {
                        if (this.useSystemOut)
                            System.out.println(commands.get(parsedCommand).toString()); // информация о набранной команде
                        result = commands.get(parsedCommand).execute(null);
                    } else if (commands.get(parsedCommand).params.isSubjectManipulation) {
                        RealtorCommandParams rcp = new RealtorCommandParams();
                        rcp.realtor = realtor;
                        rcp.params = commands.get(parsedCommand).params.isFileName ? fileNameJson : Orchestrator.parseParams(command);
                        rcp.fileNameJson = this.fileNameJson;
                        if (this.useSystemOut)
                            System.out.println(commands.get(parsedCommand).toString()); // информация о набранной команде
                        result = commands.get(parsedCommand).execute(rcp);
                    }

                    // если необходимо вводить значения полей и ранее не было ошибок, то...
                    if (commands.get(parsedCommand).params.isFieldsManipulation && result.isEmpty()) {
                        commandFieldsManipulation = commands.get(parsedCommand);
                        // предлагаем ввод первого значения
                        if (!commandFieldsManipulation.getTypingDataDescription().isEmpty()) {
                            if (useSystemOut) System.out.print(commandFieldsManipulation.getTypingDataDescription());
                        }
                    } else result.multilineText.forEach((str) -> {
                        if (this.useSystemOut) System.out.println(str);
                    });
                }
            }
            catch (Exception ex) {
                if (useSystemOut) System.out.println("Ошибка выполнения команды: " + ex.getMessage() + " Давайте попробуем ещё раз");
                else TroubleWatcher.putProblem("Ошибка выполнения команды: " + ex.getMessage() );
            }
        } while(useSystemOut);
    }
}
