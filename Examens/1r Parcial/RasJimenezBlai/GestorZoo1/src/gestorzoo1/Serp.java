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
public class Serp extends Animal{

    
    public Serp(int id, Data dataCura, int anys) {
        super(id,dataCura,anys);
    }
    
    @Override
    public void realitzarCura() {
        System.out.println("La cua de la serp "+ super.id+" s'ha de realitzar la data "+super.dataCura);
    }
    
    @Override
    public String toString() {
        String s = "";
        
        s = s +" Serp amb identificador " + super.id;
        return s;
    }
    
    
    
    
    
}
