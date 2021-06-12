import java.security.SecureRandom;
import java.util.Scanner;
import java.io.IOException;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
/**
 * @author Joao Menezes
 */
public class Main {   
    private static int lim,randomIndex,option;
    final private static String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789\\/+=-_()[]*&%$#@!.;,?";
    private static String certeza;
    private static Scanner sc;
    private static SecureRandom random;
    private static StringBuilder builder;
    private static StringSelection stringSelection;
    private static Clipboard clipboard;
    private static final String ANSI_RESET = "\u001B[0m", ANSI_RED = "\u001B[31m", ANSI_GREEN = "\u001B[32m";
    
    public static void clear() throws InterruptedException, IOException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }

    public static void confirmar() throws InterruptedException, IOException {
            System.out.println("Certeza que deseja sair? (y/n)");
            certeza = sc.next();
        if (certeza.toLowerCase().equals("s") || certeza.toLowerCase().equals("y")) {
            Thread.sleep(1000);
            clear();
            System.exit(0); 
        }else{
            opcoes();
        }
    }

    public static String generatePassword(){

        random = new SecureRandom();
        builder = new StringBuilder();

        for (int i = 0; i < lim; i++)
        {
            randomIndex = random.nextInt(chars.length());
            builder.append(chars.charAt(randomIndex));
        }
 
        return builder.toString();
    }
 
    public static void copy() throws InterruptedException, IOException {
        //Copia para area de transferencia
        stringSelection = new StringSelection(builder.toString());
        clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        /***/
        System.out.println(ANSI_GREEN+"Copiado"+ANSI_RESET);
        Thread.sleep(1000);
        clear();
        Thread.sleep(550);
        System.exit(0);
    }

    public static void opcoes() throws InterruptedException, IOException
    {
         try {
            System.out.println("\n[1] - Copiar senha?\n[2] - Repetir senha\n[3] - Repetir gerador\n[4] - Sair ");
            option = sc.nextInt();
            if (option == 1) {
                copy();
            }else if(option == 2){
                clear();
            }else if(option == 3){
                Thread.sleep(500);
                clear();
                _init_();
            }else if(option == 4){
                confirmar();
            }

            while (option != 1 || option != 2 || option != 3 || option != 4) {
                clear();
                if (option == 2) {
                   System.out.println("Password: "+ANSI_GREEN+generatePassword()+ANSI_RESET); 
                }
                opcoes();
            }
        } catch (Exception e) {}
    }

    public static void _init_() throws InterruptedException, IOException {
        lim = 0;
        sc = new Scanner(System.in);
        try {
            while(lim <= 0){
                System.out.println("Quantos caracteres serao inseridos? ");
                lim = sc.nextInt();
            }
            System.out.println("Password: "+ANSI_GREEN+generatePassword()+ANSI_RESET);  
            opcoes(); 
        } catch (Exception e) {
           System.err.println(ANSI_RED+"Digite apenas numeros inteiros"+ANSI_RESET);
           Thread.sleep(1000);
           _init_();
        }
    }
    public static void main(String[] args) throws InterruptedException, IOException
    {
         _init_();
    }
}
