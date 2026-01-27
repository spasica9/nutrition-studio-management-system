/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import domen.Jelo;
import domen.Mesto;
import domen.Nutricionista;
import domen.Pacijent;
import domen.PlanIshrane;
import domen.Sertifikat;
import domen.StavkaPlanaIshrane;
import exception.PacijentNeMozeDaSeObriseException;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.Odgovor;
import komunikacija.Posiljalac;
import komunikacija.Primalac;
import komunikacija.Zahtev;
import kontroler.Kontroler;
import exception.PacijentVecPostojiException;
import exception.SertifikatVecPostojiException;

/**
 *
 * @author anas
 */
public class ObradaKlijentskihZahteva extends Thread {
    
    Socket socket;
    Posiljalac posiljalac;
    Primalac primalac;
    boolean kraj = false;

    public ObradaKlijentskihZahteva(Socket socket) {
        this.socket = socket;
        posiljalac = new Posiljalac(socket);
        primalac = new Primalac(socket);
    }
    
     @Override
    public void run() {
        while(!kraj){
            try {
                Zahtev zahtev = (Zahtev) primalac.primi();
                Odgovor odgovor = new Odgovor();
                switch(zahtev.getOperacija()){
                    case PRIJAVA:
                        Nutricionista n = (Nutricionista) zahtev.getParametar();
                        n = Kontroler.getInstance().login(n);
                        odgovor.setOdgovor(n);
//                        try {
//
//                        } catch (LoginException ivp) {
//
//                            odgovor.setOdgovor(ivp);
//
//                        } catch (Exception excp) {
//
//                            odgovor.setOdgovor(excp);
//                        }
                        break;
                    case VRATI_LISTU_PACIJENATA:
                        List<Pacijent> pacijenti = Kontroler.getInstance().ucitajPacijente();
                        odgovor.setOdgovor(pacijenti);
                        break;
                    case OBRISI_PACIJENTA:
                        Pacijent oPacijent = (Pacijent) zahtev.getParametar();
                        
                        try {
                            Kontroler.getInstance().obrisiPacijenta(oPacijent);
                            odgovor.setOdgovor(null);
                        } catch (PacijentNeMozeDaSeObriseException opexc) {
                            odgovor.setOdgovor(opexc);
                        } catch (Exception ex) {
                            odgovor.setOdgovor(ex);
                        }
                        break;
                    case VRATI_LISTU_MESTA:
                        List<Mesto> mesta = Kontroler.getInstance().ucitajMesta();
                        odgovor.setOdgovor(mesta);
                        break;
                    case ZAPAMTI_PACIJENTA:
                        Pacijent pacijent = (Pacijent) zahtev.getParametar();
                        try {
                            Kontroler.getInstance().dodajPacijenta(pacijent);
                            odgovor.setOdgovor(null);
                        } catch (PacijentVecPostojiException pvp) {

                            odgovor.setOdgovor(pvp);

                        } catch (Exception excp) {

                            odgovor.setOdgovor(excp);
                        }

                        break;
                    case PROMENI_PACIJENTA:
                        Pacijent aPacijent = (Pacijent) zahtev.getParametar();
                        try {
                            Kontroler.getInstance().azurirajPacijenta(aPacijent);
                            odgovor.setOdgovor(null);
                        } catch (PacijentVecPostojiException pvp) {

                            odgovor.setOdgovor(pvp);

                        } catch (Exception excp) {

                            odgovor.setOdgovor(excp);
                        }
                    case UCITAJ_PLANOVE_ISHRANE:
                        List<PlanIshrane> planovi = Kontroler.getInstance().ucitajPlanoveIshrane();
                        System.out.println("KLASA OKZ: " + planovi);
                        odgovor.setOdgovor(planovi);
                        break;
                    case UCITAJ_STAVKE:
                        List<StavkaPlanaIshrane> stavke = Kontroler.getInstance().ucitajStavke((int)zahtev.getParametar());
                        System.out.println("KLASA OKZ: " + stavke);
                        odgovor.setOdgovor(stavke);
                        break;
                    case VRATI_LISTU_NUTRICIONISTA:
                        List<Nutricionista> nutricioniste = Kontroler.getInstance().ucitajNutricioniste();
                        odgovor.setOdgovor(nutricioniste);
                        break;
                    case VRATI_LISTU_JELA:
                        List<Jelo> jela = Kontroler.getInstance().ucitajJela();
                        odgovor.setOdgovor(jela);
                        break;  
                    case ZAPAMTI_PLAN_ISHRANE:
                        PlanIshrane planIshrane = (PlanIshrane) zahtev.getParametar();
                        Kontroler.getInstance().dodajPlanIshrane(planIshrane);
                        break; 
                     case ZAPAMTI_SERTIFIKAT:
                         Sertifikat sertifikat = (Sertifikat) zahtev.getParametar();
                        try {
                            Kontroler.getInstance().dodajSertifikat(sertifikat);
                            odgovor.setOdgovor(null);
                        } catch (SertifikatVecPostojiException svp) {

                            odgovor.setOdgovor(svp);

                        } catch (Exception excp) {

                            odgovor.setOdgovor(excp);
                        }
                        break;
                        case UCITAJ_PLANOVE_ISHRANE_ULOGOVANOG:
                          Nutricionista ulogovani = (Nutricionista) zahtev.getParametar();
                          List<PlanIshrane> planoviIshraneUlogovanog = Kontroler.getInstance().ucitajPlanoveIshraneUlogovanog(ulogovani);  
                          odgovor.setOdgovor(planoviIshraneUlogovanog);
                          break;
                       case PROMENI_PLAN_ISHRANE:
                            try {
                                PlanIshrane planIshrane1 = (PlanIshrane) zahtev.getParametar();
                                Kontroler.getInstance().izmeniPlanIshrane(planIshrane1);
                                odgovor.setOdgovor(null); 
                            } catch(Exception ex) {
                                ex.printStackTrace();
                                odgovor.setOdgovor(ex); 
                            }
                            break;
                       case UCITAJ_PACIJENTA:
                          Pacijent pacijent1 = (Pacijent) zahtev.getParametar();
                          Pacijent ucitaniPacijent = Kontroler.getInstance().ucitajPacijenta(pacijent1);  
                          odgovor.setOdgovor(ucitaniPacijent);
                          break;
                       case PRONADJI_PACIJENTE:
                           Pacijent pacijent2 = (Pacijent) zahtev.getParametar();
                           List<Pacijent> pacijentiImePrezime = Kontroler.getInstance().pronadjiPacijentePoImenuPrezimenu(pacijent2);  
                           odgovor.setOdgovor(pacijentiImePrezime);
                           break;
                    default: System.out.println("Greska! Operacija nepostojeca");
                }
             posiljalac.posalji(odgovor);
            } catch (Exception ex) {
                Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void prekini(){
        kraj = true;
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        interrupt();
    }
    
    
}
