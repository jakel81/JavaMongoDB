/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Jo
 */
public class Database {
    
    private String nomDatabase;
    
    public Database() {
    }

    public Database(String nomDatabase) {
        this.nomDatabase = nomDatabase;
    }

    public String getNomDatabase() {
        return nomDatabase;
    }

    public void setNomDatabase(String nomDatabase) {
        this.nomDatabase = nomDatabase;
    }

    @Override
    public String toString() {
        return "Database{" + "nomDatabase=" + nomDatabase + '}';
    }   
}
