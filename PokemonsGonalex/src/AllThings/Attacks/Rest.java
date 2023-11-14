package AllThings.Attacks;

import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Rest extends PhysicalMove {
    public Rest() {super(Type.PSYCHIC, 0, 5);}
    @Override
    public void applyOppEffects(Pokemon p) {
        Effect.sleep(p);
    }
    @Override
    protected String describe() {
        return "усыпляет, но лечит";
    }
}
