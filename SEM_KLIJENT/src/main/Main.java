/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.Color;
import java.awt.Font;
import javax.swing.UIManager;

/**
 *
 * @author anas
 */
public class Main {
    

    public static void main(String[] args) {
    try {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                UIManager.setLookAndFeel(info.getClassName());
                UIManager.put("control", new Color(200, 220, 240));
                UIManager.put("nimbusBase", new Color(60, 120, 180)); 
                UIManager.put("nimbusFocus", new Color(80, 140, 200));
                UIManager.put("nimbusSelectedText", Color.WHITE);
                UIManager.put("nimbusSelectionBackground", new Color(100, 150, 220));
                UIManager.put("text", Color.BLACK);
                break;
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    koordinator.Koordinator.getInstance().otvoriLoginFormu();
}
    
}
