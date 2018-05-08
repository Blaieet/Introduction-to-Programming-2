
package edu.ub.prog2.RasJimenezBlai.model;


import edu.ub.prog2.RasJimenezBlai.controlador.ReproductorVisor;
import edu.ub.prog2.utils.AplicacioException;
import java.io.File;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Blai Ras
 */
public class Audio extends FitxerReproduible implements Serializable {
    
    //Atributs
    //private transient ReproductorVisor vr;
    private int kpbs;
    private File fitxerImatge;
    
    //Constructor, amb els atributs corresponents i l'assingacio d'aquells comuns
    public Audio(String cami, File fitxerImatge, String nom, String codec, float durada, int kpps, ReproductorVisor vr) {
        super(cami, nom, codec, durada, vr);
        this.fitxerImatge = fitxerImatge;
        this.kpbs = kpbs;
    }

    @Override //Metode a reproduir un fitxer
    public void reproduir() {
        if (fitxerImatge.exists()) {
            try {
                this.vr.reprodueix(this,fitxerImatge);
            } catch (AplicacioException ex) {
                Logger.getLogger(Audio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } 
}
