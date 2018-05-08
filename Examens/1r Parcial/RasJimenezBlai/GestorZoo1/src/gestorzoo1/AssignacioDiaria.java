/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestorzoo1;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author mat.aules
 */
public class AssignacioDiaria{
    
    private Cuidador cuidador;
    
    private Data data;
    
    private ArrayList<Animal> animalsDia = new ArrayList();
    
    public AssignacioDiaria(Cuidador cuidador, Data data) {
        
        this.cuidador = cuidador;
        this.data = data;
        emplenarAssignacioDia();
        
    }
    
    public void emplenarAssignacioDia() {
        
        for (Iterator<Animal> itr = cuidador.getLlista().iterator(); itr.hasNext();) {
            if (itr.next().proximaCura().isSameDate(data)) {
                animalsDia.add(itr.next());
            }
        }
    }
    
    @Override
    public String toString() {
        String s = "";
        
        s = s + "El cuidador "+cuidador.getNom()+" te una assingacio pel dia " +data+ " dels seguents animals " + animalsDia.toString();
        
        return s;
    }
    
    
}
