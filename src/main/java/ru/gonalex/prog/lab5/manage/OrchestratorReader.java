package ru.gonalex.prog.lab5.manage;

import java.util.Scanner;
import ru.gonalex.prog.lab5.exceptions.UnknownOrchestratorScannerException;

/**
 * Реализует чтение данных (команд и полей объектов) для оркестраторов как в интерактивном режиме, там и в скриптовом режиме.
 * @author gonalex
 * @version 1.1
 */
public class OrchestratorReader {

    /** Вызывающий экземпляр ридера оркестратор  */
    private Orchestrator orchestrator;

    /** ссылка на команду, которая в данный момент предлагает ввод значений полей  */
    private Command commandFieldsManipulation;

    /** использовать ли System.out для вывода сообщений */
    private boolean useSystemOut;

    /** Конструктор класса
     * @param orchestrator вызывающий данный ридер оркестратор */
    public OrchestratorReader(Orchestrator orchestrator)
    {
        this.orchestrator = orchestrator;
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
                command = ((Scanner) scanner).nextLine().trim(); // ввод команды пользователем в терминале
            }
            else if(scanner.getClass().getName().endsWith("String"))
                command = scanner.toString(); // передача команды из строки (скрипта)
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
                            // данные все введены, передаем данные риэлтору
                            CommandParams cp = new CommandParams();
                            cp.realtor = orchestrator.realtor;
                            cp.params = Orchestrator.parseParams(command);

                            var result = commandFieldsManipulation.executeComplete(cp);

                            // сохраняем временную копию данных
                            if (orchestrator.realtor.getIsDataModifired())
                                orchestrator.localTempCopy.save(cp);

                            // выводим результат выполнения команды
                            result.multilineText.forEach((str) -> {
                                if (this.useSystemOut) System.out.println(str);
                            });
                            // и переходим в режим ввода команд
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

                    parsedCommand = Orchestrator.parseCommand(orchestrator.commands, command);
                    if (parsedCommand.isEmpty()) {
                        if (this.useSystemOut) System.out.println(String.format("Нет такой команды [%s]", command));
                        TroubleWatcher.putProblem(String.format("Команды [%s] не существует", command));
                        continue;
                    }

                    if (orchestrator.commands.get(parsedCommand).params.isExit) break;

                    CommandResult result = new CommandResult();

                    if (orchestrator.commands.get(parsedCommand).params.isNoParams) {
                        if (this.useSystemOut)
                            System.out.println(orchestrator.commands.get(parsedCommand).toString()); // информация о набранной команде
                        result = orchestrator.commands.get(parsedCommand).execute(null);
                    } else if (orchestrator.commands.get(parsedCommand).params.isSubjectManipulation) {
                        CommandParams cp = new CommandParams();
                        cp.realtor = orchestrator.realtor;

                        // если это команда сохранения данных, то возможно указано новое имя файла
                        if(orchestrator.commands.get(parsedCommand).params.isFileName) {
                            // если это команда сохранения данных, то возможно указано новое имя файла
                            var fileName = parsedCommand == Command.getSaveCommandName() ? Orchestrator.parseParams(command) : orchestrator.fileNameJson;
                            if (fileName.isEmpty()) fileName = orchestrator.fileNameJson;
                            cp.params = cp.fileNameJson = orchestrator.fileNameJson = fileName; // и переключаемся на новый файл данных
                        }
                        else {
                            cp.params = Orchestrator.parseParams(command);
                            cp.fileNameJson = orchestrator.fileNameJson;
                        }
                        if (this.useSystemOut)
                            System.out.println(orchestrator.commands.get(parsedCommand).toString()); // информация о набранной команде
                        result = orchestrator.commands.get(parsedCommand).execute(cp);
                    }

                    // если команда подразумевает манипулирование с объектом, то сохраняем временную копию данных
                    if (orchestrator.commands.get(parsedCommand).params.isSubjectManipulation && orchestrator.realtor.getIsDataModifired()) {
                        CommandParams cp = new CommandParams();
                        cp.realtor = orchestrator.realtor;
                        orchestrator.localTempCopy.save(cp);
                    }

                    // если необходимо вводить значения полей и ранее не было ошибок, то...
                    if (orchestrator.commands.get(parsedCommand).params.isFieldsManipulation && result.isEmpty()) {
                        commandFieldsManipulation = orchestrator.commands.get(parsedCommand);
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
