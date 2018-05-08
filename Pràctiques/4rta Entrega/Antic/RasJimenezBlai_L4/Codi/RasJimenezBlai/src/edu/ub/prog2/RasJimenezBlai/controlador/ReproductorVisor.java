
package edu.ub.prog2.RasJimenezBlai.controlador;


import edu.ub.prog2.RasJimenezBlai.model.Audio;
import edu.ub.prog2.RasJimenezBlai.model.FitxerMostrable;
import edu.ub.prog2.RasJimenezBlai.model.FitxerReproduible;

import edu.ub.prog2.utils.AplicacioException;
import edu.ub.prog2.utils.EscoltadorReproduccioBasic;
import edu.ub.prog2.utils.ReproductorVisorBasic;
import java.io.File;
import java.io.Serializable;

/**
 *
 * @author mat.aules
 */
public class ReproductorVisor extends ReproductorVisorBasic implements Serializable{

    
    public ReproductorVisor(String vlcPath, EscoltadorReproduccioBasic controlador) {
        super(vlcPath, controlador);
    }

    public void mostra(FitxerMostrable fmo, int secs) throws AplicacioException {
        super.show((File)fmo,secs);
    }
    
    public void reprodueix(FitxerReproduible fmo) throws AplicacioException {
        super.play(fmo);
    }
    
    public void reprodueix(Audio audio, File fitxerImatge) throws AplicacioException {
        super.play(audio, fitxerImatge);
    }
}
