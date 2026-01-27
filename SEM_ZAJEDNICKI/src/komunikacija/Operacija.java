/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package komunikacija;

import java.io.Serializable;

/**
 *
 * @author anas
 */
public enum Operacija implements Serializable {
    PRIJAVA,
    VRATI_LISTU_PACIJENATA,
    OBRISI_PACIJENTA,
    VRATI_LISTU_MESTA,
    ZAPAMTI_PACIJENTA,
    PROMENI_PACIJENTA, 
    UCITAJ_PLANOVE_ISHRANE, 
    UCITAJ_STAVKE, 
    VRATI_LISTU_NUTRICIONISTA, 
    VRATI_LISTU_JELA,
    ZAPAMTI_PLAN_ISHRANE, 
    ZAPAMTI_SERTIFIKAT,
    UCITAJ_PLANOVE_ISHRANE_ULOGOVANOG,
    PROMENI_PLAN_ISHRANE,
    UCITAJ_PACIJENTA,
    PRONADJI_PACIJENTE
}
