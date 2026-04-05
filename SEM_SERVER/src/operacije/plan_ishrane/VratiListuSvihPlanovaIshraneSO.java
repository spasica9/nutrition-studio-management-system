/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.plan_ishrane;

import domen.ApstraktniDomenskiObjekat;
import domen.Nutricionista;
import domen.Pacijent;
import domen.PlanIshrane;
import java.util.ArrayList;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author anas
 */
public class VratiListuSvihPlanovaIshraneSO extends ApstraktnaGenerickaOperacija {

    List<PlanIshrane> planovi;

    public List<PlanIshrane> getPlanovi() {
        return planovi;
    }
    
    @Override
    protected void preduslovi(Object param) throws Exception {
    }
   @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        PlanIshrane pi = new PlanIshrane();
 
        String uslov = " JOIN nutricionista ON plan_ishrane.nutricionista = nutricionista.idNutricionista "
                     + " JOIN pacijent ON plan_ishrane.pacijent = pacijent.idPacijent "
                     + " JOIN mesto ON pacijent.mesto = mesto.idMesto"; 
        if (param instanceof Nutricionista) {
            Nutricionista n = (Nutricionista) param;
            uslov += " WHERE plan_ishrane.nutricionista = " + n.getIdNutricionista();
        }

        planovi = broker.getAll(pi, uslov);
        }
}
