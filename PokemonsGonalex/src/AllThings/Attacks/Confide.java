package AllThings.Attacks;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;
import ru.ifmo.se.pokemon.Stat;

public class Confide extends PhysicalMove {
    public Confide() {super(Type.NORMAL,0,20);}
    @Override
    protected void applyOppEffects(Pokemon opp){
        opp.setMod(Stat.SPECIAL_ATTACK,-1);
    }
    @Override
    protected String describe(){return "Внушает доверие";}
}
