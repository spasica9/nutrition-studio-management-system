/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package operacije.pacijent;

import domen.Mesto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import domen.Pacijent;
import domen.TipIshrane;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author anas
 */
public class ZapamtiPacijentaSOTest {
    
    private ZapamtiPacijentaSO so;
    private Pacijent pacijent;

    @Before
    public void setUp() throws Exception {
        so = new ZapamtiPacijentaSO();
        pacijent = new Pacijent();
    }

    @After
    public void tearDown() {
        so = null;
        pacijent = null;
    }

    @Test
    public void testPredusloviIspravanObjekat() throws Exception {
        pacijent.setIme("Marko");
        pacijent.setPrezime("Markovic");
        pacijent.setEmail("marko" + System.currentTimeMillis() + "@example.com");
        pacijent.setDatumRodjenja(new Date());
        pacijent.setTipIshrane(TipIshrane.STANDARDNA);
        pacijent.setMesto(new Mesto(1, "Beograd", "11000"));
        
        so.preduslovi(pacijent);
    }

    @Test(expected = Exception.class)
    public void testPredusloviImePrazno() throws Exception {
        pacijent.setIme("");
        pacijent.setPrezime("Markovic");
        so.preduslovi(pacijent);
    }

    @Test(expected = Exception.class)
    public void testPredusloviPrezimePrazno() throws Exception {
        pacijent.setIme("Marko");
        pacijent.setPrezime(null);
        so.preduslovi(pacijent);
    }

    @Test(expected = Exception.class)
    public void testPredusloviDatumRodjenjaNull() throws Exception {
        pacijent.setIme("Marko");
        pacijent.setPrezime("Markovic");
        pacijent.setDatumRodjenja(null);
        so.preduslovi(pacijent);
    }

    @Test(expected = Exception.class)
    public void testPredusloviMestoNull() throws Exception {
        pacijent.setIme("Marko");
        pacijent.setMesto(null);
        so.preduslovi(pacijent);
    }

    @Test(expected = Exception.class)
    public void testPredusloviTipIshraneNull() throws Exception {
        pacijent.setIme("Marko");
        pacijent.setTipIshrane(null);
        so.preduslovi(pacijent);
    }

    @Test(expected = Exception.class)
    public void testPredusloviPacijentPostoji() throws Exception {
        pacijent.setIme("Marko");
        pacijent.setPrezime("Markovic");
        pacijent.setEmail("as@gmail.com"); 
        pacijent.setDatumRodjenja(new Date());
        pacijent.setMesto(new Mesto(1, "Beograd", "11000"));
        pacijent.setTipIshrane(TipIshrane.STANDARDNA);
        
        so.preduslovi(pacijent);
    }
    
    @Test
    public void testIzvrsiOperacijuUspesno() {
        try {
            pacijent.setIme("Ivan");
            pacijent.setPrezime("Ivanovic");
            pacijent.setEmail("ivan" + System.currentTimeMillis() + "@gmail.com");
            
            Calendar cal = new GregorianCalendar(1995, Calendar.MAY, 15);
            pacijent.setDatumRodjenja(cal.getTime());
            pacijent.setTipIshrane(TipIshrane.STANDARDNA);
            pacijent.setMesto(new Mesto(1, "Beograd", "11000"));
            
            so.izvrsiOperaciju(pacijent, null);
            assertTrue(true);
        } catch (Exception ex) {
            fail("Izvrsenje operacije nije uspelo: " + ex.getMessage());
        }
    }

    @Test(expected = Exception.class)
    public void testIzvrsiOperacijuNeuspesno() throws Exception {
        Pacijent nepotpun = new Pacijent();
        so.izvrsiOperaciju(nepotpun, null);
    }
    
}
