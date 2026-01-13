import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Thermenangebote extends JFrame {

    //Deklaration der Datentypen
    protected String name;
    protected int Personenanzahl;
    protected String Tarif;
    protected String Aufenhaltsdauer;
    protected double zeit;
    protected double standortpreis;
    protected String choose2;
    protected String choose1;
    protected int maxPreis;
    protected double preis;

    //Komponenten der Benutzeroberfläche
    protected JLabel lblStandortauswahl;
    protected JComboBox cbStandortwahl;
    protected JLabel lblBarrierefreiheit;
    protected JLabel lblPersonenanzahl;
    protected JTextField txtPersonenanzahl;
    protected JComboBox cbAufenthaltsdauerTherme;
    protected JButton btnSpeichern;
    protected JLabel lblOeffnungszeiten;
    protected JLabel lblBewertung;
    protected JButton btnFiltern;
    protected JPanel mainPanel;
    protected JList list1;
    protected JLabel lblTarifauswahl;
    protected JComboBox cbTarifauswahl;
    protected JLabel lblAufenthaltsdauer;
    protected JTextField txtFiltern;
    protected JLabel lblFiltern;
    private JPanel FormatierungsPanel1;
    private JPanel FormatierungsPanel2;
    private JPanel FormatierungsPanel3;
    private JScrollPane scp1;

    //Listenerstellung
    DefaultListModel<buchungen> model = new DefaultListModel<>();

    //allgemeine Einstellungen der Benutzeroberfläche
    public Thermenangebote() {
        setTitle("Erstellen und Vergleichen Sie unsere HelloWorld Thermenangebote!");
        setSize(1100, 600);
        setContentPane(mainPanel);

        //Verbindung der Liste aus UI mit der StringList
        list1.setModel(model);
        initObjekte();

        //Formatierung der Liste, Quelle: ChatGPT
        list1.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setText("<html>" + value.toString().replace("\n", "<br>") + "</html>");
                label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Abstand innen
                return label;
            }
        });

        list1.setFixedCellHeight(-1); // erlaubt variable Höhe

        //Elemente der Comboboxen
        cbStandortwahl.addItem("Bitte wählen...");
        cbStandortwahl.addItem("Ulm");
        cbStandortwahl.addItem("Regensburg");
        cbStandortwahl.addItem("Kempten");

        cbStandortwahl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ausgewaehlt = (String) cbStandortwahl.getSelectedItem();

                //Attribute der Elemente innerhalb der Comboboxen unsichtbar, wenn noch keine Auswahl getroffen wurde
                if (ausgewaehlt == null || ausgewaehlt.isEmpty() || ausgewaehlt.equals("Bitte wählen...")) {
                    lblOeffnungszeiten.setVisible(false);
                    lblBarrierefreiheit.setVisible(false);
                    lblBewertung.setVisible(false);
                } else {
                    lblOeffnungszeiten.setVisible(true);
                    lblBarrierefreiheit.setVisible(true);
                    lblBewertung.setVisible(true);
                }
                if (ausgewaehlt == null || ausgewaehlt.trim().isEmpty()|| ausgewaehlt.equals("Bitte wählen...")) {
                    lblOeffnungszeiten.setVisible(false);
                    lblBarrierefreiheit.setVisible(false);
                    lblBewertung.setVisible(false);
                } else {
                    lblOeffnungszeiten.setVisible(true);
                    lblBarrierefreiheit.setVisible(true);
                    lblBewertung.setVisible(true);

                    if (ausgewaehlt.equals("Ulm")) {
                        lblOeffnungszeiten.setText("Öffnungszeiten: Mo–Fr 10:00–21:00");
                        lblBarrierefreiheit.setText("Barrierefrei: Ja");
                        lblBewertung.setText("Bewertung: 4,4 Sterne");
                    } else if (ausgewaehlt.equals("Regensburg")) {
                        lblOeffnungszeiten.setText("Öffnungszeiten: Mo–So 09:00–22:00");
                        lblBarrierefreiheit.setText("Barrierefrei: Ja");
                        lblBewertung.setText("Bewertung: 4,9 Sterne");
                    } else if (ausgewaehlt.equals("Kempten")) {
                        lblOeffnungszeiten.setText("Öffnungszeiten: Mo–So 08:00–20:00");
                        lblBarrierefreiheit.setText("Barrierefrei: Nein");
                        lblBewertung.setText("Bewertung: 3,2 Sterne");
                    }
                }
            }
        });

        cbAufenthaltsdauerTherme.addItem("Bitte wählen...");
        cbAufenthaltsdauerTherme.addItem("2h");
        cbAufenthaltsdauerTherme.addItem("4h");
        cbAufenthaltsdauerTherme.addItem("Tageskarte (bis zu 10h)");

        cbTarifauswahl.addItem("Bitte wählen...");
        cbTarifauswahl.addItem("Ermäßigt");
        cbTarifauswahl.addItem("Erwachsen");

        lblOeffnungszeiten.setVisible(false);
        lblBarrierefreiheit.setVisible(false);
        lblBewertung.setVisible(false);

        //Funktionen der Button
        btnSpeichern.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buchungserstellungen();
            }
        });

        btnFiltern.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtern();
            }
        });
    }

    //Initialisierung der vorgespeicherten Objekte
    void main(String[] args) {
            SwingUtilities.invokeLater(() -> { //Quelle: ChatGPT
                new Thermenangebote().setVisible(true);
            });
        }

    public void initObjekte() {
        buchungen b1 = new buchungen("Therme HelloWorld Ulm", 2, "4h", "Ermäßigt", 67.14);
        buchungen b2 = new buchungen("Therme HelloWorld Regensburg", 4, "Tageskarte (bis zu 10h)", "Erwachsen", 339.60);
        buchungen b3 = new buchungen("Therme HelloWorld Kempten", 1, "2h", "Erwachsen", 24.98);

        //Hinzufügen der vorgespeicherten Objekte in die Liste
        model.addElement(b1);
        model.addElement(b2);
        model.addElement(b3);
    }

    public void buchungserstellungen() {

        //Exception Handling: Prüfung, ob in den Comboboxen eine Auswahl getroffen wurde
        if (cbStandortwahl.getSelectedItem().equals("Bitte wählen...") ||
                cbTarifauswahl.getSelectedItem().equals("Bitte wählen...") ||
                cbAufenthaltsdauerTherme.getSelectedItem().equals("Bitte wählen...")) {

            JOptionPane.showMessageDialog(this,
                    "Bitte treffen Sie überall eine Auswahl.",
                    "Fehler",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        name = cbStandortwahl.getSelectedItem().toString();

        //Exception Handling
        try { //Prüfung, ob das Eingabefeld der Personenanzahl leer ist
            if (txtPersonenanzahl.getText().trim().isEmpty()) {
                throw new Exception("Leeres Feld");
            }
            try { //Prüfung, ob der korrekte Datentyp beim Eingabefeld der Personenanzahl verwendet wurde
                Personenanzahl = Integer.parseInt(txtPersonenanzahl.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        this,
                        "Bitte geben Sie eine Zahl ein.",
                        "Fehler",
                        JOptionPane.ERROR_MESSAGE
                );
            }

            Tarif = cbTarifauswahl.getSelectedItem().toString();
            Aufenhaltsdauer = cbAufenthaltsdauerTherme.getSelectedItem().toString();

            //Preisberechnung
            zeit = 0;
            standortpreis = 0;

            choose2 = cbStandortwahl.getSelectedItem().toString(); //Entnehmen der Standortwahl und Festlegung des Standortpreises
            if (choose2.equals("Ulm")) {
                standortpreis = 10.49;
            }
            if (choose2.equals("Kempten")) {
                standortpreis = 12.49;
            }
            if (choose2.equals("Regensburg")) {
                standortpreis = 8.49;
            }

            choose1 = cbAufenthaltsdauerTherme.getSelectedItem().toString(); //Entnehmen der Aufenthaltsdauer und Festlegung der Stundenanzahl
            if (choose1.equals("2h")) {
                zeit = 2;
            }
            if (choose1.equals("4h")) {
                zeit = 4;
            }
            if (choose1.equals("Tageskarte (bis zu 10h)")) {
                zeit = 10;
            }

            preis = 0;
            String choose = cbTarifauswahl.getSelectedItem().toString();
            if (choose.equals("Ermäßigt")) { //Entnehmen der Tarifauswahl
                preis = zeit * standortpreis * Personenanzahl * 0.8; //Preisberechnung Ermäßigt (20% Rabatt)
            }
            if (choose.equals("Erwachsen")) { //Entnehmen der Tarifauswahl
                preis = zeit * standortpreis * Personenanzahl; //Preisberechnung Erwachsen
            }

            //auf zwei Nachkommastellen runden
            preis = Math.round(preis * 100.0) / 100.0;

            //Erstellung und Hinzufügen der durch die Nutzer erstellten Objekte in die Liste
            buchungen b1 = new buchungen(name, Personenanzahl, Tarif, Aufenhaltsdauer, preis);
            model.addElement(b1); //wird hierdurch in der Liste gespeichert

        //Exeption Handling: Prüfung, ob das Eingabefeld der Filterfunktion leer ist
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Sie haben nicht alle Felder ausgefüllt.",
                    "Fehler",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    //Exception Handling
    public void filtern() {
        DefaultListModel model2 = new DefaultListModel<>();

        try { //Prüfung, ob das Eingabefeld der Filterfunktion leer ist
            if (txtFiltern.getText().trim().isEmpty()) {
                throw new Exception("Leeres Feld");
            }


            try {
                maxPreis = Integer.parseInt(txtFiltern.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        this,
                        "Bitte geben Sie eine Zahl ein.",
                        "Fehler",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        } catch (Exception e) { //Prüfung, ob der korrekte Datentyp beim Eingabefeld der Filterfunktion verwendet wurde
            JOptionPane.showMessageDialog(
                    this,
                    "Sie haben nicht alle Felder ausgefüllt.",
                    "Fehler",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        //Filter
        for (int i = 0; i < list1.getModel().getSize(); i++) {
            buchungen element = (buchungen) list1.getModel().getElementAt(i);

            if (maxPreis <= element.preis)
                list1.setModel(model2);
            model2.addElement(element);
        }
    }
}

// Tätigkeitsprotokoll
// Video