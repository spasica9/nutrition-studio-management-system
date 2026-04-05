/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.plan_ishrane;

import domen.ApstraktniDomenskiObjekat;
import domen.PlanIshrane;
import java.util.ArrayList;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author anas
 */

public class PronadjiPlanoveIshraneSO extends ApstraktnaGenerickaOperacija {

    private List<PlanIshrane> lista;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof PlanIshrane)) {
            throw new Exception("Sistem ne može da pronađe planove ishrane.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
       PlanIshrane filter = (PlanIshrane) param;

        String uslov = " JOIN nutricionista ON plan_ishrane.nutricionista = nutricionista.idNutricionista "
                     + " JOIN pacijent ON plan_ishrane.pacijent = pacijent.idPacijent "
                     + " JOIN mesto ON pacijent.mesto = mesto.idMesto "
                     + " WHERE 1=1";
        if (filter.getNutricionista() != null && filter.getNutricionista().getIdNutricionista() != -1) {
            uslov += " AND plan_ishrane.nutricionista = " + filter.getNutricionista().getIdNutricionista();
        }

        if (filter.getPacijent() != null && filter.getPacijent().getIdPacijent() != -1) {
            uslov += " AND pacijent.idPacijent = " + filter.getPacijent().getIdPacijent();
        }

        List<ApstraktniDomenskiObjekat> rezultati = broker.getAll(filter, uslov);

        lista = new ArrayList<>();
        for (ApstraktniDomenskiObjekat ado : rezultati) {
            lista.add((PlanIshrane) ado);
        }
    }

    public List<PlanIshrane> getLista() {
        return lista;
    }
}

