
import java.io.IOException;
import java.lang.StringBuilder.*;


public class Line {

    StringBuilder linia;
    private boolean sobreescriure;
    private int cursor;
    private final int colsTerminal;

    public Line(){
        linia = new StringBuilder();
        sobreescriure = false;
        cursor = 0;
        colsTerminal = colsTerminal();//80;
    }

    public boolean right(){
        if(cursor<linia.length()){
            cursor++;
            return true;
        }else return false;
    }

    public boolean left(){
        if(cursor>0){
            cursor--;
            return true;
        }else return false;
    }

    public boolean suprimir(){
        if(cursor<linia.length()){
            linia.deleteCharAt(cursor);
            return true;
        }else return false;
    }

    public boolean backspace(){
        if(cursor>0){
            linia.deleteCharAt(cursor--);
            return true;
        }else return false;
    }

    public void sobreescriure(){
            sobreescriure = !sobreescriure;
    }

    public boolean fin(){
        if(cursor<linia.length()){
            cursor = linia.length();
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
        if(cursor<colsTerminal){
            if(sobreescriure){
                linia.replace(cursor, cursor + 1, Character.toString((char)i));
            }else{
                linia.insert(cursor, (char)i);
            }
            cursor++;
            return true;
        }else return false;
    }

    @Override
    public String toString(){
        return linia.toString();
    }
    
    public int getLengthLinia(){ // crec que no el fem servir
        return linia.length();
    }
    
    public int getCursor(){
        return cursor;
    }
    
    public final int colsTerminal(){
        String[] cmd = {"/bin/sh", "-c", "tput cols 2>/dev/tty"};
        try{
            Process p = Runtime.getRuntime().exec(cmd);
            BufferedReader b = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String cols = b.readLine();
            return Integer.parseInt(cols);  
        }catch(IOException e){}
        return -1;
    }   
}
