package ru.gonalex.prog.lab5.models;

import ru.gonalex.prog.lab5.enums.Furnish;
import ru.gonalex.prog.lab5.enums.View;
import ru.gonalex.prog.lab5.enums.Transport;
import ru.gonalex.prog.lab5.exceptions.AreaValueException;
import ru.gonalex.prog.lab5.manage.Utils;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Класс, описывающий квартиру.
 * @author gonalex
 * @version 1.0
 */
public class Flat implements Comparable<Flat> {
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private float area; //Максимальное значение поля: 866, Значение поля должно быть больше 0
    private Long numberOfRooms; //Значение поля должно быть больше 0
    private Furnish furnish; //Поле может быть null
    private View view; //Поле может быть null
    private Transport transport; //Поле не может быть null
    private House house; //Поле может быть null

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) throws AreaValueException {
        if (!(area > 0 && area <= 866))
            throw new AreaValueException("Значение должно быть в интервале (0, 866]! ");
        this.area = area;
    }

    public Long getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(Long numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public Furnish getFurnish() {
        return furnish;
    }

    public void setFurnish(Furnish furnish) {
        this.furnish = furnish;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public Flat() {
        coordinates = new Coordinates();

    }

    /**
     * Сортировка по id по убыванию.
     *
     * @param o the object Flat to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     */
    @Override
    public int compareTo(Flat o) {
        if (this.getId() > o.id) return -1;
        else if (this.getId() < o.id) return 1;
        else return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Flat o1 = (Flat) o;
        return this.name == o1.getName() && this.coordinates.equals(o1.coordinates) && this.house.equals(o1.getHouse());
    }

    @Override
    public int hashCode() {
        var houseInfo = this.getHouse() == null ? "" : this.getHouse();
        //return Objects.hash(this.getName(), this.coordinates.toString(), houseInfo);
        return Objects.hash(this.toString());
    }

    @Override
    public String toString() {
        String houseInfo = this.getHouse() == null ? "null" : this.getHouse().toString();
        String info = String.format("[id: %s, Name: %s, coordinates: (%s, %s), creationDate: %s, area: %s, numberOfRooms: %s, furnish: %s, view: %s, transport: %s, house: %s]",
                this.getId(), this.getName(), this.getCoordinates().getX(), this.getCoordinates().getY(), Utils.dateToString(this.getCreationDate()),
                this.getArea(), this.getNumberOfRooms(),
                this.getFurnish() == null ? "null" : this.getFurnish(),
                this.getView() == null ? "null" : this.getView(),
                this.getTransport(), houseInfo);
        return info;
    }
}
