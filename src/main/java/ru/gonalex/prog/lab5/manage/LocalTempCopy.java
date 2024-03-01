package ru.gonalex.prog.lab5.manage;

import java.nio.file.Path;
import java.nio.file.Paths;
import ru.gonalex.prog.lab5.exceptions.TemporaryDataFileException;

/**
 * Класс, реализующий промежуточное сохранение данных риэлтора
 * @author gonalex
 * @version 1.0
 */
public class LocalTempCopy {
    /** Наименование файла, в котором хранится модифицируемая копия коллекции */
    private static String fileName = "temp.json";
    public LocalTempCopy() {

    }

    /** Получить имя файла, в котором хранится текущая копия модифицируемой коллекции
     * @return имя файла */
    public String getStorageFileName() { return fileName; }

    /** Выполняет сохранение данных коллекции во временный файл */
    public void save(CommandParams params) throws TemporaryDataFileException {
        DataProviderJSON provider = new DataProviderJSON(fileName);
        try {
            provider.write(params.realtor.getFlats());
        } catch (Exception ex) {
            throw new TemporaryDataFileException("Ошибка промежуточного сохранения файла с коллекцией: " + ex.getMessage());
        }
    }

    /** Удалить временный файл коллекции
     * @exception TemporaryDataFileException ошибка при работе с файлом */
    public static void dropStorage() throws TemporaryDataFileException {
        Path path = Paths.get(fileName);
        var file = path.toFile();
        if (!file.exists()) return;
        else if (!file.canRead()) throw new TemporaryDataFileException(String.format("Нет доступа к временному файлу [%s] с данными", fileName));

        try {
            var result = file.delete();
            if (!result) throw new TemporaryDataFileException(String.format("Неизвестная ошибка при удалении временного файла [%s] с данными коллекции", fileName));
        }
        catch (Exception ex) {
            throw new TemporaryDataFileException(String.format("Ошибка при удалении временного файла [%s] с данными коллекции: " + ex.getMessage(), fileName));
        }
    }

    /** Существует ли временный файл с данными? */
    public static boolean isStorageExists() {
        Path path = Paths.get(fileName);
        var file = path.toFile();
        return file.exists();
    }
}
