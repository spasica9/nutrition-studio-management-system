/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.Nutricionista;
import forme.GlavnaForma; 
import koordinator.Koordinator;

/**
 *
 * @author anas
 */
public class GlavnaFormaKontroler {
    
    private final GlavnaForma gf;
    
     public GlavnaFormaKontroler(GlavnaForma gf) {
        this.gf=gf;
        addActionListeners();
    }

    private void addActionListeners() {
    }
    
    
    public void otvoriFormu() {
        gf.setVisible(true);
        Nutricionista ulogovani = Koordinator.getInstance().getUlogovani();
        gf.getLblUlogovani().setText(ulogovani.getIme() + " " + ulogovani.getPrezime());
    }
    
}
