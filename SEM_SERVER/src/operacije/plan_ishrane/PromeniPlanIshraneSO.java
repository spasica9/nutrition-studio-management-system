/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.plan_ishrane;

import domen.PlanIshrane;
import domen.StavkaPlanaIshrane;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author anas
 */
public class PromeniPlanIshraneSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (!(param instanceof PlanIshrane)) {
            throw new Exception("Sistem ne može da zapamti plan ishrane.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        PlanIshrane pi = (PlanIshrane) param;
        broker.edit(pi); 

        List<StavkaPlanaIshrane> stavke = pi.getStavke();
        if (stavke != null) {
            for (StavkaPlanaIshrane s : stavke) {
                s.setPlanIshrane(pi);

                if (s.getStatus() == null) continue;

                switch (s.getStatus()) {
                    case NOVA:
                        broker.add(s);
                        break;
                    case OBRISANA:
                        broker.delete(s);
                        break;
                    case IZMENJENA:
                        broker.edit(s);  
                        break;
                    case POSTOJECA:
                        break;
                }
            }
        }
    }
    
}
