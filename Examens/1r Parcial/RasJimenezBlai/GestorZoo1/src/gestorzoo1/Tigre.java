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
public class Tigre extends Animal{
    
    private float litres;
    

    public Tigre(int id, Data dataCura, int anys, float litres) {
        super(id, dataCura,anys);
        this.litres = litres;
    }
    
    //Consultor
    
    public void setLitres(float litres) {
        this.litres = litres;
    }
    
    //Getter
    
    public float getLitres() {
        return litres;
    }
    
    @Override
    public void realitzarCura() {
        System.out.println("La cura del tigre "+ super.id + " s'ha de realitzar la data "+super.dataCura+ " i donant "+litres+" litres de llet");
    }
    
    @Override
    public String toString() {
        String s = "";
        s = s + "Tigre amb identificador "+ super.id;
        return s;
    }

    
}
