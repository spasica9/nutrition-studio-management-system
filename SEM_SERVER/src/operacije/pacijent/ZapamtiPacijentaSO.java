/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.pacijent;

import domen.Pacijent;
import exception.PacijentVecPostojiException;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author anas
 */
public class ZapamtiPacijentaSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
       if (param == null || !(param instanceof Pacijent)) {
        throw new Exception("Sistem ne može da kreira pacijenta");
        }

        Pacijent p = (Pacijent) param;

        if (p.getIme() == null || p.getIme().isEmpty()) throw new Exception("Ime je obavezno");
        if (p.getPrezime() == null || p.getPrezime().isEmpty()) throw new Exception("Prezime je obavezno");
        if (p.getEmail() == null || p.getEmail().isEmpty()) throw new Exception("Email je obavezan");
        if (p.getDatumRodjenja() == null) throw new Exception("Datum rodjenja je obavezan");
        if (p.getMesto() == null) throw new Exception("Mesto je obavezno");
        if (p.getTipIshrane() == null) throw new Exception("Tip ishrane je obavezan");

        String uslov = " JOIN mesto ON pacijent.mesto = mesto.idMesto WHERE email = '" + p.getEmail() + "'";
        Pacijent postojeci = (Pacijent) broker.get(p, uslov);
        if (postojeci != null) {
            throw new Exception("Sistem ne može da kreira pacijenta. Pacijent sa tim emailom već postoji.");
        }
    }


    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.add((Pacijent)param);
        
    }
    
}
