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
public class TestReadLine {

    public TestReadLine() {
    }

    private void setRaw() throws IOException {
        String[] command = {"/bin/sh", "-c", "stty raw </dev/tty"};
        Process proc = Runtime.getRuntime().exec(command);
    }

    private void unsetRaw() throws IOException {
        String[] command = {"/bin/sh", "-c", "stty cooked </dev/tty"};
        Process proc = Runtime.getRuntime().exec(command);
    }   
    
    
    public static void main(String[] args) {
        TestReadLine trl = new TestReadLine();
        
        try{
            trl.setRaw();
        }catch(IOException e){}
        
        
        BufferedReader in = new EditableBufferedReader(new InputStreamReader(System.in));
        String str = null;
        int num = 0;
        try {
            //str = in.readLine();
            num = in.read();
        } catch(IOException e) {
            e.printStackTrace();
        }
        System.out.println("\nline is: " + num);
        
        try{
            trl.unsetRaw();
        }catch(IOException e){}
    }
}
