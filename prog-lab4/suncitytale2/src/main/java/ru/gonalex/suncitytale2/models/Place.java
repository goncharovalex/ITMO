package ru.gonalex.suncitytale2.models;

import ru.gonalex.suncitytale2.abstracts.APlace;
import ru.gonalex.suncitytale2.enums.*;

import java.util.Objects;

public class Place extends APlace {
    private EPlaceType Type;
    public Place(String Name, EPlaceType Type) {
        super(Type);
        this.Name = Name;
        this.Type = Type;
        addCase(Name, ECase.Nominative);
    }

    @Override
    public String getCase(ECase Case) {
        String vText = super.getCase(Case) + " ";
        if(Type == EPlaceType.City) {
            if (Case == ECase.Nominative) return vText + "город";
            if (Case == ECase.Genitive) return vText + "города";
            if (Case == ECase.Dative) return vText + "городу";
            if (Case == ECase.Accusative) return vText + "город";
        }
        if(Type == EPlaceType.Village) {
            if (Case == ECase.Nominative) return vText + "деревня";
            if (Case == ECase.Genitive) return vText + "деревни";
            if (Case == ECase.Dative) return vText + "деревне";
            if (Case == ECase.Accusative) return vText + "деревню";
        }
        return vText;
    }

    @Override
    public String toString() {
        return getCase(ECase.Nominative);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Place place = (Place) o;
        return Type == place.Type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), Type);
    }
}
