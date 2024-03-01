package ru.gonalex.prog.lab5.manage;

import ru.gonalex.prog.lab5.enums.Furnish;
import ru.gonalex.prog.lab5.enums.Transport;
import ru.gonalex.prog.lab5.enums.View;
import ru.gonalex.prog.lab5.exceptions.*;
import ru.gonalex.prog.lab5.models.Coordinates;
import ru.gonalex.prog.lab5.models.Flat;
import ru.gonalex.prog.lab5.models.House;

import static java.lang.Double.parseDouble;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

/**
 * Взаимодействие с полями объекта при выполнении той или иной команды
 * @author gonalex
 * @version 1.0
 */
public class CommandManipulation extends Command {

    /** счетчик, используемый при выборе поля, информацию по которому необходимо будет указать на данной итерации выполнения команды */
    protected int counter = 0;

    /** используется при выполнении некоторых команд */
    protected Flat flat;

    /** используется при выполнении некоторых команд */
    protected House house;

    /** Конструктор класса. Имена входных параметров говорят сами за себя */
    public CommandManipulation(String name, String description, String help) {
        super(name, description);
        setHelp(help);
    }

    /**
     * @see ru.gonalex.prog.lab5.manage.Command#execute(CommandParams params)
     * */
    @Override
    public CommandResult execute(CommandParams params) {
        return null;
    }

    /**
     * Получить значения enum Transport одной строкой через запятую
     * @return Строка со списком возможных значений
     * */
    public static String getTransportValuesAsString() {
        String evalues = "";
        for(Transport value : Transport.values())
        {
            if (!evalues.isEmpty()) evalues += ", ";
            evalues += value ;
        }

        return evalues;
    }

    /**
     * Получить значения enum View одной строкой через запятую
     * @return Строка со списком возможных значений
     * */
    public static String getViewValuesAsString() {
        String evalues = "";
        for(View value : View.values())
        {
            if (!evalues.isEmpty()) evalues += ", ";
            evalues += value ;
        }

        return evalues;
    }

    /**
     * @see Command#getTypingDataDescription()
     * */
    @Override
    public String getTypingDataDescription() {
        String evalues;
        switch (counter) {
            case 1:
                return "Name: ";
            case 2:
                return "Coordinates x y (через пробел): ";
            case 3:
                return "Area: ";
            case 4:
                return "numberOfRooms: ";
            case 5:
                evalues = "";
                for(Furnish value : Furnish.values())
                {
                    if (!evalues.isEmpty()) evalues += ", ";
                    evalues += value ;
                }
                return String.format("Furnish [%s]: ", evalues);
            case 6:
                return String.format("View [%s]: ", getViewValuesAsString());
            case 7:
                return String.format("Transport [%s]: ", getTransportValuesAsString());
            case 8:
                return String.format("Есть информация о доме %s? ", Command.get_MSG_YES_NO());
            case 9:
                return "House name: ";
            case 10:
                return "House year: ";
            case 11:
                return "House numberOfFloors: ";
            case 12:
                return "House numberOfFlatsOnFloor: ";
            case 13:
                return "House numberOfLifts: ";
        }
        return "";
    }

    /**
     * @see Command#setTypingData(String) ()
     * */
    @Override
    public String setTypingData(String value) {
        TroubleWatcher.clear();
        value = value.trim();
        switch (counter) {
            case 1: // Name
                if (value.isEmpty()) {
                    TroubleWatcher.putProblem(get_MSG_NO_EMPTY_REPEAT());
                    return TroubleWatcher.getProblem();
                }
                flat.setName(value);
                counter++;
                break;
            case 2: // Coordinates
                if (value.isEmpty()) {
                    TroubleWatcher.putProblem(get_MSG_NO_EMPTY_REPEAT());
                    return TroubleWatcher.getProblem();
                }
                var xy = value.split(" ");
                if(xy.length == 1) {
                    TroubleWatcher.putProblem("Укажите две координаты, а не одну! " + get_MSG_REPEAT_INPUT());
                    return TroubleWatcher.getProblem();
                }
                double x, y;
                try {
                    x = parseDouble(xy[0]);
                }
                catch(Exception ex) {
                    TroubleWatcher.putProblem("Координата X не является вещественным числом! " + get_MSG_REPEAT_INPUT());
                    return TroubleWatcher.getProblem();
                }
                try {
                    y = parseDouble(xy[xy.length-1]);
                }
                catch(Exception ex) {
                    TroubleWatcher.putProblem("Координата Y не является вещественным числом! " + get_MSG_REPEAT_INPUT());
                    return TroubleWatcher.getProblem();
                }
                try {
                    flat.setCoordinates(new Coordinates(x, y));
                } catch (CoordinatesXException e) {
                    TroubleWatcher.putProblem(e.getMessage() + get_MSG_REPEAT_INPUT());
                    return TroubleWatcher.getProblem();
                } catch (CoordinatesYException e) {
                    TroubleWatcher.putProblem(e.getMessage() + get_MSG_REPEAT_INPUT());
                    return TroubleWatcher.getProblem();
                }
                counter++;
                break;
            case 3: // Area
                if (value.isEmpty()) {
                    TroubleWatcher.putProblem(get_MSG_NO_EMPTY_REPEAT());
                    return TroubleWatcher.getProblem();
                }
                try {
                    float val = parseFloat(value);
                    try {
                        flat.setArea(val);
                    }
                    catch (AreaValueException e) {
                        TroubleWatcher.putProblem(e.getMessage() + get_MSG_REPEAT_INPUT());
                        return TroubleWatcher.getProblem();
                    }

                    counter++;
                }
                catch(Exception ex) {
                    TroubleWatcher.putProblem("Значение не является вещественным числом! " + get_MSG_REPEAT_INPUT());
                    return TroubleWatcher.getProblem();
                }
                break;
            case 4: // numberOfRooms
                if (value.isEmpty()) {
                    TroubleWatcher.putProblem(get_MSG_NO_EMPTY_REPEAT());
                    return TroubleWatcher.getProblem();
                }
                try {
                    long val = parseInt(value);
                    if (val <= 0) {
                        TroubleWatcher.putProblem("Значение должно быть больше 0! " + get_MSG_REPEAT_INPUT());
                        return TroubleWatcher.getProblem();
                    }
                    flat.setNumberOfRooms(val);
                    counter++;
                }
                catch(Exception ex) {
                    TroubleWatcher.putProblem(get_MSG_NO_NUMERIC_VALUE() + get_MSG_REPEAT_INPUT());
                    return TroubleWatcher.getProblem();
                }
                break;
            case 5: // Furnish
                if (value.isEmpty())  {
                    flat.setFurnish(null);
                }
                else {
                    try {
                        Furnish fur = Furnish.valueOf(value.toUpperCase());
                        flat.setFurnish(fur);
                    }
                    catch(Exception ex) {
                        TroubleWatcher.putProblem("Значение неверное! " + get_MSG_REPEAT_INPUT());
                        return TroubleWatcher.getProblem();
                    }
                }
                counter++;
                break;
            case 6: // View
                if (value.isEmpty())  {
                    flat.setView(null);
                }
                else {
                    try {
                        View v = View.valueOf(value.toUpperCase());
                        flat.setView(v);
                    }
                    catch(Exception ex) {
                        TroubleWatcher.putProblem("Значение неверное! " + get_MSG_REPEAT_INPUT());
                        return TroubleWatcher.getProblem();
                    }
                }
                counter++;
                break;
            case 7: // Transport
                if (value.isEmpty()) {
                    TroubleWatcher.putProblem(get_MSG_NO_EMPTY_REPEAT());
                    return TroubleWatcher.getProblem();
                }
                else {
                    try {
                        Transport t = Transport.valueOf(value.toUpperCase());
                        flat.setTransport(t);
                    }
                    catch(Exception ex) {
                        TroubleWatcher.putProblem("Значение неверное! " + get_MSG_REPEAT_INPUT());
                        return TroubleWatcher.getProblem();
                    }
                }
                counter++;
                break;
            case 8: // есть информация о доме?
                if (value.isEmpty()) {
                    TroubleWatcher.putProblem("Вы не ответили на вопрос. Введите ответ: ");
                    return TroubleWatcher.getProblem();
                }
                var answer = Command.parseYesNoAnswer(value);
                if (answer == 0) {
                    counter = 14; // заканчиваем вводить информацию
                    return "";
                }
                else if (answer == 1)
                {
                    house = new House();
                    counter++;
                    break;
                }
                else {
                    TroubleWatcher.putProblem("Вы не выбрали ни один из вариантов ответа. Укажите один из вариантов ответа: ");
                    return TroubleWatcher.getProblem();
                }
            case 9: // House name
                house.setName(value);
                counter++;
                break;
            case 10: // House year
                if (value.isEmpty()) {
                    TroubleWatcher.putProblem(get_MSG_NO_EMPTY_REPEAT());
                    return TroubleWatcher.getProblem();
                }
                try {
                    long val = parseInt(value);
                    house.setYear(val);
                }
                catch(HouseYearException e) {
                    TroubleWatcher.putProblem(e.getMessage() + get_MSG_REPEAT_INPUT());
                    return TroubleWatcher.getProblem();
                }
                catch(Exception ex) {
                    TroubleWatcher.putProblem(get_MSG_NO_NUMERIC_VALUE() + get_MSG_REPEAT_INPUT());
                    return TroubleWatcher.getProblem();
                }
                counter++;
                break;
            case 11: // House numberOfFloors
                if (value.isEmpty()) {
                    TroubleWatcher.putProblem(get_MSG_NO_EMPTY_REPEAT());
                    return TroubleWatcher.getProblem();
                }
                try {
                    int val = parseInt(value);
                    house.setNumberOfFloors(val);
                }
                catch(NoPositiveValueException e) {
                    TroubleWatcher.putProblem(e.getMessage() + get_MSG_REPEAT_INPUT());
                    return TroubleWatcher.getProblem();
                }
                catch(Exception ex) {
                    TroubleWatcher.putProblem(get_MSG_NO_NUMERIC_VALUE() + get_MSG_REPEAT_INPUT());
                    return TroubleWatcher.getProblem();
                }
                counter++;
                break;
            case 12: // House numberOfFlatsOnFloor
                if (value.isEmpty()) {
                    TroubleWatcher.putProblem(get_MSG_NO_EMPTY_REPEAT());
                    return TroubleWatcher.getProblem();
                }
                try {
                    int val = parseInt(value);
                    house.setNumberOfFlatsOnFloor(val);
                }
                catch(NoPositiveValueException e) {
                    TroubleWatcher.putProblem(e.getMessage() + get_MSG_REPEAT_INPUT());
                    return TroubleWatcher.getProblem();
                }
                catch(Exception ex) {
                    TroubleWatcher.putProblem(get_MSG_NO_NUMERIC_VALUE() + get_MSG_REPEAT_INPUT());
                    return TroubleWatcher.getProblem();
                }
                counter++;
                break;
            case 13: // House numberOfLifts
                if (value.isEmpty()) {
                    TroubleWatcher.putProblem(get_MSG_NO_EMPTY_REPEAT());
                    return TroubleWatcher.getProblem();
                }
                try {
                    int val = parseInt(value);
                    house.setNumberOfLifts(val);
                }
                catch(NoPositiveValueException e) {
                    TroubleWatcher.putProblem(e.getMessage() + get_MSG_REPEAT_INPUT());
                    return TroubleWatcher.getProblem();
                }
                catch(Exception ex) {
                    TroubleWatcher.putProblem(get_MSG_NO_NUMERIC_VALUE() + get_MSG_REPEAT_INPUT());
                    return TroubleWatcher.getProblem();
                }
                flat.setHouse(house);
                counter++;
                break;
        }
        return "";
    }
}
