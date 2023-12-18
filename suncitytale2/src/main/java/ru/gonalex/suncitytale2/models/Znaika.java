package ru.gonalex.suncitytale2.models;

import ru.gonalex.suncitytale2.enums.ECase;
import ru.gonalex.suncitytale2.enums.ESex;
import ru.gonalex.suncitytale2.enums.ETime;
import ru.gonalex.suncitytale2.exceptions.*;

import java.util.Objects;

// Описание Знайки и его действий
public class Znaika extends Person {

    Action Action1, Action2, Action3, Action4, Action5;

    public Znaika() {
        super("Знайка");
        Sex = ESex.Male;
        addCase("Знайка", ECase.Nominative);
        addCase("Знайки", ECase.Genitive);
        addCase("Знайке", ECase.Dative);
        
        // демонстрация локального класса в методе
        class ActionBinding {
        	private Action _Action;
        	public ActionBinding(String ActionPresent, String ActionPast, String ActionFuture) {
        		_Action = new Action(ActionPresent);
        		_Action.addTime(ActionPast, ETime.Past);
        		_Action.addTime(ActionFuture, ETime.Future);
        	}
        	
        	public Action get() {
        		return _Action;
        	}
        }
        
        Action1 = (new ActionBinding("привязывать", "привязал", "привяжет")).get();
        Action2 = (new ActionBinding("делит", "поделил", "поделит")).get();
        Action3 = (new ActionBinding("велит собирать", "велел собирать", "повелит собирать")).get();
        Action4 = (new ActionBinding("наделывает", "наделал", "наделать")).get();
        Action5 = (new ActionBinding("завязывает", "завязал", "завяжет")).get();
    }

    // привязывание чего-то чем-то к чему-то
    public String tie(Item item1, Item item2, Item item3, ETime Time, boolean UsePronoun) throws TieException {
        String vResult;
        
        if(item1 == null || item2 == null || item3 == null) {
        	throw new TieException(String.format("У %s НЕ ПОЛУЧИТСЯ ПРИВЯЗАТЬ ЧТО-ТО ЧЕМ-ТО К ЧЕМУ-ТО, ИБО ЧТО-ТО ИЗ ЭТОГО ЕМУ ДЛЯ ЭТОГО НЕ ХВАТАЕТ", getCase(ECase.Genitive)));
        }

        vResult = String.format("%s %s %s %s к %s",
                (UsePronoun) ? getPronoun() : Name,
                Action1.getTime(Time),
                item1.Name(),
                item2.getCase(ECase.Creative),
                item3.getCase(ECase.Dative)
        );
        return vResult;
    }

    // завязывать что-то чем-то
    public String knot(Item item1, Item item2, ETime Time, boolean UsePronoun) throws TieException {
        String vResult;
        
        if(item1 == null || item2 == null) {
        	throw new TieException(String.format("У %s НЕ ПОЛУЧИТСЯ ЗАВЯЗАТЬ ЧТО-ТО ЧЕМ-ТО, ИБО ЧТО-ТО ИЗ ЭТОГО ЕМУ ДЛЯ ЭТОГО НЕ ХВАТАЕТ", getCase(ECase.Genitive)));
        }

        vResult = String.format("%s %s %s %s",
                (UsePronoun) ? getPronoun() : Name,
                Action5.getTime(Time),
                item1.getCase(ECase.Dative),
                item2.getCase(ECase.Accusative)
        );
        return vResult;
    }    
    
    // разделяет
    public String div(Person item1, String Qnt, Item item2, ETime Time) throws DivWhomException, DivOnWhatException {
    	
    	if (item1 == null)
    		throw new DivWhomException(String.format("НЕ ПОНЯТНО ЧТО ИЛИ КОГО ДЕЛИТ %s", getCase(ECase.Nominative)));
        if (item2 == null)
        	throw new DivOnWhatException(String.format("НЕ ПОНЯТНО НА ЧТО ДЕЛИТ %s", getCase(ECase.Nominative)));    	
    	
        String vResult = String.format("%s %s на %s%s",
                Action2.getTime(Time),
                item1.getCase(ECase.Genitive),
                (Qnt == "") ? "" : Qnt+" ",
                item2.getCase(ECase.Accusative)
                );
        return vResult;
    }

    // велеть
    public String command(Item item1, ETime Time, boolean UsePronoun) {
        String vResult = String.format("%s %s %s",
                item1.getCase(ECase.Dative),
                (UsePronoun) ? getPronoun() : Name,
                Action3.getTime(Time)
        );
        return vResult;
    }
    // наделывать
    public String doThing(Item item1, ETime Time) {
        String vResult = String.format("%s %s",
                Action4.getTime(Time),
                item1.getCase(ECase.Genitive)
        );
        return vResult;
    }

    // сплести огромную сетку
    public String doBigCell(ETime Time, boolean UsePronoun) {
        String vResult = String.format("%s %s им сплести огромную сетку",
                (UsePronoun) ? getPronoun() : Name,
                Action3.getTime(Time)
        );
        return vResult.replace("собирать ", ""); // Holy hack
    }

    // делать корзину
    public String doBasket(Item item1, ETime Time, boolean UsePronoun) {
        String vResult = String.format("%s %s %s сделать большую корзину из тонкой березовой коры",
                item1.getCase(ECase.Dative),
                (UsePronoun) ? getPronoun() : Name,
                Action3.getTime(Time)
        );
        return vResult.replace("собирать ", ""); // Holy hack
    }

    // процесс работы
    public String whileWorking(boolean UsePronoun) {
        String vResult = String.format("Пока %s со своими товарищами занимался этой работой",
                (UsePronoun) ? getPronoun() : Name
        );
        return vResult;
    }
    
    // говорит о сохнущем шаре
    public String sayAboutDryingBall(boolean UseDirectSpeech) {
        return (UseDirectSpeech ? "- " : "") + "Теперь шар будет сохнуть, а мы с вами примемся за другую работу";
    }    

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Znaika znaika = (Znaika) o;
        return Objects.equals(Action1, znaika.Action1) && Objects.equals(Action2, znaika.Action2) && Objects.equals(Action3, znaika.Action3) && Objects.equals(Action4, znaika.Action4);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), Action1, Action2, Action3, Action4);
    }
}
