package AllThings.Types;

import AllThings.Attacks.Sing;
import AllThings.Attacks.Confide;
import AllThings.Attacks.Rest;
import AllThings.Attacks.Dream_Eater;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Lapras extends Pokemon {
    public Lapras(String name, int level){
        super(name,level);
        setStats(130,85,80,85,95,60);
        setType(Type.WATER, Type.ICE);
        setMove(new Sing(), new Confide(), new Rest(), new Dream_Eater());
    }
}