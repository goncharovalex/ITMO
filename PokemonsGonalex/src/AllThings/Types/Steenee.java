package AllThings.Types;

import AllThings.Attacks.Magical_Leaf;
import AllThings.Attacks.Teeter_Dance;
import AllThings.Attacks.Play_Nice;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;
public class Steenee extends Pokemon {
    public Steenee(String name, int level){
        super(name,level);
        setStats(52,40,48,40,48,62);
        setType(Type.GRASS);
        setMove(new Magical_Leaf(), new Teeter_Dance(), new Play_Nice());
    }
}