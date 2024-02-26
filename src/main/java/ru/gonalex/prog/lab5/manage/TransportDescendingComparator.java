package ru.gonalex.prog.lab5.manage;

import ru.gonalex.prog.lab5.models.Flat;
import java.util.Comparator;

/**
 * Класс предназначен для организации упорядочивания по убыванию по полю Transport элементов коллекции
 * @author gonalex
 * @version 1.0
 */
public class TransportDescendingComparator implements Comparator<Flat> {

    /** Веса значимости используемых перечислений */
    private final ElementWeight elementWeight;

    public TransportDescendingComparator() {
        elementWeight = new ElementWeight();
    }

    /**
     * Реализует сортировку по полю Transport по убыванию
     *
     * @param flat1 the first object to be compared.
     * @param flat2 the second object to be compared.
     * @return a negative integer, zero, or a positive integer as the
     * first argument is less than, equal to, or greater than the
     * second.
     * @throws NullPointerException if an argument is null and this
     *                              comparator does not permit null arguments
     * @throws ClassCastException   if the arguments' types prevent them from
     *                              being compared by this comparator.
     * @apiNote It is generally the case, but <i>not</i> strictly required that
     * {@code (compare(x, y)==0) == (x.equals(y))}.  Generally speaking,
     * any comparator that violates this condition should clearly indicate
     * this fact.  The recommended language is "Note: this comparator
     * imposes orderings that are inconsistent with equals."
     */
    @Override
    public int compare(Flat flat1, Flat flat2) {
        // если поле Transport не указано, то откидываем эти элементы списка вниз
        if (flat1.getTransport() == null && flat2.getTransport() == null) return 0;
        else if (flat1.getTransport() == null && flat2.getTransport() != null) return 1;
        else if (flat1.getTransport() != null && flat2.getTransport() == null) return -1;

        var t1 = elementWeight.ofTransport(flat1.getTransport());
        var t2 = elementWeight.ofTransport(flat2.getTransport());
        if (t1 > t2) return -1;
        else if (t1 < t2) return 1;
        return 0;
    }
}
