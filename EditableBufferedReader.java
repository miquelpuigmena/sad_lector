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

    public EditableBufferedReader(InputStreamReader in) {
        super(in);
    }
    

    @Override
    public int read() throws IOException{
        //llegir char de teclat

        int llegit =  super.read();
        
	    //separar char de seq escape
        //retornar int en funcio del que sha llegit
        
        if (llegit == 27){
            //MIRAR QUINA SEQ D'ESCAPE SHA APRETAT

            this.read(); //per descartar la segona [
            switch(llegit = this.read()){

                case 67:			// ^[[C tecla dreta
                    return Constants.DRETA;
                    break;
                case 68:			// ^[[D tecla esquerra
                    return Constants.ESQUERRA;
                    break;
                case 72:			// ^[[H tecla inici
                    return Constants.INICI;
                    break;
                case 70:			// ^[[F tecla final
                    return Constants.FI;
                    break;
                case 50:			// ^[[2~ tecla insertar + this.read per borrar ~
                    this.read();
                    return Constants.INSERT;
                    break;
                case 51:			// ^[[3~ tecla suprimir + this.read per borrar ~
                    this.read();
                    return Constants.SUPRIMIR;
                    break;
            }

        }else if (llegit == 127){
            return Constants.BACKSPACE();
        }else
            return llegit;
        }

    }



    @Override
    public String readLine() throws IOException{
        int tecla;
        while((tecla = this.read())!= '\r'){ //com que la comanda esta en mode raw l'ultim caracter quan s'acaba d'escriure es \r

            switch(tecla){ //COMPLETAR CRIDANT ELS METODES DE LINE

                case Constants.DRETA:
                    line.moureDreta();
                    break;

                case Constants.ESQUERRA:
                    line.moureEsquerra();
                    break;

                case Constants.INICI:
                    line.iniciLinia();
                    break;

                case Constants.FI:
                    line.fiLinia();
                    break;

                case Constants.SUPRIMIR:
                    line.suprimir();
                    break;
                case Constants.BACKSPACE():
                    line.backspace();
                    break;

                case Constants.INSERT:
                    line.sobreescriure(); //canviar el metode pq el boolean sigui un atribut de line i no faci falta aqui
                    break;
                default:
                    line.escriure((char)tecla);
                    break;
            }
        }
       	return line;
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
