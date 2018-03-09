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
    
    private static final int ESC = 27;
    private static final int BACKSPACE = 127;
    private static final int DRETA = 67;
    private static final int ESQUERRA = 68;
    private static final int FI = 70;
    private static final int INICI = 72;
    private static final int INSERT = 50;
    private static final int SUPRIMIR = 51;
    

    Line line;
    public EditableBufferedReader(InputStreamReader in) {
        super(in);
        line = new Line();
    }
    

    @Override
    public int read() throws IOException{
        //llegir char de teclat
        int llegit =  super.read();

        if (llegit == ESC){

            this.read(); //per descartar la segona [
            switch(llegit = this.read()){

                case DRETA:			// ^[[C tecla dreta
                    llegit =  Constants.RIGHT;
                    break;
                case ESQUERRA:			// ^[[D tecla esquerra
                    llegit = Constants.LEFT;
                    break;
                case INICI:			// ^[[H tecla inici
                    llegit = Constants.HOME;
                    break;
                case FI:			// ^[[F tecla final
                    llegit = Constants.FIN;
                    break;
                case INSERT:			// ^[[2~ tecla insertar + this.read per borrar ~
                    this.read();
                    llegit = Constants.INSERT;
                    break;
                case SUPRIMIR:			// ^[[3~ tecla suprimir + this.read per borrar ~
                    this.read();
                    llegit = Constants.SUPRIMIR;
                    break;
            }

        }else if (llegit == BACKSPACE){
            llegit = Constants.BACKSPACE;
        }
        
        return llegit;
    }

    



    @Override
    public String readLine() throws IOException{
        
        int tecla;
        boolean noError = true;
        
        while((tecla = this.read())!= '\r'){ //com que la comanda esta en mode raw l'ultim caracter quan s'acaba d'escriure es \r

            switch(tecla){ 

                case Constants.RIGHT:
                    noError = line.right();
                    break;

                case Constants.LEFT:
                    noError = line.left();
                    break;

                case Constants.HOME:
                    noError = line.home();
                    break;

                case Constants.FIN:
                    noError = line.fin();
                    break;

                case Constants.SUPRIMIR:
                    noError = line.suprimir();
                    break;
                case Constants.BACKSPACE:
                    noError = line.backspace();
                    break;

                case Constants.INSERT:
                    line.sobreescriure(); 
                    break;
                default:
                    noError = line.write((char)tecla);
                    break;
            }
        }
        if(noError) return line.toString();
        else return "Tractar error";
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

}
