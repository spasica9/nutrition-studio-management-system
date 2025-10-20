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
public class Sertifikat implements ApstraktniDomenskiObjekat {
    
    private int idSertifikat;
    private String nazivSertifikata;
    private String nazivInstitucije;

    public Sertifikat() {
    }

    public Sertifikat(int idSertifikat, String nazivSertifikata, String nazivInstitucije) {
        this.idSertifikat = idSertifikat;
        this.nazivSertifikata = nazivSertifikata;
        this.nazivInstitucije = nazivInstitucije;
    }

    public int getIdSertifikat() {
        return idSertifikat;
    }

    public void setIdSertifikat(int idSertifikat) {
        this.idSertifikat = idSertifikat;
    }

    public String getNazivSertifikata() {
        return nazivSertifikata;
    }

    public void setNazivSertifikata(String nazivSertifikata) {
        this.nazivSertifikata = nazivSertifikata;
    }

    public String getNazivInstitucije() {
        return nazivInstitucije;
    }

    public void setNazivInstitucije(String nazivInstitucije) {
        this.nazivInstitucije = nazivInstitucije;
    }

    @Override
    public String toString() {
        return nazivSertifikata;
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
        final Sertifikat other = (Sertifikat) obj;
        if (!Objects.equals(this.nazivSertifikata, other.nazivSertifikata)) {
            return false;
        }
        return Objects.equals(this.nazivInstitucije, other.nazivInstitucije);
    }

    @Override
    public String vratiNazivTabele() {
        return "sertifikat";
    }

   @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()){
        int idSertifikat = rs.getInt("sertifikat.idSertifikat");
        String nazivSertifikata = rs.getString("sertifikat.nazivSertifikata");
        String nazivInstitucije = rs.getString("sertifikat.nazivInstitucije");
        
        Sertifikat s = new Sertifikat(idSertifikat, nazivSertifikata, nazivInstitucije);
        lista.add(s);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "nazivSertifikata, nazivInstitucije";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+nazivSertifikata+"','"+nazivInstitucije+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "sertifikat.idSertifikat = " + idSertifikat;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "nazivSertifikata = '" + nazivSertifikata + "', nazivInstitucije = '" + nazivInstitucije + "'";
    }

    @Override
    public String vratiVrednostZaSortiranje() {
        return "sertifikat.idSertifikat";
    }
    
    
}
