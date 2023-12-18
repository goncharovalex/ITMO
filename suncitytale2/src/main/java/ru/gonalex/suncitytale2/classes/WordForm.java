package ru.gonalex.suncitytale2.classes;

import ru.gonalex.suncitytale2.enums.ECase;
import ru.gonalex.suncitytale2.enums.ETime;
import ru.gonalex.suncitytale2.interfaces.IWordCase;
import ru.gonalex.suncitytale2.interfaces.IWordTime;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

public class WordForm implements IWordCase, IWordTime {
    public String WordIfNoCase; // текст, если слово в указанном падеже не найдено

    protected HashMap<Enum, String> Words;
    public WordForm() {
        Words = new HashMap<Enum, String>();
        WordIfNoCase = "[...]";
    }

    @Override
    public void addCase(String Word, ECase Case) {
        Words.put(Case, Word);
    }

    @Override
    public String getCase(ECase Case) {
        if (Words.containsKey(Case))
            return Words.get(Case).toString();
        else return WordIfNoCase;
    }

    public boolean hasCase(ECase Case) {
        return Words.containsKey(Case);
    }
    @Override
    public void addTime(String Word, ETime Time) {
        Words.put(Time, Word);
    }

    @Override
    public String getTime(ETime Time) {
        if (Words.containsKey(Time))
            return Words.get(Time).toString();
        else return WordIfNoCase;
    }

    public boolean hasTime(ETime Time) {
        return Words.containsKey(Time);
    }

    @Override
    public String toString() {
        if (Words.isEmpty()) return "[НЕТ СЛОВ]";
        else {
            Iterator<Entry<Enum, String>> iterator = Words.entrySet().iterator();
            Entry<Enum, String> item = iterator.next();
            return item.getValue();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordForm wordForm = (WordForm) o;
        return Objects.equals(WordIfNoCase, wordForm.WordIfNoCase) && Objects.equals(Words, wordForm.Words);
    }

    @Override
    public int hashCode() {
        return Objects.hash(WordIfNoCase, Words);
    }

    /*
    @Override
    public boolean equals(Object o) {
        boolean isEq;
        if(o == null) return false;
        if(this == o) return true;
        if (!(o instanceof WordForm)) return false;

        isEq = this.WordIfNoCase == ((WordForm) o).WordIfNoCase;
        if(!isEq) return false;

        isEq = true;
        Iterator<Map.Entry<Object, String>> iterator = Words.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Object, String> entry = iterator.next();
            Object key = entry.getKey();
            String value = entry.getValue();

            Object oValue = ((WordForm) o).Words.get(key);
            if(oValue == null) return false;
            String vWord = oValue.toString();
            if(value != vWord) return false;
        }

        // и наоборот, ибо набор словоформ может быть разный
        iterator = ((WordForm)o).Words.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Object, String> entry = iterator.next();
            Object key = entry.getKey();
            String value = entry.getValue();

            Object oValue = Words.get(key);
            if(oValue == null) return false;
            String vWord = oValue.toString();
            if(value != vWord) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {

    }

     */
}
