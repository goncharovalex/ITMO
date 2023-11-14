package AllThings.Attacks;

import ru.ifmo.se.pokemon.*;

public class Rock_Slide extends PhysicalMove {
    public Rock_Slide() {
        super(Type.ROCK,75,90);
    }

    @Override
    protected void applyOppEffects(Pokemon p){
        if(Math.random()<0.3){
            Effect.flinch(p);
        }
    }
    @Override
    protected String describe(){
        return "использует Rock Slide";
    }

}
