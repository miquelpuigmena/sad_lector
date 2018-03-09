
import java.io.IOException;
import java.lang.StringBuilder.*;


public class Line {
    
    public static String ESCSEQ = "\033";
    StringBuilder linia;
    private boolean sobreescriure;
    private int cursor;
    private final int colsTerminal;
    
    public Line(){
        linia = new StringBuilder();
        sobreescriure = false;
        cursor = 0;
        colsTerminal = colsTerminal();
    } 
    
    public boolean right(){ 
        if(cursor<linia.length()){
            System.out.print(ESCSEQ + "[C");
            cursor++;
            return true;
        }else return false;
    }
    
    public boolean left(){
        // moure cap a l'esquerra 1 caracters ESQSEQ[1D
        //cal moure el cursor tant a la pantalla com al que apunta a string
        if(cursor>0){
            System.out.print(ESCSEQ + "[D");
            cursor--;
            return true;
        }else return false;
        //PROBLEMA: EN COMPTES DE MOURE EL CURSOR CAP A L'ESQUERRA ESBORRA ELS CARACTERS
    }
    
    public boolean suprimir(){
        if(cursor<linia.length()){
            System.out.print(ESCSEQ + "[3~");
            linia.deleteCharAt(cursor);
            return true;
        }else return false;
    }
    
    public boolean backspace(){
        if(cursor>0){
            System.out.print("\b");
            linia.deleteCharAt(cursor--);
            return true;
        }else return false;
    }
    
    public void sobreescriure(){
            System.out.print(ESCSEQ + "[2~");
            sobreescriure = !sobreescriure;
    }
    
    public boolean fin(){
        if(cursor<linia.length()){
            System.out.print(ESCSEQ + "[F");
            cursor = linia.length();
            return true;
        }else return false;
    }
    
    public boolean home(){
        if(cursor>0){
            System.out.print(ESCSEQ + "[H");
            cursor = 0;
            return true;
        }else return false;
    }
    
    public boolean write(int i){
        //CAL TRACTAR EL SOBREESCRIURE AQUI, I L'ESCRIURE AL MIG DE STRING!!!!
        if(cursor<colsTerminal){
            if(sobreescriure){
                //borrar caracter anterior i insertar el nou a la seva posicio
            }else{
                linia.append((char)i); //falta afegir a quina posicio es fa l'append
            }
            
            cursor++;
            return true;
        }else return false;
    }

    @Override
    public String toString(){
        return linia.toString();
    }

    public final int colsTerminal(){
        
        return -1;        
    }   
}
