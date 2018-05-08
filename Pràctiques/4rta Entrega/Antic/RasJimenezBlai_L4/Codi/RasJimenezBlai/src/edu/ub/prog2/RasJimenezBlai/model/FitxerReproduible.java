
package edu.ub.prog2.RasJimenezBlai.model;

import edu.ub.prog2.RasJimenezBlai.controlador.ReproductorVisor;
import java.io.Serializable;

/**
 *
 * @author Blai Ras
 */

public abstract class FitxerReproduible extends FitxerMultimedia implements Serializable{
    
    //Atributs
    private String nom;
    private float durada;
    private String codec;
    protected transient ReproductorVisor vr;
    
    //Metode de clase, reprodueix un fitxer, adaptanse el tipus
    protected FitxerReproduible(String cami, String nom, String codec, float durada, ReproductorVisor vr) {
        super(cami,nom);
        this.codec = codec;
        this.durada = durada;
        this.vr = vr;
    }
    
    public void setReproductor(ReproductorVisor vr) {
        this.vr = vr;
    }
    
    //Crida al metode
    public abstract void reproduir();

    
}
