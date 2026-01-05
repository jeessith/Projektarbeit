import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BenutzeroberflächeTherme extends JFrame {
    private JLabel lblStandortauswahl;
    private JComboBox cbStandortwahl;
    private JLabel lblBarrierefreiheit;
    private JLabel lblPersonenanzahl;
    private JTextField txtPersonenanzahl;
    private JComboBox cbAufenthaltsdauerTherme;
    private JButton cbSpeichern;
    private JLabel lblOeffnungszeiten;
    private JLabel lblBewertung;
    private JButton cbPreisberechnung;
    private JPanel mainPanel;
    private JList list1;
    private JComboBox cbTarifauswahl;

    //Listenerstellung
    DefaultListModel< Buchungen > model = new DefaultListModel<>();

    public BenutzeroberflächeTherme() {
        setTitle("HelloWorld Thermen, die zu Ihnen passen");
        setSize(850, 600);
        setContentPane(mainPanel);
        setVisible(true);

        list1.setModel(model);
        initObjekte();

        cbStandortwahl.addItem("Ulm");
        cbStandortwahl.addItem("Regensburg");
        cbStandortwahl.addItem("Kempten");
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


        cbAufenthaltsdauerTherme.addItem("2h");
        cbAufenthaltsdauerTherme.addItem("4h");
        cbAufenthaltsdauerTherme.addItem("Tageskarte (bis zu 10h)");

        cbSpeichern.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Buchungserstellungen();
            }
        });
    }
    void main(String[] args) {
        new BenutzeroberflächeTherme();
    }
public void initObjekte(){
        Buchungen b1 = new Buchungen("Therme HelloWorld Ulm", 2, "Ermäßigt", "4h", 100.0);
        Buchungen b2 = new Buchungen("Therme HelloWorld Regensburg", 4, "Erwachsen", "Tageskarte (bis zu 10h)", 300.0);
        Buchungen b3 = new Buchungen("Therme HelloWorld Kempten", 1, "Erwachsen", "2h", 50.0);

        model.addElement(b1);
        model.addElement(b2);
        model.addElement(b3);
    }
public void Buchungserstellungen(){
    String name = cbStandortwahl.getSelectedItem().toString();
    int Personenanzahl = Integer.parseInt(txtPersonenanzahl.getText());
    String Tarif = cbTarifauswahl.getSelectedItem().toString();
    String Aufenhaltsdauer = cbAufenthaltsdauerTherme.getSelectedItem().toString();

    // Preis berechnung
    int zeit =0;
    String choose1 = cbAufenthaltsdauerTherme.getSelectedItem().toString();
    if (choose1.equals("2h")) {
        zeit=2;

    }
    if (choose1.equals("4h")) {

        zeit=4;
    }

    if (choose1.equals("Tageskarte (bis zu 10h)")) {

        zeit=10;
    }



    double preis = 0;

    String choose = cbTarifauswahl.getSelectedItem().toString();
    if (choose.equals("Ermäßigt") ){
        preis = Personenanzahl*10.50*zeit;

    }

    if (choose.equals("Erwachsen") ){
        preis = Personenanzahl*12.50*zeit;
    }


    Buchungen b1 = new Buchungen(name,Personenanzahl,Tarif,Aufenhaltsdauer,preis);
    model.addElement(b1);



}

}
