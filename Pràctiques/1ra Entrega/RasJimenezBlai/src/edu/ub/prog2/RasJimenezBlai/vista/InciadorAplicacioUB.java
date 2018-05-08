/*
 *
 * @author Blai Ras
 * Data: 9 de Febrer de 2016
 */
package edu.ub.prog2.RasJimenezBlai.vista;

import java.util.Scanner;


public class InciadorAplicacioUB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc;
        sc = new Scanner(System.in);
        boolean impliTaula;
        String resposta;
        System.out.println("Vols utilitzar TaulaFitxers o CarpetaFitxers? (T/C)"); //Pregunto quina implementacio vol
        resposta = sc.nextLine(); //La proceso
        if ("T".equals(resposta)) { //Si la resposta es "T"...:
            impliTaula = true;
        } else {
            impliTaula = false;
        }
        
        AplicacioUB1 aplicacio = new AplicacioUB1(impliTaula); //Crea el reproductor
        
        aplicacio.gestioAplicacioUB(); //Administra el reproductor
        
    }
    
}
