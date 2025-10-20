/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.Dan;
import domen.Jelo;
import domen.PlanIshrane;
import domen.StavkaPlanaIshrane;
import domen.VremeObroka;
import forme.DodajStavkuForma;
import forme.modeli.ModelTabeleJelo;
import forme.modeli.ModelTabeleStavkaPlanaIshrane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import koordinator.Koordinator;

/**
 *
 * @author anas
 */
public class DodajStavkuKontroler {
    
     private final DodajStavkuForma dsf;

    public DodajStavkuKontroler(DodajStavkuForma dsf) {
        this.dsf = dsf;
        addActionListener();
    }

    public void otvoriFormu() {
        pripremiFormu();
        dsf.setVisible(true);
    }
    
    public void pripremiFormu() {
        List<Jelo> jela = komunikacija.Komunikacija.getInstance().ucitajJela();
        ModelTabeleJelo mtj = new ModelTabeleJelo(jela);
        dsf.getTblJela().setModel(mtj);
        for (Dan d : Dan.values()) {
            dsf.getCmbDan().addItem(d);
        }
        for (VremeObroka v : VremeObroka.values()) {
            dsf.getCmbVreme().addItem(v);
        }
    }


    private void addActionListener() {
        
        StavkaPlanaIshrane sp = new StavkaPlanaIshrane();
        
        dsf.pretraziAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String naziv = dsf.getTfJelo().getText().trim();

                ModelTabeleJelo mtj = (ModelTabeleJelo) dsf.getTblJela().getModel();
                mtj.pretrazi(naziv);
                if (mtj.getLista().isEmpty()) {
                    JOptionPane.showMessageDialog(dsf, "Sistem ne može da nađe jela po zadatom kriterijumu", "NEUSPESNO", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(dsf, "Sistem je našao jela po zadatom kriterijumu", "USPESNO", JOptionPane.INFORMATION_MESSAGE);
                }
            }

        });
        dsf.resetujAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pripremiFormu();
                dsf.getTfJelo().setText("");

            }

        });
        
        dsf.izaberiAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = dsf.getTblJela().getSelectedRow();

                if (red == -1) {
                    JOptionPane.showMessageDialog(dsf, "Morate izabrati jelo", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                ModelTabeleJelo mtj = (ModelTabeleJelo) dsf.getTblJela().getModel();
                Jelo jelo = mtj.getLista().get(red);

                int jedKcal = jelo.getKcalNa100g();
                int jedCena = jelo.getCenaNa100g();

                double kolicina;
                try {
                    kolicina = Double.parseDouble(dsf.getTfKol().getText().trim());
                } catch (NumberFormatException exc) {
                    JOptionPane.showMessageDialog(dsf, "Količina nije ispravno uneta", "NEUSPESNO", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (kolicina <= 0) {
                    JOptionPane.showMessageDialog(dsf, "Količina mora biti veća od nule", "NEUSPESNO", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double ukupnoKcal = kolicina * jedKcal;
                double iznos = kolicina * jedCena;


                dsf.getTfjedKcal().setText(String.valueOf(jedKcal));
                dsf.getTfjedCena().setText(String.valueOf(jedCena));
                dsf.getLblKcal().setText(String.valueOf(ukupnoKcal));
                dsf.getLblIznos().setText(String.valueOf(iznos));
            }
        });


        dsf.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = dsf.getTblJela().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(dsf, "Morate izabrati jelo", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                ModelTabeleJelo mtj = (ModelTabeleJelo) dsf.getTblJela().getModel();
                Jelo jelo = mtj.getLista().get(red);

                double kolicina;
                try {
                    kolicina = Double.parseDouble(dsf.getTfKol().getText().trim());
                } catch (NumberFormatException exc) {
                    JOptionPane.showMessageDialog(dsf, "Količina nije ispravno uneta", "NEUSPESNO", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (kolicina <= 0) {
                    JOptionPane.showMessageDialog(dsf, "Količina mora biti veća od nule", "NEUSPESNO", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int jedKcal = jelo.getKcalNa100g();
                int jedCena = jelo.getCenaNa100g();
                double ukupnoKcal = kolicina * jedKcal;
                double iznos = kolicina * jedCena;


                StavkaPlanaIshrane sp = new StavkaPlanaIshrane();
                sp.setDan((Dan) dsf.getCmbDan().getSelectedItem());
                sp.setVremeObroka((VremeObroka) dsf.getCmbVreme().getSelectedItem());
                sp.setJelo(jelo);
                sp.setKolicina(kolicina);
                sp.setJedKcal(jedKcal);
                sp.setJedCena(jedCena);
                sp.setUkupnoKcal(ukupnoKcal);
                sp.setIznos(iznos);

                if (dsf.getParent() != null) {
                    PlanIshrane plan = dsf.getParent().getPlanIshrane();
                    sp.setPlanIshrane(plan);

                    if (plan.getStavke() == null) {
                        plan.setStavke(new ArrayList<>());
                    }
                    int noviRb = 1;
                    if (!plan.getStavke().isEmpty()) {
                        int maxRb = 0;
                        for (StavkaPlanaIshrane stavkaPostojeca : plan.getStavke()) {
                            if (stavkaPostojeca.getRb() > maxRb) {
                                maxRb = stavkaPostojeca.getRb();
                            }
                        }
                        noviRb = maxRb + 1;
                    }   
                    sp.setRb(noviRb);
                    plan.getStavke().add(sp);
                    
                    ModelTabeleStavkaPlanaIshrane mts =
                        (ModelTabeleStavkaPlanaIshrane) dsf.getParent().getTblStavke().getModel();

                    mts.dodaj(sp);
                } else {
                    Koordinator.getInstance().dodajParam("stavka", sp);
                }

                JOptionPane.showMessageDialog(dsf, "Stavka je uspešno dodata u plan ishrane", "USPEŠNO", JOptionPane.INFORMATION_MESSAGE);
                dsf.dispose();
            }
        });
    }

   
    public void osveziFormu() {
        pripremiFormu();
    }

    
}
