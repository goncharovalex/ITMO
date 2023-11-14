package AllThings.Attacks;

import ru.ifmo.se.pokemon.*;

public class Constrict extends SpecialMove {
    public Constrict(){
        super(Type.NORMAL,10,100);
    }
    @Override
    protected void applyOppEffects(Pokemon p) {
        super.applyOppEffects(p);
        if (Math.random()<=0.1){
            p.setMod(Stat.SPEED,-1);
        }

    }
    public void applyOppDamage(Pokemon p, double x) {
        p.setMod(Stat.HP, (int)(Math.round(x)));}
    @Override
    protected String describe() {
        return "Constrict";
    }
}