package ru.gonalex.suncitytale2.models;

import ru.gonalex.suncitytale2.classes.WordForm;
import ru.gonalex.suncitytale2.enums.ECase;

import java.util.Objects;

// орфографическое описание признака, определения предмета, объекта, субъекта
public class Attr extends WordForm {
    private String _Name; // подразумеваем именительный падеж
    public Attr(String Name) {
        super();
        _Name = Name;
        addCase(Name, ECase.Nominative); // сразу добавляем именительный падеж
    }

    public String Name() {
        return _Name;
    }

    @Override
    public String toString() {
        return Name();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Attr attr = (Attr) o;
        return Objects.equals(_Name, attr._Name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), _Name);
    }
}
