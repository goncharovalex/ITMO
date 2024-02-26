package ru.gonalex.prog.lab5.manage;

import ru.gonalex.prog.lab5.enums.Transport;
import ru.gonalex.prog.lab5.enums.View;
import java.util.HashMap;

/**
 * Веса значимостей элементов перечислений.
 * Требуется для применения сортировки основной коллекции
 * @author gonalex
 * @version 1.0
 */
public class ElementWeight {
    private HashMap<Transport, Byte> transport;
    private HashMap<View, Byte> view;

    public ElementWeight() {
        // проставляем веса значимости
        transport = new HashMap<>();
        transport.put(Transport.FEW, (byte)1);
        transport.put(Transport.LITTLE, (byte)2);
        transport.put(Transport.ENOUGH, (byte)3);

        view = new HashMap<>();
        view.put(View.TERRIBLE, (byte)1);
        view.put(View.BAD, (byte)2);
        view.put(View.NORMAL, (byte)3);
        view.put(View.GOOD, (byte)4);
    }

    /**
    * Веса значимостей элементов Transport.
     * @param transportElement элемент перечисления Transport
     * @return вес значимости указанного элемента */
    public byte ofTransport(Transport transportElement) {
        return transport.get(transportElement);
    }

    /**
     * Веса значимостей элементов View.
     * @param viewElement элемент перечисления View
     * @return вес значимости указанного элемента */
    public byte ofView(View viewElement) {
        return view.get(viewElement);
    }
}
