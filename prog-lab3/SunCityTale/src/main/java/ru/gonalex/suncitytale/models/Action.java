package main.java.ru.gonalex.suncitytale.models;

import main.java.ru.gonalex.suncitytale.classes.WordForm;
import main.java.ru.gonalex.suncitytale.enums.ECase;
import main.java.ru.gonalex.suncitytale.enums.ETime;

import java.util.Objects;

public class Action extends WordForm {
    private String _Name; // подразумеваем глагол в настоящем времени
    public Action(String Name) {
        super();
        _Name = Name;
        addTime(Name, ETime.Present); // сразу добавляем настоящее время
    }

    @Override
    public final void addCase(String Word, ECase Case) { };

    @Override
    public final String getCase(ECase Case) { return ""; }

    @Override
    public String toString() {
        return _Name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Action action = (Action) o;
        return Objects.equals(_Name, action._Name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), _Name);
    }

}
