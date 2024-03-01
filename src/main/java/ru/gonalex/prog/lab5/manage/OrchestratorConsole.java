package ru.gonalex.prog.lab5.manage;

import ru.gonalex.prog.lab5.exceptions.TemporaryDataFileException;
import ru.gonalex.prog.lab5.exceptions.UnknownOrchestratorScannerException;
import ru.gonalex.prog.lab5.models.Realtor;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Оркестратор для управления коллекцией квартир путем ввода команд в консольном режиме с интерактивным взаимодействием с пользователем
 * @author gonalex
 * @version 1.1
 */
public class OrchestratorConsole extends Orchestrator {
    private final Scanner scanner;

    public OrchestratorConsole() {
        super(new Realtor());
        scanner = new Scanner(System.in);
    }

    public OrchestratorConsole(String fileNameJson) {
        this();
        this.fileNameJson = fileNameJson;
    }

    /** Загрузить коллекцию из файла
     * @return удалось или нет считать данные */
    public boolean loadDataFromFile(String fileNameData) {
        var dataProvider = new DataProviderJSON(fileNameData);
        var result = dataProvider.read();
        if (result.message.isEmpty()) {
            realtor.init(result.flats);
            if (fileNameData == fileNameJson)
                System.out.println("Загружены данные из файла " + Paths.get(fileNameJson).toAbsolutePath());
            else System.out.println("Данные предыдущей сессии восстановлены.");
            return true;
        }
        System.out.println(result.message);
        return false;
    }

    /** Запуск оркестратора */
    @Override
    public void run() {
        // проверка на существование файла с данными и предложение создать такой, если его нет
        Path path = Paths.get(fileNameJson);
        var file = path.toFile();
        if(!file.exists()) {
            System.out.print(String.format("Файла с данными [%s] не существует. Хотите создать новый пустой файл с данными %s ?", fileNameJson, Command.get_MSG_YES_NO()));
            var answer = -1;
            while(answer < 0) {
                var answerText = scanner.nextLine().trim();
                answer = Command.parseYesNoAnswer(answerText);
                if (answer == 1) {
                    try {
                        file.createNewFile();
                    }
                    catch (Exception ex) {
                        System.out.println("Ошибка создания нового файла с данными: " + ex.getMessage());
                    }
                } else if (answer == 0) {
                    // если нет, то работаем далее по алгоритму
                }
                else {
                    System.out.print(String.format("Вы не ответили на вопрос. Введите ответ повторно %s: ", Command.get_MSG_YES_NO()));
                }
            }
        }

        // проверка на наличие несохраненных данных предыдущей сессии работы программы
        String fileNameData = this.fileNameJson;
        if(LocalTempCopy.isStorageExists()) {
            System.out.println("Обнаружены несохраненные данные с предыдущего запуска программы.");
            System.out.print(String.format("Хотите восстановить данные %s? ", Command.get_MSG_YES_NO()));
            var answer = -1;
            while(answer < 0) {
                var answerText = scanner.nextLine().trim();
                answer = Command.parseYesNoAnswer(answerText);
                if (answer == 1) {
                    fileNameData = localTempCopy.getStorageFileName();
                }
                else if(answer == 0) {
                    // если НЕТ, то работаем с указанным файлом данных
                    System.out.println("Несохраненные данные с предыдущего запуска программы будут удалены.");
                    System.out.print(String.format("Вы уверены, что хотите удалить эти несохраненные данные %s? ", Command.get_MSG_YES_NO()));
                    answer = -2;
                    while(answer < 0 && answer != 100) {
                        answerText = scanner.nextLine().trim();
                        answer = Command.parseYesNoAnswer(answerText);
                        if (answer == 1) {
                            try {
                                LocalTempCopy.dropStorage();
                            }
                            catch (TemporaryDataFileException ex) {
                                System.out.println(ex.getMessage());
                            }
                            // ну и далее загружаем данные из "основного" файла с данными
                         }
                        else if(answer == 0) {
                            answer = 100; // переходим в основной цикл с опросом, что делать с временным файлом
                            System.out.println("Итак, ещё раз. Обнаружены несохраненные данные с предыдущего запуска программы.");
                            System.out.print(String.format("Хотите восстановить данные %s? ", Command.get_MSG_YES_NO()));
                        }
                        else {
                            System.out.println("Вы не ответили на вопрос что делать с несохраненными ранее данными.");
                            System.out.print(String.format("Введите ответ повторно %s: ", Command.get_MSG_YES_NO()));
                        }
                    }
                    if (answer == 100) answer = -1; // чтобы опять перейти в цикл опроса
                }
                else {
                    System.out.println(String.format("Вы не выбрали ни один из вариантов ответов %s.", Command.get_MSG_YES_NO()));
                    System.out.print("Введите ответ повторно: ");
                }
            }
        }

        if(!loadDataFromFile(fileNameData)) return;



        var reader = new OrchestratorReader(this);
        System.out.println("Чтобы узнать список доступных команд, наберите help");
        System.out.println("Введите команду:");
        try {
            var confirmationExit = false;
            do {
                reader.read(scanner);
                // именно такая вот проверка на наличие несохраненных данных.
                if(LocalTempCopy.isStorageExists() || this.realtor.getIsDataModifired()) {
                    System.out.print(String.format("Данные не были сохранены. Желаете сохранить данные %s? ", Command.get_MSG_YES_NO()));
                    var answer = -1;
                    while(answer < 0) {
                        var answerText = scanner.nextLine().trim();
                        answer = Command.parseYesNoAnswer(answerText);
                        if (answer == 1) {
                            // сохраняем данные
                            var cp = new CommandParams();
                            cp.realtor = this.realtor;
                            cp.params = this.fileNameJson;
                            var result = commands.get(Command.getSaveCommandName()).execute(cp);
                            System.out.println(result.multilineText.toString());
                        }
                        else if(answer == 0) {
                            System.out.println("Хорошо. Данные в файл сохранены не будут.");
                            System.out.print(String.format("Но может быть желаете оставить возможность восстановить несохраненные данные при следующем запуске программы %s? ", Command.get_MSG_YES_NO()));
                            answer = -1;
                            while(answer < 0) {
                                answerText = scanner.nextLine().trim();
                                answer = Command.parseYesNoAnswer(answerText);
                                if (answer == 1) {
                                    // оставляем временный файл для возможности восстановления
                                } else if (answer == 0) {
                                    // удаляем временный файл
                                    try {
                                        LocalTempCopy.dropStorage();
                                    }
                                    catch (TemporaryDataFileException ex) {
                                        System.out.println(ex.getMessage());
                                    }
                                } else {
                                    System.out.println(String.format("Вы не выбрали ни один из вариантов ответов %s.", Command.get_MSG_YES_NO()));
                                    System.out.print("Введите ответ повторно: ");
                                }
                            }
                        }
                        else {
                            System.out.println(String.format("Вы не выбрали ни один из вариантов ответов %s.", Command.get_MSG_YES_NO()));
                            System.out.print("Введите ответ повторно: ");
                        }
                    }
                }
            } while(confirmationExit);
        } catch (UnknownOrchestratorScannerException e) {
            System.out.println("Работа программы завершена c ошибкой: " + e.getMessage());
        }

        System.out.println("Работа программы завершена");
    }
}
