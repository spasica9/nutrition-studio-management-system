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
public class Nutricionista implements ApstraktniDomenskiObjekat {
    private int idNutricionista;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String sifra;
    private String email;

    public Nutricionista() {
    }

    public Nutricionista(String korisnickoIme, String sifra) {
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
    }
    

    public Nutricionista(int idNutricionista, String ime, String prezime, String korisnickoIme, String sifra, String email) {
        this.idNutricionista = idNutricionista;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
        this.email = email;
    }

    @Override
    public String toString() {
        return ime+" "+prezime;
    }

    public int getIdNutricionista() {
        return idNutricionista;
    }

    public void setIdNutricionista(int idNutricionista) {
        this.idNutricionista = idNutricionista;
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

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        final Nutricionista other = (Nutricionista) obj;
        if (!Objects.equals(this.korisnickoIme, other.korisnickoIme)) {
            return false;
        }
        return Objects.equals(this.sifra, other.sifra);
    }

    

    @Override
    public String vratiNazivTabele() {
        return "nutricionista";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()){
            int idNutricionista = rs.getInt("nutricionista.idNutricionista");
            String ime = rs.getString("nutricionista.ime");
            String prezime = rs.getString("nutricionista.prezime");
            String korisnickoIme = rs.getString("nutricionista.korisnickoIme");
            String sifra = rs.getString("nutricionista.sifra");
            String email = rs.getString("nutricionista.email");
            
            Nutricionista n = new Nutricionista(idNutricionista, ime, prezime, korisnickoIme, sifra, email);
            lista.add(n);
        }
        
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime, prezime, korisnickoIme, sifra, email";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+ime+"','"+prezime+"','"+korisnickoIme+"','"+sifra+"','"+email+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "nutricionista.idNutricionista = " + idNutricionista;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   @Override
    public String vratiVrednostiZaIzmenu() {
        return "ime = '" + ime + "', prezime = '" + prezime + "', korisnickoIme = '" + korisnickoIme + 
           "', sifra = '" + sifra + "', email = '" + email + "'";
}

    @Override
    public String vratiVrednostZaSortiranje() {
        return "nutricionista.idNutricionista";
    }
    
    
    
           
}
