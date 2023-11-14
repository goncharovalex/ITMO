package AllThings.Attacks;

import ru.ifmo.se.pokemon.*;

public class Teeter_Dance extends StatusMove{
    public Teeter_Dance(){
        super(Type.NORMAL, 0, 100);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        Effect.confuse(p);
    }

    @Override
    public String describe() {
        return "throws Teeter Dance";
    }
}