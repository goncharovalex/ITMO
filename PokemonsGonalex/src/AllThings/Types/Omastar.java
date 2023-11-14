package AllThings.Types;
import AllThings.Attacks.Ancient_Power;
import AllThings.Attacks.Constrict;
import AllThings.Attacks.Rock_Slide;
import AllThings.Attacks.Stone_Edge;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;
public class Omastar extends Pokemon {
    public Omastar(String name, int level){
        super(name,level);
        setStats(70,60,125,115,70,55);
        setType(Type.ROCK, Type.WATER);
        setMove(new Ancient_Power(), new Constrict(), new Rock_Slide(), new Stone_Edge());
    }
}