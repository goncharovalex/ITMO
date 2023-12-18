package ru.gonalex.suncitytale2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import ru.gonalex.suncitytale2.models.Tale;

class TaleTest {

	Tale oTale = new Tale("Незнайка в Солнчном городе. Фрагмент.");

	String ReferenceText =

"""
Знайка завязал веревочкой резиновую трубу, которая была снизу, чтобы из шара не выходил воздух, и сказал:
- Теперь шар будет сохнуть, а мы с вами примемся за другую работу.
Он привязал шар веревкой к ореховому кусту, чтобы не унесло ветром, после чего поделил малышей на два отряда.Одному отряду он велел собирать шелковичные коконы, чтобы размотать их и наделать шелковых нитей.Из этих нитей он велел им сплести огромную сетку.Другому отряду Знайка велел сделать большую корзину из тонкой березовой коры.Пока Знайка со своими товарищами занимался этой работой, все жители Цветочного города приходили и смотрели на огромнейший шар, который был привязан к ореховому кусту.Каждому хотелось потрогать шар руками, а некоторые даже пытались его приподнять.
- Шар легкий, — говорили они, — его свободно можно поднять кверху одной рукой.
- Легкий-то он легкий, но, по-моему, он не полетит, - сказал малыш, по имени Топик.""";

	@Test
	void testPlay() {
		oTale.generate();
		oTale.play();
		//assertEquals(ReferenceText, oTale.Text);
		assertEquals(ReferenceText, oTale.Text);
	}

}
