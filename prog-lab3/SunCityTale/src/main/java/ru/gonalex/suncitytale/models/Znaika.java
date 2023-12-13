package main.java.ru.gonalex.suncitytale.models;

import main.java.ru.gonalex.suncitytale.enums.ECase;
import main.java.ru.gonalex.suncitytale.enums.ESex;
import main.java.ru.gonalex.suncitytale.enums.ETime;

import java.util.Objects;

// Описание Знайки и его действий
public class Znaika extends Person {

    Action Action1, Action2, Action3, Action4;

    public Znaika() {
        super("Знайка");
        Sex = ESex.Male;
        addCase("Знайки", ECase.Genitive);
        addCase("Знайке", ECase.Dative);

        Action1 = new Action("привязывает");
        Action1.addTime("привязал", ETime.Past);
        Action1.addTime("привяжет", ETime.Future);

        Action2 = new Action("делит");
        Action2.addTime("поделил", ETime.Past);
        Action2.addTime("поделит", ETime.Future);

        Action3 = new Action("велит собирать");
        Action3.addTime("велел собирать", ETime.Past);
        Action3.addTime("повелит собирать", ETime.Future);

        Action4 = new Action("наделывать");
        Action4.addTime("наделал", ETime.Past);
        Action4.addTime("наделать", ETime.Future);
    }

    // привязывание чего-то чем-то к чему-то
    public String tie(Item item1, Item item2, Item item3, ETime Time, boolean UsePronoun) {
        String vResult;

        vResult = String.format("%s %s %s %s к %s",
                (UsePronoun) ? getPronoun() : Name,
                Action1.getTime(Time),
                item1.Name(),
                item2.getCase(ECase.Creative),
                item3.getCase(ECase.Dative)
        );
        return vResult;
    }

    // разделяет
    public String div(Person item1, String Qnt, Item item2, ETime Time) {
        String vResult = String.format("%s %s на %s%s",
                Action2.getTime(Time),
                item1.getCase(ECase.Genitive),
                (Qnt == "") ? "" : Qnt+" ",
                item2.getCase(ECase.Accusative)
                );
        return vResult;
    }

    // велеть
    public String command(Item item1, ETime Time, boolean UsePronoun) {
        String vResult = String.format("%s %s %s",
                item1.getCase(ECase.Dative),
                (UsePronoun) ? getPronoun() : Name,
                Action3.getTime(Time)
        );
        return vResult;
    }
    // наделывать
    public String doThing(Item item1, ETime Time) {
        String vResult = String.format("%s %s",
                Action4.getTime(Time),
                item1.getCase(ECase.Genitive)
        );
        return vResult;
    }

    // сплести огромную сетку
    public String doBigCell(ETime Time, boolean UsePronoun) {
        String vResult = String.format("%s %s им сплести огромную сетку",
                (UsePronoun) ? getPronoun() : Name,
                Action3.getTime(Time)
        );
        return vResult.replace("собирать ", ""); // Holy hack
    }

    // делать корзину
    public String doBasket(Item item1, ETime Time, boolean UsePronoun) {
        String vResult = String.format("%s %s %s сделать большую корзину из тонкой березовой коры",
                item1.getCase(ECase.Dative),
                (UsePronoun) ? getPronoun() : Name,
                Action3.getTime(Time)
        );
        return vResult.replace("собирать ", ""); // Holy hack
    }

    // процесс работы
    public String whileWorking(boolean UsePronoun) {
        String vResult = String.format("Пока %s со своими товарищами занимался этой работой",
                (UsePronoun) ? getPronoun() : Name
        );
        return vResult;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Znaika znaika = (Znaika) o;
        return Objects.equals(Action1, znaika.Action1) && Objects.equals(Action2, znaika.Action2) && Objects.equals(Action3, znaika.Action3) && Objects.equals(Action4, znaika.Action4);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), Action1, Action2, Action3, Action4);
    }
}
