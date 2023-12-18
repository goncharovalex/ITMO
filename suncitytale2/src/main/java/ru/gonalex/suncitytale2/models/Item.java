package ru.gonalex.suncitytale2.models;

import ru.gonalex.suncitytale2.enums.ECase;

import java.util.Objects;

// предмет, объект, субъект
public class Item extends Attr {

    public Attr Attr; // описание сущности
    private ECase UseCaseDefault; // какой апдеж по умолчанию использвовать
    public Item(String Name) {
        super(Name);
        UseCaseDefault = ECase.Nominative;
        Attr = null;
    }

    // добавить признак
    void addAttr(Attr ItemAttr, ECase UseCaseDefault) {
        this.UseCaseDefault = UseCaseDefault;
        // привязываем признак предмета
        this.Attr = null;
        this.Attr = new Attr(ItemAttr.Name());
        this.Attr.WordIfNoCase = ItemAttr.WordIfNoCase;
        for (ECase enu: ECase.values()) {
            if(ItemAttr.hasCase(enu))
                this.Attr.addCase(ItemAttr.getCase(enu), enu);
        }
    }

    // переопределяем метод, т.к. может использоваться признак
    @Override
    public String getCase(ECase Case) {
        if (Words.containsKey(Case)) {
            String vAttr = "";
            if (Attr != null)
                vAttr = Attr.getCase(Case) + " ";
            return vAttr + Words.get(Case).toString();
        }
        else return WordIfNoCase;
    }

    @Override
    public String toString() {
        String vText = "";
        if (Attr != null)
            vText = Attr.getCase(UseCaseDefault);
        vText += " " + getCase(UseCaseDefault);
        return vText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Item item = (Item) o;
        return Objects.equals(Attr, item.Attr) && UseCaseDefault == item.UseCaseDefault;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), Attr, UseCaseDefault);
    }
}
