
package edu.ub.prog2.RasJimenezBlai.model;


import edu.ub.prog2.RasJimenezBlai.controlador.ReproductorVisor;
import edu.ub.prog2.utils.AplicacioException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Blai Ras
 */

public class Video extends FitxerReproduible implements Serializable{
    
    //Atributs

    private int alcada;
    private int amplada;
    private float fps;
    
    
    //Constructor amb els parametres corresponents i l'assignacio dels comuns
    public Video(String cami, String nom, String codec, float durada, int alcada, int amplada, float fps, ReproductorVisor vr) {
        super(cami, nom, codec, durada, vr);
        this.alcada = alcada;
        this.amplada = amplada;
        this.fps = fps;
        
        
    }

    @Override //Metode a reproduir un fitxer
    public void reproduir(){
        try {
            this.vr.reprodueix(this);
        } catch (AplicacioException ex) {
            Logger.getLogger(Video.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
