
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
        String str  = "";
        switch((Integer)arg){
                case Constants.RIGHT:
                    str = Constants.ESCSEQ + "[C";
                    break;

                case Constants.LEFT:
                    str = Constants.ESCSEQ + "[D";
                    break;

                case Constants.HOME:
                    str = Constants.ESCSEQ + "[G";
                    break;

                case Constants.FIN:
                    str = Constants.ESCSEQ + "[C";
                    break;

                case Constants.SUPRIMIR:
                    str = Constants.ESCSEQ + "[P";
                    break;
                case Constants.BACKSPACE:
                    str = Constants.ESCSEQ + "[D" + Constants.ESCSEQ + "[P";
                    break;
                default:
                    str = Constants.ESCSEQ + "[C";
                    break;
            }
        System.out.print(str);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
