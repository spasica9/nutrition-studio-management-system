/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.plan_ishrane;

import domen.Nutricionista;
import domen.PlanIshrane;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author anas
 */
public class VratiListuPlanIshraneSO extends ApstraktnaGenerickaOperacija {

    private List<PlanIshrane> lista;
    
   @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Nutricionista)) {
            throw new Exception("Parametar mora biti Nutricionista.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Nutricionista n = (Nutricionista) param;

        String uslov = " JOIN pacijent ON plan_ishrane.pacijent = pacijent.idPacijent " +
                       "JOIN nutricionista ON plan_ishrane.nutricionista= nutricionista.idNutricionista " +
                       "JOIN mesto ON pacijent.mesto = mesto.idMesto " +
                       "WHERE plan_ishrane.nutricionista= " + n.getIdNutricionista();

        lista = broker.getAll(new PlanIshrane(), uslov);
    }


    public List<PlanIshrane> getLista() {
        return lista;
    }
    
}
