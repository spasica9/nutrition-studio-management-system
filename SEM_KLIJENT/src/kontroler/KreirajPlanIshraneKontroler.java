/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;


import domen.Nutricionista;
import domen.Pacijent;
import domen.PlanIshrane;
import domen.StatusStavke;
import domen.StavkaPlanaIshrane;
import forme.FormaMod;
import forme.KreirajPlanIshraneForma;
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

    modForme = mod;
    kpif.getCmbNutricionista().setSelectedItem(Koordinator.getInstance().getUlogovani());

    
    switch (modForme) {
        case DODAJ:
            planIshrane = new PlanIshrane();
            planIshrane.setStavke(new ArrayList<>());
            mtspi = new ModelTabeleStavkaPlanaIshrane(planIshrane.getStavke());
            trenutniRedniBroj = 1;
            kpif.getBtnIzmeni().setVisible(false);
            kpif.getBtnObrisi().setVisible(false);
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
            kpif.getBtnDodaj().setVisible(false);

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
       kpif.dodajAddActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (kpif.getCmbPacijent().getSelectedItem() == null) {
                JOptionPane.showMessageDialog(kpif, "Niste izabrali pacijenta", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                return;
            }

            do {
                Koordinator.getInstance().otvoriDodajStavkuFormu(kpif);

                pripremiFormuStavke(); 

                ModelTabeleStavkaPlanaIshrane model = (ModelTabeleStavkaPlanaIshrane) kpif.getTblStavke().getModel();
                double noviIznos = model.getUkupanIznos();
                kpif.getLblIznos().setText(String.valueOf(noviIznos));
                kpif.getPlanIshrane().setUkupanIznosJela(noviIznos);

            } while (JOptionPane.showConfirmDialog(null, "Želite li da dodate još stavki?", "POTVRDA", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
        }
    });
        
        
        kpif.obrisiAddActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int red = kpif.getTblStavke().getSelectedRow();
            if (red == -1) {
                JOptionPane.showMessageDialog(kpif, "Selektujte stavku koju želite da obrišete", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int potvrda = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da želite da obrišete stavku?", "POTVRDA", JOptionPane.YES_NO_OPTION);
            if (potvrda != JOptionPane.YES_OPTION) return;

            ModelTabeleStavkaPlanaIshrane mts = (ModelTabeleStavkaPlanaIshrane) kpif.getTblStavke().getModel();
            StavkaPlanaIshrane stavka = mts.getAktivneStavke().get(red); 

            mts.obrisiStavku(stavka); 

            double noviIznos = mts.getUkupanIznos();
            kpif.getPlanIshrane().setUkupanIznosJela(noviIznos);
            kpif.getLblIznos().setText(String.valueOf(noviIznos));
        }
    });
        
        kpif.sacuvajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                double iznos = Double.parseDouble(kpif.getLblIznos().getText());
                if(iznos <= 0){
                    JOptionPane.showMessageDialog(kpif, "Sistem ne može da zapamti plan ishrane", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                PlanIshrane planIshrane = kpif.getPlanIshrane();
                planIshrane.setDatumIzrade(new Date());
                planIshrane.setNutricionista((Nutricionista) kpif.getCmbNutricionista().getSelectedItem());
                planIshrane.setPacijent((Pacijent) kpif.getCmbPacijent().getSelectedItem());
                if (!kpif.getTfNazivPlana().getText().matches("[a-zA-ZšđčćžŠĐČĆŽ\\s]+")) {
                    JOptionPane.showMessageDialog(kpif, "Naziv koji ste uneli nije odgovarajući", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                planIshrane.setNazivPlana(kpif.getTfNazivPlana().getText());
                 
                planIshrane.setCenaPlana(Double.parseDouble(kpif.getTfCenaPlana().getText()));
                
                try{
                    komunikacija.Komunikacija.getInstance().dodajPlanIshrane(planIshrane);
                    JOptionPane.showMessageDialog(kpif, "Sistem je zapamtio plan ishrane", "USPEŠNO", JOptionPane.INFORMATION_MESSAGE);
                    
                    int potvrda = JOptionPane.showConfirmDialog(null, "Da li želite da kreirate novi plan ishrane?", "POTVRDA", JOptionPane.YES_NO_OPTION);
                    if(potvrda == JOptionPane.YES_OPTION){
                        Koordinator.getInstance().otvoriFormuDodajPlanIshrane();
                    }
                    kpif.dispose();
                }catch(Exception exc){
                    JOptionPane.showMessageDialog(kpif, "Sistem ne može da kreira plan ishrane", "NEUSPEŠNO", JOptionPane.ERROR_MESSAGE);
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
        
    }
    


}
