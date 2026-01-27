/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;


import domen.Dan;
import domen.Jelo;
import domen.Nutricionista;
import domen.Pacijent;
import domen.PlanIshrane;
import domen.StatusStavke;
import domen.StavkaPlanaIshrane;
import domen.VremeObroka;
import forme.FormaMod;
import forme.KreirajPlanIshraneForma;
import forme.modeli.ModelTabeleJelo;
import forme.modeli.ModelTabeleStavkaPlanaIshrane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koordinator.Koordinator;

/**
 *
 * @author anas
 */
public class KreirajPlanIshraneKontroler {
        
    private final KreirajPlanIshraneForma kpif;
    double iznos;
    private int trenutniRedniBroj = 1;
    private ModelTabeleStavkaPlanaIshrane mtspi;
    private List<StavkaPlanaIshrane> stavke;
    private PlanIshrane planIshrane;
    private FormaMod modForme;

    public KreirajPlanIshraneKontroler(KreirajPlanIshraneForma kpif) {
        this.kpif = kpif;
        addActionListener();
    }


    public void otvoriFormu(FormaMod mod) {
        pripremiFormu(mod);
        kpif.setVisible(true);
    }

    private void pripremiFormu(FormaMod mod) {
    List<Nutricionista> nutricioniste = Komunikacija.getInstance().ucitajNutricioniste();
    for (Nutricionista n : nutricioniste) {
        kpif.getCmbNutricionista().addItem(n);
    }

    List<Pacijent> pacijenti = Komunikacija.getInstance().ucitajPacijente();
    for (Pacijent p : pacijenti) {
        kpif.getCmbPacijent().addItem(p);
    }
    List<Jelo> jela = Komunikacija.getInstance().ucitajJela();
    ModelTabeleJelo mtj = new ModelTabeleJelo(jela);
    kpif.getTblJela().setModel(mtj);

    kpif.getCmbDan().removeAllItems();
    for (Dan d : Dan.values()) {
        kpif.getCmbDan().addItem(d);
    }
    kpif.getCmbVreme().removeAllItems();
    for (VremeObroka v : VremeObroka.values()) {
        kpif.getCmbVreme().addItem(v);
    }

    modForme = mod;
    kpif.getCmbNutricionista().setSelectedItem(Koordinator.getInstance().getUlogovani());

    
    switch (modForme) {
        case DODAJ:
            planIshrane = new PlanIshrane();
            planIshrane.setStavke(new ArrayList<>());
            mtspi = new ModelTabeleStavkaPlanaIshrane(planIshrane.getStavke());
            trenutniRedniBroj = 1;
            kpif.getBtnIzmeni().setVisible(false);
            kpif.getBtnObrisi().setVisible(true);
        break;
    
        case IZMENI: 
            PlanIshrane noviPlan = (PlanIshrane) Koordinator.getInstance().vratiParam("planIshrane");
            planIshrane = noviPlan;


            for (Nutricionista n : nutricioniste) {
                if (n.equals(Koordinator.getInstance().getUlogovani())) {
                    kpif.getCmbNutricionista().setSelectedItem(n);
                }
            }
            for (Pacijent p : pacijenti) {
                if (p.equals(noviPlan.getPacijent())) {
                    kpif.getCmbPacijent().setSelectedItem(p);
                }
            }


            List<StavkaPlanaIshrane> stavkeZaPrikaz = Komunikacija.getInstance().ucitajStavke(noviPlan.getIdPlanIshrane());
            int maxRb = 0;
            for (StavkaPlanaIshrane s : stavkeZaPrikaz) {
                if (s.getStatus() == null) s.setStatus(StatusStavke.POSTOJECA);
                s.setPlanIshrane(planIshrane);
                if (s.getRb() > maxRb) maxRb = s.getRb();
            }
            planIshrane.setStavke(stavkeZaPrikaz); 
            mtspi = new ModelTabeleStavkaPlanaIshrane(planIshrane.getStavke());
            trenutniRedniBroj = maxRb + 1;


            kpif.getTfNazivPlana().setText(planIshrane.getNazivPlana());
            kpif.getTfCenaPlana().setText(String.valueOf(planIshrane.getCenaPlana()));
            kpif.getBtnSacuvaj().setVisible(false);

        break;
        
       case DETALJI:
        try {
            PlanIshrane planDetalji = (PlanIshrane) Koordinator.getInstance().vratiParam("planIshrane");
            List<StavkaPlanaIshrane> stavke = Komunikacija.getInstance().ucitajStavke(planDetalji.getIdPlanIshrane());

            for (StavkaPlanaIshrane s : stavke) {
                if (s.getStatus() == null) s.setStatus(StatusStavke.POSTOJECA);
                s.setPlanIshrane(planDetalji);
            }

            mtspi = new ModelTabeleStavkaPlanaIshrane(stavke);
            kpif.getTblStavke().setModel(mtspi);

            kpif.getLblIznos().setText(String.valueOf(mtspi.getUkupanIznos()));

            kpif.getBtnIzmeni().setVisible(false);
            kpif.getBtnSacuvaj().setVisible(false);

            kpif.getCmbNutricionista().setSelectedItem(planDetalji.getNutricionista());
            kpif.getCmbNutricionista().setEnabled(false);
            kpif.getCmbPacijent().setSelectedItem(planDetalji.getPacijent());
            kpif.getCmbPacijent().setEnabled(false);
            kpif.getTfNazivPlana().setText(planDetalji.getNazivPlana());
            kpif.getTfNazivPlana().setEnabled(false);
            kpif.getTfCenaPlana().setText(String.valueOf(planDetalji.getCenaPlana()));
            kpif.getTfCenaPlana().setEnabled(false);

            kpif.getBtnObrisi().setVisible(false);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(kpif, "Greška pri učitavanju stavki!", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    break;
               
               
    }

    kpif.getTblStavke().setModel(mtspi);
    kpif.getLblIznos().setText(String.valueOf(mtspi.getUkupanIznos()));
}
    
       public void pripremiFormuStavke() {
    ModelTabeleStavkaPlanaIshrane model = (ModelTabeleStavkaPlanaIshrane) kpif.getTblStavke().getModel();

    model.fireTableDataChanged();

    kpif.getLblIznos().setText(String.valueOf(model.getUkupanIznos()));
}
   
    private void addActionListener() {
    kpif.obrisiAddActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        int red = kpif.getTblStavke().getSelectedRow();
        if (red == -1) {
            JOptionPane.showMessageDialog(kpif, "Selektujte stavku u tabeli plana!", "GREŠKA", JOptionPane.ERROR_MESSAGE);
            return;
        }

        StavkaPlanaIshrane sp = mtspi.getAktivneStavke().get(red);
        
        int potvrda = JOptionPane.showConfirmDialog(kpif, "Da li ste sigurni?", "POTVRDA", JOptionPane.YES_NO_OPTION);
        if (potvrda == JOptionPane.YES_OPTION) {
            mtspi.obrisiStavku(sp);
            kpif.getLblIznos().setText(String.valueOf(mtspi.getUkupanIznos()));
        }
    }
});
        
      kpif.sacuvajAddActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String naziv = kpif.getTfNazivPlana().getText().trim();
                String cenaStr = kpif.getTfCenaPlana().getText().trim();

                if (naziv.isEmpty() || cenaStr.isEmpty()) {
                    JOptionPane.showMessageDialog(kpif, "Sistem ne može da zapamti plan ishrane. (Naziv ili cena nedostaju)", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double cenaPlana = Double.parseDouble(cenaStr);

                double ukupanIznosJela = mtspi.getUkupanIznos();

                if (ukupanIznosJela <= 0) {
                    JOptionPane.showMessageDialog(kpif, "Sistem ne može da zapamti plan ishrane. (Tabela je prazna)", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                planIshrane.setStavke(mtspi.getLista()); 
                planIshrane.setDatumIzrade(new Date());
                planIshrane.setNutricionista((Nutricionista) kpif.getCmbNutricionista().getSelectedItem());
                planIshrane.setPacijent((Pacijent) kpif.getCmbPacijent().getSelectedItem());
                planIshrane.setNazivPlana(naziv);
                planIshrane.setCenaPlana(cenaPlana);

                planIshrane.setUkupanIznosJela(ukupanIznosJela);

                komunikacija.Komunikacija.getInstance().dodajPlanIshrane(planIshrane);

                JOptionPane.showMessageDialog(kpif, "Sistem je zapamtio plan ishrane.", "USPEŠNO", JOptionPane.INFORMATION_MESSAGE);

                int potvrda = JOptionPane.showConfirmDialog(kpif, "Da li želite da kreirate još jedan plan?", "Novi plan", JOptionPane.YES_NO_OPTION);
                if (potvrda == JOptionPane.YES_OPTION) {
                    Koordinator.getInstance().otvoriFormuDodajPlanIshrane();
                }
                kpif.dispose();

            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(kpif, "Sistem ne može da zapamti plan ishrane. (Cena mora biti broj)", "GREŠKA", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(kpif, "Sistem ne može da zapamti plan ishrane. (Greška na serveru)", "GREŠKA", JOptionPane.ERROR_MESSAGE);
            }
        }
    });
       kpif.izmeniAddActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Nutricionista n = (Nutricionista) kpif.getCmbNutricionista().getSelectedItem();
            Pacijent p = (Pacijent) kpif.getCmbPacijent().getSelectedItem();

            planIshrane.setNutricionista(n);
            planIshrane.setPacijent(p);
            planIshrane.setNazivPlana(kpif.getTfNazivPlana().getText());
            planIshrane.setCenaPlana(Double.parseDouble(kpif.getTfCenaPlana().getText()));

            ModelTabeleStavkaPlanaIshrane mtspi = 
                (ModelTabeleStavkaPlanaIshrane) kpif.getTblStavke().getModel();

            planIshrane.setStavke(mtspi.getLista());
            planIshrane.setUkupanIznosJela(mtspi.getUkupanIznos());

            if (mtspi.getLista().isEmpty()) {
                JOptionPane.showMessageDialog(kpif, "Sistem ne može da zapamti plan ishrane", 
                        "GREŠKA", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Komunikacija.getInstance().izmeniPlanIshrane(planIshrane);

            JOptionPane.showMessageDialog(kpif, "Sistem je zapamtio plan ishrane.", 
                    "USPEŠNO", JOptionPane.INFORMATION_MESSAGE);
            kpif.dispose();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(kpif, "Sistem ne može da zapamti plan ishrane.", 
                    "GREŠKA", JOptionPane.ERROR_MESSAGE);
        }
    }
});
       kpif.getBtnPretrazi().addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String naziv = kpif.getTfJelo().getText().trim();
        ModelTabeleJelo mtj = (ModelTabeleJelo) kpif.getTblJela().getModel();
        mtj.pretrazi(naziv);
        if (mtj.getLista().isEmpty()) {
            JOptionPane.showMessageDialog(kpif, "Sistem ne može da nađe jela po zadatom kriterijumu", "Greška", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(kpif, "Sistem je našao jela po zadatom kriterijumu.");
        }
    }
});
       kpif.getBtnIzaberi().addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        int red = kpif.getTblJela().getSelectedRow();
        if (red == -1) {
            JOptionPane.showMessageDialog(kpif, "Morate izabrati jelo iz tabele!", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ModelTabeleJelo mtj = (ModelTabeleJelo) kpif.getTblJela().getModel();
        Jelo jelo = mtj.getLista().get(red);

        try {
            double kolicina = Double.parseDouble(kpif.getTfKol().getText().trim());
            if (kolicina <= 0) throw new NumberFormatException();

            double ukupnoKcal = kolicina * jelo.getKcalNa100g();
            double iznosStavke = kolicina * jelo.getCenaNa100g();

            kpif.getTfjedKcal().setText(String.valueOf(jelo.getKcalNa100g()));
            kpif.getTfjedCena().setText(String.valueOf(jelo.getCenaNa100g()));
            kpif.getLblKcal().setText(String.valueOf(ukupnoKcal));
            kpif.getLblIznos1().setText(String.valueOf(iznosStavke));

        } catch (NumberFormatException exc) {
            JOptionPane.showMessageDialog(kpif, "Količina mora biti pozitivan broj!", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }
});
       kpif.getBtnDodaj1().addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
       int redJelo = kpif.getTblJela().getSelectedRow();
        if (redJelo == -1) {
            JOptionPane.showMessageDialog(kpif, "Niste izabrali jelo iz tabele!", "GREŠKA", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            ModelTabeleJelo mtj = (ModelTabeleJelo) kpif.getTblJela().getModel();
            Jelo jelo = mtj.getLista().get(redJelo);
            
            double kolicina = Double.parseDouble(kpif.getTfKol().getText().trim());
            if (kolicina <= 0) {
                JOptionPane.showMessageDialog(kpif, "Količina mora biti veća od nule!");
                return;
            }

            StavkaPlanaIshrane sp = new StavkaPlanaIshrane();
            sp.setPlanIshrane(planIshrane);
            sp.setJelo(jelo);
            sp.setDan((Dan) kpif.getCmbDan().getSelectedItem());
            sp.setVremeObroka((VremeObroka) kpif.getCmbVreme().getSelectedItem());
            sp.setKolicina(kolicina);
            sp.setJedKcal(jelo.getKcalNa100g());
            sp.setJedCena(jelo.getCenaNa100g());
            sp.setUkupnoKcal(kolicina * jelo.getKcalNa100g());
            sp.setIznos(kolicina * jelo.getCenaNa100g());

            mtspi.dodaj(sp);

            kpif.getLblIznos().setText(String.valueOf(mtspi.getUkupanIznos()));

        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(kpif, "Neispravan unos količine!", "GREŠKA", JOptionPane.ERROR_MESSAGE);
        }
    }
});
        
    }
    


}
