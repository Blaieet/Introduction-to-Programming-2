/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ub.prog2.RasJimenezBlai.model;

import edu.ub.prog2.RasJimenezBlai.controlador.ReproductorVisor;
import edu.ub.prog2.utils.AplicacioException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author mat.aules
 */
public class Imatge extends FitxerMostrable implements Serializable{
    
    //Atributs d'una imatge
    private int alcada;
    private int amplada;

    
    public Imatge(String cami, String nom, int alcada, int amplada, ReproductorVisor vr) {
        super(cami,nom,vr); //Clase FitxerMostrable
        this.alcada = alcada;
        this.amplada = amplada;
    }


    @Override
    public void mostrar(int segons){
        try {
            this.vr.mostra(this, segons);
        } catch (AplicacioException ex) {
            Logger.getLogger(Imatge.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
}
