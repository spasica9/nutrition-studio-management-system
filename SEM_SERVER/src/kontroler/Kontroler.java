/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.Jelo;
import domen.Mesto;
import domen.Nutricionista;
import domen.Pacijent;
import domen.PlanIshrane;
import domen.Sertifikat;
import domen.StavkaPlanaIshrane;
import java.util.List;
import operacije.jelo.UcitajJelaSO;
import operacije.nutricionista.PrijavaOperacija;
import operacije.mesto.UcitajMestaSO;
import operacije.nutricionista.UcitajNutricionisteSO;
import operacije.pacijent.KreirajPacijentaSO;
import operacije.pacijent.PromeniPacijentaSO;
import operacije.pacijent.ObrisiPacijentaSO;
import operacije.pacijent.VratiListuSviPacijentSO;
import operacije.plan_ishrane.KreirajPlanIshraneSO;
import operacije.plan_ishrane.PromeniPlanIshraneSO;
import operacije.plan_ishrane.VratiListuSviPlanIshraneSO;
import operacije.plan_ishrane.VratiListuPlanIshraneSO;
import operacije.sertifikat.UbaciSertifikatSO;
import operacije.stavka_plana_ishrane.UcitajStavkePlanaIshraneSO;

/**
 *
 * @author anas
 */
public class Kontroler {
    
    private static Kontroler instance;

    public Kontroler() {
    }
    
    
    public static Kontroler getInstance(){
        if (instance==null) {
            instance=new Kontroler();
        }
        return instance;
    }

    public Nutricionista login(Nutricionista n) throws Exception {
        
        PrijavaOperacija operacija = new PrijavaOperacija();
        operacija.izvrsi(n, null);
        System.out.println("KLASA Kontroler: " + operacija.getNutricionista());
        return operacija.getNutricionista();
        
    }

    public List<Pacijent> ucitajPacijente() throws Exception {
        VratiListuSviPacijentSO operacija = new VratiListuSviPacijentSO();
        operacija.izvrsi(new Pacijent(), null);
        System.out.println("KLASA Kontroler: " + operacija.getPacijenti());
        return operacija.getPacijenti();
    }

    public void obrisiPacijenta(Pacijent p) throws Exception {
        ObrisiPacijentaSO operacija = new ObrisiPacijentaSO();
        operacija.izvrsi(p, null);
    }

    public List<Mesto> ucitajMesta() throws Exception {
        UcitajMestaSO operacija = new UcitajMestaSO();
        operacija.izvrsi(new Mesto(), null);
        System.out.println("KLASA Kontroler: " + operacija.getMesta());
        return operacija.getMesta();
    }

    public void dodajPacijenta(Pacijent p) throws Exception {
       KreirajPacijentaSO  operacija = new KreirajPacijentaSO();
        operacija.izvrsi(p, null);
    }

    public void azurirajPacijenta(Pacijent p) throws Exception {
        PromeniPacijentaSO operacija = new PromeniPacijentaSO();
        operacija.izvrsi(p, null);
    }

    public List<PlanIshrane> ucitajPlanoveIshrane() throws Exception {
        VratiListuSviPlanIshraneSO operacija = new VratiListuSviPlanIshraneSO();
        operacija.izvrsi(new PlanIshrane(), null);
        System.out.println("KLASA Kontroler: " + operacija.getPlanovi());
        return operacija.getPlanovi();
    }

    public List<StavkaPlanaIshrane> ucitajStavke(int id) throws Exception {
        UcitajStavkePlanaIshraneSO operacija = new UcitajStavkePlanaIshraneSO();
        operacija.izvrsi(id, null);
        return operacija.getStavke();
    }

    public List<Nutricionista> ucitajNutricioniste() throws Exception {
        UcitajNutricionisteSO operacija = new UcitajNutricionisteSO();
        operacija.izvrsi(new Nutricionista(), null);
        System.out.println("KLASA Kontroler: " + operacija.getNutricioniste());
        return operacija.getNutricioniste();
    }

    public List<Jelo> ucitajJela() throws Exception {
        UcitajJelaSO operacija = new UcitajJelaSO();
        operacija.izvrsi(new Jelo(), null);
        System.out.println("KLASA Kontroler: " + operacija.getJela());
        return operacija.getJela();
    }

    public void dodajPlanIshrane(PlanIshrane planIshrane) throws Exception {
        KreirajPlanIshraneSO  operacija = new KreirajPlanIshraneSO();
        operacija.izvrsi(planIshrane, null);
    }

    public void dodajSertifikat(Sertifikat s) throws Exception {
        UbaciSertifikatSO operacija = new UbaciSertifikatSO();
        operacija.izvrsi(s, null);
    }

    public List<PlanIshrane> ucitajPlanoveIshraneUlogovanog(Nutricionista ulogovani) throws Exception {
        VratiListuPlanIshraneSO operacija = new VratiListuPlanIshraneSO();
        operacija.izvrsi(ulogovani, null);
        return operacija.getLista();
    }

    public void izmeniPlanIshrane(PlanIshrane planIshrane1) throws Exception {
        PromeniPlanIshraneSO operacija = new PromeniPlanIshraneSO();
        operacija.izvrsi(planIshrane1, null);
    }
    
}
