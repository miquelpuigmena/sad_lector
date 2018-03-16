/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.*;
import java.util.*;
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
	List<String> list_commandes = new ArrayList<String>();
        while((tecla = this.read())!= '\r'){ //com que la comanda esta en mode raw l'ultim caracter quan s'acaba d'escriure es \r
            list_commandes.clear();
            switch(tecla){
                case Constants.RIGHT:
                    if(line.right())
                    	list_commandes.add(ESCSEQ + "[C");
                    break;

                case Constants.LEFT:
                    if(line.left())
                    	list_commandes.add(ESCSEQ + "[D");
                    break;

                case Constants.HOME:
                    if(line.home())
                    	list_commandes.add(ESCSEQ + "[G");
                    break;

                case Constants.FIN:
                    int rows_to_move_forward = line.getLengthLinia()-line.getCursor()+1;
                    if(line.fin())
			list_commandes.add(ESCSEQ + "[" + rows_to_move_forward + "G");
                    break;

                case Constants.SUPRIMIR:
                    if(line.suprimir())
                    	list_commandes.add(ESCSEQ +"[P");
                    break;
                case Constants.BACKSPACE:
                    if(line.backspace()){
		    	list_commandes.add(ESCSEQ +"[D");
                    	list_commandes.add(ESCSEQ +"[P");
		    }
                    break;

                case Constants.INSERT:
                    line.sobreescriure();
                    break;
                default:
                    if(line.write((char)tecla)){
			if(line.getInsertMode()){
			    list_commandes.add(Character.toString((char)tecla));
			}else{
			    list_commandes.add(ESCSEQ+"[@");
			    list_commandes.add(Character.toString((char)tecla));
			}			
		    }
                    break;
            }
            renovarLinia(list_commandes);
        }
        return line.toString();
        //else return "Tractar error";
    }

    public void setRaw() {
	String[] set_raw_command = {"/bin/bash", "-c", "stty -echo raw < /dev/tty"};
        run_commana(set_raw_command);
    }

    public void unsetRaw() {
	String[] unset_raw_command = {"/bin/bash", "-c", "stty echo cooked < /dev/tty"};
        run_commana(unset_raw_command);
    }

    private void run_commana(String[] command){
        try{
            Process proc = Runtime.getRuntime().exec(command);
        }catch(IOException e){}
    }
    private void renovarLinia(List<String> llista_commanes){
        for(String commana : llista_commanes){
	    System.out.print(commana);
	}
    }
}
