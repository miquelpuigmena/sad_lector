import java.io.*;
import java.lang.StringBuilder.*;
import java.util.Observable;

public class Line extends Observable{

    StringBuilder linia;
    private boolean sobreescriure;
    private int cursor;
    private final int colsTerminal;
    private static String ESCSEQ = "\033";

    
    
    public Line(){
        linia = new StringBuilder();
        sobreescriure = false;
        cursor = 0;
        colsTerminal = colsTerminal();
        
    }

    public boolean right(){
        if(cursor<linia.length()){
            cursor++;
            setChanged();
            notifyObservers(Constants.RIGHT);
            return true;
        }else return false;
    }

    public boolean left(){
        if(cursor>0){
	    cursor--;
            setChanged();
            notifyObservers(Constants.LEFT);
            return true;
	}
        else return false;
    }

    public boolean suprimir(){
        if(cursor<linia.length()){
            linia.deleteCharAt(cursor);
            setChanged();
            notifyObservers(Constants.SUPRIMIR);
            return true;
        }else return false;
    }

    public boolean backspace(){
        if(cursor>0){
            linia.deleteCharAt(--cursor);
            setChanged();
            notifyObservers(Constants.BACKSPACE);
            return true;
        }else return false;
    }

    public void sobreescriure(){
            sobreescriure = !sobreescriure;
    }

    public boolean fin(){
        if(cursor<linia.length()){
            int rows_to_move_forward = linia.length()-cursor+1;
            setChanged();
            notifyObservers(ESCSEQ + "[" + rows_to_move_forward + "G");
            cursor = linia.length();
            return true;
        }else return false;
    }

    public boolean home(){
       if(cursor>0){
            cursor = 0;
            setChanged();
            notifyObservers(ESCSEQ + "[G");
            return true;
        }else return false;
    }

    public boolean write(int i){
        if(cursor<colsTerminal){
            if(sobreescriure){
                linia.replace(cursor, cursor + 1, Character.toString((char)i));
                setChanged();
                notifyObservers(Character.toString((char)i));
            }else{
                linia.insert(cursor, (char)i);
                setChanged();
                notifyObservers(ESCSEQ + "[@" + Character.toString((char)i));
            }
            cursor++;
            return true;
        }else return false;
    }

    @Override
    public String toString(){
        return linia.toString();
    }

    private final int colsTerminal(){
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
