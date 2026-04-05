/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.PlanIshrane;
import domen.StavkaPlanaIshrane;
import domen.Nutricionista;
import forme.IzmeniPlanIshraneForma;
import forme.modeli.ModelTabelePlanIshrane;
import forme.modeli.ModelTabeleStavkaPlanaIshrane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koordinator.Koordinator;

/**
 *
 * @author anas
 */
public class IzmeniPlanIshraneKontroler {
    private final IzmeniPlanIshraneForma ipif;
    List<StavkaPlanaIshrane> originalneStavke;

    public List<StavkaPlanaIshrane> getOriginalneStavke() {
        return originalneStavke;
    }
    
    
    public void setOriginalneStavke(List<StavkaPlanaIshrane> originalneStavke) {
        this.originalneStavke = originalneStavke;
    }
    
    public IzmeniPlanIshraneKontroler(IzmeniPlanIshraneForma ipif) {
        this.ipif = ipif;
        addActionListener();
    }
     public void otvoriFormu() throws Exception {
        pripremiFormu();
        ipif.setVisible(true);
    }
     public void osveziFormu() throws Exception {
        pripremiFormu();
    }
      public void pripremiFormu() throws Exception {
        Nutricionista ulogovani = Koordinator.getInstance().getUlogovani();
        List<PlanIshrane> planoviIshrane = Komunikacija.getInstance().ucitajPlanoveIshraneUlogovanog(ulogovani);
        ModelTabelePlanIshrane mtpi = new ModelTabelePlanIshrane(planoviIshrane);
        ipif.getTblPlanovi().setModel(mtpi);
        
        List<StavkaPlanaIshrane> stavke = new ArrayList<>();
        ModelTabeleStavkaPlanaIshrane mts = new ModelTabeleStavkaPlanaIshrane(stavke);
    }
      
      
    private void addActionListener(){
        ipif.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String imePacijenta = ipif.getTxtIme().getText().trim();
                String prezimePacijenta = ipif.getTxtPrezime().getText().trim();
                
                
                ModelTabelePlanIshrane mtpi = (ModelTabelePlanIshrane) ipif.getTblPlanovi().getModel();
                mtpi.pretraziP(imePacijenta,prezimePacijenta);
                
                if (mtpi.getLista().isEmpty()) {
                    JOptionPane.showMessageDialog(ipif, "Sistem ne može da pronađe planove ishrane po zadatim kriterijumima.", "NEUSPEŠNO", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(ipif, "Sistem je pronašao planove ishrane po zadatim kriterijumima.", "USPEŠNO", JOptionPane.INFORMATION_MESSAGE);
                }
                
            }
        });
        ipif.addBtnResetujActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pripremiFormu();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                ipif.getTxtIme().setText("");
                ipif.getTxtPrezime().setText("");    
            }
        });
        
        ipif.addBtnIzmeniActionListener(new ActionListener() {
          @Override
    public void actionPerformed(ActionEvent e) {
                int red = ipif.getTblPlanovi().getSelectedRow();
                if (red != -1) {
                    try {
                        ModelTabelePlanIshrane mtpi = (ModelTabelePlanIshrane) ipif.getTblPlanovi().getModel();
                        PlanIshrane selektovani = mtpi.getLista().get(red);

                        PlanIshrane kompletanPlan = Komunikacija.getInstance().ucitajPlanIshrane(selektovani);

                        if (kompletanPlan != null) {
                            JOptionPane.showMessageDialog(ipif, "Sistem je učitao plan ishrane.", "USPEŠNO", JOptionPane.INFORMATION_MESSAGE);
                            Koordinator.getInstance().dodajParam("planIshrane", kompletanPlan);
                            ipif.dispose(); 
                            Koordinator.getInstance().otvoriIzmeniPlanIshraneFormuUlogovanog();
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(ipif, "Sistem ne može da učita plan ishrane.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(ipif, "Sistem ne može da učita plan ishrane.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

   
    

    }

