import javax.swing.*;

public class Therme extends JFrame {

    //Festlegung der Attribute:
    protected String name;
    protected String standort;
    protected String oeffnungszeiten;
    protected String kinderfreundlichkeit;
    protected boolean barrierefreiheit;
    protected double bewertungen;

    //Konstruktor:
    public Therme(String name, String standort, String oeffnungszeiten, String kinderfreundlichkeit, boolean barrierefreiheit, double bewertungen) {
        this.name = name;
        this.standort = standort;
        this.oeffnungszeiten = oeffnungszeiten;
        this.kinderfreundlichkeit = kinderfreundlichkeit;
        this.barrierefreiheit = barrierefreiheit;
        this.bewertungen = bewertungen;
    }

    public void ausgeben() {
        System.out.println("Name der Therme: " + name);
        System.out.println("Standort: " + standort);
        System.out.println("Öffnungszeiten: " + oeffnungszeiten);
        System.out.println("Kinderfreundlichkeit: " + kinderfreundlichkeit);
        System.out.println("Barrierefreiheit: " + (barrierefreiheit ? "ja" : "nein"));
        System.out.println("Bewertung: " + bewertungen);
        System.out.println();
    }

    //Erstellung der Objekte:
    public static void main(String[] args) {
        Therme t1 = new Therme("Therme HelloWorld Ulm", "Ulm", "08.00-20.00 Uhr", "Kinderfreundlich", true, 6.4);
        Therme t2 = new Therme("Therme HelloWorld Regensburg", "Regensburg", "10:00-22:00 Uhr", "Kinderfreundlich", true, 8.9);
        Therme t3 = new Therme("Therme HelloWorld Kempten", "Kempten", "14.00-24:00 Uhr", "Eintritt ab 18", false, 9.7);
        t2.ausgeben();
    }

    @Override
    public String toString() {
        return "Therme{" +
                "name='" + name + '\'' +
                ", standort='" + standort + '\'' +
                ", oeffnungszeiten='" + oeffnungszeiten + '\'' +
                ", kinderfreundlichkeit='" + kinderfreundlichkeit + '\'' +
                ", barrierefreiheit=" + barrierefreiheit +
                ", bewertungen=" + bewertungen +
                '}';
    }
}