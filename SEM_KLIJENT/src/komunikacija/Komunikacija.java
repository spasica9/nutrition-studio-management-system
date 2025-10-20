/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import domen.Jelo;
import domen.Mesto;
import domen.Nutricionista;
import domen.Pacijent;
import domen.PlanIshrane;
import domen.Sertifikat;
import domen.StavkaPlanaIshrane;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anas
 */
public class Komunikacija {
    
    private Socket soket;
    private Posiljalac posiljalac;
    private Primalac primalac;
    
    private static Komunikacija instance;
    public static Komunikacija getInstance(){
        if (instance==null) {
            instance = new Komunikacija();
        }
        return instance;
    }
    
   
    public void konekcija(){
        try {
            soket = new Socket("localhost",9000);
            posiljalac = new Posiljalac(soket);
            primalac = new Primalac(soket);
        } catch (IOException ex) {
            System.out.println("SERVER NIJE POVEZAN");
        }
    }

    public Nutricionista login(String username, String password) {
        Nutricionista n = new Nutricionista(username, password);
        Zahtev zahtev = new Zahtev(Operacija.LOGIN, n);
        
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        n = (Nutricionista) odgovor.getOdgovor();
        System.out.println("KLASA Komunikacija primanje nutricioniste: " + n);
        
        return n;
    }

    public List<Pacijent> ucitajPacijente() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_PACIJENTE, null);
        List<Pacijent> pacijenti = new ArrayList<>();
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        pacijenti = (List<Pacijent>) odgovor.getOdgovor();
        return pacijenti;
    }

    public void obrisiPacijenta(Pacijent p) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_PACIJENTA, p);
        posiljalac.posalji(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primi();
        
        if(odgovor.getOdgovor()==null) {
            System.out.println("USPEH");
        }else {
            System.out.println("GREŠKA");
            ((Exception)odgovor.getOdgovor()).printStackTrace();
            throw new Exception("GREŠKA");
        }
    }

    public List<Mesto> ucitajMesta() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_MESTA, null);
        List<Mesto> mesta = new ArrayList<>();
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        mesta = (List<Mesto>) odgovor.getOdgovor();
        return mesta;
    }

    public void dodajPacijenta(Pacijent p) throws Exception {
         Zahtev zahtev = new Zahtev(Operacija.DODAJ_PACIJENTA, p);
          posiljalac.posalji(zahtev);
          Odgovor odgovor = (Odgovor) primalac.primi();
           if(odgovor.getOdgovor()==null) {
            System.out.println("USPEH");
        }else {
            System.out.println("GREŠKA");
            Exception e = (Exception) odgovor.getOdgovor();
            throw e;
        }
    }

    public void azurirajPacijenta(Pacijent p) {
          Zahtev zahtev = new Zahtev(Operacija.AZURIRAJ_PACIJENTA, p);
          posiljalac.posalji(zahtev);
          Odgovor odgovor = (Odgovor) primalac.primi();
           if(odgovor.getOdgovor()==null) {
            System.out.println("USPEH");
            koordinator.Koordinator.getInstance().osveziFormu();
        }else {
            System.out.println("GREŠKA");
        }
    }

    public List<PlanIshrane> ucitajPlanove() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_PLANOVE_ISHRANE, null);
        List<PlanIshrane> planovi = new ArrayList<>();
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        planovi = (List<PlanIshrane>) odgovor.getOdgovor();
        return planovi;
    }

    public List<StavkaPlanaIshrane> ucitajStavke(int idPlanIshrane) {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_STAVKE, idPlanIshrane);
        List<StavkaPlanaIshrane> stavke = new ArrayList<>();
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        stavke = (List<StavkaPlanaIshrane>) odgovor.getOdgovor();
        System.out.println("KLASA Komunikacija: " + stavke);
        return stavke;
    }

    public List<Nutricionista> ucitajNutricioniste() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_NUTRICIONISTE, null);
        List<Nutricionista> nutricioniste = new ArrayList<>();
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        nutricioniste = (List<Nutricionista>) odgovor.getOdgovor();
        return nutricioniste;
    }

    public List<Jelo> ucitajJela() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_JELA, null);
        List<Jelo> jela = new ArrayList<>();
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        jela = (List<Jelo>) odgovor.getOdgovor();
        return jela;
    }

    public void dodajPlanIshrane(PlanIshrane planIshrane) {
         Zahtev zahtev = new Zahtev(Operacija.DODAJ_PLAN_ISHRANE, planIshrane);
          posiljalac.posalji(zahtev);
          Odgovor odgovor = (Odgovor) primalac.primi();
           if(odgovor.getOdgovor()==null) {
            System.out.println("USPEH");
        }else {
            System.out.println("GREŠKA");
        }
    }

    public void dodajSertifikat(Sertifikat s) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_SERTIFIKAT, s);
          posiljalac.posalji(zahtev);
          Odgovor odgovor = (Odgovor) primalac.primi();
           if(odgovor.getOdgovor()==null) {
            System.out.println("USPEH");
        }else {
            System.out.println("GREŠKA");
            Exception e = (Exception) odgovor.getOdgovor();
            throw e;
        } 
    }
    
    
    public List<PlanIshrane> ucitajPlanoveIshraneUlogovanog(Nutricionista ulogovani) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_PLANOVE_ISHRANE_ULOGOVANOG, ulogovani);
        List<PlanIshrane> evidencije = new ArrayList<>();
           
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primalac.primi();
        evidencije = (List<PlanIshrane>) odg.getOdgovor();
        return evidencije;
    }

    public void izmeniPlanIshrane(PlanIshrane planIshrane) {
        Zahtev zahtev = new Zahtev(Operacija.IZMENI_PLAN_ISHRANE, planIshrane);
        posiljalac.posalji(zahtev);

        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("USPEH");
        } else {
            System.out.println("GREŠKA");
        }
    }
    
    
    
}
