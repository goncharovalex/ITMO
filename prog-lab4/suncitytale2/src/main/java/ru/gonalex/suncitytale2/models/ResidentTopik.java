package ru.gonalex.suncitytale2.models;

import ru.gonalex.suncitytale2.enums.ECase;
import ru.gonalex.suncitytale2.enums.ESex;

public class ResidentTopik extends Person {
	public ResidentTopik() {
        super("Топик");
        Sex = ESex.Male;
        addCase("Топик", ECase.Nominative);
        addCase("Топика", ECase.Genitive);
        addCase("Топику", ECase.Dative);
	}
	
	public String sayEasyButWillntFly(boolean UseDirectSpeech) {
        return (UseDirectSpeech ? "- " : "") + "Легкий-то он легкий, но, по-моему, он не полетит";
	}

	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}
}
