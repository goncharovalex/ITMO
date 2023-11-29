package AllThings.Types;

import AllThings.Attacks.Magical_Leaf;
import AllThings.Attacks.Teeter_Dance;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Bounsweet extends Pokemon {
    public Bounsweet(String name, int level){
        super(name,level);
        setStats(42,30,38,30,38,32);
        setType(Type.GRASS);
        setMove(new Magical_Leaf(), new Teeter_Dance());
    }
}