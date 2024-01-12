package ru.gonalex.suncitytale2;

import ru.gonalex.suncitytale2.models.Tale;
import ru.gonalex.suncitytale2.interfaces.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class App
{
    public static void main( String[] args_main )
    {
        String UseRunMode = "proxy+reflection"; // варианты технологии исполнения сцены: classic, reflection, proxy+reflection

		if(UseRunMode == "proxy+reflection") {
			class TaleInvocationHandler implements InvocationHandler { // перехватчик вызовов методов Tale
				private Tale tale;

				public TaleInvocationHandler(Tale tale) {
					this.tale = tale;
				}

				@Override
				public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
					if(method.getName().equals("play")) {
						// меняем поведение метода tale
						tale.generate();
						tale.play();
						System.out.print(tale);
						return tale;
					}
					return method.invoke(tale, args);
				}
			}

			Tale oTale = new Tale("Незнайка в Солнечном городе. Фрагмент главы.");

			//Получаем загрузчик класса у оригинального объекта
			ClassLoader taleClassLoader = oTale.getClass().getClassLoader();

			//Получаем все интерфейсы, которые реализует оригинальный объект
			Class[] interfaces = oTale.getClass().getInterfaces();

			//Создаем прокси нашего объекта
			IScene proxyTale = (IScene) Proxy.newProxyInstance(taleClassLoader, interfaces, new TaleInvocationHandler(oTale));

			//Вызываем у прокси объекта один из методов нашего оригинального объекта
			proxyTale.play();
		}
		if(UseRunMode == "reflection") {
    		// можем сформировать историю через анонимный класс
    		IScene oTale = new IScene() { // О чудо! Мы создаем объект интерфейса! ;-)
    			Tale SunCityTale = new Tale("Незнайка в Солнечном городе. Фрагмент главы.");
    			
    			@Override
    			public void generate() {
    				SunCityTale.generate();
    			}
    			
    			@Override
    			public void play() {
    				SunCityTale.play();
    				// String vText = SunCityTale.toString();
    	            // System.out.print(vText);
    			}

				private String executeScene() { // будем считать, что метод надежно спрятан в private
					generate();
					play();
					return SunCityTale.toString();
				}
    		}; // ; - обязательно! это часть синтаксиса использования анонимного класса

			// Используя рефлексию, вызываем Private_метод анонимного класса
			try {
				Method method = oTale.getClass().getDeclaredMethod("executeScene");
				method.setAccessible(true); // а вот и нифига. добрались до него.
				var t = method.invoke(oTale); // oTale.method()
				System.out.print(t);
			} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
				e.printStackTrace();
			}
			/*
    		oTale.generate();
    		oTale.play();
            oTale = null;
			 */
    	}
    	if(UseRunMode == "classic") {
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
