/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;


import domen.Sertifikat;
import forme.KreirajSertifikatForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 *
 * @author anas
 */
public class KreirajSertifikatKontroler {
    
     private final KreirajSertifikatForma usf;

    public KreirajSertifikatKontroler(KreirajSertifikatForma usf) {
        this.usf = usf;
        addActionListener();
    }

    

    public void otvoriFormu() {
        usf.setVisible(true);
    }

    private void addActionListener() {
        usf.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                try {
                    dodaj(e);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            
            }

            private void dodaj(ActionEvent e) throws ParseException {
                String nazivS = usf.getTfSert().getText().trim();
                String nazivI = usf.getTfInst().getText().trim();
                
                if(nazivS.isEmpty()|| nazivI.isEmpty()){
                JOptionPane.showMessageDialog(usf,"Sistem ne može da zapamti sertifikat." , "NEUSPEŠNO", JOptionPane.ERROR_MESSAGE);
                     return;
                }
                
                Komunikacija.getInstance().konekcija();
                Sertifikat s = new Sertifikat(-1, nazivS, nazivI);
                try{
                Komunikacija.getInstance().dodajSertifikat(s);
                JOptionPane.showMessageDialog(usf, "Sistem je zapamtio sertifikat.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                usf.dispose();               
                }catch(Exception ex) {
                    JOptionPane.showMessageDialog(usf, "Sistem ne može da zapamti sertifikat.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    
}
