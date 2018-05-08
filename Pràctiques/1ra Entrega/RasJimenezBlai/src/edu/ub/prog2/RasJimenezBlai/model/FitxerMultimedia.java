/**
 *
 * @author Blai Ras Jimenez
 * Data: 9 de Febrer de 2016
 */

//Imports necesaris
package edu.ub.prog2.RasJimenezBlai.model;

import java.io.File;
import java.util.Date;
import java.util.Scanner;


public class FitxerMultimedia extends File{ //La clase de FitxerMultimedia hereda de la superclase File
    
    private String nomQueliposo; //Atribut corresponent al nom que desitjo posarli, no l'original
    
   
    
    public FitxerMultimedia(String cami) { //Metode que em crea fitxers
        super(cami); 
    }
    
    public String getNomFitxer() { //Mètode que em retorna el nom desitjat
        return nomQueliposo;
    }
    
    public void setNomFitxer(String nom) { //Setter que m'estableix el nom que desitjo
        nom = nomQueliposo;
    }
    
    public String getNom() { //Metode que troba el nom original del fitxer dins la clase File
        return super.getName();
    }

    public Date getUltimaModificacio() { //Mètode que em retorna la data d'última modificació
    long tempsMillisecs = super.lastModified(); // super és la classe pare File
    Date data = new Date(tempsMillisecs);
    return data;
    }
        
    public String getCamiAbsolut() { //Getter que em retorna el cami on es troba el fitxer
        return (super.getAbsolutePath());
    }

    public String getExtensio() { //Getter que retalla el cami del fitxer fins nomès obtenir l'extensio
        
        String extensio;
        int index;
        index = super.getAbsolutePath().lastIndexOf("."); //L'extensio es troba just despres del punt
        extensio=super.getAbsolutePath().substring(index+1);
        return extensio;
    }
       
    public boolean demanaDadesTeclat() { //Metode que demana per pantalla el nom desitjat
        Scanner sc;
        sc = new Scanner(System.in);
        System.out.println("Digues el nom del fitxer"); 
        nomQueliposo = sc.nextLine();
        if (nomQueliposo != null) {
            System.out.println("Gràcies");
            this.setNomFitxer(nomQueliposo);
            return (true);

        } else {
            System.out.println("Nom mal introduit");
            return false;
        }
 
    }
        

    @Override
    public boolean equals(Object fitxerMultimedia) { //Bolea que comproba si dos fitxers son iguals
        return super.equals(fitxerMultimedia);
    }
        //Equals de Superclase
        //Son iguals si tenen fitxers iguals
            
    
        
    @Override
    public String toString() { //Metode que imprimeix per pantalla tota la informació d'un fitxer
        String s;
        s ="Nom= "+getNom()+" data= "+getUltimaModificacio()+" Nom Fitxer= "+getNomFitxer()+" Ext= "+"'"+getExtensio()+"'"+" Cami Complet= "+getCamiAbsolut();
        return s;
    }

}
