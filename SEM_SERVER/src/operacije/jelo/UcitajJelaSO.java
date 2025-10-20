/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.jelo;

import domen.Jelo;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author anas
 */
public class UcitajJelaSO extends ApstraktnaGenerickaOperacija {

     List<Jelo> jela;

    public List<Jelo> getJela() {
        return jela;
    }
    

    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        jela = broker.getAll((Jelo)param,null);
    }
    
}
