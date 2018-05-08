/*
 * @author Blai Ras Jimenez
 * Data: 9 de Febrer de 2016
 */

// Imports necesaris:
package edu.ub.prog2.RasJimenezBlai.vista;

import edu.ub.prog2.RasJimenezBlai.model.CarpetaFitxers;
import edu.ub.prog2.RasJimenezBlai.model.FitxerMultimedia;
import edu.ub.prog2.RasJimenezBlai.model.TaulaFitxers;
import edu.ub.prog2.utils.AplicacioException;
import edu.ub.prog2.utils.InFileFolder;
import edu.ub.prog2.utils.Menu;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;





public class AplicacioUB1 { 
    //Declarem les opcions per referir-se a le opcions del menú

    static private enum OpcionsMenuPrincipal{MENU_PRINCIPAL_AFEGIRF, MENU_PRINCIPAL_ELIMINARF, MENU_PRINCIPAL_MOSTRARCARPETA, MENU_PRINCIPAL_GUARDARCARPETA, MENU_PRINCIPAL_RECUPERAR, MENU_PRINCIPAL_SORTIR}

    //Declarem descripcions de les opcions

    static private String[] descMenuPrincipal ={"Afegir Fitxer Multimèdia", "Elimina un arxiu", "Mostra una carpeta", "Guarda una carpeta", "Recupera una carpeta", "Surt de l'aplicació"};

    //Taula de fitxer
    InFileFolder taula;
    
    
    // Constructor:
    
    public AplicacioUB1(boolean impliTaula) { 
        if (impliTaula) { //Si es cert que vull una implementacio amb una taula...:
            taula = new TaulaFitxers();
        } else {
            taula = new CarpetaFitxers();
        }
    }
    
    
    //Crea Menú
    
    
    
    
    public void gestioAplicacioUB() {

        //Creem l'objecte pel Menú. Li passem com a parametre el nom del menú
        Scanner sc;
        sc=new Scanner(System.in);
        //Creo una nova taula de tipus TaulaFitxers
        //Inicialitzo l'arxiu a afegir "en blanc"
        FitxerMultimedia arxiu = null;
        
        //Creem l'objecte pel Menú. Li passem com a parametre el nom del menú
        Menu<OpcionsMenuPrincipal> menu = new Menu<OpcionsMenuPrincipal>("Menu Principal", OpcionsMenuPrincipal.values());
        
        //Decripció de les opcions
        menu.setDescripcions(descMenuPrincipal);
        
        //Obtenim una opció des del menú i accionala
        
        OpcionsMenuPrincipal opcio = null; //Inicialitzo opcio
        do {
            //MOstrem opcions
            menu.mostrarMenu();
            //Demana una opcio
            opcio = menu.getOpcio(sc);
            
            //Fem les accions
            switch(opcio) {
                
                
                case MENU_PRINCIPAL_AFEGIRF: //Opció d'afegir un fitxer
                    System.out.println("Has triat Afegir Fitxer");
                    //Demano el cami on es troba el fitxer a afegir
                    System.out.println("On està el fitxer que vols afegir?");
                    String cami;
                    cami = sc.nextLine();
                    
                    arxiu = new FitxerMultimedia(cami); //Creo l'arxiu amb el cami demanat
                    //Actualizto els arxius amb tota la informacio que em demana l'enunciat
                
                    arxiu.demanaDadesTeclat(); //Demano per teclat el nom que ell desitja
                    
                    try { //Intenta, si no sorgira cap error...:
                        taula.addFitxer(arxiu); //Afegeixlo
                        System.out.println("Fitxer afegit correctament!");
                    } catch (AplicacioException error){
                        //Llença l'error que hagi pogut ocorrer
                        System.out.println("Fitxer NO afegit correctament, " +error.getMessage());
                    }
                    break;
                case MENU_PRINCIPAL_ELIMINARF: //Opcio d'eliminar un fitxer
                    
                    System.out.println("Has triat eliminar un arxiu");
                    
                    int index;
                    if (taula.getSize() != 0) { //Si la taula no esta buida...
                        System.out.println("Indica el numero del fitxer a eliminar");
                        index = sc.nextInt(); 
                        taula.removeFitxer(taula.getAt(index-1)); //Elimina el fitxer passat
                        System.out.println("Fitxer eliminat amb exit!");
                    } else {
                        System.out.println("No hi ha cap fitxer afegit!");
                    }
                    break;
                case MENU_PRINCIPAL_MOSTRARCARPETA:
                    if (taula.getSize() != 0) { //Si la taula no esta buida...
                        System.out.println("Has triat Mostrar Carpeta");
                        System.out.println(taula);
                    } else {
                        System.out.println("La carpeta esta buida!");
                    }
                    break;
                case MENU_PRINCIPAL_GUARDARCARPETA:
                    System.out.println("Has triat Guadar la Carpeta");
                    FitxerMultimedia fitxer;
                    String path;
                    System.out.println("Dona'm el cami del fitxer a guardar");
                    path = sc.nextLine();
                    fitxer = new FitxerMultimedia(path);
                    guardarCarpeta(fitxer);

                    break;
                case MENU_PRINCIPAL_RECUPERAR:
                    System.out.println("Has triar recuperar carpeta");
                    FitxerMultimedia f = null;
                    recuperarCarpeta(f);
                    System.out.println("Carpeta recuperada amb èxit!");
                    break;
                case MENU_PRINCIPAL_SORTIR:
                    System.out.println("Fins aviat!");
                    break;
            }
        } while (opcio!=OpcionsMenuPrincipal.MENU_PRINCIPAL_SORTIR);
        
        
    }
    
    public void guardarCarpeta(File fitxer) {
        try(FileOutputStream fout = new FileOutputStream(fitxer)) {
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(taula);
            oos.close();
            fout.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Error, el fitxer no existeix!");
        } catch (IOException ex) {
            Logger.getLogger(AplicacioUB1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void recuperarCarpeta(File fitxer) {
        Scanner sc;
        sc = new Scanner(System.in);
        String camiRecuperar;
        System.out.println("A on has guardat la carpeta?");
        
        camiRecuperar = sc.nextLine();
        fitxer = new File(camiRecuperar);
        try {
            FileInputStream fin = new FileInputStream(fitxer);
            ObjectInputStream ois = new ObjectInputStream(fin);
            taula = (InFileFolder)ois.readObject();
            ois.close();
            fin.close();
        } catch (IOException ex) {
            Logger.getLogger(AplicacioUB1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AplicacioUB1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
