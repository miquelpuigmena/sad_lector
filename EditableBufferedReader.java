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
        int llegit = 0;
        
        llegit = (char) super.read();
        //separar char de seq escape
        if(llegit == 27){ //23 is ASCII for ^[ (ESC)
            int to_dispose = super.read();
            int declare_input = super.read();   
        }
        

        //retornar int en funcio del que sha llegit
        return llegit;
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
        return super.readLine();
    }
}
