package AllThings;
import AllThings.Types.*;
import ru.ifmo.se.pokemon.*;

public class Main {
    public static void main(String[] args){
        Battle b = new Battle();
        Lapras p1 = new Lapras("Черепахозавр",2);
        Omanyte p2 = new Omanyte("Пучеглазый",2);
        Omastar p3 = new Omastar("Непучеглазый",2);
        Bounsweet p4 = new Bounsweet("Редька",2);
        Steenee p5 = new Steenee("Илол",2);
        Tsareena p6 = new Tsareena("Афлим",2);

        b.addAlly(p1);
        b.addAlly(p2);
        b.addAlly(p3);
        b.addFoe(p4);
        b.addFoe(p5);
        b.addFoe(p6);
        b.go();
    }

}
