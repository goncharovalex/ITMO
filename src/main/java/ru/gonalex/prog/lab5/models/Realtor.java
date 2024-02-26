package ru.gonalex.prog.lab5.models;

import ru.gonalex.prog.lab5.enums.Transport;
import ru.gonalex.prog.lab5.enums.View;
import ru.gonalex.prog.lab5.manage.ElementWeight;
import ru.gonalex.prog.lab5.manage.TransportDescendingComparator;
import ru.gonalex.prog.lab5.manage.Utils;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Класс <b>Риэлтор</b>. Реализует управление данными по квартирам
 * @author gonalex
 * @version 1.0
 */
public class Realtor {

    private HashSet<Flat> flats;
    private long lastID = 0;

    ElementWeight elementWeight;

    /**
    * Класс класса по умолчанию */
    public Realtor() {
        flats = new HashSet<Flat>();
        elementWeight = new ElementWeight();
    }

    /**
     * Наименование коллекции
     * @return строка */
    public String getCollectionName() {
        return "База данных очень честного и ответственного риэлтора";
    }

    /**
     * Наименование типа применяемой коллекции для хранения данных.
     * @return строка */
    public String getCollectionType() {
        return flats.getClass().toString();
    }

    /**
     * Количество записей в коллекции.
     * @return Количество записей в коллекции */
    public long getCollectionSize() {
        return flats.size();
    }

    /**
     * Возвращает <b>копию</b> коллекции квартир
     * @return коллекция квартир */
    public HashSet<Flat> getFlats() {
        return (HashSet<Flat>)flats.clone();
    }

    /**
     * Возвращает дату инициализации коллекции.
     * Дата инициализации считается датой создания первой записи
     * @return дата */
    public LocalDate getCreationDate() {
        LocalDate minDate = LocalDate.of(1900, 1, 1);
        for(Flat flat : flats){
            if (flat.getCreationDate().isAfter(minDate)) minDate = flat.getCreationDate();
        }
        return minDate;
    }

    /**
     * Возвращает последний ID записи коллекции
     * @return id записи коллекции */
    public long getLastID() {
        lastID = 0;
        flats.forEach((obj) -> lastID = Math.max(obj.getId(), lastID));
        return lastID;
    }

    /**
     * Инициализация данных при создании объекта Риалтор
      */
    public void init(HashSet<Flat> flats) {
        this.flats = flats;
        getLastID();
    }

    /**
     * Добавить информацию о квартире в коллекцию
     * @param flat объект класса Flat
     */
    public void addFlat(Flat flat) {
        flat.setId(++lastID);
        flat.setCreationDate(LocalDate.now());
        flats.add(flat);
    }

    /**
     * Добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции.
     * Анализ на условие <i>меньше</i> проводится по полю numberOfFloors (потому что я так решил)
     * @param flat объект класса Flat. Не очень удобно в данном контексте, но так по заданию
     * @return true: если получилось добавить объект, иначе - false
     */
    public boolean addIfMinFlat(Flat flat) {
        if (flat.getHouse() == null) return false; // если инфа о доме отсутствует, то даже не пытаемся

        int minFloors = Integer.MAX_VALUE; // MAX_VALUE останется если в коллекции нет информации ни об одном доме
        for(Flat flat0 : flats) {
            if (flat0.getHouse() != null) {
                if (flat0.getHouse().getNumberOfFloors() < minFloors)
                    minFloors = flat0.getHouse().getNumberOfFloors();
            }
        }
        if (minFloors == Integer.MAX_VALUE) return false;
        if (flat.getHouse().getNumberOfFloors() < minFloors) {
            addFlat(flat);
            return true;
        }
        return false;
    }

    /**
     * Удалить из коллекции все элементы, превышающие заданный
     * Анализ на условие <i>больше</i> проводится по полю numberOfFloors (потому что я так решил)
     * @param flat объект класса Flat. Не очень удобно в данном контексте, но так по заданию
     * @return true: если получилось удалить объект, иначе - false
     */
    public boolean removeGreater(Flat flat) {
        if (flat.getHouse() == null) return false; // если инфа о доме отсутствует, то даже не пытаемся

        ArrayList<Flat> flats4remove = new ArrayList<>();
        for(Flat flat0 : flats) {
            if (flat0.getHouse() != null) {
                if (flat0.getHouse().getNumberOfFloors() > flat.getHouse().getNumberOfFloors())
                    flats4remove.add(flat0);
            }
        }

        if (flats4remove.isEmpty()) return false;
        while(!flats4remove.isEmpty()) {
            removeFlat(flats4remove.get(0));
            flats4remove.remove(0);
        }
        lastID = getLastID(); // пересчитывает последний ID элемента. В принципе, можно этого и не делать.
        return true;
    }

    /**
     * Удалить из коллекции все элементы, меньшие, чем заданный
     * Анализ на условие <i>меньше</i> проводится по полю numberOfFloors (потому что я так решил)
     * @param flat объект класса Flat. Не очень удобно в данном контексте, но так по заданию
     * @return true: если получилось удалить объект, иначе - false
     */
    public boolean removeLower(Flat flat) {
        if (flat.getHouse() == null) return false; // если инфа о доме отсутствует, то даже не пытаемся

        ArrayList<Flat> flats4remove = new ArrayList<>();
        for(Flat flat0 : flats) {
            if (flat0.getHouse() != null) {
                if (flat0.getHouse().getNumberOfFloors() < flat.getHouse().getNumberOfFloors())
                    flats4remove.add(flat0);
            }
        }

        if (flats4remove.isEmpty()) return false;
        while(!flats4remove.isEmpty()) {
            removeFlat(flats4remove.get(0));
            flats4remove.remove(0);
        }
        lastID = getLastID(); // пересчитывает последний ID элемента. В принципе, можно этого и не делать.
        return true;
    }

    /**
     * Получить список элементов, значение поля transport которых больше заданного
     * @param transport элемент перечисления Transport
     * @return коллекция объектов, описывающие квартиры
     */
    public HashSet<Flat> filterGreaterThanTransport(Transport transport) {
        if(transport == null) return  new HashSet<Flat>();
        // есть такой способ фильтрации
        return (HashSet<Flat>) flats.stream().filter(new Predicate<Flat>() {
            @Override
            public boolean test(Flat flat) {
                return flat.getTransport() != null && elementWeight.ofTransport(flat.getTransport()) > elementWeight.ofTransport(transport);
            }
        }).collect(Collectors.toSet());
    }

    /**
     * Получить список элементов, значение поля view которых меньше заданного
     * @param view элемент перечисления View
     * @return коллекция объектов, описывающие квартиры
     */
    public HashSet<Flat> filterLessThanView(View view) {
        if (view == null) return new HashSet<Flat>();
        // а есть такой способ фильтрации
        return (HashSet<Flat>) flats.stream()
                .filter((flat) -> flat.getView() != null && elementWeight.ofView(flat.getView()) < elementWeight.ofView(view))
                .collect(Collectors.toSet());
    }

    /**
     * Получить список элементов, отсортированные по убыванию по полю transport
     * @return список объектов, описывающие квартиры
     */
    public ArrayList<Flat> sortByTransportDescending() {
        var arrayList = new ArrayList(flats);
        Collections.sort(arrayList, new TransportDescendingComparator());
        return arrayList;
    }

    /**
     * Обновить данные по элементу типа Flat коллекции
     * @param flat объект типа Flat
     */
    public void postFlat(Flat flat) {
        flats.remove(getFlatById(flat.getId()));
        flats.add(flat);
    }

    /**
     * Удалить квартиру из коллекции
     * @param flat объект типа Flat
     */
    public void removeFlat(Flat flat) {
        flats.remove(flat);
    }

    /**
     * Очистить коллекцию
     */
    public void clearAll() {
        flats.clear();
    }

    /**
     * Информация о всех квартирах коллекции
     * @return список строк с описанием квартир
     */
    public ArrayList<String> allFlatsInfo() {
        return flatsFormattedInfo(flats); // flats из класса
    }

    /**
     * Информация о квартирах коллекции, которая передается в качестве параметра
    * @param flats
     * @return список строк с описанием квартир
     */
    public static ArrayList<String> allFlatsInfo(HashSet<Flat> flats) {
        return flatsFormattedInfo(flats); // flats из параметра
    }

    /**
     * Форматированная информация о квартирах коллекции, которая передается в качестве параметра
     * @param flats
     * @return список строк с описанием квартир
     */
    protected static ArrayList flatsFormattedInfo(HashSet<Flat> flats) {
        var lst = new ArrayList<String>();
        if(flats.isEmpty()) {
            lst.add("Данные отсутствуют");
            return lst;
        }

        // применяем сортировку по умолчанию (по убыванию id)
        var arrayList = new ArrayList(flats);
        Collections.sort(arrayList);
        for(Object _flat : arrayList) {
            var flat = (Flat)_flat;
            lst.add(flat.toString());
        }
        return lst;
    }

    /**
     * Получить объект Квартира по её идентификатору в коллекции
     * @param id идентификатор записи в коллекции (ID)
     * @return объект квартиры
     */
    public Flat getFlatById(long id) {
        for(Flat flat : flats) {
            if (flat.getId() == id) return flat;
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Realtor o1 = (Realtor) o;

        var eq = this.getCollectionName() == o1.getCollectionName()
                && this.getCollectionType() == o1.getCollectionType()
                && this.getCollectionSize() == o1.getCollectionSize()
                && this.getCreationDate() == o1.getCreationDate();
        return eq;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getCollectionName(), this.getCollectionType(), this.getCollectionSize(), this.getCreationDate(), this.flats);
    }

    @Override
    public String toString() {
        return this.getCollectionName() + " " + this.getCollectionType() + " " + this.getCollectionSize() + " " + this.getCreationDate();
    }
}
