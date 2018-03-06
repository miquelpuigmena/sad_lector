
import java.io.IOException;
import static java.lang.String.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Berta
 */
public class Line {
    
    public static String ESCSEQ = "\27";
    String linia;
    
    public Line(){
        linia = "";
    }
    
    public void moureDreta(){ 
    }
    
    public void moureEsquerra(){
        // moure cap a l'esquerra 1 caracters ESQSEQ[1D
        //cal moure el cursor tant a la pantalla com al que apunta a string
        System.out.print(ESCSEQ + "[D");
        
        //PROBLEMA: EN COMPTES DE MOURE EL CURSOR CAP A L'ESQUERRA ESBORRA ELS CARACTERS
    }
    
    public void suprimir(){
        
    }
    
    public void backspace(){
        
    }
    
    public void sobreescriure(boolean b){
        // veure metode replace de string
    }
    
    public void fiLinia(){
        
    }
    
    public void escriure(int i){
        linia += valueOf(i);
    }
    
    public void iniciLinia(){
        
    }
    
  
   
    
}
