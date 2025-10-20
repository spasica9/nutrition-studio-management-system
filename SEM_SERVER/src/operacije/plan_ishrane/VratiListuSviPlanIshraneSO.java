/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.plan_ishrane;

import domen.Pacijent;
import domen.PlanIshrane;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author anas
 */
public class VratiListuSviPlanIshraneSO extends ApstraktnaGenerickaOperacija {

    List<PlanIshrane> planovi;

    public List<PlanIshrane> getPlanovi() {
        return planovi;
    }
    
    
    
    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        String uslov = " JOIN nutricionista ON plan_ishrane.nutricionista = nutricionista.idNutricionista JOIN pacijent ON plan_ishrane.pacijent = pacijent.idPacijent JOIN mesto ON pacijent.mesto = mesto.idMesto";
        planovi = broker.getAll((PlanIshrane)param,uslov);
    }
}
