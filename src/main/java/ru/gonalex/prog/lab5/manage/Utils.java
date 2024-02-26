package ru.gonalex.prog.lab5.manage;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Статик-класс с разными обслуживающими методами без привязки к какой-либо сущности/модели
 * @author gonalex
 * @version 1.0
 */
public class Utils {
    public static String dateToString(LocalDate datetime) {
        Calendar cal = new GregorianCalendar(datetime.getYear(), datetime.getMonth().getValue()-1, datetime.getDayOfMonth()); // -1, потому что у Calendar отчет идет с нуля
        SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyyy");
        return fmt.format(cal.getTime());
    }
}
