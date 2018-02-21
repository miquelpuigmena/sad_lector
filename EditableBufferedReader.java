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
    public char read(){
        //llegir char de teclat
        char llegit = null;
        try{
            llegit = (char) super.read();
            //separar char de seq escape
            if(llegit == 27){ //23 is ASCII for ^[ (ESC)
                char to_dispose = super.read();
                char declare_input = super.read();   
            }
        }catch(IOException e){
        }
        

        //retornar int en funcio del que sha llegit
        return llegit;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String readLine() throws IOException{
        return super.readLine();
    }
}
