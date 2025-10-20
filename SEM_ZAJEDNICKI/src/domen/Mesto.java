/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author anas
 */
public class Mesto implements ApstraktniDomenskiObjekat {
    
    private int idMesto;
    private String nazivMesta;
    private String postanskiBroj;

    public Mesto() {
    }

    public Mesto(int idMesto, String nazivMesta, String postanskiBroj) {
        this.idMesto = idMesto;
        this.nazivMesta = nazivMesta;
        this.postanskiBroj = postanskiBroj;
    }

    public int getIdMesto() {
        return idMesto;
    }

    public void setIdMesto(int idMesto) {
        this.idMesto = idMesto;
    }

    public String getNazivMesta() {
        return nazivMesta;
    }

    public void setNazivMesta(String nazivMesta) {
        this.nazivMesta = nazivMesta;
    }

    public String getPostanskiBroj() {
        return postanskiBroj;
    }

    public void setPostanskiBroj(String postanskiBroj) {
        this.postanskiBroj = postanskiBroj;
    }

    @Override
    public String toString() {
        return nazivMesta;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Mesto other = (Mesto) obj;
        if (!Objects.equals(this.nazivMesta, other.nazivMesta)) {
            return false;
        }
        return Objects.equals(this.postanskiBroj, other.postanskiBroj);
    }

    @Override
    public String vratiNazivTabele() {
        return "mesto";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
    List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
    while(rs.next()){
        int idMesto = rs.getInt("mesto.idMesto");
        String nazivMesta = rs.getString("mesto.nazivMesta");
        String postanskiBroj = rs.getString("mesto.postanskiBroj");
        
        Mesto m = new Mesto(idMesto, nazivMesta, postanskiBroj);
        lista.add(m);
    }
    return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "nazivMesta, postanskiBroj";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+nazivMesta+"','"+postanskiBroj+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "mesto.idMesto = " + idMesto;
    }


    @Override
    public ApstraktniDomenskiObjekat vratiObjekatRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "nazivMesta = '" + nazivMesta + "', postanskiBroj = '" + postanskiBroj + "' ";
    }

    @Override
    public String vratiVrednostZaSortiranje() {
        return "mesto.idMesto";
    }
    
    
    
}
