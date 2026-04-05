/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.nutricionista;

import domen.Nutricionista;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author anas
 */
public class PrijaviNutricionistaSO extends ApstraktnaGenerickaOperacija {

    Nutricionista nutricionista;

    public Nutricionista getNutricionista() {
        return nutricionista;
    }
    
     @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Nutricionista)) {
        throw new Exception("Korisnicko ime i sifra nisu ispravni.");
        }

        Nutricionista n = (Nutricionista) param;

        if (n.getKorisnickoIme() == null || n.getKorisnickoIme().isEmpty()) {
            throw new Exception("Korisnicko ime ne sme biti prazno.");
        }

        if (n.getSifra() == null || n.getSifra().isEmpty()) {
            throw new Exception("Sifra ne sme biti prazna.");
        }

        if (n.getKorisnickoIme().length() > 30) {
            throw new Exception("Korisnicko ime je predugacko.");
        }
    }
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        List<Nutricionista> sviNutricionisti = broker.getAll((Nutricionista)param, null);
        System.out.println("KLASA LoginOperacija SO" + sviNutricionisti);
        
        if (sviNutricionisti.contains((Nutricionista)param)) {
        for (Nutricionista n : sviNutricionisti) {
            if (n.equals((Nutricionista)param)){
                nutricionista = n;
                return;
            }
        }
        }else {
            nutricionista=null;
        }
        
    }

}
