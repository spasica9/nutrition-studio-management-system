/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.plan_ishrane;

import domen.PlanIshrane;
import domen.StavkaPlanaIshrane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author anas
 */
public class KreirajPlanIshraneSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof PlanIshrane)) {
            throw new Exception("Sistem ne može da kreira plan ishrane.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        PlanIshrane planIshrane = (PlanIshrane) param;
        PreparedStatement ps = broker.add(param);
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        int id = rs.getInt(1);
        planIshrane.setIdPlanIshrane(id);
        
        for (StavkaPlanaIshrane sp : planIshrane.getStavke()) {
            sp.setPlanIshrane(planIshrane);
            broker.add(sp);
        }
    }
    
}
