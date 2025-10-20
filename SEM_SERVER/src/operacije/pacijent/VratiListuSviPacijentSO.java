/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.pacijent;

import domen.Pacijent;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author anas
 */
public class VratiListuSviPacijentSO extends ApstraktnaGenerickaOperacija {

    List<Pacijent> pacijenti;

    public List<Pacijent> getPacijenti() {
        return pacijenti;
    }
    
    
    
    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        String uslov = " JOIN mesto ON pacijent.mesto = mesto.idMesto";
        pacijenti = broker.getAll((Pacijent)param,uslov);
    }
    
}
