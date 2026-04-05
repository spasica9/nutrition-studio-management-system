/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package operacije.pacijent;

import domen.Pacijent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author anas
 */
public class ObrisiPacijentaSOTest {
    
   private ObrisiPacijentaSO so;
    private Pacijent pacijent;

    @Before
    public void setUp() throws Exception {
        so = new ObrisiPacijentaSO();
        pacijent = new Pacijent();
    }

    @After
    public void tearDown() {
        so = null;
        pacijent = null;
    }

    @Test
    public void testPredusloviIspravanObjekat() throws Exception {
        pacijent.setIdPacijent(6); 
        so.preduslovi(pacijent); 
    }

    @Test(expected = Exception.class)
    public void testPredusloviNullObjekat() throws Exception {
        so.preduslovi(null);
    }

    @Test(expected = Exception.class)
    public void testPredusloviIdJeNula() throws Exception {
        pacijent.setIdPacijent(0);
        so.preduslovi(pacijent);
    }

    @Test
    public void testIzvrsiOperacijuUspesno() {
        try {
            pacijent.setIdPacijent(6); 
            so.izvrsiOperaciju(pacijent, "");
            assertTrue(true);
        } catch (Exception ex) {
            fail("Sistem nije uspeo da obriše pacijenta: " + ex.getMessage());
        }
    }

    @Test(expected = Exception.class)
    public void testIzvrsiOperacijuNeuspesno() throws Exception {
        pacijent.setIdPacijent(-1);
        so.izvrsiOperaciju(pacijent, null);
    }

}
