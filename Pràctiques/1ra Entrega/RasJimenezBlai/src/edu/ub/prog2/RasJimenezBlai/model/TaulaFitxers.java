/**
 *
 * @author Blai Ras
 * Data: 9 de Febrer de 2016
 */

//Imports necesaris
package edu.ub.prog2.RasJimenezBlai.model;

import edu.ub.prog2.utils.AplicacioException;
import edu.ub.prog2.utils.InFileFolder;
import java.io.File;
import java.io.Serializable;



public class TaulaFitxers implements InFileFolder, Serializable { //Clase TaulaFitxers hereda de la superclase File
    
    
    private File [] taulaFitxers; //La defineixo
    
    private int numFitxers; //Declaro una variable que conta el numero de Fitxers
    
    
    public TaulaFitxers() { //Constructor que em construeix la taula de mida 100
        taulaFitxers = new File[100];
        numFitxers = 0;
    }

    @Override
    public int getSize() { //Metode que em retorna el numero de Fitxers creats 
        return numFitxers;
    }
    
    //Metode que afegeix un fitxer, i retorna un error (Excepcio) si hi ha algun tipus d'error
    @Override
    public void addFitxer(File fitxer) throws AplicacioException{
        if (numFitxers < taulaFitxers.length) { //Si la taula no esta plena...
           
            taulaFitxers[numFitxers] = fitxer; //Afegeixlo
            numFitxers++; //Incrementa
        } else {
            throw new AplicacioException("No hi ha més espai"); //Error d'espai
        }
    }
    
    
    @Override
    public void removeFitxer(File fitxer){ //Metode que permet eliminar un fitxer pasat
        int i = 0; //Variable que conta els arxius
        while (!fitxer.equals(taulaFitxers[i])) { //Mentre no el trobi...
                if (i >= taulaFitxers.length) { //Si arribo al final de la taula
                    System.out.println("Fitxer no trobat...!"); //Vol dir que el fitxer no existeix
                    break;
            } else {
                i++; //Si cap de les anteriors, ves a pel seguent fitxer
            }
        }
        
        for(int t = i+1; t<numFitxers; t++) { //Iterador per no deixar espais en blanc
            
            taulaFitxers[t-1] = taulaFitxers[t]; //Tiro cap a la dreta cada arxiu
             
        }
        taulaFitxers[numFitxers] = null; //Esborro l'arxiu
        numFitxers--; //Vaig a pel anterior arxiu
    }


    @Override
    public void clear() { //Metode que m'esborra tota la carpeta
        for (int i =0; i<taulaFitxers.length; i++) { //Iterador que va fitxer per fitxer
            taulaFitxers[i]=null; //Esborrals tots
        }
    }
    
    
    @Override
    public File getAt(int position) { //Getter que em retorna la posicio (Int) dun fitxer passat
        return taulaFitxers[position];
        
    }
    
    
    @Override
    public boolean isFull() { //Bolea que comproba si la taula esta plena
        return (numFitxers==100);
    }
    
    public boolean isEmpty() {
        return (numFitxers==0);
    }
    
    
    @Override
    public String toString() {
        String s = "\n------------------TAULA FITXERS------------------\n";
        int t = 0;
        
        for (int i = 0; i < numFitxers; i++) {
            t++;
            s =s+"\n" + "[" + t + "]"+taulaFitxers[i].toString();
        }
        
        return s;
    }

}

//ToString str = [i+1]+getAt(i)
