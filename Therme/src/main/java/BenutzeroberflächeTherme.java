import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BenutzeroberflächeTherme extends JFrame {

    String name;
    int Personenanzahl;
    String Tarif;
    String Aufenhaltsdauer;

    // Preis berechnung
    double zeit;
    double standortpreis;

    String choose2;
    String choose1;

    int maxPreis;


    private JLabel lblStandortauswahl;
    private JComboBox cbStandortwahl;
    private JLabel lblBarrierefreiheit;
    private JLabel lblPersonenanzahl;
    private JTextField txtPersonenanzahl;
    private JComboBox cbAufenthaltsdauerTherme;
    private JButton cbSpeichern;
    private JLabel lblOeffnungszeiten;
    private JLabel lblBewertung;
    private JButton cbFiltern;
    private JPanel mainPanel;
    private JList list1;
    private JLabel lblTarifauswahl;
    private JComboBox cbTarifauswahl;
    private JLabel lblAufenthaltsdauer;
    private JTextField txtFiltern;
    private JLabel lblFiltern;

    //Listenerstellung
    DefaultListModel<Buchungen> model = new DefaultListModel<>();

    public BenutzeroberflächeTherme() {
        setTitle("HelloWorld Thermenangebote");
        setSize(850, 600);
        setContentPane(mainPanel);
        setVisible(true);

        list1.setModel(model);
        initObjekte();

        cbStandortwahl.addItem("Bitte wählen...");
        cbStandortwahl.addItem("Ulm");
        cbStandortwahl.addItem("Regensburg");
        cbStandortwahl.addItem("Kempten");
        cbTarifauswahl.addItem("Bitte wählen...");
        cbTarifauswahl.addItem("Ermäßigt");
        cbTarifauswahl.addItem("Erwachsen");

        lblOeffnungszeiten.setVisible(false);
        lblBarrierefreiheit.setVisible(false);
        lblBewertung.setVisible(false);

        cbStandortwahl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ausgewaehlt = (String) cbStandortwahl.getSelectedItem();

                //Eigenschaften unsichtbar bei keiner Auswahl
                if (ausgewaehlt == null || ausgewaehlt.isEmpty()) {
                    lblOeffnungszeiten.setVisible(false);
                    lblBarrierefreiheit.setVisible(false);
                    lblBewertung.setVisible(false);
                } else {
                    lblOeffnungszeiten.setVisible(true);
                    lblBarrierefreiheit.setVisible(true);
                    lblBewertung.setVisible(true);
                }
                if (ausgewaehlt == null || ausgewaehlt.trim().isEmpty()) {
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
                        lblBewertung.setText("Bewertung: 4,6 Sterne");
                    } else if (ausgewaehlt.equals("Regensburg")) {
                        lblOeffnungszeiten.setText("Öffnungszeiten: Mo–So 09:00–22:00");
                        lblBarrierefreiheit.setText("Barrierefrei: Ja");
                        lblBewertung.setText("Bewertung: 4,4 Sterne");
                    } else if (ausgewaehlt.equals("Kempten")) {
                        lblOeffnungszeiten.setText("Öffnungszeiten: Mo–So 08:00–20:00");
                        lblBarrierefreiheit.setText("Barrierefrei: Ja");
                        lblBewertung.setText("Bewertung: 4,2 Sterne");
                    }
                }
            }
        });


        cbAufenthaltsdauerTherme.addItem("Bitte wählen...");
        cbAufenthaltsdauerTherme.addItem("2h");
        cbAufenthaltsdauerTherme.addItem("4h");
        cbAufenthaltsdauerTherme.addItem("Tageskarte (bis zu 10h)");

        cbSpeichern.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Buchungserstellungen();
            }
        });
        cbFiltern.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtern();
            }
        });
    }

    void main(String[] args) {
        new BenutzeroberflächeTherme();
    }

    public void initObjekte() {
        Buchungen b1 = new Buchungen("Therme HelloWorld Ulm", 2, "Ermäßigt", "4h", 100.0);
        Buchungen b2 = new Buchungen("Therme HelloWorld Regensburg", 4, "Erwachsen", "Tageskarte (bis zu 10h)", 300.0);
        Buchungen b3 = new Buchungen("Therme HelloWorld Kempten", 1, "Erwachsen", "2h", 50.0);

        model.addElement(b1);
        model.addElement(b2);
        model.addElement(b3);
    }

    public void Buchungserstellungen() {
        name = cbStandortwahl.getSelectedItem().toString();

        try{
            if(txtPersonenanzahl.getText().trim().isEmpty()){
                throw new Exception("Leeres Feld");
            }


        try{
            Personenanzahl = Integer.parseInt(txtPersonenanzahl.getText());
        } catch (Exception e){
            JOptionPane.showMessageDialog(
                    this,
                    "Bitte geben Sie eine Zahl ein.",
                    "Fehler",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        Tarif = cbTarifauswahl.getSelectedItem().toString();
        Aufenhaltsdauer = cbAufenthaltsdauerTherme.getSelectedItem().toString();

        // Preis berechnung
        zeit = 0;
        standortpreis = 0;

        choose2 = cbStandortwahl.getSelectedItem().toString();
        if (choose2.equals("Ulm")) {
            standortpreis = 10.49;
        }
        if (choose2.equals("Kempten")) {
            standortpreis = 12.49;
        }
        if (choose2.equals("Regensburg")) {
            standortpreis = 8.49;
        }

            String choose1 = cbAufenthaltsdauerTherme.getSelectedItem().toString();
            if (choose1.equals("2h")) {
                zeit = 2;
            }
            if (choose1.equals("4h")) {
                zeit = 4;
            }
        if (choose1.equals("Tageskarte (bis zu 10h)")) {
            zeit = 10;
        }

            double preis = 0;
            String choose = cbTarifauswahl.getSelectedItem().toString();
            if (choose.equals("Ermäßigt")) {
                 preis = zeit * standortpreis * Personenanzahl * 0.8;
            }
            if (choose.equals("Erwachsen")) {
                 preis = zeit * standortpreis * Personenanzahl;
            }
            Buchungen b1 = new Buchungen(name, Personenanzahl, Tarif, Aufenhaltsdauer, preis);
            model.addElement(b1);

        }catch(Exception e){
            JOptionPane.showMessageDialog(
                    this,
                    "Sie haben nicht alle Felder ausgefüllt.",
                    "Fehler",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        }


        public void filtern() {
                DefaultListModel model2 = new DefaultListModel<>();

            try{
                if(txtFiltern.getText().trim().isEmpty()){
                    throw new Exception("Leeres Feld");
                }


                try {
                    maxPreis = Integer.parseInt(txtFiltern.getText());
                }catch(Exception e){
                    JOptionPane.showMessageDialog(
                            this,
                            "Bitte geben Sie eine Zahl ein.",
                            "Fehler",
                            JOptionPane.ERROR_MESSAGE
                );
            }
            }catch(Exception e){
                JOptionPane.showMessageDialog(
                        this,
                        "Sie haben nicht alle Felder ausgefüllt.",
                        "Fehler",
                        JOptionPane.ERROR_MESSAGE
                );
            }

                for (int i = 0; i < list1.getModel().getSize(); i++) {
                    Buchungen element = (Buchungen) list1.getModel().getElementAt(i);

                    if (maxPreis <= element.preis)
                        list1.setModel(model2);
                    model2.addElement(element);
                }
            }
        }
// Exception handeling
// Juit Test
// UML Diagramm
// Tätigkeitsprotokoll
// Video

//Oberfläche vs Nutzklasse (Objektgenerierung) -> zwei Klassen
//Oberfläche zur Obejektspeicherung
//Objektklasse auslagern
//Projektarbeit inkl. Befragung: 35P