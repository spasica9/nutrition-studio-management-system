/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije;

import repozitorijum.Repozitorijum;
import repozitorijum.db.DbGenerickiRepozitorijum;
import repozitorijum.db.DbRepozitorijum;

/**
 *
 * @author anas
 */
public abstract class ApstraktnaGenerickaOperacija {
    protected final Repozitorijum broker;

    public ApstraktnaGenerickaOperacija() {
        this.broker = new DbGenerickiRepozitorijum();
    }
    
     public final void izvrsi(Object objekat,String kljuc) throws Exception{
        try{
            preduslovi(objekat);
            zapocniTransakciju();
            izvrsiOperaciju(objekat,kljuc);
            potvrdiTransakciju();
        }catch (Exception e){
            ponistiTransakciju();
            throw e;
        }
    }

    protected abstract void preduslovi(Object param) throws Exception;
    protected abstract void izvrsiOperaciju(Object param, String kljuc) throws Exception;

    private void zapocniTransakciju() throws Exception{
        ((DbRepozitorijum) broker).connect();
    }

    private void potvrdiTransakciju() throws Exception{
        ((DbRepozitorijum) broker).commit();
    }

    private void ponistiTransakciju() throws Exception {
        ((DbRepozitorijum) broker).rollback();
    }

    private void ugasiKonekciju() throws Exception {
        ((DbRepozitorijum) broker).disconnect();
    }
    
    
    
}
