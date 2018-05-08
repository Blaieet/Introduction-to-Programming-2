
package edu.ub.prog2.RasJimenezBlai.model;



import edu.ub.prog2.RasJimenezBlai.controlador.ReproductorVisor;
import edu.ub.prog2.utils.AplicacioException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author mat.aules
 */
public class Dades implements Serializable {
    //Atributs
    BibliotecaFitxersMultimedia biblio;
    ArrayList<AlbumFitxersMultimedia> albums;
    boolean ciclic;


    
    //Constructor
    public Dades() {
        albums = new ArrayList();
        biblio = new BibliotecaFitxersMultimedia();
        ciclic = false;
    }
    
    public void esborrarPath(String path) throws AplicacioException {
        File fitxer = new File(path);
        biblio.removeFitxer(fitxer);
        for (int z = 0; z < albums.size(); z++) {
            if (albums.get(z).contains(fitxer)) {
                for (int i = 0; i < albums.get(z).getMida(); i++){
                    albums.get(z).removeFitxer(fitxer);
                }
            }
        }
    }
    
    //NOUS METODES DE LA TERCERA ENTREGA

    /**
     * Retorna la carpeta per ser usada a Controlador
     * @return
     */
    
    public CarpetaFitxers getCarpeta() {
        return biblio;
    }
    
    /**
     * Retorna l'Array d'Albums per ser usat a Controlador
     * @return
     */
    public ArrayList<AlbumFitxersMultimedia> getAlbum() {
        return albums;
    }
    
    /**
     * Crida al metode setReproductor de Biblioteca
     * @param vr
     */
    public void setReproductor(ReproductorVisor vr) {
        biblio.setReproductor(vr);
    }
    
    public boolean getCiclic() {
        return ciclic;
    }
    
    public void setCiclic(boolean ciclic) {
        this.ciclic = ciclic;
    }

    
    
    
    //Metodes "Antics"

    
    public void addFile(File fitxer) throws AplicacioException {
        biblio.addFitxer(fitxer);
    }

    public List<String> mostrarBiblioteca() {
        ArrayList<String> aRetornar = new ArrayList();
        if (biblio.getSize() > 0) {
            for (int i = 0; i < biblio.getSize(); i++) {
                aRetornar.add(biblio.getAt(i).toString());
            }
        } else{
        }
        return aRetornar;
    }
    //Metode que afegeix un album
    public void afegirAlbum(String titol) throws AplicacioException {
        int titolValid = indexAlbum(titol);
        if (titolValid == -1) {
            AlbumFitxersMultimedia x = new AlbumFitxersMultimedia(titol);
            albums.add(x);
        } else {
            throw new AplicacioException("El titol introduit ja esta utilitzat");
        }
    }
    
    //Metode que mostra tots els albums
    public List<String> mostrarLlistatAlbums() {
        
        ArrayList<String> aRetornar = new ArrayList();
        
        if (albums.size() >0) {
            for (int i = 0; i < albums.size(); i++) {
                aRetornar.add(albums.get(i).toString());
            }
        } else {

        }
        return aRetornar;
    }

    //Boolea de si dos albums son iguals
    public boolean existeixAlbum(String titol) {
        int numAlbum = indexAlbum(titol);
        return !(numAlbum == -1);
    }

    //Metode que afegeix un fitxer a un album
    public void afegirFitxer(String titol, int i) throws AplicacioException {
        i--;
        int album;
        album = indexAlbum(titol);
        if (album == -1){
            throw new AplicacioException ("No s'ha trobat l'album");
        } else {
            albums.get(album).addFitxer(biblio.getAt(i));
        }
    }

    //Metode que mostra la informacio dun album
    public List<String> mostrarAlbum(String titol) throws AplicacioException {
        int numAlbum = indexAlbum(titol);
        ArrayList<String> aRetornar = new ArrayList();
        
        if (albums.get(numAlbum).getMida() > 0) {
        
            for (int index = 0; index<albums.get(numAlbum).getSize(); index++){
                    aRetornar.add(albums.get(numAlbum).getAt(index).toString());
                }
        }else {
            
        }
        return aRetornar;
    }
    
    //Metode que esborra un FM
    public void esborrarFitxer(int i) throws AplicacioException {
        
        i--;
        String path = biblio.getAt(i).getAbsolutePath();
        
        File fitxer = new File(path);
        
        if (i > biblio.getSize()) {
            throw new AplicacioException("No s'ha pogut suprimir el fitxer!");
        } else {
            biblio.removeFitxer(fitxer);
            for (int z = 0; z < albums.size(); z++) {
                albums.get(z).removeFitxer(fitxer);
            }
        }
    }
    
    public void esborrarFitxer(String string, int i) {
        int pos = indexAlbum(string);
        AlbumFitxersMultimedia album = albums.get(pos); // Obtenim àlbum
        album.removeFitxerIndex(i);
    }

    //Metode que canvia el titol dun album
    public void canviarTitolAlbum(String titol, String nouTitol) throws AplicacioException {
        int comprovarTitol = indexAlbum(nouTitol);
        int trobarAlbum = indexAlbum(titol);
        
        if (trobarAlbum != -1 && comprovarTitol == -1) {
            albums.get(trobarAlbum).setTitol(nouTitol);
        } else {
            throw new AplicacioException("El titol introduit ja esta utilitzat o l'Album no existeix!");
        }
    }

    //Metode que canvia la mida dun album
    public void canviarMidaAlbum(String titol, int i) throws AplicacioException {
        int numAlbum = indexAlbum(titol);
        if (numAlbum != -1) {
            albums.get(numAlbum).setMida(i);
        } else {
            throw new AplicacioException("L'album que has triat no existeix!");
        }
        
    }
    //Metde que guarda les dades a un fitxer
    public void guardarDadesDisc(String cami) throws AplicacioException {
        File fitxer;
        fitxer = new File(cami);
        try(FileOutputStream fout = new FileOutputStream(fitxer)) {
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(this);
            oos.close();
            fout.close();
        } catch (FileNotFoundException ex) {
            throw new AplicacioException("Error, el fitxer no existeix!");
        } catch (IOException ex) {
            throw new AplicacioException("Error de tipus IO");
        }
    }

    //Mètode que carrega les dades desde un fitxer
    public static Dades carregarDadesDisc(String cami) throws AplicacioException  {
        File fitxer = new File(cami);
        Dades dades= null;
        try {
            FileInputStream fin = new FileInputStream(fitxer);
            ObjectInputStream ois = new ObjectInputStream(fin);
            dades = (Dades)ois.readObject();
            ois.close();
            fin.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Dades.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Dades.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Dades.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dades;
       
    }

    //MEtode que esborra un album
    public void esborrarAlbum(String titol) throws AplicacioException {
        
        int posicio = indexAlbum(titol);

        if (albums.size() > 0) {
        
            if (posicio == -1) {
                throw new AplicacioException("L'album no existeix!");
            } else {
                albums.remove(posicio);
            }
        } else {
            throw new AplicacioException("No hi ha cap album afegit!");
        }
    }
    
    //Metode que troba la posicio d'un album pel seu titol
    public int indexAlbum(String titul) {
        int index;
        for (index = 0; index < albums.size(); index++) {
            if (albums.get(index).getTitol().equals(titul)) {
                return index;
            }
        } return -1;
    }
    
    public AlbumFitxersMultimedia getAlbumTitol(String titol) {
        for (int i = 0; i < albums.size(); i++) {
            if (albums.get(i).getTitol() == titol) {
                return albums.get(i);
            }
        }
        return null;
    }
    

    
}
