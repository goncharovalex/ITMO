package AllThings.Attacks;
import ru.ifmo.se.pokemon.*;
public class Play_Nice extends StatusMove{
    public Play_Nice(){
        super(Type.NORMAL, 0, 0);
    }
    @Override
    protected void applyOppEffects(Pokemon p){
        p.setMod(Stat.ATTACK, -1);
    }
    @Override
    protected boolean checkAccuracy(Pokemon p1, Pokemon p2){
        return true;
    }
    @Override
    protected String describe(){
        return "использует Play Nice";
    }
}