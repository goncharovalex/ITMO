package ru.gonalex.prog.lab5.manage;

import ru.gonalex.prog.lab5.exceptions.NoCommandParamException;
import ru.gonalex.prog.lab5.interfaces.CommandDescriptionInterface;
import java.util.Objects;

/**
 * Абстрактный класс, описывающий любую команду, которая может быть выполнена
 * @author gonalex
 * @version 1.0
 */
public abstract class Command implements CommandDescriptionInterface {
    /** Команда */
    private final String name;

    /** Описание по команде (что она делает) */
    private final String description;

    /** Справка по команде */
    private String help;

    /**
     * Класс, описывающий атрибуты поведения команды, по которым
     * @author gonalex
     * @version 1.0
     * @see Orchestrator предпринимает то или иное действие при обработке команды
     */
    protected class Params {
        /** Атрибут: команда завершения работы */
        public boolean isExit = false;

        /** Атрибут: команда, вызываемая без параметров */
        public boolean isNoParams = false;

        /** Атрибут: команда, которая требует ввод данных по свойствам объекта коллекции */
        public boolean isFieldsManipulation = false;

        /** Атрибут: команда, которая манипулирует с коллекцией */
        public boolean isSubjectManipulation = false;

        /** Атрибут: команда, которая требует завершающего дополнительного действия после её выполнения */
        public boolean isCompleteCommandNeeds = false;

        /** Атрибут: команда, в качестве параметра которой, передается имя файла, в котором хранится коллекция */
        public boolean isFileName = false;
    }

    /** атрибуты поведения команды  */
    public Params params = new Params();

    private static final String _MSG_NO_PARAMS_COMMAND = "команда вызывается без параметров";
    private static final String _MSG_NO_EMPTY_FIELD = "Поле не может быть пустым";
    private static final String _MSG_REPEAT_INPUT = "Укажите корректное значение: ";
    private static final String _MSG_NO_NUMERIC_VALUE = "Значение не является числом! ";
    public static String get_MSG_NO_PARAMS_COMMAND() { return _MSG_NO_PARAMS_COMMAND; }
    public static String get_MSG_NO_EMPTY_FIELD() { return _MSG_NO_EMPTY_FIELD; }
    public static String get_MSG_REPEAT_INPUT() { return _MSG_REPEAT_INPUT; }
    public static String get_MSG_NO_EMPTY_REPEAT() { return _MSG_NO_EMPTY_FIELD + " " + _MSG_REPEAT_INPUT; }
    public static String get_MSG_NO_NUMERIC_VALUE() { return _MSG_NO_NUMERIC_VALUE; }


    public Command(String name, String description) {
        this.name = name;
        this.description = description;
        help = "";
    }

    public void setHelp(String helpText) {
        help = helpText;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getHelp() {
        return (help == "") ? "Нет описания команды" : help;
    }


    /**
     * Выполнить команду
     * @param realtorCommandParams параметры для выполнения команды
     * @return Объект-оболочка с результатом выполнения команды
     */
    @Override
    public CommandExecuteResult execute(RealtorCommandParams realtorCommandParams) { return null; }

    /**
     * Завершить выполнение команды, если требуется.
     * @param realtorCommandParams параметры для выполнения команды
     * @return Объект-оболочка с результатом выполнения команды
     * @see Params#isCompleteCommandNeeds isCompleteCommandNeeds
     */
    public CommandExecuteResult executeComplete(RealtorCommandParams realtorCommandParams) { return null; }

    /**
     * Вызывается при выполнении команды, если требуется вводить данные по полям объекта коллекции.
     * Вызов данного метода зависит от значения isFieldsManipulation
     * @return Результат выполнения метода является текст с наименованием поля для текущего ввода
     * @see Params#isFieldsManipulation isFieldsManipulation
     */
    public String getTypingDataDescription() { return null; }

    /**
     * Вызывается при выполнении команды, если требуется вводить данные по полям объекта коллекции.
     * Метод осуществляет передачу введенного значения пользователем в команду.
     * Вызов данного метода зависит от значения isFieldsManipulation
     * @return Результат выполнения метода является текст с информацией об ошибочном введенном значении или пустая строка, если всё нормально
     * @see Params#isFieldsManipulation isFieldsManipulation
     */
    public String setTypingData(String value)  { return null; }

    /**
     * Возвращает параметры команды
     * @return строка с параметрами
     * @exception NoCommandParamException - если параметры команды не указаны
     */
    public static String getCommandParam(String command) throws NoCommandParamException {
        var ar = command.trim().split(" ");
        if (ar.length <= 1) throw new NoCommandParamException("Не указаны параметры команды!");
        for(var i=1; i<ar.length; i++) {
            if (ar[i] != "") return ar[i];
        }
        return "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Command o1 = (Command) o;
        return Objects.equals(getName(), o1.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getName());
    }

    @Override
    public String toString() {
        return String.format("%s - %s [%s]", getName(), getDescription(), getHelp());
    }
}
