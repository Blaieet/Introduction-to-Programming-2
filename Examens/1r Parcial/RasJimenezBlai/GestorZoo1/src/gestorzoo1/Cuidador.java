/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestorzoo1;

import static com.sun.org.apache.bcel.internal.Repository.instanceOf;
import java.util.ArrayList;
import java.util.Iterator;


/**
 *
 * @author mat.aules
 */
public class Cuidador {
    
    private String nom;
    private String nif;
    private ArrayList<Animal> llista;
    
    public Cuidador(String nom, String nif) {
        this.nom = nom;
        this.nif = nif;
        llista = new ArrayList();
    }
    
    //Getters
    
    public String getNom() {
        return nom;
    }
    
    public String getNif() {
        return nif;
    }
    
    public ArrayList<Animal> getLlista() {
        return llista;
    }
    
    //Consultors
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public void setNif(String nif) {
        this.nif = nif;
    }
    
    
    public void afegirAnimal(Animal a) {
        llista.add(a);
    }

    @Override
    public String toString() {
        
        ArrayList<Animal>aRetornar = new ArrayList();
        String s ="";
        
        for(Iterator<Animal> itr = llista.iterator(); itr.hasNext(); ) {
            s = "\n"+ s + itr.next().toString()+"\n";

            //aRetornar.add(itr.next().toString());
        }
        
        return s;
    }
    
    //Exercici 2
    
    public void realitzarTotesCures() {
        for(Iterator<Animal> itr = llista.iterator(); itr.hasNext();) {
            itr.next().realitzarCura();
        }
    }
    
    //Exercici 3
    
    public String alletarTigres() {
        String s = "";
        Tigre temp;
        
        for(Iterator<Animal> itr = llista.iterator(); itr.hasNext();) {
            if (itr.next() instanceof Tigre){
                
                temp = (Tigre) itr.next();
                
                s = s+"El tigre "+temp.id+" necesita "+temp.getLitres()+" Litres de llet";
                
                
            }
        }
        
        
        return s;
        
        
    }
    
    
     
    
}
