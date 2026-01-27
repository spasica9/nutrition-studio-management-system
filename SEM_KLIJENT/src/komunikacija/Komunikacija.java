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
        Zahtev zahtev = new Zahtev(Operacija.PRIJAVA, n);
        
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        n = (Nutricionista) odgovor.getOdgovor();
        System.out.println("KLASA Komunikacija primanje nutricioniste: " + n);
        
        return n;
    }

    public List<Pacijent> ucitajPacijente() {
        Zahtev zahtev = new Zahtev(Operacija.VRATI_LISTU_PACIJENATA, null);
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

         if (odgovor.getOdgovor() == null) {
             System.out.println("USPEH");
         } else {
             System.out.println("GREŠKA");
             Exception ex = (Exception) odgovor.getOdgovor();
             ex.printStackTrace();
             throw ex;
         }
     }
    public List<Mesto> ucitajMesta() {
        Zahtev zahtev = new Zahtev(Operacija.VRATI_LISTU_MESTA, null);
        List<Mesto> mesta = new ArrayList<>();
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        mesta = (List<Mesto>) odgovor.getOdgovor();
        return mesta;
    }

    public void dodajPacijenta(Pacijent p) throws Exception {
         Zahtev zahtev = new Zahtev(Operacija.ZAPAMTI_PACIJENTA, p);
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

       public void azurirajPacijenta(Pacijent p) throws Exception {
            Zahtev zahtev = new Zahtev(Operacija.PROMENI_PACIJENTA, p);
            posiljalac.posalji(zahtev);

            Odgovor odgovor = (Odgovor) primalac.primi();

            if (odgovor.getOdgovor() instanceof Exception) {
                System.out.println("GREŠKA");
                throw (Exception) odgovor.getOdgovor();
            }

            System.out.println("USPEH");
            koordinator.Koordinator.getInstance().osveziFormu();
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
        Zahtev zahtev = new Zahtev(Operacija.VRATI_LISTU_NUTRICIONISTA, null);
        List<Nutricionista> nutricioniste = new ArrayList<>();
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        nutricioniste = (List<Nutricionista>) odgovor.getOdgovor();
        return nutricioniste;
    }

    public List<Jelo> ucitajJela() {
        Zahtev zahtev = new Zahtev(Operacija.VRATI_LISTU_JELA, null);
        List<Jelo> jela = new ArrayList<>();
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        jela = (List<Jelo>) odgovor.getOdgovor();
        return jela;
    }

    public void dodajPlanIshrane(PlanIshrane planIshrane) {
         Zahtev zahtev = new Zahtev(Operacija.ZAPAMTI_PLAN_ISHRANE, planIshrane);
          posiljalac.posalji(zahtev);
          Odgovor odgovor = (Odgovor) primalac.primi();
           if(odgovor.getOdgovor()==null) {
            System.out.println("USPEH");
        }else {
            System.out.println("GREŠKA");
        }
    }

    public void dodajSertifikat(Sertifikat s) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.ZAPAMTI_SERTIFIKAT, s);
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
        Zahtev zahtev = new Zahtev(Operacija.PROMENI_PLAN_ISHRANE, planIshrane);
        posiljalac.posalji(zahtev);

        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("USPEH");
        } else {
            System.out.println("GREŠKA");
        }
    }
    
    public Pacijent ucitajPacijenta(Pacijent pacijent) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_PACIJENTA, pacijent);
        posiljalac.posalji(zahtev);

        Odgovor odg = (Odgovor) primalac.primi();
        Pacijent p = (Pacijent)odg.getOdgovor();
        return p;
    }
    
    public List<Pacijent> pronadjiPacijente(Pacijent p) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.PRONADJI_PACIJENTE, p);
        List<Pacijent> pacijenti = new ArrayList<>();
        posiljalac.posalji(zahtev);

        Odgovor odg = (Odgovor) primalac.primi();
        pacijenti = (List<Pacijent>) odg.getOdgovor();
        return pacijenti;
    }
    
    
    
}
