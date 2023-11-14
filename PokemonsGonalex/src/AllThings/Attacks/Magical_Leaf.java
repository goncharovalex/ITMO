package AllThings.Attacks;

import ru.ifmo.se.pokemon.*;

public class Magical_Leaf extends SpecialMove {
    public Magical_Leaf() {
        super(Type.GRASS, 60, 100);
    }
    public void applyOppDamage (Pokemon p, double x) {
        p.setMod(Stat.HP, (int)(x));
    }
    public String describe() {
        return "использовал Magical Leaf";
    }
}