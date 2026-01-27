/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package konfiguracija;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author anas
 */
public class Konfiguracija {
    
    private static Konfiguracija instance;
    private Properties konfiguracija;

    public Konfiguracija() throws IOException {
        konfiguracija = new Properties();
        try {
            konfiguracija.load(new FileInputStream("D:\\projekti\\PS\\SEM_SERVER\\config\\config.properties"));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        
    }
    
    
   
    public static Konfiguracija getInstance()  {
        if (instance==null) {
            try {
                instance = new Konfiguracija();
            } catch (IOException ex) {
                Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return instance;
    }

    public String getProperty(String key) {
        return konfiguracija.getProperty(key,"n/a");
    }

    public void setProperty(String key, String value) {
        konfiguracija.setProperty(key, value);
    }
    
    public void sacuvajIzmene() {
        try {
            konfiguracija.store(new FileOutputStream("D:\\PS vezbe\\SEM_SERVER\\config\\config.properties"),null);
        } catch (IOException ex) {
            Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
