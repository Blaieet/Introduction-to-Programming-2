/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestorzoo1;

/**
 *
 * @author mat.aules
 */
public class GestorZoo2 {
    
    public static void main(String[] args) {
        
        Cuidador pere = new Cuidador("Pere","12345678E");
        
        Data dataTigre = new Data("15/05/2016");
        
        Data dataTigre2 = new Data("28/04/2016");
        
        Data dataSerp = new Data("06/05/2016");
        
        Data dataSerp2 = new Data("20/04/2016");
        
        Tigre tigre = new Tigre(1,dataTigre,5, (float) 1.5);
        
        Tigre tigre2 = new Tigre(2,dataTigre2,2,2);
        
        Serp serp = new Serp(3, dataSerp,4);
        
        Serp serp2 = new Serp(4, dataSerp2, 1);
        
        pere.afegirAnimal(tigre);
        pere.afegirAnimal(tigre2);
        pere.afegirAnimal(serp);
        pere.afegirAnimal(serp2);
        
        Data avui = new Data("20/04/2016");
        
        //AssignacioDiaria assignacio = new AssignacioDiaria(pere,avui);
        
        pere.realitzarTotesCures();
        
        
    }
    
}
