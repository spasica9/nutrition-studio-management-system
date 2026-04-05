/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.plan_ishrane;

import domen.PlanIshrane;
import domen.StatusStavke;
import domen.StavkaPlanaIshrane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author anas
 */
public class ZapamtiPlanIshraneSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof PlanIshrane)) {
            throw new Exception("Sistem ne može da kreira plan ishrane.");
        }
        
        PlanIshrane plan = (PlanIshrane) param;

        if (plan.getDatumIzrade() == null) {
            throw new Exception("Datum izrade plana je obavezan.");
        }
        if (plan.getPacijent() == null) {
            throw new Exception("Plan ishrane mora biti vezan za pacijenta.");
        }
        if (plan.getNutricionista() == null) {
            throw new Exception("Plan ishrane mora imati dodeljenog nutricionistu.");
        }
        if (plan.getUkupanIznosJela() < 0) {
            throw new Exception("Ukupan iznos plana ne može biti negativan.");
        }

        if (plan.getStavke() == null || plan.getStavke().isEmpty()) {
            throw new Exception("Plan ishrane mora imati barem jednu stavku.");
        }

        for (StavkaPlanaIshrane sp : plan.getStavke()) {

            if (sp.getJelo() == null) {
                throw new Exception("Svaka stavka plana mora imati izabrano jelo.");
            }
            if (sp.getKolicina() <= 0) {
                throw new Exception("Količina u stavkama mora biti veća od nule.");
            }
            if (sp.getDan() == null) {
                throw new Exception("Svaka stavka mora imati definisan dan.");
            }
            if (sp.getVremeObroka() == null) {
                throw new Exception("Svaka stavka mora imati definisano vreme obroka.");
            }
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        PlanIshrane planIshrane = (PlanIshrane) param;
        System.out.println("Iznos plana koji je stigao: " + planIshrane.getUkupanIznosJela());
        PreparedStatement ps = broker.add(param);
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        int id = rs.getInt(1);
        planIshrane.setIdPlanIshrane(id);
        for (StavkaPlanaIshrane sp : planIshrane.getStavke()) {
            if (sp.getStatus() == StatusStavke.OBRISANA) {
                continue; 
            }

            sp.setPlanIshrane(planIshrane);
            broker.add(sp);
        }
        }
    
}
