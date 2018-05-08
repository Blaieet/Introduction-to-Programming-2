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
public abstract class Animal {
    
    protected int id;
    protected Data dataCura;
    protected int anys;
    
    public Animal(int id, Data dataCura, int anys) {
        this.id = id;
        this.dataCura = dataCura;
        this.anys = anys;
        
    }
    
    public Data proximaCura() {
      return dataCura;  
    } 
    
    public abstract void realitzarCura();
    
    
    
    
    
}
