package AllThings.Attacks;
import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Type;

public class Sing extends PhysicalMove {
    public Sing(){super(Type.NORMAL, 0, 55);}
    @Override
    protected void applyOppEffects(Pokemon p) {
        Effect.sleep(p);
    }
    @Override
    protected String describe() {
        return "усыпляет";
    }

    }