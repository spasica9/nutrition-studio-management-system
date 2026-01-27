/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.pacijent;

import domen.ApstraktniDomenskiObjekat;
import domen.Pacijent;
import java.util.ArrayList;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author anas
 */
public class PronadjiPacijenteSO extends ApstraktnaGenerickaOperacija {

    private List<Pacijent> lista;

    @Override
    protected void preduslovi(Object param) throws Exception {
           if (param == null || !(param instanceof Pacijent)) { // Promijeni String u Pacijent
        throw new Exception("Sistem ne može da pronađe pacijente (neispravan parametar).");
    }
    }
    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
            Pacijent p = (Pacijent) param;
            StringBuilder kriterijum = new StringBuilder("1=1"); 

            if (p.getIme() != null && !p.getIme().isEmpty()) {
                kriterijum.append(" AND pacijent.ime LIKE '").append(p.getIme()).append("%'");
            }
            if (p.getPrezime() != null && !p.getPrezime().isEmpty()) {
                kriterijum.append(" AND pacijent.prezime LIKE '").append(p.getPrezime()).append("%'");
            }
            if (p.getMesto() != null) {
                kriterijum.append(" AND pacijent.mesto = ").append(p.getMesto().getIdMesto());
            }
            if (p.getTipIshrane() != null) {
                kriterijum.append(" AND pacijent.tipIshrane = '").append(p.getTipIshrane()).append("'");
            }

            String uslov = " JOIN mesto ON pacijent.mesto = mesto.idMesto WHERE " + kriterijum.toString();

            List<ApstraktniDomenskiObjekat> pacijenti = broker.getAll(new Pacijent(), uslov);

            lista = new ArrayList<>();
            for (ApstraktniDomenskiObjekat ado : pacijenti) {
                lista.add((Pacijent) ado);
            }

    }
    
    public List<Pacijent> getLista() {
        return lista;
    }
    
}
