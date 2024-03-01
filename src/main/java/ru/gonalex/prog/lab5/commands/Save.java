package ru.gonalex.prog.lab5.commands;

import ru.gonalex.prog.lab5.exceptions.TemporaryDataFileException;
import ru.gonalex.prog.lab5.manage.*;

import java.io.IOException;

/**
 * Класс, реализующий команду [save] - сохранить коллекцию в файл
 * @author gonalex
 * @version 1.1
 */
public class Save extends CommandManipulation {
    public Save() {
        super(Command.getSaveCommandName(), "сохранить коллекцию в файл", "В параметре команды может быть указано имя файла. В этом случае сохранение данных будет произведено в указанный файл, который станет основный файлом с данными. Если имя файла в качестве параметра не указано, то будет произведено сохранение в текущий файл.");
        params.isSubjectManipulation = true;
        params.isFileName = true;
        params.isParamsNoRequired = true; // в качестве параметра может быть имя файла, но это не обязательно
    }

    /**
     * @see ru.gonalex.prog.lab5.manage.Command#execute(CommandParams params)
     * */
    @Override
    public CommandResult execute(CommandParams params) {
        TroubleWatcher.clear();
        if (params.params.isEmpty()) {
            TroubleWatcher.putProblem("Не указано имя файла");
            return new CommandResult(TroubleWatcher.getProblem());
        }
        DataProviderJSON provider = new DataProviderJSON(params.params);
        try {
            // сохраняем данные
            provider.write(params.realtor.getFlats());

            // помечаем данные как неизмененные и удаляем временный файл
            try {
                LocalTempCopy.dropStorage();
                params.realtor.resetIsDataModifired();
            }
            catch (TemporaryDataFileException ex) {
                throw new IOException(ex); // если ошибка удаления временного файла, то пробрасываем её наверх
            }
        } catch (IOException ex) {
            TroubleWatcher.putProblem("Ошибка записи коллекции в файл: " + ex.getMessage());
            return new CommandResult(TroubleWatcher.getProblem());
        }

        // при выполнении данной команды, удаляем временный файл с данными
        try {
            LocalTempCopy.dropStorage();
        }
        catch (TemporaryDataFileException ex) {
            TroubleWatcher.putProblem(ex.getMessage());
            return new CommandResult(TroubleWatcher.getProblem());
        }

        return new CommandResult("Коллекция сохранена");
    }
}
