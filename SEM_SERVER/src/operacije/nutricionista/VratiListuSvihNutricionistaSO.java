/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.nutricionista;

import domen.Mesto;
import domen.Nutricionista;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author anas
 */
public class VratiListuSvihNutricionistaSO extends ApstraktnaGenerickaOperacija {

    List<Nutricionista> nutricioniste;

    public List<Nutricionista> getNutricioniste() {
        return nutricioniste;
    }
    

    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        nutricioniste = broker.getAll((Nutricionista)param,null);
    }
    
}
