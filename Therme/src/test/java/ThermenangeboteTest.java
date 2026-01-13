
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.*;
import java.lang.reflect.Field;

class ThermenangeboteTest {

    @Test
    void buchungserstellungen() throws Exception {

        //neues Objekt, dass den Test simuliert
        Thermenangebote ui = new Thermenangebote();

        //Zugriff auf die Felder, in denen eine Eingabe getätigt wird
        JComboBox<String> cbStandortwahl = getField(ui, "cbStandortwahl");
        JComboBox<String> cbTarifauswahl = getField(ui, "cbTarifauswahl");
        JComboBox<String> cbAufenthaltsdauerTherme = getField(ui, "cbAufenthaltsdauerTherme");
        JTextField txtPersonenanzahl = getField(ui, "txtPersonenanzahl");
        DefaultListModel<?> model = getField(ui, "model");

        //Simulation der Eingaben
        cbStandortwahl.setSelectedItem("Ulm");          // Standortpreis = 10.49
        cbTarifauswahl.setSelectedItem("Erwachsen");    // Kein Rabatt
        cbAufenthaltsdauerTherme.setSelectedItem("4h"); // 4 Stunden
        txtPersonenanzahl.setText("2");                 // 2 Personen

        //Methode aufrufen
        ui.buchungserstellungen();

        //Element, das wir gerade erstellt haben, aus der Liste holen
        Object lastBooking = model.getElementAt(model.getSize() - 1);

        //Preis auslesen
        Field preisField = lastBooking.getClass().getDeclaredField("preis");
        preisField.setAccessible(true);
        double actualPreis = (double) preisField.get(lastBooking);

        //Erwarteter Preis: 4h * 10.49 * 2 Personen = 83.92
        double expectedPreis = 4 * 10.49 * 2;

        //Vergleich
        assertEquals(expectedPreis, actualPreis, 0.01);
    }

    //Hilfsmethode für Reflection
    @SuppressWarnings("unchecked")
    private <T> T getField(Object obj, String fieldName) throws Exception {
        Field f = obj.getClass().getDeclaredField(fieldName);
        f.setAccessible(true);
        return (T) f.get(obj);
    }
}