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
    
    
    public static void main(String[] args) {        
       
        BufferedReader in = new EditableBufferedReader(new InputStreamReader(System.in));
        in.setRaw();
        String str = null;
        int num = 0;
        try {
            //str = in.readLine();
            num = in.read();
        } catch(IOException e) {
            e.printStackTrace();
        }
        System.out.println("\nline is: " + num);
        
        in.unsetRaw();
    }
}
