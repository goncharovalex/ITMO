package ru.gonalex.suncitytale2.models;

import ru.gonalex.suncitytale2.enums.ECase;

public class Residents {
    private Place Loc;
    
    // демонстрация вложенного static class. Поименный перечень жителей некоторого географического места
    public static class ResidentNames {
    	//  Солнечного города
    	public static String SunCity() {
    		return "Незнайка, Знайка, Пилюлькин, Винтик, Шпунтик, Сиропчик, Тюбик, Гусля, Торопыжка, Ворчун, Молчун, Пончик, Растеряйка, Авоська, Небоська, Топик";
    	}
    	
    	//  Изумрудного города
    	public static String DiamondCity() {
    		return "Гудвин, Железный Дровосек, Урфин Джюс, Страшила, Гингема, и др.";
    	}    	
    }
    
    public Residents(Place Loc) {
        this.Loc = Loc;
    }

    public String allInterestingBall() {
        String  vResult;
        vResult = String.format("все жители %s приходили и смотрели на огромнейший шар", Loc.getCase(ECase.Genitive));
        return vResult;
    }

    public String eachWantToTouch(Item item1) {
        String  vResult;
        vResult = String.format("каждому хотелось потрогать %s руками", item1.Name());
        return vResult;
    }

    public String tryPickup(String item1) {
        String  vResult;
        vResult = String.format("некоторые даже пытались %s приподнять", item1);
        return vResult;
    }

    public String sayEasyBall(boolean UseDirectSpeech) {
    	return (UseDirectSpeech ? "- " : "") + "Шар легкий, — говорили они, — его свободно можно поднять кверху одной рукой.";
    }
    
    @Override
    public String toString() {
        return "Жители " + Loc.getCase(ECase.Genitive);
    }
}
