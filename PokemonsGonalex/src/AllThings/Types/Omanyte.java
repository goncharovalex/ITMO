package AllThings.Types;

import AllThings.Attacks.Ancient_Power;
import AllThings.Attacks.Constrict;
import AllThings.Attacks.Rock_Slide;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Omanyte extends Pokemon {
    public Omanyte(String name, int level){
        super(name,level);
        setStats(35,40,100,90,55,35);
        setType(Type.ROCK, Type.WATER);
        setMove(new Ancient_Power(), new Constrict(), new Rock_Slide());
    }
}