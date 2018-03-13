
import java.io.IOException;
import java.lang.StringBuilder.*;


public class Line {

    public static char ESCSEQ = '\033';
    StringBuilder linia;
    private boolean sobreescriure;
    private int cursor;
    private final int colsTerminal;

    public Line(){
        linia = new StringBuilder();
        sobreescriure = false;
        cursor = 0;
        colsTerminal = 80;
    }

    public boolean right(){
        if(cursor<getLengthLinia()){
            cursor++;
            return true;
        }else return false;
    }

    public boolean left(){
        // moure cap a l'esquerra 1 caracters ESQSEQ[1D
        //cal moure el cursor tant a la pantalla com al que apunta a string
        if(cursor>0){
            cursor--;
            return true;
        }else return false;
        //PROBLEMA: EN COMPTES DE MOURE EL CURSOR CAP A L'ESQUERRA ESBORRA ELS CARACTERS
    }

    public boolean suprimir(){
        if(cursor<=getLengthLinia()-1){
            linia.deleteCharAt(cursor);
            return true;
        }else return false;
    }

    public boolean backspace(){
        if(cursor>0 && cursor<=getLengthLinia()){
            linia.deleteCharAt(--cursor);
            return true;
        }else return false;
    }

    public void sobreescriure(){
            sobreescriure = sobreescriure;
    }

    public boolean fin(){
        if(cursor<getLengthLinia()){
            cursor = getLengthLinia();
            return true;
        }else return false;
    }

    public boolean home(){
        if(cursor>0){
            cursor = 0;
            return true;
        }else return false;
    }

    public boolean write(int i){
        //CAL TRACTAR EL SOBREESCRIURE AQUI, I L'ESCRIURE AL MIG DE STRING!!!!
        if(cursor<1900000){
            if(sobreescriure){
                linia.replace(cursor,cursor,Character.toString((char) i));//borrar caracter anterior i insertar el nou a la seva posicio
                if(cursor==getLengthLinia()){
                  cursor--;
                }
            }else{
                linia.insert(cursor, Character.toString((char) i));
            }
            cursor++;
            return true;
        }else return false;
    }

    @Override
    public String toString(){
        return linia.toString();
    }
    public int getLengthLinia(){
        return linia.length();
    }
    public int getCursor(){
        return cursor;
    }
    public final int colsTerminal(){

        return -1;
    }
}
