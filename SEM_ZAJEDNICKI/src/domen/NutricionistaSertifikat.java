/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

/**
 *
 * @author anas
 */
public class NutricionistaSertifikat implements ApstraktniDomenskiObjekat {
    
    private Nutricionista nutricionista;
    private Sertifikat sertifikat;
    private Date datumIzdavanja;

    public NutricionistaSertifikat() {
    }

    public NutricionistaSertifikat(Nutricionista nutricionista, Sertifikat sertifikat, Date datumIzdavanja) {
        this.nutricionista = nutricionista;
        this.sertifikat = sertifikat;
        this.datumIzdavanja = datumIzdavanja;
    }

    public Nutricionista getNutricionista() {
        return nutricionista;
    }

    public void setNutricionista(Nutricionista nutricionista) {
        this.nutricionista = nutricionista;
    }

    public Sertifikat getSertifikat() {
        return sertifikat;
    }

    public void setSertifikat(Sertifikat sertifikat) {
        this.sertifikat = sertifikat;
    }

    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(Date datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    
    @Override
    public String vratiNazivTabele() {
        return "nutricionista-sertifikat";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "nutricionista, sertifikat, datumIzdavanja";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return nutricionista.getIdNutricionista()+","+sertifikat.getIdSertifikat() + "'"+datumIzdavanja+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "nutricionista_sertifikat.nutricionista = " + nutricionista.getIdNutricionista() +
           " AND nutricionista_sertifikat.sertifikat = " + sertifikat.getIdSertifikat();
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "datumIzdavanja = '" + datumIzdavanja + "'";
    }

    @Override
    public String vratiVrednostZaSortiranje() {
        return "nutricionista_sertifikat.nutricionista";
    }
    
    
    
}
