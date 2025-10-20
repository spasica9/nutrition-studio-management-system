/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author anas
 */
public class Pacijent implements ApstraktniDomenskiObjekat {
    
    private int idPacijent;
    private String ime;
    private String prezime;
    private Date datumRodjenja;
    private String email;
    private TipIshrane tipIshrane;
    private Mesto mesto;

    public Pacijent() {
    }

    public Pacijent(int idPacijent, String ime, String prezime, Date datumRodjenja, String email, TipIshrane tipIshrane, Mesto mesto) {
        this.idPacijent = idPacijent;
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.email = email;
        this.tipIshrane = tipIshrane;
        this.mesto = mesto;
    }

    public int getIdPacijent() {
        return idPacijent;
    }

    public void setIdPacijent(int idPacijent) {
        this.idPacijent = idPacijent;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TipIshrane getTipIshrane() {
        return tipIshrane;
    }

    public void setTipIshrane(TipIshrane tipIshrane) {
        this.tipIshrane = tipIshrane;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
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
        final Pacijent other = (Pacijent) obj;
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        if (!Objects.equals(this.prezime, other.prezime)) {
            return false;
        }
        return Objects.equals(this.email, other.email);
    }

    @Override
    public String vratiNazivTabele() {
        return "pacijent";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()){
            int idPacijent = rs.getInt("pacijent.idPacijent");
            String ime = rs.getString("pacijent.ime");
            String prezime = rs.getString("pacijent.prezime");
            java.sql.Date sqlDate = rs.getDate("pacijent.datumRodjenja");
            java.util.Date datumRodjenja = new java.util.Date(sqlDate.getTime());
            String email = rs.getString("pacijent.email");
            TipIshrane tipIshrane = TipIshrane.valueOf(rs.getString("pacijent.tipIshrane"));
            
            int idMesto = rs.getInt("mesto.idMesto");
            String nazivMesta = rs.getString("mesto.nazivMesta");
            String postanskiBroj = rs.getString("mesto.postanskiBroj");
            Mesto m = new Mesto(idMesto, nazivMesta, postanskiBroj);
            Pacijent p = new Pacijent(idPacijent, ime, prezime, datumRodjenja, email, tipIshrane, m);
            lista.add(p);
        }
        
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime, prezime, datumRodjenja, email, tipIshrane, mesto";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        java.sql.Date sqlDate = new java.sql.Date(datumRodjenja.getTime());
        return "'"+ime+"','"+prezime+"','"+sqlDate+"','"+email+"','"+tipIshrane.name()+"',"+mesto.getIdMesto();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "pacijent.idPacijent = " + idPacijent;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        java.sql.Date sqlDate = new java.sql.Date(datumRodjenja.getTime());
        return "ime = '" + ime + "', prezime = '" + prezime + "', datumRodjenja = '" + sqlDate + 
           "', email = '" + email + "', tipIshrane = '" + tipIshrane.name() + "', mesto = " + mesto.getIdMesto();
    }

    @Override
    public String vratiVrednostZaSortiranje() {
        return "pacijent.idPacijent";
    }
    
    
    
}
