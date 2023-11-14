package AllThings.Types;
import AllThings.Attacks.Magical_Leaf;
import AllThings.Attacks.Teeter_Dance;
import AllThings.Attacks.Play_Nice;
import AllThings.Attacks.Confide;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;
public class Tsareena extends Pokemon {
    public Tsareena(String name, int level){
        super(name,level);
        setStats(72,120,98,50,98,72);
        setType(Type.GRASS);
        setMove(new Magical_Leaf(), new Teeter_Dance(), new Play_Nice(), new Confide());
    }
}
