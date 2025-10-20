/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.modeli;

import domen.Mesto;
import domen.Pacijent;
import domen.TipIshrane;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author anas
 */
public class ModelTabelePacijent extends AbstractTableModel {    

     List<Pacijent> lista;
    String[] kolone = {"ID", "Ime", "Prezime", "Datum rodjenja", "Email", "Tip ishrane", "Mesto"};

    public ModelTabelePacijent(List<Pacijent> lista) {
        this.lista = lista;
    }
       
    
    @Override
    public int getRowCount() {
        return lista.size();
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
        Pacijent p = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return p.getIdPacijent();
            case 1:
                return p.getIme();
            case 2:
                return p.getPrezime();
            case 3:
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
                return sdf.format(p.getDatumRodjenja());
            case 4:
                return p.getEmail();
            case 5:
                return p.getTipIshrane();
            case 6:
                return p.getMesto().getNazivMesta();
            default:
                return "NA";
        }
    }

    public List<Pacijent> getLista() {
        return lista;
    }
    

    public void pretrazi(String ime, String prezime, Date datumRodjenja, String email, TipIshrane tipIshrane, Mesto mesto) {
    List<Pacijent> filteredList = lista.stream()
        .filter(p -> (ime == null || ime.isEmpty() || p.getIme().toLowerCase().contains(ime.toLowerCase())))
        .filter(p -> (prezime == null || prezime.isEmpty() || p.getPrezime().toLowerCase().contains(prezime.toLowerCase())))
        .filter(p -> (datumRodjenja == null || p.getDatumRodjenja().equals(datumRodjenja)))
        .filter(p -> (email == null || email.isEmpty() || p.getEmail().toLowerCase().contains(email.toLowerCase())))
        .filter(p -> (tipIshrane == null || p.getTipIshrane() == tipIshrane))
        .filter(p -> (mesto == null || p.getMesto().equals(mesto)))
        .collect(Collectors.toList());

    this.lista = filteredList;
    fireTableDataChanged();
}
}
