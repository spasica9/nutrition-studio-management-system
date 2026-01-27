/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.pacijent;

import domen.ApstraktniDomenskiObjekat;
import domen.Pacijent;
import java.util.ArrayList;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author anas
 */
public class UcitajPacijentaSO extends ApstraktnaGenerickaOperacija{
    
 private Pacijent ucitani;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (!(param instanceof Pacijent)) {
            throw new Exception("Neispravan parametar za učitavanje.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Pacijent p = (Pacijent) param;
        
        String uslov = " AS p JOIN mesto m ON p.mesto = m.idMesto WHERE p.idPacijent = " + p.getIdPacijent();
        
        List<ApstraktniDomenskiObjekat> lista = broker.getAll(new Pacijent(), uslov);

        if (lista != null && !lista.isEmpty()) {
            this.ucitani = (Pacijent) lista.get(0);
        } else {
            throw new Exception("Pacijent više ne postoji u bazi.");
        }
    }

    public Pacijent getUcitani() { return ucitani; }
}
