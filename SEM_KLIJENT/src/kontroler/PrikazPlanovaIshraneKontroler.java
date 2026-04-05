/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.Nutricionista;
import domen.Pacijent;
import domen.PlanIshrane;
import forme.PrikazPlanovaIshraneForma;
import forme.modeli.ModelTabelePlanIshrane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koordinator.Koordinator;

/**
 *
 * @author anas
 */
public class PrikazPlanovaIshraneKontroler {
    
     private final PrikazPlanovaIshraneForma ppif;

    public PrikazPlanovaIshraneKontroler(PrikazPlanovaIshraneForma ppif) {
        this.ppif = ppif;
        addActionListener();
        addMouseListener();
    }

    public void otvoriFormu() {
        pripremiFormu();
        ppif.setVisible(true);
    }

    private void pripremiFormu() {
        try {
            List<Nutricionista> nutricionisti = Komunikacija.getInstance().ucitajNutricioniste();
            DefaultComboBoxModel modelN = new DefaultComboBoxModel();
            Nutricionista sviN = new Nutricionista();
            sviN.setIdNutricionista(-1);
            sviN.setIme("Svi");
            sviN.setPrezime("nutricionisti");
            modelN.addElement(sviN); 
            for (Nutricionista n : nutricionisti) modelN.addElement(n);
            ppif.getCmbNutricionista().setModel(modelN);
            List<Pacijent> pacijenti = Komunikacija.getInstance().ucitajPacijente();
            DefaultComboBoxModel modelP = new DefaultComboBoxModel();
            Pacijent sviP = new Pacijent();
            sviP.setIdPacijent(-1);
            sviP.setIme("Svi");
            sviP.setPrezime("pacijenti");
            modelP.addElement(sviP);
            for (Pacijent p : pacijenti) modelP.addElement(p);
            ppif.getCmbPacijent().setModel(modelP);
            List<PlanIshrane> planovi = Komunikacija.getInstance().ucitajPlanove();
            ppif.getTblPlanovi().setModel(new ModelTabelePlanIshrane(planovi));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addActionListener() {}

    private void addMouseListener() {
        
        ppif.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Nutricionista n = (Nutricionista) ppif.getCmbNutricionista().getSelectedItem();
                Pacijent p = (Pacijent) ppif.getCmbPacijent().getSelectedItem();

                PlanIshrane filter = new PlanIshrane();
                filter.setNutricionista(n);
                filter.setPacijent(p);

                try {
                    List<PlanIshrane> lista = Komunikacija.getInstance().pronadjiPlanove(filter);
                    if (lista.isEmpty()) {
                        JOptionPane.showMessageDialog(ppif, "Sistem ne može da nađe planove ishrane po zadatim kriterijumima.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                        pripremiFormu();
                    } else {
                        JOptionPane.showMessageDialog(ppif, "Sistem je našao planove ishrane po zadatim kriterijumima.", "USPEŠNO", JOptionPane.INFORMATION_MESSAGE);
                        ppif.getTblPlanovi().setModel(new ModelTabelePlanIshrane(lista));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        
        ppif.addBtnResetujActionListener(new ActionListener() {
           @Override
            public void actionPerformed(ActionEvent e) {
                ppif.getCmbNutricionista().setSelectedIndex(0);
                ppif.getCmbPacijent().setSelectedIndex(0);
                pripremiFormu();
            }
        });
        
        ppif.addBtnDetaljiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = ppif.getTblPlanovi().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(ppif, "Sistem ne može da učita plan ishrane.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ModelTabelePlanIshrane mtpi = (ModelTabelePlanIshrane) ppif.getTblPlanovi().getModel();
                PlanIshrane p = mtpi.getLista().get(red);

                try {
                    PlanIshrane ucitaniPlan = Komunikacija.getInstance().ucitajPlanIshrane(p);
                    
                    if (ucitaniPlan != null) {
                        JOptionPane.showMessageDialog(ppif, "Sistem je učitao plan ishrane.", "USPEŠNO", JOptionPane.INFORMATION_MESSAGE);
                        Koordinator.getInstance().dodajParam("planIshrane", ucitaniPlan);
                        Koordinator.getInstance().otvoriDetaljiPlanaFormu();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ppif, "Sistem ne može da učita plan ishrane.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }
    
      public void osveziFormu() {
        pripremiFormu();
        }
    
    }
