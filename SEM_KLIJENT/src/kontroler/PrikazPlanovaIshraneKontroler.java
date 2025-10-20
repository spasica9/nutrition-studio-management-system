/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.Mesto;
import domen.PlanIshrane;
import domen.StavkaPlanaIshrane;
import domen.TipIshrane;
import forme.PrikazPlanovaIshraneForma;
import forme.modeli.ModelTabelePacijent;
import forme.modeli.ModelTabelePlanIshrane;
import forme.modeli.ModelTabeleStavkaPlanaIshrane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        List<PlanIshrane> planovi = Komunikacija.getInstance().ucitajPlanove();
        ModelTabelePlanIshrane mtpi = new ModelTabelePlanIshrane(planovi);
        ppif.getTblPlanovi().setModel(mtpi);
        
        List<StavkaPlanaIshrane> stavke = new ArrayList<>();
        ModelTabeleStavkaPlanaIshrane mtspi = new ModelTabeleStavkaPlanaIshrane(stavke);
        ppif.getTblStavke().setModel(mtspi);
    }

    private void addActionListener() {}

    private void addMouseListener() {
        ppif.getTblPlanovi().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int red = ppif.getTblPlanovi().getSelectedRow();
                if (red!=-1) {
                    ModelTabelePlanIshrane mtpi = (ModelTabelePlanIshrane) ppif.getTblPlanovi().getModel();
                    PlanIshrane p = mtpi.getLista().get(red);
                    List<StavkaPlanaIshrane> stavke = Komunikacija.getInstance().ucitajStavke(p.getIdPlanIshrane());
                    ModelTabeleStavkaPlanaIshrane mtspi = new ModelTabeleStavkaPlanaIshrane(stavke);
                    ppif.getTblStavke().setModel(mtspi);
                }
            }
        });
        
        ppif.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String imeN = ppif.getTfNutricionistaIme().getText().trim();
                String prezimeN = ppif.getTfNutricionistaPrezime().getText().trim();
                String imeP = ppif.getTfPacijentIme().getText().trim();
                String prezimeP = ppif.getTfPacijentPrezime().getText().trim();
                
                if (imeN.isEmpty() && prezimeN.isEmpty() && imeP.isEmpty() && prezimeP.isEmpty()) {
         
                    pripremiFormu();
                    return;
                }
                
                ModelTabelePlanIshrane mtp = (ModelTabelePlanIshrane) ppif.getTblPlanovi().getModel();
                mtp.pretrazi(imeN, prezimeN, imeP, prezimeP);
                if (mtp.getLista().isEmpty()) {
                    JOptionPane.showMessageDialog(ppif, "Sistem ne može da nađe planove ishrane po zadatim kriterijumima", "NEUSPEŠNO", JOptionPane.ERROR_MESSAGE);
                    pripremiFormu();
                } else {
                    JOptionPane.showMessageDialog(ppif, "Sistem je našao planove ishrane po zadatim kriterijumima", "USPEŠNO", JOptionPane.INFORMATION_MESSAGE);
                    
                }
                
            }
        });
        
        
        ppif.addBtnResetujActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pripremiFormu();
                ppif.getTfNutricionistaIme().setText("");
                ppif.getTfNutricionistaPrezime().setText("");
                ppif.getTfPacijentIme().setText("");
                ppif.getTfPacijentPrezime().setText(""); 
            }
            
        });
        
        ppif.addBtnDetaljiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = ppif.getTblPlanovi().getSelectedRow();
                if(red==-1){
                JOptionPane.showMessageDialog(ppif, "Sistem ne može da nađe plan ishrane.", "NEUSPEŠNO", JOptionPane.ERROR_MESSAGE);
                }else{
                    
                        JOptionPane.showMessageDialog(ppif, "Sistem je našao plan ishrane.", "USPEŠNO", JOptionPane.INFORMATION_MESSAGE);
                        ModelTabelePlanIshrane mtpi  = (ModelTabelePlanIshrane) ppif.getTblPlanovi().getModel();
                        PlanIshrane plan = mtpi.getLista().get(red);
                        Koordinator.getInstance().dodajParam("planIshrane", plan);
                    try {
                        Koordinator.getInstance().otvoriDetaljiPlanaFormu();
                    } catch (Exception ex) {
                        Logger.getLogger(PrikazPlanovaIshraneKontroler.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
            }
        });

    }
    
      public void osveziFormu() {
        pripremiFormu();
        }
    
    }
