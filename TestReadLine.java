import java.io.*;

/**
 *
 * @author lsadusr10
 */
public class TestReadLine {

    public TestReadLine() {
    }


    public static void main(String[] args) {

        EditableBufferedReader in = new EditableBufferedReader(new InputStreamReader(System.in));
        in.setRaw();
        String str = null;
        int num = 0;
        try {
            str = in.readLine();
            //num = in.read();
        } catch(IOException e) {
            e.printStackTrace();
        }
        System.out.println("\nline is: " + str);

        in.unsetRaw();
    }
}
