/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.modeli;


import domen.PlanIshrane;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author anas
 */
public class ModelTabelePlanIshrane extends AbstractTableModel {

    
     List<PlanIshrane> lista;
     String[] kolone = {"ID", "Naziv", "Cena plana", "Datum izrade", "Ukupan iznos jela", "Nutricionista", "Pacijent"};
     

    public ModelTabelePlanIshrane(List<PlanIshrane> lista) {
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
        PlanIshrane p = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return p.getIdPlanIshrane();
            case 1:
                return p.getNazivPlana();
            case 2:
                return p.getCenaPlana();
            case 3:
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
                return sdf.format(p.getDatumIzrade());
            case 4:
                return p.getUkupanIznosJela();
            case 5:
                return p.getNutricionista().getIme() + " " + p.getNutricionista().getPrezime();
            case 6:
                return p.getPacijent().getIme() + " " + p.getPacijent().getPrezime();
            default:
                return "NA";
        }
    }

    public List<PlanIshrane> getLista() {
        return lista;
    }
    
    
    public void pretrazi(String imeN, String prezimeN, String imeP, String prezimeP) {
        List<PlanIshrane> filteredList = lista.stream()
                .filter(e -> (imeN == null || imeN.isEmpty() || e.getNutricionista().getIme().toLowerCase().contains(imeN.toLowerCase())))
                .filter(e -> (prezimeN == null || prezimeN.isEmpty() || e.getNutricionista().getPrezime().toLowerCase().contains(prezimeN.toLowerCase())))
                .filter(e -> (imeP == null || imeP.isEmpty() || e.getPacijent().getIme().toLowerCase().contains(imeP.toLowerCase())))
                .filter(e -> (prezimeP == null || prezimeP.isEmpty() || e.getPacijent().getPrezime().toLowerCase().contains(prezimeP.toLowerCase())))
                .collect(Collectors.toList());
        
        this.lista = filteredList;
        fireTableDataChanged();
    }
    
    public void pretraziP(String imeP, String prezimeP) {
        List<PlanIshrane> filteredList = lista.stream()
                .filter(e -> (imeP == null || imeP.isEmpty() || e.getPacijent().getIme().toLowerCase().contains(imeP.toLowerCase())))
                .filter(e -> (prezimeP == null || prezimeP.isEmpty() || e.getPacijent().getPrezime().toLowerCase().contains(prezimeP.toLowerCase())))
                .collect(Collectors.toList());  
        this.lista = filteredList;
        fireTableDataChanged();
    }

    public void setLista(List<PlanIshrane> lista) {
        this.lista = lista;
    }
    
}
