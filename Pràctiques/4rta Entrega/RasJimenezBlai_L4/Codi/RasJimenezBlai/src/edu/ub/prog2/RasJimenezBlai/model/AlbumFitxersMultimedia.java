/**
 *
 * @author Blai Ras
 * Data: 28 de Març de 2016
 */

package edu.ub.prog2.RasJimenezBlai.model;

import edu.ub.prog2.utils.AplicacioException;
import java.io.File;
import java.io.Serializable;

/**
 *
 * @author blair
 */
public class AlbumFitxersMultimedia extends CarpetaFitxers implements Serializable {
    private String titol;
    private int mida;
    
    //Constuctor
    public AlbumFitxersMultimedia(String titol) {
        this.titol = titol;
        this.mida = 10;
    }
    
    public void removeFitxerIndex(int id) {
        taulaFitxers.remove(id);
    }
    
    public boolean isEmpty() {
        return taulaFitxers.size() == 0;
    }
    
    public String getTitol() {
        return titol;
    }
    
    public void setTitol(String titol) {
        this.titol = titol;
    }
    
    public int getMida() {
        return mida;
    }
    
    public void setMida(int mida) {
        this.mida = mida;
    }
    
    
    
    
    @Override
    public String toString() {
        String s = "";
        s = s + "Titol: " + getTitol()+
                ", Mida: " + getMida();
        return s;  
        
    }
    

    @Override
    public void addFitxer(File fitxer) throws AplicacioException {
        if (this.isFull()) {
            throw new AplicacioException("La taula esta plena");
        } else {
            super.taulaFitxers.add((FitxerMultimedia) fitxer);
        }
    }
    
    @Override
    public boolean isFull() {
        return (taulaFitxers.size() >= mida); 
    }
    

    


    
}
