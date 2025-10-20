/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repozitorijum.db;

import repozitorijum.Repozitorijum;

/**
 *
 * @author anas
 */
public interface DbRepozitorijum<T> extends Repozitorijum<T> {
    
     default public void connect() throws Exception{
        DbConnectionFactory.getInstance().getConnection();
    }
    
    default public void disconnect() throws Exception{
        DbConnectionFactory.getInstance().getConnection().close();
    }
    
    default public void commit() throws Exception{
        DbConnectionFactory.getInstance().getConnection().commit();
    }
    
    default public void rollback() throws Exception{
        DbConnectionFactory.getInstance().getConnection().rollback();
    }
    
}
