/**
 *
 * @author Blai Ras Jimenez
 */
package edu.ub.prog2.RasJimenezBlai.controlador;

import edu.ub.prog2.RasJimenezBlai.model.CarpetaFitxers;
import edu.ub.prog2.RasJimenezBlai.model.FitxerMostrable;
import edu.ub.prog2.RasJimenezBlai.model.FitxerReproduible;
import edu.ub.prog2.RasJimenezBlai.model.Imatge;
import edu.ub.prog2.utils.EscoltadorReproduccioBasic;
import java.io.File;
import java.io.Serializable;
import javax.swing.JOptionPane;




/**
 *
 * @author blrasjim7.alumnes
 */
public class EscoltadorReproduccio extends EscoltadorReproduccioBasic {
    
    CarpetaFitxers carpeta;
    int count;
    boolean ciclic;
    boolean premium = true;
    boolean publi;
    File f;
    Imatge anunci;
    final int TEMPS = 10;
    
    public EscoltadorReproduccio() {
        publi=false;
        premium = true;
        count = 0;
    }

    /**
     * Es crida cada cop que acaba de reproduirse un fitxer
     * Es gestiona si ets premiu, si vens de publicitat o si no
     */
    @Override
    protected void onEndFile() {
        if (!premium) {
            if (publi) {
                publi=false;
                next(); //No ets premium i acabes de veure publicitat
            } else {
                nextPubli(); //No et premium i acabes de veure un arxiu
            }
        } else {
            next(); //Ets premium, pases al seguent
        }

    }

    /**
     * Metode que gestiona el seguent fitxer a reproduir
     * Metre tinguis fitxer reproduiras. Si no tens mes i no hi ha reproduccio ciclcia acaba
     * Si la reproduccio ciclica esta activada, torna a comencar
     */
    @Override
    protected void next() {
        if (hasNext()) {
            f = this.carpeta.getAt(count);
            if (f.exists()) { //Si el fitxer existeix
                if (f instanceof FitxerMostrable) { //Si em trobo un fitxer de tipus Mostrable
                    ((FitxerMostrable)f).mostrar(TEMPS); //El mostro 10 segons
                } else if (f instanceof FitxerReproduible){ //Si es de tipus Reproduible
                    ((FitxerReproduible)f).reproduir(); //El reprodueixo
                }
                count++; //Incremento per agafar el seguent fitxer
            } else {
                count++; //Si no exiteix, vaig a pel seguent fitxer
                next(); //I torno a intentar-ho
            }
        } else{
            if (ciclic) { //Si la repro. ciclica esta actibada, torno a reproduir
                reiniciaRepro();
            } else {
                JOptionPane.showMessageDialog(null, "La reproducció ha acabat!", "Avís", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        
    }
      
         //Try catch intenta reproduir el seguetn que es pot
        //Fa preguntes (Ciclic, ha acabat...)

    /**
     *Metode que mira si tinc mes fitxers a reproduir
     * @return
     */
    
    @Override
    protected boolean hasNext() { 
        return (count < carpeta.getSize());
    }

    /**
     *Metode que reprodueix la publicitat durant 5 segons
     */
    @Override
    protected void nextPubli() {
        anunci.mostrar(5);
        publi = true;
    }
    
    /**
     *Inicia la reproduccio de la carpeta. Assigna booleans segons condicio
     * @param carpeta
     * @param ciclic
     */
    public void iniciarReproduccio(CarpetaFitxers carpeta, boolean ciclic){        
        this.carpeta = carpeta;
        this.ciclic = ciclic;
        this.publi = false;
        this.premium = premium;
        count = 0;
        next();
    }
    
    /**
     *Metode que retorna cert si estic mirant publicitat, false si no
     * @return
     */
    public boolean esticPubli() {
        return publi;
    }
    
    /**
     *Metode que desactiva el mode premium i seteja la imatge de publicitat
     * @param anunci
     */
    public void setPremium(Imatge anunci) {
        this.premium = false;
        this.anunci = anunci;
    }

    /**
     * Metode que activa o desactiva la reproduccio ciclica
     * @param ciclic
     */
    public void setCiclic(boolean ciclic) {
        this.ciclic = ciclic;
    }
    
    /**
     * Metode que retorna la reproduccio ciclica
     * @return
     */
    public boolean getCiclic() {
        return ciclic;
    }

    
    public void salta()  {
        if (premium) {
            next();
        } else {
            nextPubli();
        }
    }
    
    public void reiniciaRepro() {
        count = 0;
        next();
    }
    
}
