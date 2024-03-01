package ru.gonalex.prog.lab5.manage;

import ru.gonalex.prog.lab5.interfaces.DataProviderInterface;
import ru.gonalex.prog.lab5.models.Flat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.JsonParseException;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Класс для сохранения в файл формата JSON и чтения коллекции из файла формата JSON
 * @author gonalex
 * @version 1.0
 */
public class DataProviderJSON implements DataProviderInterface {

    private String fileName;
    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .create();

    public DataProviderJSON(String fileName) {
        this.fileName = fileName;

    }

    /**
     * Запись данных в файл
     * @param flats "Список квартир"
     * @throws IOException если что-то пошло не так при работе с файлом
     */
    @Override
    public void write(HashSet<Flat> flats) throws IOException {
        String jsonText = gson.toJson(flats);
        try(FileOutputStream out=new FileOutputStream(fileName);
            BufferedOutputStream bos = new BufferedOutputStream(out))
        {
            // перевод строки в байты
            byte[] buffer = jsonText.getBytes();
            bos.write(buffer, 0, buffer.length);
        }
        catch(IOException ex){
            throw ex;
        }
    }

    /**
     * Чтение данных из файла
     * @return Объект-оболочка с данными
     */
    @Override
    public DataWrapper read() {
        var dataWrapper = new DataWrapper();

        if (fileName == null || fileName.trim().isEmpty()) {
            dataWrapper.message = "Имя файла с данными не указано. Укажите его в качестве параметра запуска программы.";
            return dataWrapper;
        }

        try {
            Path path = Paths.get(fileName);
            var file = path.toFile();
            if (!file.exists()) dataWrapper.message = String.format("Файла с данными [%s] не существует", fileName);
            else if (!file.isFile()) dataWrapper.message = "Вы указали путь к файлу, а не имя файла";
            else if (!file.canRead()) dataWrapper.message = String.format("Нет доступа к файлу [%s]", fileName);
            if (!dataWrapper.message.isEmpty()) return dataWrapper;

            Scanner scanner = new Scanner(path);

            StringBuilder jsonText = new StringBuilder(); // это быстрее, чем конкатенация строк

            scanner.useDelimiter(System.getProperty("line.separator"));
            while (scanner.hasNext()) { //построчно считываем файл
                String text = scanner.next().trim();
                if (!text.equals(""))
                    jsonText.append(text);
            }
            scanner.close();

            if (jsonText.length() == 0) {
                jsonText.append("[]");
            }
            var collectionType = new TypeToken<HashSet<Flat>>() {
            }.getType();
            dataWrapper.flats = gson.fromJson(jsonText.toString(), collectionType);
        }
        catch (FileNotFoundException ex) {
            dataWrapper.message = "Файл с данными не найден";
        }
        catch (JsonParseException ex) {
            dataWrapper.message = "Ошибка парсинга коллекции: " + ex.getMessage();
        }
        catch (IOException ex) {
            dataWrapper.message = "Ошибка ввода-вывода при чтении файла коллекции: " + ex.getMessage();
        }
        catch (Exception ex) {
            dataWrapper.message = "Ошибка загрузки данных коллекции: " + ex;
        }

        return dataWrapper;
    }
}
