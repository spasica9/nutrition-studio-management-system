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
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        PlanIshrane planIshrane = (PlanIshrane) param;
        System.out.println("DEBUG: Iznos plana koji je stigao: " + planIshrane.getUkupanIznosJela());
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
