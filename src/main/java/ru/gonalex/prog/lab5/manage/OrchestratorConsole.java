package ru.gonalex.prog.lab5.manage;

import ru.gonalex.prog.lab5.exceptions.UnknownOrchestratorScannerException;
import ru.gonalex.prog.lab5.models.Realtor;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Оркестратор для управления коллекцией квартир путем ввода команд в консольном режиме с интерактивным взаимодействием с пользователем
 * @author gonalex
 * @version 1.0
 */
public class OrchestratorConsole extends Orchestrator {
    private final Scanner scanner;

    /** Имя файла с данными в формате JSON, в котором хранится коллекция риэлтора */
    private String fileNameJson;


    public OrchestratorConsole() {
        super(new Realtor());
        scanner = new Scanner(System.in);
        init();
    }

    public OrchestratorConsole(String fileNameJson) {
        this();
        this.fileNameJson = fileNameJson;
    }

    @Override
    protected void init() {
        super.init();
    }

    /** Загрузить коллекцию из файла
     * @return удалось или нет считать данные */
    public boolean loadDataFromFile() {
        var dataProvider = new DataProviderJSON(fileNameJson);
        var result = dataProvider.read();
        if (result.message.isEmpty()) {
            realtor.init(result.flats);
            System.out.println("Загружены данные из файла " + Paths.get(fileNameJson).toAbsolutePath());
            return true;
        }
        System.out.println(result.message);
        return false;
    }

    /** Запуск оркестратора */
    @Override
    public void run() {
        if(!loadDataFromFile()) return;

        var reader = new OrchestratorReader(commands, realtor, fileNameJson);
        System.out.println("Чтобы узнать список доступных команд, наберите help");
        System.out.println("Введите команду:");
        try {
            reader.read(scanner);
        } catch (UnknownOrchestratorScannerException e) {
            System.out.println("Работа программы завершена c ошибкой: " + e.getMessage());
        }

        System.out.println("Работа программы завершена");
    }
}
