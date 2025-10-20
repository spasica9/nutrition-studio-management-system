/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author anas
 */
public class PlanIshrane implements ApstraktniDomenskiObjekat {
    
    private int idPlanIshrane;
    private String nazivPlana;
    private double cenaPlana;
    private Date datumIzrade;
    private double ukupanIznosJela;
    private Nutricionista nutricionista;
    private Pacijent pacijent;
    private List<StavkaPlanaIshrane> stavke = new ArrayList<>();

    public PlanIshrane() {
    }

    public PlanIshrane(int idPlanIshrane) {
        this.idPlanIshrane = idPlanIshrane;
    }

    
    
    public PlanIshrane(int idPlanIshrane, String nazivPlana, double cenaPlana, Date datumIzrade, double ukupanIznosJela, 
            Nutricionista nutricionista, Pacijent pacijent, List<StavkaPlanaIshrane> stavke) {
        this.idPlanIshrane = idPlanIshrane;
        this.nazivPlana = nazivPlana;
        this.cenaPlana = cenaPlana;
        this.datumIzrade = datumIzrade;
        this.ukupanIznosJela = ukupanIznosJela;
        this.nutricionista = nutricionista;
        this.pacijent = pacijent;
        this.stavke = stavke;
    }

    public int getIdPlanIshrane() {
        return idPlanIshrane;
    }

    public void setIdPlanIshrane(int idPlanIshrane) {
        this.idPlanIshrane = idPlanIshrane;
    }

    public String getNazivPlana() {
        return nazivPlana;
    }

    public void setNazivPlana(String nazivPlana) {
        this.nazivPlana = nazivPlana;
    }

    public double getCenaPlana() {
        return cenaPlana;
    }

    public void setCenaPlana(double cenaPlana) {
        this.cenaPlana = cenaPlana;
    }

    public Date getDatumIzrade() {
        return datumIzrade;
    }

    public void setDatumIzrade(Date datumIzrade) {
        this.datumIzrade = datumIzrade;
    }

    public double getUkupanIznosJela() {
        return ukupanIznosJela;
    }

    public void setUkupanIznosJela(double ukupanIznosJela) {
        this.ukupanIznosJela = ukupanIznosJela;
    }

    public Nutricionista getNutricionista() {
        return nutricionista;
    }

    public void setNutricionista(Nutricionista nutricionista) {
        this.nutricionista = nutricionista;
    }

    public Pacijent getPacijent() {
        return pacijent;
    }

    public void setPacijent(Pacijent pacijent) {
        this.pacijent = pacijent;
    }

    public List<StavkaPlanaIshrane> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaPlanaIshrane> stavke) {
        this.stavke = stavke;
    }

    @Override
    public String toString() {
        return "PlanIshrane{" + "nazivPlana=" + nazivPlana + ", cenaPlana=" + cenaPlana + ", datumIzrade=" + datumIzrade + '}';
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
        final PlanIshrane other = (PlanIshrane) obj;
        if (Double.doubleToLongBits(this.ukupanIznosJela) != Double.doubleToLongBits(other.ukupanIznosJela)) {
            return false;
        }
        if (!Objects.equals(this.nazivPlana, other.nazivPlana)) {
            return false;
        }
        return Objects.equals(this.datumIzrade, other.datumIzrade);
    }

    @Override
    public String vratiNazivTabele() {
        return "plan_ishrane";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int idPlanIshrane = rs.getInt("plan_ishrane.idPlanIshrane");
            String nazivPlana = rs.getString("plan_ishrane.nazivPlana");
            double cenaPlana = rs.getDouble("plan_ishrane.cenaPlana");
            java.sql.Date sqlDate = rs.getDate("plan_ishrane.datumIzrade");
            java.util.Date datumIzrade = (sqlDate != null) ? new java.util.Date(sqlDate.getTime()) : null;
            double ukupanIznosJela = rs.getDouble("plan_ishrane.ukupanIznosJela");

           
            int idPacijent = rs.getInt("pacijent.idPacijent");
            String ime = rs.getString("pacijent.ime");
            String prezime = rs.getString("pacijent.prezime");
            java.sql.Date sqlDatePacijent = rs.getDate("pacijent.datumRodjenja");
            java.util.Date datumRodjenja = (sqlDatePacijent != null) ? new java.util.Date(sqlDatePacijent.getTime()) : null;
            String email = rs.getString("pacijent.email");
            TipIshrane tipIshrane = TipIshrane.valueOf(rs.getString("pacijent.tipIshrane"));

            int idMesto = rs.getInt("mesto.idMesto");
            String nazivMesta = rs.getString("mesto.nazivMesta");
            String postanskiBroj = rs.getString("mesto.postanskiBroj");
            Mesto mesto = new Mesto(idMesto, nazivMesta, postanskiBroj);

            Pacijent pacijent = new Pacijent(idPacijent, ime, prezime, datumRodjenja, email, tipIshrane, mesto);

            
            int idNutricionista = rs.getInt("nutricionista.idNutricionista");
            String imeN = rs.getString("nutricionista.ime");
            String prezimeN = rs.getString("nutricionista.prezime");
            String korisnickoIme = rs.getString("nutricionista.korisnickoIme");
            String sifra = rs.getString("nutricionista.sifra");
            String emailN = rs.getString("nutricionista.email");
            
            Nutricionista nutricionista = new Nutricionista(idNutricionista, imeN, prezimeN, korisnickoIme, sifra, emailN);

           
            PlanIshrane plan = new PlanIshrane(idPlanIshrane, nazivPlana, cenaPlana, datumIzrade, ukupanIznosJela,
                                               nutricionista, pacijent, null); 
            lista.add(plan);
            }
        return lista;
    }


    @Override
    public String vratiKoloneZaUbacivanje() {
        return "nazivPlana, cenaPlana, datumIzrade, ukupanIznosJela, nutricionista, pacijent";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        java.sql.Date sqlDate = new java.sql.Date(datumIzrade.getTime());
        return "'"+nazivPlana+"',"+cenaPlana+",'"+sqlDate+"',"+ukupanIznosJela+","+
                nutricionista.getIdNutricionista()+","+pacijent.getIdPacijent();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "plan_ishrane.idPlanIshrane = " + idPlanIshrane;
    }


    @Override
    public ApstraktniDomenskiObjekat vratiObjekatRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        java.sql.Date sqlDate = new java.sql.Date(datumIzrade.getTime());
        return "nazivPlana = '" + nazivPlana + 
           "', cenaPlana = " + cenaPlana + ", datumIzrade = '" + sqlDate + 
           "', ukupanIznosJela = " + ukupanIznosJela + ", nutricionista = " + nutricionista.getIdNutricionista() +
           ", pacijent = " + pacijent.getIdPacijent();
    }

    @Override
    public String vratiVrednostZaSortiranje() {
        return "plan_ishrane.idPlanIshrane";
    }

    
    
    
}
