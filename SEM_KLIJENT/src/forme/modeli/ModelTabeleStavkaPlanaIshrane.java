/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.modeli;

import domen.StatusStavke;
import domen.StavkaPlanaIshrane;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author anas
 */
public class ModelTabeleStavkaPlanaIshrane extends AbstractTableModel {

    private List<StavkaPlanaIshrane> lista;
    private final String[] kolone = {"RB", "Dan", "Vreme obroka", "Količina", "Kcal na 100g", "Cena", "Ukupno kcal", "Iznos", "Jelo"};

    public ModelTabeleStavkaPlanaIshrane(List<StavkaPlanaIshrane> lista) {
        this.lista = (lista != null) ? lista : new ArrayList<>();
        fireTableDataChanged();
    }

    public ModelTabeleStavkaPlanaIshrane() {
        this.lista = new ArrayList<>();
    }

    public List<StavkaPlanaIshrane> getAktivneStavke() {
        List<StavkaPlanaIshrane> aktivne = new ArrayList<>();
        for (StavkaPlanaIshrane sp : lista) {
            if (sp.getStatus() != StatusStavke.OBRISANA) {
                aktivne.add(sp);
            }
        }
        return aktivne;
    }

    @Override
    public int getRowCount() {
        return getAktivneStavke().size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaPlanaIshrane spi = getAktivneStavke().get(rowIndex);
        switch (columnIndex) {
            case 0: return spi.getRb();
            case 1: return spi.getDan();
            case 2: return spi.getVremeObroka();
            case 3: return spi.getKolicina();
            case 4: return spi.getJedKcal();
            case 5: return spi.getJedCena();
            case 6: return spi.getUkupnoKcal();
            case 7: return spi.getIznos();
            case 8: return spi.getJelo().getNazivJela();
            default: return "NA";
        }
    }

    public List<StavkaPlanaIshrane> getLista() {
        return lista;
    }

    public void dodaj(StavkaPlanaIshrane sp) {
    boolean postoji = false;
    for (StavkaPlanaIshrane s : lista) {
        if (s.getJelo().equals(sp.getJelo()) 
                && s.getDan().equals(sp.getDan()) 
                && s.getVremeObroka().equals(sp.getVremeObroka())
                && s.getStatus() != StatusStavke.OBRISANA) {

            double novaKolicina = s.getKolicina() + sp.getKolicina();
            s.setKolicina(novaKolicina);
            s.setUkupnoKcal(novaKolicina * s.getJedKcal());
            s.setIznos(novaKolicina * s.getJedCena());

            if (s.getStatus() == StatusStavke.POSTOJECA) {
                s.setStatus(StatusStavke.IZMENJENA);
            }
            postoji = true;
            break;
        }
    }

    if (!postoji) {
        lista.add(sp);
    }


    int rb = 1;
    for (StavkaPlanaIshrane s : lista) {
        if (s.getStatus() != StatusStavke.OBRISANA) {
            s.setRb(rb++);
            if (s.getStatus() == null) {
                s.setStatus(StatusStavke.NOVA);
            }
        }
    }

    fireTableDataChanged();
}
    public void izmeniKolicinu(int rowIndex, int novaKolicina) {
        StavkaPlanaIshrane sp = getAktivneStavke().get(rowIndex);
        sp.setKolicina(novaKolicina);
        sp.setUkupnoKcal(novaKolicina * sp.getJedKcal());
        sp.setIznos(novaKolicina * sp.getJedCena());

        if (sp.getStatus() == StatusStavke.POSTOJECA) {
            sp.setStatus(StatusStavke.IZMENJENA);
        }

        fireTableRowsUpdated(rowIndex, rowIndex);
    }

    public void reindeksirajRb() {
    int rb = 1;
    for (StavkaPlanaIshrane s : lista) {
        if (s.getStatus() != StatusStavke.OBRISANA) {
            s.setRb(rb++);
        }
    }
    fireTableDataChanged();
    }
    
   public void obrisiStavku(StavkaPlanaIshrane sp) {
    sp.setStatus(StatusStavke.OBRISANA);
    reindeksirajRb();
}
    public void setLista(List<StavkaPlanaIshrane> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }

    public double getUkupanIznos() {
        double ukupno = 0;
        for (StavkaPlanaIshrane s : getAktivneStavke()) {
            ukupno += s.getIznos();
        }
        return ukupno;
    }

    public void ocistiTabelu() {
        lista.clear();
        fireTableDataChanged();
    }
}
