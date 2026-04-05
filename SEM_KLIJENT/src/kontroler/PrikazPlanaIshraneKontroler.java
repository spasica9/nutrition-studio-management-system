/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.Mesto;
import domen.Nutricionista;
import domen.Pacijent;
import domen.PlanIshrane;
import domen.StavkaPlanaIshrane;
import domen.TipIshrane;
import forme.PrikazPlanaIshraneForma;
import forme.PrikazPlanovaIshraneForma;
import forme.modeli.ModelTabeleStavkaPlanaIshrane;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import komunikacija.Komunikacija;
import koordinator.Koordinator;

/**
 *
 * @author anas
 */
public class PrikazPlanaIshraneKontroler {
    
    private final PrikazPlanaIshraneForma ppif;
    
    public PrikazPlanaIshraneKontroler(PrikazPlanaIshraneForma ppif) {
        this.ppif = ppif;
    }

    public void otvoriFormu() {
        pripremiFormu();
        ppif.setVisible(true);
    }

    private void pripremiFormu() {
         
          try {
              
             
                PlanIshrane planIshrane = (PlanIshrane) Koordinator.getInstance().vratiParam("planIshrane");
               
               List<Nutricionista> listaN = Komunikacija.getInstance().ucitajNutricioniste();
               for (Nutricionista n : listaN) {
                   ppif.getCmbNutricionista().addItem(n);
               }
               List<Pacijent> listaP = Komunikacija.getInstance().ucitajPacijente();
               for (Pacijent p : listaP) {
                   ppif.getCmbPacijent().addItem(p);
               }
               
               List<StavkaPlanaIshrane> stavkeDet = planIshrane.getStavke();
                if (stavkeDet == null) {
                   stavkeDet = new ArrayList<>();
                }

              ModelTabeleStavkaPlanaIshrane mtspi = new ModelTabeleStavkaPlanaIshrane(stavkeDet);
              ppif.getTblStavke().setModel(mtspi);
              ppif.getTblStavke().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
              ppif.getTblStavke().getColumnModel().getColumn(0).setPreferredWidth(40);
              ppif.getTblStavke().getColumnModel().getColumn(1).setPreferredWidth(120);
              ppif.getTblStavke().getColumnModel().getColumn(8).setPreferredWidth(300);
              ppif.getLblIznos1().setText(String.valueOf(mtspi.getUkupanIznos()));
              ppif.getTfNazivPlana().setText(planIshrane.getNazivPlana());
              ppif.getTfCenaPlana().setText(String.valueOf(planIshrane.getCenaPlana()));
              
                ppif.getTfNazivPlana().setEnabled(false);
                ppif.getTfCenaPlana().setEnabled(false);
                ppif.getCmbNutricionista().setEnabled(false);
                ppif.getCmbPacijent().setEnabled(false);

                
               } catch (Exception ex) {
                   ex.printStackTrace();
                   JOptionPane.showMessageDialog(ppif, "Greška pri učitavanju plana ishrane.", "Greška", JOptionPane.ERROR_MESSAGE);
               }
        
    }

    
}
