/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.Mesto;
import domen.Pacijent;
import domen.PlanIshrane;
import domen.StatusStavke;
import domen.StavkaPlanaIshrane;
import domen.TipIshrane;
import forme.KreirajPacijentaForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import koordinator.Koordinator;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import forme.FormaMod;
import forme.modeli.ModelTabeleStavkaPlanaIshrane;

/**
 *
 * @author anas
 */
public class KreirajPacijentaKontroler {
    
    private final KreirajPacijentaForma dpf;

    public KreirajPacijentaKontroler(KreirajPacijentaForma dpf) {
        this.dpf = dpf;
        addActionListener();
    }

    public void otvoriFormu(FormaMod mod) {
        pripremiFormu(mod);
        dpf.setVisible(true);
    }

    private void pripremiFormu(FormaMod mod) {
       switch(mod) {
           case DODAJ:
               dpf.getBtnAzuriraj().setVisible(false);
               dpf.getBtnDodaj().setVisible(true);
               dpf.getBtnDodaj().setEnabled(true);
               dpf.getTfID().setEnabled(false);
               for (TipIshrane tip : TipIshrane.values()) {
                    dpf.getCmbTip().addItem(tip);
               }
               List<Mesto> mesta = Komunikacija.getInstance().ucitajMesta();
               for (Mesto m : mesta) {
                   dpf.getCmbMesto().addItem(m);
               }
               break;
           case IZMENI:
               for (TipIshrane tip : TipIshrane.values()) {
                    dpf.getCmbTip().addItem(tip);
               }
               List<Mesto> mesta1 = Komunikacija.getInstance().ucitajMesta();
               for (Mesto m : mesta1) {
                   dpf.getCmbMesto().addItem(m);
               }
               dpf.getBtnDodaj().setVisible(false);
               dpf.getBtnAzuriraj().setVisible(true);
               dpf.getBtnAzuriraj().setEnabled(true);
               Pacijent p = (Pacijent) Koordinator.getInstance().vratiParam("pacijent");
               dpf.getTfIme().setText(p.getIme());
               dpf.getTfPrezime().setText(p.getPrezime());
               dpf.getTfEmail().setText(p.getEmail());
               dpf.getCmbTip().setSelectedItem(p.getTipIshrane());
               dpf.getCmbMesto().setSelectedItem(p.getMesto());
               SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
               dpf.getTfDatum().setText(sdf.format(p.getDatumRodjenja()));
               dpf.getTfID().setText(p.getIdPacijent()+"");
               break;
           case DETALJI:
                try {
                Pacijent pacijent = (Pacijent) Koordinator.getInstance().vratiParam("pacijent");
                 for (TipIshrane tip : TipIshrane.values()) {
                    dpf.getCmbTip().addItem(tip);
               }
               List<Mesto> mesta2 = Komunikacija.getInstance().ucitajMesta();
               for (Mesto m : mesta2) {
                   dpf.getCmbMesto().addItem(m);
               }
                dpf.getBtnAzuriraj().setVisible(false);
                dpf.getBtnDodaj().setVisible(false);

               dpf.getTfIme().setText(pacijent.getIme());
                dpf.getTfIme().setEnabled(false);

                dpf.getTfPrezime().setText(pacijent.getPrezime());
                dpf.getTfPrezime().setEnabled(false);

                dpf.getTfEmail().setText(pacijent.getEmail());
                dpf.getTfEmail().setEnabled(false);

                dpf.getCmbTip().setSelectedItem(pacijent.getTipIshrane());
                dpf.getCmbTip().setEnabled(false);

                dpf.getCmbMesto().setSelectedItem(pacijent.getMesto());
                dpf.getCmbMesto().setEnabled(false);

                SimpleDateFormat sdf1 = new SimpleDateFormat("dd.MM.yyyy");
                dpf.getTfDatum().setText(sdf1.format(pacijent.getDatumRodjenja()));
                dpf.getTfDatum().setEnabled(false);

                dpf.getTfID().setText(pacijent.getIdPacijent() + "");
                dpf.getTfID().setEnabled(false);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(dpf, "Greška pri učitavanju pacijenta!", "Greška", JOptionPane.ERROR_MESSAGE);
            }
                   break;
           }
       
    }
    
    private Date parseDatumStrict(String datum) throws ParseException {
        if (!datum.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
            throw new ParseException("Neispravan format datuma", 0);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        sdf.setLenient(false);
        return sdf.parse(datum);
    }

    private void addActionListener() {
         dpf.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                try {
                    dodaj(e);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            
            }

            private void dodaj(ActionEvent e) throws ParseException {
                String ime = dpf.getTfIme().getText().trim();
                String prezime = dpf.getTfPrezime().getText().trim();
                String datum = dpf.getTfDatum().getText().trim();
                String email = dpf.getTfEmail().getText().trim();
                TipIshrane tipIshrane = (TipIshrane) dpf.getCmbTip().getSelectedItem();
                Mesto mesto = (Mesto) dpf.getCmbMesto().getSelectedItem();
                
                if (ime.isEmpty() && prezime.isEmpty() && email.isEmpty()) {
                    JOptionPane.showMessageDialog(dpf, "Sistem ne može da zapamti pacijenta", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (ime.isEmpty()) {
                    JOptionPane.showMessageDialog(dpf, "Potrebno je da unesete ime pacijenta", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!ime.matches("[a-zA-ZšđčćžŠĐČĆŽ\\s]+")) {
                    JOptionPane.showMessageDialog(dpf, "Ime koje ste uneli nije odgovarajuće", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (prezime.isEmpty()) {
                    JOptionPane.showMessageDialog(dpf, "Potrebno je da unesete prezime pacijenta", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!prezime.matches("[a-zA-ZšđčćžŠĐČĆŽ\\s]+")) {
                    JOptionPane.showMessageDialog(dpf, "Prezime koje ste uneli nije odgovarajuće", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (email.isEmpty()) {
                    JOptionPane.showMessageDialog(dpf, "Potrebno je da unesete email pacijenta", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!email.contains("@")) {
                    JOptionPane.showMessageDialog(dpf, "Email nije u odgovarajućem formatu", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (datum.isEmpty()) {
                     JOptionPane.showMessageDialog(dpf, "Potrebno je da unesete datum rođenja", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                     return;
                }

                Date datumRodjenja = null;
                try {
                    datumRodjenja = parseDatumStrict(datum);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(dpf, "Datum mora biti u formatu dd.MM.yyyy", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (tipIshrane == null) {
                    JOptionPane.showMessageDialog(dpf, "Potrebno je da izaberete tip ishrane", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                return;
                }

                if (mesto == null) {
                    JOptionPane.showMessageDialog(dpf, "Potrebno je da izaberete mesto", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                return;
                }
                
                
                Komunikacija.getInstance().konekcija();
                Pacijent p = new Pacijent(-1, ime, prezime, datumRodjenja, email, tipIshrane, mesto);
                try{
                Komunikacija.getInstance().dodajPacijenta(p);
                JOptionPane.showMessageDialog(dpf, "Sistem je zapamtio pacijenta.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                dpf.dispose();
                
                }catch(Exception ex) {
                    JOptionPane.showMessageDialog(dpf, "Sistem ne može da zapamti pacijenta.", "GRESKA", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
         dpf.azurirajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                try {
                    azuriraj(e);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            
            }

            private void azuriraj(ActionEvent e) throws ParseException {
                int idPacijent = Integer.parseInt(dpf.getTfID().getText());
                String ime = dpf.getTfIme().getText().trim();
                String prezime = dpf.getTfPrezime().getText().trim();
                String datum = dpf.getTfDatum().getText().trim();
                String email = dpf.getTfEmail().getText().trim();
                TipIshrane tipIshrane = (TipIshrane) dpf.getCmbTip().getSelectedItem();
                Mesto mesto = (Mesto) dpf.getCmbMesto().getSelectedItem();
                
                if (ime.isEmpty() && prezime.isEmpty() && email.isEmpty()) {
                    JOptionPane.showMessageDialog(dpf, "Sistem ne može da zapamti pacijenta", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (ime.isEmpty()) {
                    JOptionPane.showMessageDialog(dpf, "Potrebno je da unesete ime pacijenta", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!ime.matches("[a-zA-ZšđčćžŠĐČĆŽ\\s]+")) {
                    JOptionPane.showMessageDialog(dpf, "Ime koje ste uneli nije odgovarajuće", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (prezime.isEmpty()) {
                    JOptionPane.showMessageDialog(dpf, "Potrebno je da unesete prezime pacijenta", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!prezime.matches("[a-zA-ZšđčćžŠĐČĆŽ\\s]+")) {
                    JOptionPane.showMessageDialog(dpf, "Prezime koje ste uneli nije odgovarajuće", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (email.isEmpty()) {
                    JOptionPane.showMessageDialog(dpf, "Potrebno je da unesete email pacijenta", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!email.contains("@")) {
                    JOptionPane.showMessageDialog(dpf, "Email nije u odgovarajućem formatu", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (datum.isEmpty()) {
                     JOptionPane.showMessageDialog(dpf, "Potrebno je da unesete datum rođenja", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                     return;
                }

                Date datumRodjenja = null;
               try {
                    datumRodjenja = parseDatumStrict(datum);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(dpf, "Datum mora biti u formatu dd.MM.yyyy (npr. 20.05.2000)", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (tipIshrane == null) {
                    JOptionPane.showMessageDialog(dpf, "Potrebno je da izaberete tip ishrane", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                return;
                }

                if (mesto == null) {
                    JOptionPane.showMessageDialog(dpf, "Potrebno je da izaberete mesto", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                return;
                }
                
                Komunikacija.getInstance().konekcija();
                Pacijent p = new Pacijent(idPacijent, ime, prezime, datumRodjenja, email, tipIshrane, mesto);
                try{
                Komunikacija.getInstance().azurirajPacijenta(p);
                JOptionPane.showMessageDialog(dpf, "Sistem je zapamtio pacijenta.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                dpf.dispose();
                
                }catch(Exception ex) {
                    JOptionPane.showMessageDialog(dpf, "Sistem ne može da zapamti pacijenta.", "GRESKA", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
    }
    
}
