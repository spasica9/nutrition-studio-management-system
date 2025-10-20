/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.pacijent;

import domen.Pacijent;
import exception.PacijentNeMozeDaSeObriseException;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author anas
 */
public class ObrisiPacijentaSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param==null || !(param instanceof Pacijent)) {
            throw new PacijentNeMozeDaSeObriseException ("Sistem ne može da nađe pacijenta.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.delete((Pacijent)param);
    }
    
}
