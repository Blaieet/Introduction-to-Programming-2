
package edu.ub.prog2.RasJimenezBlai.model;

import edu.ub.prog2.RasJimenezBlai.controlador.ReproductorVisor;
import java.io.Serializable;


/**
 *
 * @author mat.aules
 */
public abstract class FitxerMostrable extends FitxerMultimedia implements Serializable{
    

    protected transient ReproductorVisor vr;

    public FitxerMostrable(String cami, String nom, ReproductorVisor vr) {
        super(cami,nom); //FitxerMultimedia
        this.vr = vr; //Assigno reproductor
    }
    
    public void setReproductor(ReproductorVisor vr) {
        this.vr = vr;
    }

    public abstract void mostrar(int i);
    
    
    
}
