/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.modeli;

import domen.Jelo;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author anas
 */
public class ModelTabeleJelo extends AbstractTableModel {

    private List<Jelo> lista;
    private final List<Jelo> originalnaLista;
    private final String[] kolone = {"ID", "Naziv jela", "Opis", "Kcal/100g", "Cena/100g"};
    
    public ModelTabeleJelo(List<Jelo> lista) {
        this.lista = lista;
        this.originalnaLista = new ArrayList<>(lista);
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
        Jelo j = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return j.getIdJelo();
            case 1:
                return j.getNazivJela();
            case 2:
                return j.getOpis();
            case 3:
                return j.getKcalNa100g();
            case 4:
                return j.getCenaNa100g();
            default:
                return "NA";
        }
    }

    public List<Jelo> getLista() {
        return lista;
    }

    public void pretrazi(String naziv) {
        this.lista = originalnaLista.stream()
            .filter(f -> (naziv == null || naziv.isEmpty() || f.getNazivJela().toLowerCase().contains(naziv.toLowerCase())))
            .collect(Collectors.toList());
        
        fireTableDataChanged();    
    }   
}
