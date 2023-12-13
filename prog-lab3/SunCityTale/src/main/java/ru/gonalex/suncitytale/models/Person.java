package main.java.ru.gonalex.suncitytale.models;

import main.java.ru.gonalex.suncitytale.classes.WordForm;
import main.java.ru.gonalex.suncitytale.enums.*;

import java.util.Objects;

public class Person extends WordForm {

    String Name = "";
    int Age = -1;
    ESex Sex = ESex.Undefined;
    public Person() {
        this("");
    }
    public Person(String Name) {
        super();
        this.Name = Name;
    }

    // получить местоимение
    public String getPronoun(ECase Case) {
        if (Sex == ESex.Male) {
            if (Case == ECase.Nominative) return "он";
            else if (Case == ECase.Genitive) return "его";
            else if (Case == ECase.Dative) return "ему";
            else return WordIfNoCase;
        }
        else if (Sex == ESex.Fermale) {
            if (Case == ECase.Nominative) return "она";
            else if (Case == ECase.Genitive) return "её";
            else if (Case == ECase.Dative) return "ей";
            else return WordIfNoCase;
        }

        return WordIfNoCase;
    }
    public String getPronoun() {
        return getPronoun(ECase.Nominative);
    }

    @Override
    public String toString() {
        return Name;
    }

    public String toFullString() {
        String vResult;
        vResult = (Name == "") ? "Имя неизвестно" : Name;
        vResult += ", " + ((Age == -1) ? "возраст неопределен" : String.valueOf(Age));
        if(Sex == ESex.Male) vResult += ", пол мужской";
        else if(Sex == ESex.Fermale) vResult += ", пол женский";
        else vResult += ", пол неопределен";
        return vResult;
    }

    @Override
    public final boolean hasTime(ETime Time) {
        return false;
    }

    @Override
    public final void addTime(String Word, ETime Time)  { }

    @Override
    public final String getTime(ETime Time) {
        return "";
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Person person = (Person) o;
        return Age == person.Age && Objects.equals(Name, person.Name) && Sex == person.Sex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), Name, Age, Sex);
    }
}
