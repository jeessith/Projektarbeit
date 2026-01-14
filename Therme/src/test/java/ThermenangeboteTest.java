
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import javax.swing.*;
import java.lang.reflect.Field;

class ThermenangeboteTest {
    @Test
    void preisBerechnungTest(){ //Testet die Preisberechnung
        double p1 = Thermenangebote.preisBerechnung(10.49,2,true,2);
        assertEquals(41.96,p1);
    }
}