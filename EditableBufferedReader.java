/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.*;
/**
 *
 * @author lsadusr10
 */

/*
ESCAPE SEQUENCES BEGIN WITH ^[
*/

public class EditableBufferedReader extends BufferedReader {

    private char[] caracters; 
    
    public EditableBufferedReader(InputStreamReader in) {
        super(in);
        caracters = new char[10];
    }
    

    @Override
    public int read() throws IOException{
        //llegir char de teclat
        int chars_llegits = 0;
        chars_llegits =  super.read(caracters, 0, 10);
        //separar char de seq escape
        
        int suma = 0;
        for(int c: caracters){
            suma += c;
        }
        
        //retornar int en funcio del que sha llegit
        return suma;
    }
    
    public void setRaw() {
        String[] command = {"/bin/sh", "-c", "stty raw </dev/tty"};
        try{
            Process proc = Runtime.getRuntime().exec(command);
        }catch(IOException e){}
    }

    public void unsetRaw() {
        String[] command = {"/bin/sh", "-c", "stty cooked </dev/tty"};
        try{
            Process proc = Runtime.getRuntime().exec(command);
        }catch(IOException e){}    
    } 
    /**
     *
     * @return
     */
    @Override
    public String readLine() throws IOException{
         int tecla = this.read();
        
        switch(tecla){ //COMPLETAR CRIDANT ELS METODES DE LINE
                
                case Constants.DRETA:
                    
                case Constants.ESQUERRA:
                    
                case Constants.INICI:
                    
                case Constants.FI:
                
                case Constants.SUPRIMIR:
                    
                case Constants.INSERT:
                    
                //altres casos 
                            line.escriure(tecla);
                    
                    break;
            }
        return null;
    }
    }
}
