/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koordinator;


import domen.Nutricionista;
import forme.DodajStavkuForma;
import forme.KreirajPacijentaForma;
import forme.GlavnaForma;
import forme.PrijavaForma;
import forme.PrikazPacijenataForma;
import java.util.HashMap;
import java.util.Map;
import kontroler.KreirajPacijentaKontroler;
import kontroler.GlavnaFormaKontroler;
import kontroler.PrijavaKontroler;
import kontroler.PrikazPacijenataKontroler;
import forme.FormaMod;
import forme.IzmeniPlanIshraneForma;
import forme.KreirajPlanIshraneForma;
import forme.PrikazPlanovaIshraneForma;
import forme.UbaciSertifikatForma;
import kontroler.DodajStavkuKontroler;
import kontroler.IzmeniPlanIshraneKontroler;
import kontroler.KreirajPlanIshraneKontroler;
import kontroler.PrikazPlanovaIshraneKontroler;
import kontroler.UbaciSertifikatKontroler;

/**
 *
 * @author anas
 */
public class Koordinator {
    
    private static Koordinator instance;
    private Nutricionista ulogovani;
    private PrijavaKontroler loginKontroler;
    private GlavnaFormaKontroler gfKontroler;
    private PrikazPacijenataKontroler prikazPKontroler;
    private KreirajPacijentaKontroler dodajPKontroler;
    private Map<String, Object> parametri;
    private PrikazPlanovaIshraneKontroler prikazPIKontroler;
    public KreirajPlanIshraneKontroler kreirajPIKontroler;
    private DodajStavkuKontroler dodajSKontroler;
    private UbaciSertifikatKontroler ubaciSKontroler;
    private IzmeniPlanIshraneKontroler izmeniPIKontroler;
    
    public Nutricionista getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(Nutricionista ulogovani) {
        this.ulogovani = ulogovani;
    }
    
    public Koordinator() {
        parametri = new HashMap<>();
        
    }
    
    public static Koordinator getInstance(){
        if (instance==null) {
            instance=new Koordinator();
        }
        return instance;
    }

    public void otvoriLoginFormu() {
        loginKontroler = new PrijavaKontroler(new PrijavaForma());
        loginKontroler.otvoriFormu();
    }

    public void otvoriGlavnuFormu() {
        gfKontroler = new GlavnaFormaKontroler(new GlavnaForma());
        gfKontroler.otvoriFormu();
    }

    public void otvoriFormuPrikazPacijenata() {
    prikazPKontroler = new PrikazPacijenataKontroler(new PrikazPacijenataForma());
    prikazPKontroler.otvoriFormu();
}

    public void otvoriFormuDodajPacijenta() {
        dodajPKontroler = new KreirajPacijentaKontroler(new KreirajPacijentaForma());
        dodajPKontroler.otvoriFormu(FormaMod.DODAJ);
    }
    
    public void dodajParam(String s, Object o) {
        parametri.put(s, o);
    }
    
    public Object vratiParam(String s) {
        return parametri.get(s);
    }

    public void obrisiParam(String kljuc) {
        parametri.remove(kljuc);
    }
    
    public void otvoriFormuIzmeniPacijenta() {
        dodajPKontroler = new KreirajPacijentaKontroler(new KreirajPacijentaForma());
        dodajPKontroler.otvoriFormu(FormaMod.IZMENI);
    }

    public void osveziFormu() {
        prikazPKontroler.osveziFormu();
        
    }

    public void otvoriFormuPrikazPlanovaIshrane() {
        prikazPIKontroler = new PrikazPlanovaIshraneKontroler(new PrikazPlanovaIshraneForma());
        prikazPIKontroler.otvoriFormu();
    }

    public void otvoriFormuDodajPlanIshrane() {
        kreirajPIKontroler = new KreirajPlanIshraneKontroler(new KreirajPlanIshraneForma());
        kreirajPIKontroler.otvoriFormu(FormaMod.DODAJ);
    }
    
    public void otvoriDodajStavkuFormu(KreirajPlanIshraneForma kpif) {
        dodajSKontroler = new DodajStavkuKontroler(new DodajStavkuForma(kpif, true));
        dodajSKontroler.otvoriFormu();
    }

    public void otvoriFormuUbaciSertifikat() {
        ubaciSKontroler = new UbaciSertifikatKontroler(new UbaciSertifikatForma());
        ubaciSKontroler.otvoriFormu();
    }

    public void otvoriFormuIzmeniPlanIshrane() throws Exception {
        izmeniPIKontroler = new IzmeniPlanIshraneKontroler(new IzmeniPlanIshraneForma());
        izmeniPIKontroler.otvoriFormu();
    }

    public void otvoriIzmeniPlanIshraneFormuUlogovanog() {
        kreirajPIKontroler = new KreirajPlanIshraneKontroler(new KreirajPlanIshraneForma());
        kreirajPIKontroler.otvoriFormu(FormaMod.IZMENI);
    }
    
    public void otvoriDetaljiPlanaFormu() throws Exception {
        kreirajPIKontroler = new KreirajPlanIshraneKontroler(new KreirajPlanIshraneForma());
        kreirajPIKontroler.otvoriFormu(FormaMod.DETALJI);
    }

    public void otvoriDetaljiPacijentaFormu() {
        dodajPKontroler = new KreirajPacijentaKontroler(new KreirajPacijentaForma());
        dodajPKontroler.otvoriFormu(FormaMod.DETALJI);
    }
   
}
