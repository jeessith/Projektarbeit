import javax.swing.*;
import java.awt.*;

public class buchungen extends JFrame {

        //Festlegung der Attribute:
        protected String name;
        protected int personenanzahl;
        protected String aufenthaltsauer;
        protected String tarif;
        protected double preis;

        //Konstruktor:
    public buchungen(String name, int personenanzahl, String aufenthaltsauer, String tarif, double preis) throws HeadlessException {
        this.name = name;
        this.personenanzahl = personenanzahl;
        this.aufenthaltsauer = aufenthaltsauer;
        this.tarif = tarif;
        this.preis = preis;
    }

    //Stinglist
    @Override
    public String toString() {
        return name +
                "\nPersonenanzahl: " + personenanzahl +
                "\nAufenthaltsdauer: " + aufenthaltsauer +
                "\nTarif: " + tarif +
                "\nPreis: " + preis + "€";
    }
}