import javax.swing.*;
import java.awt.*;
import java.util.prefs.PreferenceChangeListener;

public class Buchungen extends JFrame {

        //Festlegung der Attribute:
        protected String name;
        protected int personenanzahl;
        protected String tarif;
        protected String aufenthaltsauer;
        protected double preis;

    //Konstruktor:
        public Buchungen(String name, int personenanzahl, String tarif, String aufenthaltsauer, double preis) throws HeadlessException {
        this.name = name;
        this.personenanzahl = personenanzahl;
        this.tarif = tarif;
        this.aufenthaltsauer = aufenthaltsauer;
        this.preis = preis;
    }
    @Override
    public String toString() {
        return "Buchungen" +
                "name=" + name + " personenanzahl=" + personenanzahl + " tarif=" + tarif + " aufenthaltsauer=" + aufenthaltsauer + " preis=" + preis;

    }
}
