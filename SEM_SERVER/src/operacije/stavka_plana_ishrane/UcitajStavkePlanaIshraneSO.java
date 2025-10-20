/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.stavka_plana_ishrane;

import domen.PlanIshrane;
import domen.StavkaPlanaIshrane;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author anas
 */
public class UcitajStavkePlanaIshraneSO extends ApstraktnaGenerickaOperacija {

    List<StavkaPlanaIshrane> stavke;

    public List<StavkaPlanaIshrane> getStavke() {
        return stavke;
    }
    
    
    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        String uslov = " JOIN jelo ON stavka_plana_ishrane.jelo = jelo.idJelo WHERE stavka_plana_ishrane.idPlanIshrane = " + (int)param + " ";
        stavke = broker.getAll(new StavkaPlanaIshrane(),uslov);
    }
    
}
