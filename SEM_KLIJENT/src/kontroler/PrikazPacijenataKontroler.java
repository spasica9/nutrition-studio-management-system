/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.Mesto;
import domen.Pacijent;
import domen.PlanIshrane;
import domen.TipIshrane;
import forme.PrikazPacijenataForma;
import komunikacija.Komunikacija;
import forme.modeli.ModelTabelePacijent;
import forme.modeli.ModelTabelePlanIshrane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import koordinator.Koordinator;

/**
 *
 * @author anas
 */
public class PrikazPacijenataKontroler {
    
    private final PrikazPacijenataForma ppf;

    public PrikazPacijenataKontroler(PrikazPacijenataForma ppf) {
        this.ppf = ppf;
        addActionListener();
    }

    public void otvoriFormu() {
        pripremiFormu();
        ppf.setVisible(true);
    }

    private void pripremiFormu() {
        List<Pacijent> pacijenti = Komunikacija.getInstance().ucitajPacijente();
        ModelTabelePacijent mtp = new ModelTabelePacijent(pacijenti);
        ppf.getTblPacijenti().setModel(mtp);
        if (ppf.getCmbMesto().getItemCount()==0){
        for (TipIshrane tip : TipIshrane.values()) {
                    ppf.getCmbTip().addItem(tip);
               }
        }
        if (ppf.getCmbMesto().getItemCount()==0) {
        List<Mesto> mesta = Komunikacija.getInstance().ucitajMesta();
          for (Mesto m : mesta) {
            ppf.getCmbMesto().addItem(m);
           }
        }
        ppf.getCmbMesto().setSelectedIndex(-1);
        ppf.getCmbTip().setSelectedIndex(-1);
                
    }

    private void addActionListener() {
        ppf.addBtnObrisiActionListener (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               int red = ppf.getTblPacijenti().getSelectedRow();
               if (red==-1) {
                   JOptionPane.showMessageDialog(ppf, "Sistem ne može da obriše pacijenta.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
               }
               else {
                   ModelTabelePacijent mtp = (ModelTabelePacijent) ppf.getTblPacijenti().getModel();
                   Pacijent p = mtp.getLista().get(red);
                   try{
                      Komunikacija.getInstance().obrisiPacijenta(p);
                      JOptionPane.showMessageDialog(ppf, "Sistem je obrisao pacijenta.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                      pripremiFormu();
                   }catch(Exception ex) {
                      JOptionPane.showMessageDialog(ppf, "Sistem ne može da obriše pacijenta.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                   }
                   
               }
            }
        });
        ppf.addBtnAzurirajActionListener (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               int red = ppf.getTblPacijenti().getSelectedRow();
               if (red==-1) {
                   JOptionPane.showMessageDialog(ppf, "Sistem ne može da nađe pacijenta.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
               }
               else {
                   JOptionPane.showMessageDialog(ppf, "Sistem je našao pacijenta.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                   ModelTabelePacijent mtp = (ModelTabelePacijent) ppf.getTblPacijenti().getModel();
                   Pacijent p = mtp.getLista().get(red);
                   koordinator.Koordinator.getInstance().dodajParam("pacijent", p);
                   koordinator.Koordinator.getInstance().otvoriFormuIzmeniPacijenta();
                   
               }
            }
        });
        ppf.addBtnPretraziActionListener (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               String ime = ppf.getTfIme().getText().trim();
                String prezime = ppf.getTfPrezime().getText().trim();
                String datum = ppf.getTfDatum().getText().trim();
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                sdf.setLenient(false);
                Date datumRodjenja = null;
                try {
                     datumRodjenja = sdf.parse(datum);
                } catch (ParseException ex) {
                    System.out.println("Nevalidan datum");
                }
                String email = ppf.getTfEmail().getText().trim();
                TipIshrane tipIshrane = (TipIshrane) ppf.getCmbTip().getSelectedItem();
                Mesto mesto = (Mesto) ppf.getCmbMesto().getSelectedItem();
            
                ModelTabelePacijent mtp = (ModelTabelePacijent) ppf.getTblPacijenti().getModel();
                mtp.pretrazi(ime, prezime, datumRodjenja, email, tipIshrane, mesto);
                 if (mtp.getLista().isEmpty()) {
                    JOptionPane.showMessageDialog(ppf, "Sistem ne može da nađe pacijente po zadatim kriterijumima", "NEUSPEŠNO", JOptionPane.ERROR_MESSAGE);
                    pripremiFormu();
                } else {
                    JOptionPane.showMessageDialog(ppf, "Sistem je našao pacijente po zadatim kriterijumima", "USPEŠNO", JOptionPane.INFORMATION_MESSAGE);
                    
                }
            }
        });
        ppf.addBtnResetujActionListener (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                osveziFormu();
            }
        });
        ppf.addBtnDetaljiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = ppf.getTblPacijenti().getSelectedRow();
                if(red==-1){
                JOptionPane.showMessageDialog(ppf, "Sistem ne može da nađe pacijenta.", "NEUSPEŠNO", JOptionPane.ERROR_MESSAGE);
                }else{
                    
                        JOptionPane.showMessageDialog(ppf, "Sistem je našao pacijenta.", "USPEŠNO", JOptionPane.INFORMATION_MESSAGE);
                        ModelTabelePacijent mtp  = (ModelTabelePacijent) ppf.getTblPacijenti().getModel();
                        Pacijent p = mtp.getLista().get(red);
                        Koordinator.getInstance().dodajParam("pacijent", p);
                    try {
                        Koordinator.getInstance().otvoriDetaljiPacijentaFormu();
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
