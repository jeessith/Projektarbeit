
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.*;
import java.lang.reflect.Field;

class ThermenangeboteTest {

    @Test
    void buchungserstellungen() throws Exception {

        //Simulation eines neuen Objektes (als würde die Benutzeroberfläche ausgeführt werden)
        Thermenangebote benutzeroberflaeche = new Thermenangebote();

        //Simulation der Eingabefelder (als würde man etwas in die Benutzeroberfläche eingeben)
        JComboBox<String> cbStandortwahl = getField(benutzeroberflaeche, "cbStandortwahl");
        JComboBox<String> cbTarifauswahl = getField(benutzeroberflaeche, "cbTarifauswahl");
        JComboBox<String> cbAufenthaltsdauerTherme = getField(benutzeroberflaeche, "cbAufenthaltsdauerTherme");
        JTextField txtPersonenanzahl = getField(benutzeroberflaeche, "txtPersonenanzahl");
        DefaultListModel<?> model = getField(benutzeroberflaeche, "model");

        //Simulation der Eingaben
        cbStandortwahl.setSelectedItem("Ulm");
        cbTarifauswahl.setSelectedItem("Erwachsen");
        cbAufenthaltsdauerTherme.setSelectedItem("4h");
        txtPersonenanzahl.setText("2");

        //Methode "Buchungserstellung" aufrufen
        benutzeroberflaeche.buchungserstellungen();

        //Buchung, die gerade erstellt wurde, wird aus der Liste ausgelesen
        Object lastBooking = model.getElementAt(model.getSize() - 1);

        //Preis auslesen
        Field preisField = lastBooking.getClass().getDeclaredField("preis");
        preisField.setAccessible(true);
        double tatsaechlicherPreis = (double) preisField.get(lastBooking);

        //Berechnung des erwarteten Preises
        double erwarteterPreis = 4 * 10.49 * 2;

        //Vergleich erwartetes und tatsächliches Ergebnis
        assertEquals(erwarteterPreis, tatsaechlicherPreis, 0.01);
    }

    //Hilfsmethode für Reflection
    @SuppressWarnings("unchecked")
    private <T> T getField(Object obj, String fieldName) throws Exception {
        Field f = obj.getClass().getDeclaredField(fieldName);
        f.setAccessible(true);
        return (T) f.get(obj);
    }
}