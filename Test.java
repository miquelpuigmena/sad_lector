import java.io.*;
import java.util.Scanner;
/**
 *
 * @author lsadusr10
 */
public class Test {

    public Test() {
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = "rrracb";
	System.out.print(str);
        System.out.print("\033["+5+"D");
        System.out.print("\033["+2+"P");
        scanner.next();

    }
}
