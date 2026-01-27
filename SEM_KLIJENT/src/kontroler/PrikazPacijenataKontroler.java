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
import java.util.ArrayList;
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

                if (red == -1) {
                    JOptionPane.showMessageDialog(ppf, "Sistem ne može da obriše pacijenta.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                ModelTabelePacijent mtp = (ModelTabelePacijent) ppf.getTblPacijenti().getModel();
                Pacijent p = mtp.getLista().get(red);
                int potvrda = JOptionPane.showConfirmDialog(ppf, "Da li ste sigurni da želite da obrišete pacijenta: " + p.getIme() + " " + p.getPrezime() + "?", "Potvrda brisanja", JOptionPane.YES_NO_OPTION);

                if (potvrda == JOptionPane.YES_OPTION) {
                    try {
                        Komunikacija.getInstance().obrisiPacijenta(p);
                        JOptionPane.showMessageDialog(ppf, "Sistem je obrisao pacijenta.", "USPEH", JOptionPane.INFORMATION_MESSAGE);

                        Pacijent kriterijum = new Pacijent();
                        kriterijum.setIme(ppf.getTfIme().getText().trim());
                        kriterijum.setPrezime(ppf.getTfPrezime().getText().trim());
                        kriterijum.setMesto((Mesto) ppf.getCmbMesto().getSelectedItem());
                        kriterijum.setTipIshrane((TipIshrane) ppf.getCmbTip().getSelectedItem());

                        List<Pacijent> rezultati = Komunikacija.getInstance().pronadjiPacijente(kriterijum);

                        if (rezultati == null) rezultati = new ArrayList<>();
                        ppf.getTblPacijenti().setModel(new ModelTabelePacijent(rezultati));

                    } catch (Exception ex) {
                        
                        JOptionPane.showMessageDialog(ppf, "Sistem ne može da obriše pacijenta: " + ex.getMessage(), "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        ppf.addBtnAzurirajActionListener (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = ppf.getTblPacijenti().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(ppf, "Sistem ne može da učita pacijenta. Niste izabrali red.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                ModelTabelePacijent mtp = (ModelTabelePacijent) ppf.getTblPacijenti().getModel();
                Pacijent p = mtp.getLista().get(red);

                try {
                    Pacijent ucitaniPacijent = Komunikacija.getInstance().ucitajPacijenta(p);
                    JOptionPane.showMessageDialog(ppf, "Sistem je učitao pacijenta.", "USPEH", JOptionPane.INFORMATION_MESSAGE);

                    koordinator.Koordinator.getInstance().dodajParam("pacijent", ucitaniPacijent);
                    koordinator.Koordinator.getInstance().otvoriFormuIzmeniPacijenta();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ppf, "Sistem ne može da učita pacijenta.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                }
    }
        });
        ppf.addBtnPretraziActionListener (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               Pacijent p = new Pacijent();
               p.setIme(ppf.getTfIme().getText().trim());
               p.setPrezime(ppf.getTfPrezime().getText().trim());
               p.setMesto((Mesto) ppf.getCmbMesto().getSelectedItem());
               p.setTipIshrane((TipIshrane) ppf.getCmbTip().getSelectedItem());

               try {
                   List<Pacijent> rezultati = Komunikacija.getInstance().pronadjiPacijente(p);

                   if (rezultati == null || rezultati.isEmpty()) {
                       JOptionPane.showMessageDialog(ppf, "Sistem ne može da nađe pacijente po zadatim kriterijumima.", "Greška", JOptionPane.INFORMATION_MESSAGE);
                       ppf.getTblPacijenti().setModel(new ModelTabelePacijent(new ArrayList<>()));
                   } else {
                       ModelTabelePacijent mtp = new ModelTabelePacijent(rezultati);
                       ppf.getTblPacijenti().setModel(mtp);
                       JOptionPane.showMessageDialog(ppf, "Sistem je našao pacijente po zadatim kriterijumima.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                   }
               } catch (Exception ex) {
                   JOptionPane.showMessageDialog(ppf, "Greška prilikom komunikacije sa serverom: " + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
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
            if (red == -1) {
                JOptionPane.showMessageDialog(ppf, "Sistem ne može da učita pacijenta.", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            ModelTabelePacijent mtp = (ModelTabelePacijent) ppf.getTblPacijenti().getModel();
            Pacijent selektovani = mtp.getLista().get(red);

            try {
                Pacijent ucitani = Komunikacija.getInstance().ucitajPacijenta(selektovani);

                JOptionPane.showMessageDialog(ppf, "Sistem je učitao pacijenta.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);

                Koordinator.getInstance().dodajParam("pacijent", ucitani);
                Koordinator.getInstance().otvoriDetaljiPacijentaFormu();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(ppf, "Sistem ne može da učita pacijenta.", "Greška", JOptionPane.ERROR_MESSAGE);
            }
        }
        });

        
    }

    public void osveziFormu() {
        pripremiFormu();
    }
    
    
    
}
