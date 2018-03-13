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
    private static String ESCSEQ = "\033";

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
            String esc_str = "";
            switch(tecla){

                case Constants.RIGHT:
                    noError = line.right();
                    //System.out.print(ESCSEQ + "[C");
                    esc_str=ESCSEQ + "[C";
                    break;

                case Constants.LEFT:
                    noError = line.left();
                    //System.out.print(ESCSEQ + "[D");
                    esc_str=ESCSEQ + "[D";
                    break;

                case Constants.HOME:
                    noError = line.home();
                    //System.out.print('\r');
                    esc_str="\r";
                    break;

                case Constants.FIN:
                    noError = line.fin();
                    //System.out.print(ESCSEQ + "[F");
                    esc_str=ESCSEQ + "[F";
                    break;

                case Constants.SUPRIMIR:
                    noError = line.suprimir();
                    //System.out.print(ESCSEQ + "[3~");
                    esc_str=ESCSEQ + "[3~";
                    break;
                case Constants.BACKSPACE:
                    noError = line.backspace();
                    //System.out.print("\b \b");
                    break;

                case Constants.INSERT:
                    line.sobreescriure();
                    esc_str=ESCSEQ + "[2~";
                    break;
                default:
                    noError = line.write((char)tecla);
                    break;
            }
            renovarLinia();
            setCursor();
            //System.out.print(esc_str);
        }
        if(noError) return line.toString();
        else return "Tractar error";
    }

    public void setRaw() {
        String[] command = {"/bin/bash", "-c", "stty -echo raw < /dev/tty"};
        try{
            Process proc = Runtime.getRuntime().exec(command);
        }catch(IOException e){}
    }

    public void unsetRaw() {
        String[] command1 = {"/bin/bash", "-c", "stty echo < /dev/tty"};
        String[] command2 = {"/bin/bash", "-c", "stty cooked < /dev/tty"};

        try{
            Process proc = Runtime.getRuntime().exec(command1);
            Process proc2 = Runtime.getRuntime().exec(command2);

        }catch(IOException e){}
    }
    private void renovarLinia(){
        System.out.print('\r');
        System.out.print(new String(new char[80]).replace("\0", " "));//no es 80, es fins a final del terminal line.colsterminal()
        System.out.print('\r');
        System.out.print(line.toString());
    }
    private void setCursor(){
        System.out.print('\r');
        if(line.getCursor()>0)
            System.out.print(ESCSEQ+"["+line.getCursor()+"C");
    }

}
