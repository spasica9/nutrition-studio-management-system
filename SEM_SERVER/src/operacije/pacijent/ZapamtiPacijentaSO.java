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

        String uslov = " JOIN mesto ON pacijent.mesto = mesto.idMesto WHERE email = '" + p.getEmail() + "'";
        Pacijent postojeci = (Pacijent) broker.get(p, uslov);
        if (postojeci != null) {
            throw new PacijentVecPostojiException("Sistem ne može da kreira pacijenta");
        }
        

    }


    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.add((Pacijent)param);
        
    }
    
}
