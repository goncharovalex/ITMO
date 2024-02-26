package ru.gonalex.prog.lab5.models;

import ru.gonalex.prog.lab5.exceptions.CoordinatesXException;
import ru.gonalex.prog.lab5.exceptions.CoordinatesYException;
import java.util.Objects;

/**
 * Координаты расположения квартиры
 * @author gonalex
 * @version 1.0
 */
public class Coordinates {

    /** Поле Координата X
     * @exception CoordinatesXException Значение поля должно быть больше -597 */
    private double x;

    /** Поле Координата X
     * @exception CoordinatesYException Значение поля должно быть больше -329 */
    private double y; //Значение поля должно быть больше -329

    /** Getter X */
    public double getX() {
        return x;
    }

    /** Setter X */
    public void setX(double x) throws CoordinatesXException {
        if (x <= -597)
          throw new CoordinatesXException("Координата X должна быть больше -597! ");
        this.x = x;
    }

    /** Getter Y */
    public double getY() {
        return y;
    }

    /** Setter Y */
    public void setY(double y) throws CoordinatesYException {
        if (y <= -329)
            throw new CoordinatesYException("Координата Y должна быть больше -329! ");
        this.y = y;
    }

    /**
     * Конструктор класса
     */
    public Coordinates() {
        x = y = 0;
    }

    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param x - координата X
     * @param y - координата Y
     */
    public Coordinates(double x, double y) throws CoordinatesXException, CoordinatesYException {
        setX(x);
        setY(y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates o1 = (Coordinates) o;
        return o1.x == this.x && o1.y == this.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
