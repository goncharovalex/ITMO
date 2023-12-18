package ru.gonalex.suncitytale2;

import ru.gonalex.suncitytale2.models.AnonPlay;
import ru.gonalex.suncitytale2.models.Tale;
import ru.gonalex.suncitytale2.interfaces.*;

public class App
{
    public static void main( String[] args )
    {
    	boolean UseAnonymousClass = false; // для демонстрации использования анонимного класса
    	
    	if(UseAnonymousClass) {
    		// можем сформировать историю через анонимный класс

			AnonPlay APlay = new AnonPlay() {
				@Override
				public void doGood() {
					Tale SunCityTale = new Tale("Незнайка в Солнечном городе. Фрагмент главы.");
					SunCityTale.generate();
					SunCityTale.play();
					String vText = SunCityTale.toString();
					System.out.print(vText);
				}
			};
			APlay.doGood();
			/*
    		IScene oTale = new IScene() { // О чудо! Мы создаем объект интерфейса! ;-)
    			Tale SunCityTale = new Tale("Незнайка в Солнечном городе. Фрагмент главы.");
    			
    			@Override
    			public void generate() {
    				SunCityTale.generate();
    			}
    			
    			@Override
    			public void play() {
    				SunCityTale.play();
    				String vText = SunCityTale.toString();
    	            System.out.print(vText);
    			}

    		}; // ; - обязательно! это часть синтаксиса использования анонимного класса
    		oTale.generate();
    		oTale.play();
            oTale = null;

			 */
    	}
    	else {
    		// а можем сформировать историю более традиционным способом
            Tale oTale = new Tale("Незнайка в Солнечном городе. Фрагмент.");
            //oTale.testContractors();
            
            oTale.Demo.ExceptionModeTie = false;
            oTale.Demo.ExceptionModeDiv = false;
            oTale.Demo.ExceptionModeUnchecked = false;
            oTale.Demo.StaticNestedClass = false;
            
            oTale.generate();
            oTale.play();
            //System.out.print(oTale.Text);
            System.out.print(oTale);
            oTale = null;    		
    	}
    }
}
