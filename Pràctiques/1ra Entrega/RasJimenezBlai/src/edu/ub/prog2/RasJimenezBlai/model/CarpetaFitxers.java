
package edu.ub.prog2.RasJimenezBlai.model;

//Imports necesaris
import edu.ub.prog2.utils.AplicacioException;
import edu.ub.prog2.utils.InFileFolder;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Blai Ras
 * Data: 9 de Març de 2016
 * 
 */
public class CarpetaFitxers implements Serializable, InFileFolder { //Carpeta Fitxers modifica de la classe InFileFolder
    
    protected ArrayList<FitxerMultimedia>taulaFitxers; //Declaro l'ArrayList
    
    public CarpetaFitxers() {
        taulaFitxers = new ArrayList<FitxerMultimedia>(100); //Defino l'arrayList
        
    }

    @Override
    public int getSize() { //Mètode que em retorna la mida de la taula
        return taulaFitxers.size();
    }

    @Override
        public void addFitxer(File fitxer) throws AplicacioException { //Mètode que m'afegeix un fitxer a la taula i salta si algo no va bé
        
        if (!isFull()) { //Si la taula no està plena...
            taulaFitxers.add((FitxerMultimedia) fitxer);
        } else {
            throw new AplicacioException("Taula plena"); 
        }
        
    }

    @Override
    public void removeFitxer(File fitxer) { //Mètode que m'elimina un fitxer passat de la taula
        taulaFitxers.remove(fitxer);
    }

    @Override
    public File getAt(int i) { ////Mètode que em retorna un fitxer en concret, demanat per index
        return taulaFitxers.get(i);
    }

    @Override
    public void clear() { //Mètode que em buida la taula
        taulaFitxers.clear();
    }

    @Override
    public boolean isFull() { //Mètode que em retorna cert/fals segons si la taula està plena
        if (getSize() == 100) {
            return true;
        } else {
            return false;
        }
    }
    
    //AMB ITERADORS
    /*
    @Override
    public String toString() { //Mètode que m'imprimeix per pantalla tots els fitxers de la taula
        Iterator itrFitxer = taulaFitxers.iterator();
        String s = "\n------------------TAULA FITXERS------------------\n";
        FitxerMultimedia file;
        int index = 0;
        while (itrFitxer.hasNext()) {
            file = (FitxerMultimedia) itrFitxer.next();
            index++;
            s = s+ "["+index+"]"+taulaFitxers.toString();
        }
        return s;
    }
*/
    //AMB For
    @Override
    public String toString(){
        String s = "\n-------------------TAULA FITXERS-------------------\n";
        
        int z=0;
        for (int i = 0; i < getSize(); i++) {
            z++;
            s = s+"["+z+"]" + taulaFitxers.get(i).toString()+"\n";
        }
        return s;
    }




    
}


