package ru.gonalex.prog.lab5.models;

import ru.gonalex.prog.lab5.exceptions.CoordinatesYException;
import ru.gonalex.prog.lab5.exceptions.HouseYearException;
import ru.gonalex.prog.lab5.exceptions.NoPositiveValueException;

import java.util.Objects;

/**
 * Класс, описывающий дом, в котором находится квартира.
 * @author gonalex
 * @version 1.0
 */
public class House {
    private String name; //Поле может быть null
    private long year; //Максимальное значение поля: 229, Значение поля должно быть больше 0
    private int numberOfFloors; //Значение поля должно быть больше 0
    private int numberOfFlatsOnFloor; //Значение поля должно быть больше 0
    private int numberOfLifts; //Значение поля должно быть больше 0

    private String get_MSG_NO_POSITIVE_VALUE() { return "Значение должно быть больше 0! "; };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getYear() {
        return year;
    }

    /** Setter Year
     * @exception HouseYearException Значение дожно быть в пределах (0, 229] */
    public void setYear(long year) throws HouseYearException {
        if (year > 229  || year <= 0)
            throw new HouseYearException("Значение дожно быть в пределах (0, 229]! ");
        this.year = year;
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    /** Setter Year
     * @exception NoPositiveValueException Значение дожно быть большье нуля */
    public void setNumberOfFloors(int numberOfFloors) throws NoPositiveValueException {
        if (numberOfFloors <= 0)
            throw new NoPositiveValueException(get_MSG_NO_POSITIVE_VALUE());
        this.numberOfFloors = numberOfFloors;
    }

    public int getNumberOfFlatsOnFloor() {
        return numberOfFlatsOnFloor;
    }

    /** Setter numberOfFlatsOnFloor
     * @exception NoPositiveValueException Значение дожно быть большье нуля */
    public void setNumberOfFlatsOnFloor(int numberOfFlatsOnFloor) throws NoPositiveValueException {
        if (numberOfFlatsOnFloor <= 0)
            throw new NoPositiveValueException(get_MSG_NO_POSITIVE_VALUE());
        this.numberOfFlatsOnFloor = numberOfFlatsOnFloor;
    }

    public int getNumberOfLifts() {
        return numberOfLifts;
    }

    /** Setter numberOfLifts
     * @exception NoPositiveValueException Значение дожно быть большье нуля */
    public void setNumberOfLifts(int numberOfLifts) throws NoPositiveValueException {
        if (numberOfLifts <= 0)
            throw new NoPositiveValueException(get_MSG_NO_POSITIVE_VALUE());
        this.numberOfLifts = numberOfLifts;
    }

    public House() { }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        House o1 = (House) o;
        return this.name == o1.getName() && this.year == o1.year && this.numberOfFloors == o1.numberOfFloors && this.numberOfFlatsOnFloor == o1.numberOfFlatsOnFloor && this.numberOfLifts == o1.numberOfLifts;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName(), this.year, this.numberOfFloors, this.numberOfFlatsOnFloor, this.numberOfLifts);
    }

    @Override
    public String toString() {
        return String.format("[Name: %s, year: %s, numberOfFloors: %s, numberOfFlatsOnFloor: %s, numberOfLifts: %s]", this.name, this.year, this.numberOfFloors, this.numberOfFlatsOnFloor, this.numberOfLifts);
    }
}
