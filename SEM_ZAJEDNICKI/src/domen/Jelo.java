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
public class Jelo implements ApstraktniDomenskiObjekat {
    
    private int idJelo;
    private String nazivJela;
    private String opis;
    private int kcalNa100g;
    private int cenaNa100g;

    public Jelo() {
    }

    public Jelo(int idJelo, String nazivJela, String opis, int kcalNa100g, int cenaNa100g) {
        this.idJelo = idJelo;
        this.nazivJela = nazivJela;
        this.opis = opis;
        this.kcalNa100g = kcalNa100g;
        this.cenaNa100g = cenaNa100g;
    }

    public int getIdJelo() {
        return idJelo;
    }

    public void setIdJelo(int idJelo) {
        this.idJelo = idJelo;
    }

    public String getNazivJela() {
        return nazivJela;
    }

    public void setNazivJela(String nazivJela) {
        this.nazivJela = nazivJela;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getKcalNa100g() {
        return kcalNa100g;
    }

    public void setKcalNa100g(int kcalNa100g) {
        this.kcalNa100g = kcalNa100g;
    }

    public int getCenaNa100g() {
        return cenaNa100g;
    }

    public void setCenaNa100g(int cenaNa100g) {
        this.cenaNa100g = cenaNa100g;
    }

    

    @Override
    public String toString() {
        return nazivJela;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Jelo other = (Jelo) obj;
        return Objects.equals(this.nazivJela, other.nazivJela);
    }

    @Override
    public String vratiNazivTabele() {
        return "jelo";
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "nazivJela, opis, kcalNa100g, cenaNa100g";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+nazivJela+"','"+opis+"',"+kcalNa100g+","+cenaNa100g;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "jelo.idJelo = " +idJelo;
    }


    @Override
    public String vratiVrednostiZaIzmenu() {
        return "nazivJela = '"+nazivJela+"', opis = '"+opis+"', kcalNa100g = "+kcalNa100g+", cenaNa100g = "+cenaNa100g;
    }


    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()){
            int idJelo = rs.getInt("jelo.idJelo");
            String nazivJela = rs.getString("jelo.nazivJela");
            String opis = rs.getString("jelo.opis");
            int kcalNa100g = rs.getInt("jelo.kcalNa100g");
            int cenaNa100g = rs.getInt("jelo.cenaNa100g");
            
            Jelo j = new Jelo(idJelo, nazivJela, opis, kcalNa100g, cenaNa100g);
            lista.add(j);
        }
        
        return lista;
    }

    
    @Override
    public ApstraktniDomenskiObjekat vratiObjekatRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostZaSortiranje() {
        return "jelo.idJelo";
    }
    
    
}
