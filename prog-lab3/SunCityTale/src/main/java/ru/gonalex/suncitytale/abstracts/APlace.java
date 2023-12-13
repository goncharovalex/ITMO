package main.java.ru.gonalex.suncitytale.abstracts;

import main.java.ru.gonalex.suncitytale.classes.WordForm;
import main.java.ru.gonalex.suncitytale.enums.EPlaceType;

// некоторое географическое место
public abstract class APlace extends WordForm {
    public String Name; // наименование места
    protected EPlaceType Type; // тип места (например город, деревня, двор)
    public APlace(EPlaceType Type) {
        super();
        this.Type = Type;
    }
}
