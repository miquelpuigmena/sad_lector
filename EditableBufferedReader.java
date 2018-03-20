import java.io.*;

public class EditableBufferedReader extends BufferedReader {

    private Line line;
    private Console cons;
    
    
    public EditableBufferedReader(InputStreamReader in) {
        super(in);
        line = new Line();
        cons = new Console();
        line.addObserver(cons);
    }

    
    /**
     * ESC parser:
     * 
     * ESCAPE SEQUENCES BEGIN WITH ^[
     * 
     * RIGHT:   ESC [ C
     * LEFT:    ESC [ D
     * HOME:    ESC O H, ESC [ 1 ~(keypad)
     * END:     ESC O F, ESC [ 4 ~(keypad)
     * INS:     ESC [ 2 ~
     * DEL:     ESC [ 3 ~
     */

    @Override
    public int read() throws IOException{
        int ch, ch1;
        
        if((ch = super.read()) != Constants.ESC)
            return ch;
        
        switch (ch=super.read()){
            case 'O':
                switch(ch=super.read()){
                    case 'H': return Constants.HOME;
                    case 'F': return Constants.FIN;
                    default: return ch;
                }
            case '[':
                switch(ch=super.read()){
                    case 'C': return Constants.RIGHT;
                    case 'D': return Constants.LEFT;
                    // hauriem de tenir definides les constants per ordre
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                        if ((ch1 = super.read()) != '~')
                            return ch1;
                        return Constants.HOME - ch + '1';
                    default: return ch;
                }
            default:
                return ch;
        }
    }

    @Override
    public String readLine() throws IOException{
        int tecla;
	
        while((tecla = this.read())!= '\r'){ //com que la comanda esta en mode raw l'ultim caracter quan s'acaba d'escriure es \r

            switch(tecla){
                case Constants.RIGHT:
                    line.right();
                    break;

                case Constants.LEFT:
                    line.left();
                    break;

                case Constants.HOME:
                    line.home();
                    break;

                case Constants.FIN:
                    line.fin();
                    break;

                case Constants.SUPRIMIR:
                    line.suprimir();
                    break;
                case Constants.BACKSPACE:
                    line.backspace();
                    break;

                case Constants.INSERT:
                    line.sobreescriure();
                    break;
                default:
                    line.write((char)tecla);
                    break;
            }
        }
        return line.toString();
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

}