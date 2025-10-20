/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anas
 */
public class StavkaPlanaIshrane implements ApstraktniDomenskiObjekat {
    
    private PlanIshrane planIshrane;
    private int rb;
    private Dan dan;
    private VremeObroka vremeObroka;
    private double kolicina;
    private int jedKcal;
    private int jedCena;
    private double ukupnoKcal;
    private double iznos;
    private Jelo jelo;
    private StatusStavke status;

    public StavkaPlanaIshrane() {
    }

    public StavkaPlanaIshrane(int rb, Dan dan, VremeObroka vremeObroka, double kolicina, int jedKcal, int jedCena, double ukupnoKcal,
            double iznos, Jelo jelo) {
        this.rb = rb;
        this.dan = dan;
        this.vremeObroka = vremeObroka;
        this.kolicina = kolicina;
        this.jedKcal = jedKcal;
        this.jedCena = jedCena;
        this.ukupnoKcal = ukupnoKcal;
        this.iznos = iznos;
        this.jelo = jelo;
        this.status = StatusStavke.NEIZMENJENA;
    }
    
    

    public StavkaPlanaIshrane(PlanIshrane planIshrane, int rb, Dan dan, VremeObroka vremeObroka, double kolicina, int jedKcal, int jedCena, double ukupnoKcal, double iznos, Jelo jelo) {
        this.planIshrane = planIshrane;
        this.rb = rb;
        this.dan = dan;
        this.vremeObroka = vremeObroka;
        this.kolicina = kolicina;
        this.jedKcal = jedKcal;
        this.jedCena = jedCena;
        this.ukupnoKcal = ukupnoKcal;
        this.iznos = iznos;
        this.jelo = jelo;
    }

    public PlanIshrane getPlanIshrane() {
        return planIshrane;
    }

    public void setPlanIshrane(PlanIshrane planIshrane) {
        this.planIshrane = planIshrane;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public Dan getDan() {
        return dan;
    }

    public void setDan(Dan dan) {
        this.dan = dan;
    }

    public VremeObroka getVremeObroka() {
        return vremeObroka;
    }

    public void setVremeObroka(VremeObroka vremeObroka) {
        this.vremeObroka = vremeObroka;
    }

    public double getKolicina() {
        return kolicina;
    }

    public void setKolicina(double kolicina) {
        this.kolicina = kolicina;
    }

    public int getJedKcal() {
        return jedKcal;
    }

    public void setJedKcal(int jedKcal) {
        this.jedKcal = jedKcal;
    }

    public int getJedCena() {
        return jedCena;
    }

    public void setJedCena(int jedCena) {
        this.jedCena = jedCena;
    }

    public double getUkupnoKcal() {
        return ukupnoKcal;
    }

    public void setUkupnoKcal(double ukupnoKcal) {
        this.ukupnoKcal = ukupnoKcal;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    public Jelo getJelo() {
        return jelo;
    }

    public void setJelo(Jelo jelo) {
        this.jelo = jelo;
    }

     public StatusStavke getStatus() {
        return status;
    }

    public void setStatus(StatusStavke status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return "StavkaPlanaIshrane{" + "dan=" + dan + ", vremeObroka=" + vremeObroka + ", kolicina=" + kolicina + ", jelo=" + jelo + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final StavkaPlanaIshrane other = (StavkaPlanaIshrane) obj;
        if (Double.doubleToLongBits(this.kolicina) != Double.doubleToLongBits(other.kolicina)) {
            return false;
        }
        if (this.jedKcal != other.jedKcal) {
            return false;
        }
        if (this.jedCena != other.jedCena) {
            return false;
        }
        if (this.dan != other.dan) {
            return false;
        }
        return this.vremeObroka == other.vremeObroka;
    }

    @Override
    public String vratiNazivTabele() {
        return "stavka_plana_ishrane";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
           
            int rb = rs.getInt("stavka_plana_ishrane.rb");
            String danStr = rs.getString("stavka_plana_ishrane.dan");
            Dan dan = Dan.valueOf(danStr.toUpperCase());
            String vremeStr = rs.getString("stavka_plana_ishrane.vremeObroka");
            VremeObroka vreme = VremeObroka.valueOf(vremeStr.toUpperCase());
            double kolicina = rs.getDouble("stavka_plana_ishrane.kolicina");
            int jedKcal = rs.getInt("stavka_plana_ishrane.jedKcal");
            int jedCena = rs.getInt("stavka_plana_ishrane.jedCena");
            double ukupnoKcal = rs.getDouble("stavka_plana_ishrane.ukupnoKcal");
            double iznos = rs.getDouble("stavka_plana_ishrane.iznos");
            
            int idJelo = rs.getInt("jelo.idJelo");
            String nazivJela = rs.getString("jelo.nazivJela");
            String opis = rs.getString("jelo.opis");
            int kcalNa100g = rs.getInt("jelo.kcalNa100g");
            int cenaNa100g = rs.getInt("jelo.cenaNa100g");
            Jelo jelo = new Jelo(idJelo, nazivJela, opis, kcalNa100g, cenaNa100g);
            
            StavkaPlanaIshrane stavka = new StavkaPlanaIshrane(rb, dan, vreme, kolicina, jedKcal, jedCena, ukupnoKcal, iznos, jelo);
            
            stavka.setStatus(StatusStavke.POSTOJECA);
            lista.add(stavka);
        }

        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "rb, idPlanIshrane, dan, vremeObroka, kolicina, jedKcal, jedCena, ukupnoKcal, iznos, jelo";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return rb + ", " + planIshrane.getIdPlanIshrane()+ ", '"+dan.name()+"','"+vremeObroka.name()+"',"+kolicina+","+jedKcal+","+jedCena+","+ukupnoKcal+","+
                iznos+","+jelo.getIdJelo();
    }

    @Override
    public String vratiPrimarniKljuc() {
    return "stavka_plana_ishrane.idPlanIshrane = " + planIshrane.getIdPlanIshrane() + " AND stavka_plana_ishrane.rb = " + rb;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   @Override
    public String vratiVrednostiZaIzmenu() {
    return "rb = " + rb + ", idPlanIshrane = " + planIshrane.getIdPlanIshrane() + ", dan = '" + dan.name() + "', vremeObroka = '" + vremeObroka.name() + "', kolicina = " + kolicina +
           ", jedKcal = " + jedKcal + ", jedCena = " + jedCena + ", ukupnoKcal = " + ukupnoKcal + ", iznos = " + iznos +
           ", jelo = " + jelo.getIdJelo();
    }

    @Override
    public String vratiVrednostZaSortiranje() {
        return "stavka_plana_ishrane.rb";
    }
    
    
}
