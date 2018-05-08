
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
        taulaFitxers = new ArrayList<FitxerMultimedia>(); //Defino l'arrayList -- Sense limit ara!
        
    }
    
    public int getPos(File fitxer) {
        int pos=-1;
        for (int i = 0; i<taulaFitxers.size(); i++) {
            if (taulaFitxers.get(i).equals(fitxer)) {
                pos = i;
            }
        }
        return pos;
    }
    
    public boolean isEmpty() {
        return taulaFitxers.size() == 0;
    }
    
    public ArrayList<FitxerMultimedia> getLlista() {
        return taulaFitxers;
    }

    @Override
    public int getSize() { //Mètode que em retorna la mida de la taula
        return taulaFitxers.size();
    }

    @Override
    public void addFitxer(File fitxer) throws AplicacioException { //Mètode que m'afegeix un fitxer a la taula
        taulaFitxers.add((FitxerMultimedia) fitxer);
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
       return false;
    }
    
   

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

    public boolean contains(File fitxer) {
        Iterator itr = taulaFitxers.iterator();
        boolean p = false;
        while (itr.hasNext()){
            p = (itr.next().equals(fitxer));
        }
        return p;
    }
}


