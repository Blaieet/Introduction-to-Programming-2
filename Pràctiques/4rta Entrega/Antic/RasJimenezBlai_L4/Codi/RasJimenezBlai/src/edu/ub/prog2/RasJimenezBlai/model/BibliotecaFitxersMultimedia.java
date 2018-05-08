
package edu.ub.prog2.RasJimenezBlai.model;

import edu.ub.prog2.RasJimenezBlai.controlador.ReproductorVisor;
import edu.ub.prog2.utils.AplicacioException;
import java.io.File;
import java.io.Serializable;
import java.util.Iterator;


/**
 *
 * @author Blai Ras
 * Data: 28 de Març de 2016
 */

public class BibliotecaFitxersMultimedia extends CarpetaFitxers implements Serializable  {
    
    
    //Constructor (buit)
    public BibliotecaFitxersMultimedia() {
        
    }
    
    //3ra Entrega

    /**
     * Metode que "activa" el reproductor un cop he recuperat dades
     * @param vr
     */
    
    public void setReproductor(ReproductorVisor vr) {
        Iterator<FitxerMultimedia> itr = taulaFitxers.iterator();
        while (itr.hasNext()) {
            File f = (File) itr.next();
            if (f instanceof FitxerReproduible) {
                FitxerReproduible fr = (FitxerReproduible) f;
                fr.setReproductor(vr);
            } else if (f instanceof FitxerMostrable) {
                FitxerMostrable fm = (FitxerMostrable) f;
                fm.setReproductor(vr);
            }
        }
        
    }
    
    //METODES "ANTICS"
    
    @Override //Metode que afegeix un fitxer amb les condicions que jo vull
    public void addFitxer(File fitxer) throws AplicacioException {
        if (!contains(fitxer)) {
            if (fitxer.exists()) {
                taulaFitxers.add((FitxerMultimedia) fitxer);
            }else {
                throw new AplicacioException("El fitxer no existeix!");
            }
        } else {
            throw new AplicacioException("El fitxer ja existeix!");
        }
    }
    
    
    
    
}

