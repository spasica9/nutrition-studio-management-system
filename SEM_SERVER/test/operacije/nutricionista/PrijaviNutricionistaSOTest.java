/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package operacije.nutricionista;

import domen.Nutricionista;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author anas
 */
public class PrijaviNutricionistaSOTest {
    
    private PrijaviNutricionistaSO so;
    private Nutricionista nutricionista;

    @Before
    public void setUp() {
        so = new PrijaviNutricionistaSO();
        nutricionista = new Nutricionista();
    }
    
    @After
    public void tearDown() {
        so = null;
        nutricionista = null;
    }

    @Test
    public void testPredusloviIspravanObjekat() throws Exception {
        nutricionista.setKorisnickoIme("ajovanovic");
        nutricionista.setSifra("ana123");
        so.preduslovi(nutricionista);
    }

    @Test(expected = Exception.class)
    public void testPredusloviNeispravanObjekat() throws Exception {
        so.preduslovi(new Object());
    }

    @Test(expected = Exception.class)
    public void testPredusloviPrazanUsername() throws Exception {
        nutricionista.setKorisnickoIme("");
        nutricionista.setSifra("lozinka123");
        so.preduslovi(nutricionista);
    }

    @Test(expected = Exception.class)
    public void testPredusloviPraznaLozinka() throws Exception {
        nutricionista.setKorisnickoIme("ajovanovic");
        nutricionista.setSifra("");
        so.preduslovi(nutricionista);
    }

    @Test(expected = Exception.class)
    public void testPredusloviNeispravanUsername() throws Exception {
        nutricionista.setKorisnickoIme("ime_koje_ima_vise_od_trideset_karaktera_za_test");
        nutricionista.setSifra("lozinka");
        so.preduslovi(nutricionista);
    }
    
    @Test
    public void testIzvrsiOperacijuUspesno() {
        System.out.println("Test: izvrsiOperaciju uspesno");
        try {
            nutricionista.setKorisnickoIme("ajovanovic");
            nutricionista.setSifra("ana123");
            so.izvrsiOperaciju(nutricionista, null);
            assertNotNull(so.getNutricionista());
            assertEquals("ajovanovic", so.getNutricionista().getKorisnickoIme());
        } catch (Exception ex) {
            fail("Greska prilikom izvrsavanja operacije: " + ex.getMessage());
        }
    }

    @Test(expected = Exception.class)
    public void testIzvrsiOperacijuNeuspesno() throws Exception {
        System.out.println("Test: izvrsiOperaciju neuspesno (pogresna sifra)");
        nutricionista.setKorisnickoIme("ajovanovic");
        nutricionista.setSifra("pogresna_sifra");
        so.izvrsiOperaciju(nutricionista, "");
        if(so.getNutricionista() == null) {
            throw new Exception("Prijava neuspela");
        }
    }
}
