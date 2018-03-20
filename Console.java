
import java.util.Observable;
import java.util.Observer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Berta
 */
public class Console implements Observer{

    @Override
    public void update(Observable o, Object arg) {
        o.
        System.out.print((String)arg);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
