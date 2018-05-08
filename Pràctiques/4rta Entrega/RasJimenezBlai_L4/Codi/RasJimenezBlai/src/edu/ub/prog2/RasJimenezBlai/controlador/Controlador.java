/**
 *
 * @author Blai Ras Jimenez
 */
package edu.ub.prog2.RasJimenezBlai.controlador;

import edu.ub.prog2.RasJimenezBlai.model.Audio;
import edu.ub.prog2.RasJimenezBlai.model.CarpetaFitxers;
import edu.ub.prog2.RasJimenezBlai.model.Dades;
import edu.ub.prog2.RasJimenezBlai.model.FitxerMostrable;
import edu.ub.prog2.RasJimenezBlai.model.FitxerReproduible;
import edu.ub.prog2.RasJimenezBlai.model.Imatge;
import edu.ub.prog2.RasJimenezBlai.model.Video;
import edu.ub.prog2.RasJimenezBlai.model.AlbumFitxersMultimedia;
import edu.ub.prog2.utils.AplicacioException;
import edu.ub.prog2.utils.InControlador;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 * Classe Controlador, autoritza l'acces a les dades
 *
 * @author Blai Ras
 */
public class Controlador implements InControlador {
    
    private Dades dades;
    private transient ReproductorVisor reproductor;
    private EscoltadorReproduccio escoltador;

    
    
    public Controlador() {
        dades = new Dades();
        escoltador = new EscoltadorReproduccio();
        reproductor = new ReproductorVisor("C:\\Program Files\\VideoLAN\\VLC", escoltador);
        
    }
    
    public JPanel getPanel() {
        return reproductor.getPanel();
    }
    
    public CarpetaFitxers getBiblio() {
        return dades.getCarpeta();
    }
    
    public ArrayList<AlbumFitxersMultimedia> getAlbums() {
        return dades.getAlbum();
    }
    
    public int getAlbumIndex(String titol) {
        return dades.indexAlbum(titol);
    }
    
    public void esborrarPath(String path) throws AplicacioException{
        dades.esborrarPath(path);
    }
   
    

    @Override
    public void afegirImatge(String cami, String nom, int alcada, int amplada) throws AplicacioException {
        
        Imatge imatge = new Imatge(cami,nom,alcada,amplada,reproductor);
        try {
            dades.getCarpeta().addFitxer(imatge);
        } catch (AplicacioException ex) {
            throw new AplicacioException("La imatge ja ha estat afegida!");
        }
    }

//    @Override
//    public void obrirFinestraReproductor() {
//        this.reproductor.open();
//    }
//
//    @Override
//    public void tancarFinestraReproductor() throws AplicacioException {
//        reproductor.close();
//    }

    @Override
    public void mostrarFitxer(int i, int segons) throws AplicacioException {
        CarpetaFitxers carpeta = new CarpetaFitxers();
        i--;
        carpeta.addFitxer(dades.getCarpeta().getAt(i));
       
        if (!escoltador.esticPubli()) {
            escoltador.iniciarReproduccio(carpeta, dades.getCiclic());
        } else {
            throw new AplicacioException("Espera a que acabi la publicitat");
        }

    }

    @Override
    public void reproduirCarpeta() throws AplicacioException {
       if (escoltador.esticPubli()) { //Si estic en publicitat m'he desperar
           throw new AplicacioException("Espera a que acabi la publicitat");
       } else {

           escoltador.iniciarReproduccio(dades.getCarpeta(), dades.getCiclic());
           
       }
    }

    @Override
    public void reproduirCarpeta(String titol) throws AplicacioException {
        if (!escoltador.esticPubli()) { //Si estic en publicitat m'he desperar
            int album = dades.indexAlbum(titol); //Obtinc album
            CarpetaFitxers carpeta = (CarpetaFitxers) dades.getAlbum().get(album); //Obtinc contingut del album
            escoltador.iniciarReproduccio(carpeta, dades.getCiclic());
        } else {
            throw new AplicacioException("Espera a que acabi la publicitat");
        }
    }

    @Override
    public void reemprenReproduccio() {
        reproductor.resume();
    }

    @Override
    public void pausaReproduccio() {
        reproductor.pause();
    }

    @Override
    public void aturaReproduccio() {
        reproductor.stop();
    }

    @Override
    public void saltaReproduccio() throws AplicacioException {
        if (escoltador.esticPubli()) {
            throw new AplicacioException("Espera a que acabi la publicitat!");
        } else {
            escoltador.salta();
        }
    }
    
    public void activarModeCiclic(boolean activar) {
        dades.setCiclic(activar);
    }
    
    public void desactivarModePremium() {
        Imatge imatge = new Imatge("logo.jpg", "LogoUB", 100,100,this.reproductor); //Iniciador de la imatge de publicitat
        escoltador.setPremium(imatge);
    }
    
    public boolean getCiclic() {
        return dades.getCiclic();
    }
    
    
    @Override
    public void reproduirFitxer(int i) throws AplicacioException {
        CarpetaFitxers carpeta = new CarpetaFitxers();
        i--; //Decremento ja que comenso a mostrar per 1
        carpeta.addFitxer(dades.getCarpeta().getAt(i));
        if (!escoltador.esticPubli()) {
            escoltador.iniciarReproduccio(carpeta, dades.getCiclic());
        } else {
            throw new AplicacioException("Espera a que acabi la publicitat");
        }

    }
    
    public void reproduirFitxerAlbum(File fitxer) throws AplicacioException {
        CarpetaFitxers carpeta = new CarpetaFitxers();
        carpeta.addFitxer(fitxer);
        
        if (!escoltador.esticPubli()) {
            escoltador.iniciarReproduccio(carpeta, dades.getCiclic());
        } else {
            throw new AplicacioException("Espera a que acabi la publicitat");
        }
    }
    
    
    
    //METODES "ANTICS"

    
    //Metode que afegeix un video
    @Override
    public void afegirVideo(String path, String nomVideo, String codec, float durada, int alcada, int amplada, float fps) throws AplicacioException {
        Video v = new Video(path, nomVideo, codec, durada, alcada, amplada, fps, reproductor);
        dades.getCarpeta().addFitxer(v);

    }
    
    @Override
    public void afegirAudio(String cami, String pathImatge, String nomAudio, String codec, float durada, int kpbs) throws AplicacioException {

        File fitxerImatge = new File(pathImatge);
        if (!fitxerImatge.exists()) {
            throw new AplicacioException("La imatge no existeix!");
        }
        Audio audio = new Audio(cami, fitxerImatge, nomAudio, codec, durada, kpbs, reproductor);
        
        dades.getCarpeta().addFitxer(audio);

    }

    @Override
    public List<String> mostrarBiblioteca() {
        return dades.mostrarBiblioteca();
    }





    @Override
    public void afegirAlbum(String string) throws AplicacioException {
        dades.afegirAlbum(string);
    }

    @Override
    public List<String> mostrarLlistatAlbums() {
        return this.dades.mostrarLlistatAlbums();
    }

    @Override
    public void esborrarAlbum(String string) throws AplicacioException {
        dades.esborrarAlbum(string);
    }

    @Override
    public boolean existeixAlbum(String string) {
        return dades.existeixAlbum(string);
    }

    @Override
    public void afegirFitxer(String string, int i) throws AplicacioException {
        dades.afegirFitxer(string, i);
    }

    @Override
    public List<String> mostrarAlbum(String string) throws AplicacioException {
        return dades.mostrarAlbum(string);
    }

    @Override
    public void esborrarFitxer(String string, int i) throws AplicacioException {
        dades.esborrarFitxer(string, i);
    }
    
    @Override
    public void esborrarFitxer(int i) throws AplicacioException {
        dades.esborrarFitxer(i);
    }
    

    @Override
    public void canviarTitolAlbum(String titolVell, String nouTitol) throws AplicacioException {
        dades.canviarTitolAlbum(titolVell, nouTitol);
    }

    @Override
    public void canviarMidaAlbum(String string, int i) throws AplicacioException {
        dades.canviarMidaAlbum(string, i);
    }

    @Override
    public void guardarDadesDisc(String string) throws AplicacioException {
        dades.guardarDadesDisc(string);
    }

    @Override
    public void carregarDadesDisc(String string) throws AplicacioException {
        dades = Dades.carregarDadesDisc(string);
        dades.setReproductor(reproductor);
    }
    
}
    

