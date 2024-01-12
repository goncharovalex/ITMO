package ru.gonalex.suncitytale2.abstracts;

import ru.gonalex.suncitytale2.classes.WordForm;
import ru.gonalex.suncitytale2.enums.EPlaceType;

// некоторое географическое место
public abstract class APlace extends WordForm {
    public String Name; // наименование места
    protected EPlaceType Type; // тип места (например город, деревня, двор)
    public APlace(EPlaceType Type) {
        super();
        this.Type = Type;
    }
}
