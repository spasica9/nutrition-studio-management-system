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
        UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
     
        UIManager.put("TableHeader.background", new Color(60, 120, 180));
        UIManager.put("TableHeader.foreground", Color.WHITE);
        UIManager.put("Table.selectionBackground", new Color(180, 210, 240));
        Font font = new Font("Segoe UI", Font.PLAIN, 13);
        UIManager.put("Label.font", font);
        UIManager.put("Button.font", new Font("Segoe UI", Font.BOLD, 13));
        UIManager.put("Table.font", font);

        UIManager.put("Panel.background", new Color(215, 225, 240));

    } catch (Exception e) {
        e.printStackTrace();
    }

    koordinator.Koordinator.getInstance().otvoriLoginFormu();
}
    
}
