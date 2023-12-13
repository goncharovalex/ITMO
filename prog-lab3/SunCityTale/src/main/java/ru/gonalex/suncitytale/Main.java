package main.java.ru.gonalex.suncitytale;

import main.java.ru.gonalex.suncitytale.models.Tale;

public class Main {
    public static void main(String[] args) {
        Tale oTale = new Tale("Незнайка в Солнечном городе. Фрагмент.");
        //oTale.testContractors();
        oTale.generate();
        oTale.play();
        //System.out.print(oTale.Text);
        System.out.print(oTale);
        oTale = null;
    }
}