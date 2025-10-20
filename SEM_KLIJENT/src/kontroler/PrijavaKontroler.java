/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.Nutricionista;
import forme.PrijavaForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 *
 * @author anas
 */
public class PrijavaKontroler {
    
    private final PrijavaForma lf;

    public PrijavaKontroler(PrijavaForma lf) {
        this.lf = lf;
        addActionListeners();
    }

    private void addActionListeners() {
        
        lf.loginAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                prijava(e);
            
            }

            private void prijava(ActionEvent e) {
                String username = lf.getTxtUsername().getText().trim();
                String password = String.valueOf(lf.getTxtPassword().getPassword());
                
                Komunikacija.getInstance().konekcija();
                Nutricionista ulogovani = Komunikacija.getInstance().login(username, password);
                
                if (ulogovani==null) {
                    JOptionPane.showMessageDialog(lf, "Korisničko ime i šifra nisu ispravni!", "NEUSPESNO", JOptionPane.ERROR_MESSAGE);
                }else {
                    koordinator.Koordinator.getInstance().setUlogovani(ulogovani);
                    JOptionPane.showMessageDialog(lf, "Korisničko ime i šifra su ispravni.", "USPESNO", JOptionPane.INFORMATION_MESSAGE);
                    koordinator.Koordinator.getInstance().otvoriGlavnuFormu();
                    lf.dispose();
                }

            }
        });
        
    
    }

    public void otvoriFormu() {
        lf.setVisible(true);
    }
    
    
    
}
