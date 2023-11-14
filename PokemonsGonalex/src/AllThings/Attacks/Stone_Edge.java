package AllThings.Attacks;


import ru.ifmo.se.pokemon.*;

public class Stone_Edge extends PhysicalMove {

    public Stone_Edge() {
        super(Type.ROCK, 100, 80);
    }

    @Override
    protected double calcCriticalHit(Pokemon attacker, Pokemon defender) {
        return super.calcCriticalHit(attacker, defender) * 3;
    }

    @Override
    protected String describe() {
        return "использует Stone Edge";
    }
}