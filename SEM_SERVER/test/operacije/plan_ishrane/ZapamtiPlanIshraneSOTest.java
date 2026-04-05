/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package operacije.plan_ishrane;

import domen.Dan;
import domen.Jelo;
import domen.Nutricionista;
import domen.Pacijent;
import domen.PlanIshrane;
import domen.StatusStavke;
import domen.StavkaPlanaIshrane;
import domen.VremeObroka;
import java.util.ArrayList;
import java.util.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author anas
 */
public class ZapamtiPlanIshraneSOTest {
    
    private ZapamtiPlanIshraneSO so;
    private PlanIshrane plan;

    @Before
    public void setUp() throws Exception {
        so = new ZapamtiPlanIshraneSO();
        plan = new PlanIshrane();
        plan.setStavke(new ArrayList<>());
    }

    @After
    public void tearDown() {
        so = null;
        plan = null;
    }

    @Test
    public void testPredusloviIspravanObjekat() throws Exception {
        plan.setPacijent(new Pacijent());
        plan.setNutricionista(new Nutricionista());
        plan.setDatumIzrade(new Date());
        plan.setUkupanIznosJela(1000);

        StavkaPlanaIshrane stavka = new StavkaPlanaIshrane();
        stavka.setJelo(new Jelo());
        stavka.setKolicina(200.0);
        stavka.setDan(Dan.PONEDELJAK);
        stavka.setVremeObroka(VremeObroka.RUCAK);
        stavka.setStatus(StatusStavke.NOVA);

        plan.getStavke().add(stavka);

        so.preduslovi(plan);
    }

    @Test(expected = Exception.class)
    public void testPredusloviBezStavki() throws Exception {
        plan.setStavke(new ArrayList<>());
        so.preduslovi(plan);
    }
    
    @Test(expected = Exception.class)
    public void testPredusloviBezPacijenta() throws Exception {
        plan.setPacijent(null);
        plan.setNutricionista(new Nutricionista());
        plan.setDatumIzrade(new Date());
        so.preduslovi(plan);
    }

    @Test(expected = Exception.class)
    public void testPredusloviBezNutricioniste() throws Exception {
        plan.setPacijent(new Pacijent());
        plan.setNutricionista(null);
        plan.setDatumIzrade(new Date());
        so.preduslovi(plan);
    }

    @Test(expected = Exception.class)
    public void testPredusloviNegativanIznos() throws Exception {
        plan.setPacijent(new Pacijent());
        plan.setNutricionista(new Nutricionista());
        plan.setDatumIzrade(new Date());
        plan.setUkupanIznosJela(-500);
        so.preduslovi(plan);
    }
    
    @Test(expected = Exception.class)
    public void testPredusloviStavkaKolicinaNula() throws Exception {
        plan.setPacijent(new Pacijent());
        plan.setNutricionista(new Nutricionista());
        plan.setDatumIzrade(new Date());
        
        StavkaPlanaIshrane s = new StavkaPlanaIshrane();
        s.setJelo(new Jelo());
        s.setKolicina(0);
        s.setDan(Dan.PONEDELJAK);
        s.setVremeObroka(VremeObroka.RUCAK);
        
        plan.getStavke().add(s);
        so.preduslovi(plan);
    }

    @Test(expected = Exception.class)
    public void testPredusloviStavkaBezJela() throws Exception {
        plan.setPacijent(new Pacijent());
        plan.setNutricionista(new Nutricionista());
        plan.setDatumIzrade(new Date());
        
        StavkaPlanaIshrane s = new StavkaPlanaIshrane();
        s.setJelo(null);
        s.setKolicina(200);
        s.setDan(Dan.UTORAK);
        s.setVremeObroka(VremeObroka.VECERA);
        
        plan.getStavke().add(s);
        so.preduslovi(plan);
    }

    @Test(expected = Exception.class)
    public void testPredusloviStavkaBezObroka() throws Exception {
        plan.setPacijent(new Pacijent());
        plan.setNutricionista(new Nutricionista());
        plan.setDatumIzrade(new Date());
        
        StavkaPlanaIshrane s = new StavkaPlanaIshrane();
        s.setJelo(new Jelo());
        s.setKolicina(100);
        s.setDan(Dan.SREDA);
        s.setVremeObroka(null);
        
        plan.getStavke().add(s);
        so.preduslovi(plan);
    }

    @Test
    public void testIzvrsiOperacijuUspesno() throws Exception {
        Nutricionista n = new Nutricionista();
        n.setIdNutricionista(1);
        plan.setNutricionista(n);
        Pacijent p = new Pacijent();
        p.setIdPacijent(6);
        plan.setPacijent(p);
        plan.setNazivPlana("TestPlan");
        plan.setDatumIzrade(new Date());
        plan.setUkupanIznosJela(1500);
        StavkaPlanaIshrane s1 = new StavkaPlanaIshrane();
        s1.setRb(1);
        s1.setDan(Dan.PONEDELJAK);
        s1.setVremeObroka(VremeObroka.RUCAK);
        s1.setKolicina(200.0);
        s1.setJedKcal(350);
        s1.setJedCena(500);
        s1.setUkupnoKcal(700.0);
        s1.setIznos(1000.0);
        Jelo j = new Jelo();
        j.setIdJelo(30);
        s1.setJelo(j);
        s1.setStatus(StatusStavke.NOVA);
        plan.getStavke().add(s1);
        so.izvrsiOperaciju(plan, null);
        assertTrue(true);
    }

    @Test(expected = Exception.class)
    public void testIzvrsiOperacijuNeispravanParametar() throws Exception {
        so.izvrsiOperaciju(new Object(), null);
    }

    
}
