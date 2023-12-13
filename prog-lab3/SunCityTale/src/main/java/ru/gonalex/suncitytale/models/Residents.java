package main.java.ru.gonalex.suncitytale.models;

import main.java.ru.gonalex.suncitytale.enums.ECase;

public class Residents {
    private Place Loc;
    public Residents(Place Loc) {
        this.Loc = Loc;
    }

    public String allInterestingBall() {
        String  vResult;
        vResult = String.format("все жители %s приходили и смотрели на огромнейший шар", Loc.getCase(ECase.Genitive));
        return vResult;
    }

    public String eachWantToTouch(Item item1) {
        String  vResult;
        vResult = String.format("каждому хотелось потрогать %s руками", item1.Name());
        return vResult;
    }

    public String tryPickup(String item1) {
        String  vResult;
        vResult = String.format("некоторые даже пытались %s приподнять", item1);
        return vResult;
    }

    @Override
    public String toString() {
        return "Жители " + Loc.getCase(ECase.Genitive);
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Residents residents = (Residents) object;
        return java.util.Objects.equals(Loc, residents.Loc);
    }

    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), Loc);
    }
}
