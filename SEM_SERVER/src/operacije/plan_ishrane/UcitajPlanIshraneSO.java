/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.plan_ishrane;

import domen.ApstraktniDomenskiObjekat;
import domen.PlanIshrane;
import domen.StatusStavke;
import domen.StavkaPlanaIshrane;
import java.util.ArrayList;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author anas
 */
public class UcitajPlanIshraneSO extends ApstraktnaGenerickaOperacija {

    private PlanIshrane ucitaniPlan;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof PlanIshrane)) {
            throw new Exception("Sistem ne može da učita plan ishrane.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        PlanIshrane filter = (PlanIshrane) param;

        String uslovZaPlan = " JOIN nutricionista ON plan_ishrane.nutricionista = nutricionista.idNutricionista "
                           + " JOIN pacijent ON plan_ishrane.pacijent = pacijent.idPacijent "
                           + " JOIN mesto ON pacijent.mesto = mesto.idMesto "
                           + " WHERE plan_ishrane.idPlanIshrane = " + filter.getIdPlanIshrane();

        ucitaniPlan = (PlanIshrane) broker.get(filter, uslovZaPlan);

        if (ucitaniPlan != null) {
            StavkaPlanaIshrane stavkaFilter = new StavkaPlanaIshrane();

            String uslov = " JOIN jelo ON stavka_plana_ishrane.jelo = jelo.idJelo " +
                           " WHERE stavka_plana_ishrane.idPlanIshrane = " + ucitaniPlan.getIdPlanIshrane();

            List<ApstraktniDomenskiObjekat> listaStavki = broker.getAll(stavkaFilter, uslov);

            List<StavkaPlanaIshrane> stavke = new ArrayList<>();
            for (ApstraktniDomenskiObjekat ado : listaStavki) {
                StavkaPlanaIshrane s = (StavkaPlanaIshrane) ado;
                s.setPlanIshrane(ucitaniPlan);
                s.setStatus(StatusStavke.POSTOJECA);
                stavke.add(s);
            }
            ucitaniPlan.setStavke(stavke);
        }
    }

    public PlanIshrane getUcitaniPlan() {
        return ucitaniPlan;
    }
}
