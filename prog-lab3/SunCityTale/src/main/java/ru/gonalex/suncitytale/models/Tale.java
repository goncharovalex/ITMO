package main.java.ru.gonalex.suncitytale.models;

import main.java.ru.gonalex.suncitytale.classes.WordForm;
import main.java.ru.gonalex.suncitytale.enums.ECase;
import main.java.ru.gonalex.suncitytale.enums.EPlaceType;
import main.java.ru.gonalex.suncitytale.enums.ETime;
import main.java.ru.gonalex.suncitytale.interfaces.IScene;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Tale implements IScene {
    private String POINT = ".";
    private String StoryPrevStep = "";
    public String Title;
    public String Text;

    private List<String> Story; // описание сцены

    public Tale() {
        this("");
    }

    public Tale(String Title) {
        this.Title = Title;
        Text = "";
        Story = new ArrayList<>();
    }

    /*
    public void testContractors() {
        Item w1 = new Item("шар");
        w1.addCase("шара", ECase.Genitive);
        w1.addCase("шару", ECase.Dative);

        Item w2 = new Item("шар");
        w2.addCase("шара", ECase.Genitive);
        w2.addCase("шару", ECase.Dative);

        boolean fl = w1.equals(w2);
        Story.add("w1.equals(w2): " + (fl ? "YES" : "NO"));

        int val1 = w1.hashCode(); // 539085730589305
        int val2 = w2.hashCode();
        isTrue = ( val1 == val2 )
    }
    */
    /*
    Он привязал шар веревкой к ореховому кусту, чтобы не унесло ветром, после чего поделил малышей на два отряда.
    Одному отряду он велел собирать шелковичные коконы, чтобы размотать их и наделать шелковых нитей.
    Из этих нитей он велел им сплести огромную сетку.
    Другому отряду Знайка велел сделать большую корзину из тонкой березовой коры.
    Пока Знайка со своими товарищами занимался этой работой, все жители Цветочного города приходили и смотрели на огромнейший шар,
    который был привязан к ореховому кусту. Каждому хотелось потрогать шар руками, а некоторые даже пытались его приподнять.

     */
    @Override
    public void generate() {
        Znaika MainChar = new Znaika();

        Item Ball = new Item("шар");
        Ball.addCase("шара", ECase.Genitive);
        Ball.addCase("шару", ECase.Dative);

        Item Rope = new Item("верёвка");
        Rope.addCase("веревку", ECase.Genitive);
        Rope.addCase("веревкой", ECase.Dative);
        Rope.addCase("веревкой", ECase.Creative);

        Attr attr = new Attr("ореховый");
        attr.addCase("орехового", ECase.Genitive);
        attr.addCase("ореховому", ECase.Dative);
        Item Bush = new Item("куст");
        Bush.addCase("куста", ECase.Genitive);
        Bush.addCase("кусту", ECase.Dative);
        Bush.addAttr(attr, ECase.Dative);

        Person Baby = new Person("малыши");
        Baby.addCase("малышей", ECase.Genitive);
        Baby.addCase("малышам", ECase.Dative);

        Item Group = new Item("отряд");
        Group.addCase("отряда", ECase.Accusative);

        Item Group1 = new Item("один отряд");
        Group1.addCase("одному отряду", ECase.Dative);
        Group1.addCase("одного отряда", ECase.Genitive);

        Item Group2 = new Item("другой отряд");
        Group2.addCase("другому отряду", ECase.Dative);
        Group2.addCase("другого отряда", ECase.Genitive);

        attr = new Attr("шелковичные");
        Item Cocon = new Item("коконы");
        Cocon.addCase("коконов", ECase.Genitive);
        Cocon.addCase("коконам", ECase.Dative);
        Cocon.addAttr(attr, ECase.Dative);

        attr = new Attr("шелковые");
        attr.addCase("шелковых", ECase.Genitive);
        Item SThread = new Item("нити");
        SThread.addCase("нитей", ECase.Genitive);
        SThread.addCase("нитям", ECase.Dative);
        SThread.addAttr(attr, ECase.Genitive);

        attr = new Attr("эти");
        attr.addCase("этих", ECase.Genitive);
        Item ThisThread = new Item("нити");
        ThisThread.addCase("нитей", ECase.Genitive);
        ThisThread.addCase("нитям", ECase.Dative);
        ThisThread.addAttr(attr, ECase.Genitive);

        WordForm Reason = new WordForm();
        Reason.addCase(", чтобы не унесло ветром, после чего", ECase.Undefined);

        WordForm Reason2 = new WordForm();
        Reason2.addCase(", чтобы размотать их и", ECase.Undefined);

        Place City = new Place("Цветочный", EPlaceType.City);
        City.addCase("Цветочного", ECase.Genitive);
        City.addCase("Цветочному", ECase.Dative);

        Residents Resi = new Residents(City);

        WordForm Reason3 = new WordForm();
        Reason3.addCase("который был привязан к", ECase.Undefined);

        Story.add(MainChar.tie(Ball, Rope, Bush, ETime.Past, true));
        Story.add(Reason.toString());
        Story.add(MainChar.div(Baby, "два", Group, ETime.Past));
        Story.add(POINT);
        Story.add(MainChar.command(Group1, ETime.Past, true));
        Story.add(Cocon.getCase(ECase.Nominative));
        Story.add(Reason2.toString());
        Story.add(MainChar.doThing(SThread, ETime.Future));
        Story.add(POINT);
        Story.add("Из");
        Story.add(ThisThread.getCase(ECase.Genitive));
        Story.add(MainChar.doBigCell(ETime.Past, true));
        Story.add(POINT);
        Story.add(MainChar.doBasket(Group2, ETime.Past, false));
        Story.add(POINT);
        Story.add(MainChar.whileWorking(false));
        Story.add(",");
        Story.add(Resi.allInterestingBall());
        Story.add(",");
        Story.add(Reason3.toString());
        Story.add(Bush.getCase(ECase.Dative));
        Story.add(POINT);
        Story.add(Resi.eachWantToTouch(Ball));
        Story.add(",");
        Story.add("а");
        Story.add(Resi.tryPickup("его"));
        Story.add(POINT);
    }

    @Override
    public void play() {

        Story.forEach((item) -> {
            // надо или нет ставить пробел
            char v1st = item.charAt(0);
            String vSep = (v1st == ',' || v1st == '.') ? "" : " ";

            // начало предложения или нет
            //if(this.Text.length() > 3 && this.Text.substring(this.Text.length()-2, this.Text.length()) == ".")
            if (this.StoryPrevStep == POINT || this.Text.length() == 0)
                this.Text += vSep + item.substring(0, 1).toUpperCase() + item.substring(1);
            else
                this.Text += vSep + item;

            if (item == POINT)
                this.StoryPrevStep = POINT;
            else this.StoryPrevStep = "";
        });

        this.Text = this.Text.trim();
    }

    @Override
    public String toString() {
        return Title + "\n" + Text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tale tale = (Tale) o;
        return Objects.equals(Title, tale.Title) && Objects.equals(Text, tale.Text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Title, Text);
    }
}
