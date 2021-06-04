import java.security.SecureRandom;
import java.util.Scanner;

import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
/**
 * @author Joao Menezes
 */
public class Main {   
    private static int lim,randomIndex;
    private static String pass;
    private static Scanner sc;
    private static SecureRandom random;
    private static StringBuilder builder;
    private static StringSelection stringSelection;
    private static Clipboard clipboard;
    
    public static String generatePassword(){

        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789\\/+=-_()[]*&%$#@!";
 
        random = new SecureRandom();
        builder = new StringBuilder();

        for (int i = 0; i < lim; i++)
        {
            randomIndex = random.nextInt(chars.length());
            builder.append(chars.charAt(randomIndex));
        }
 
        return builder.toString();
    }
 
    public static void copy() {
        //Copia para area de transferencia
        stringSelection = new StringSelection(builder.toString());
        clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    public static void opcoes() throws InterruptedException, IOException
    {
       
         try {
            System.out.println("\n[1] - Copiar para area de transferencia?\n[2] - Repetir\n[3] - Sair ");
            pass = sc.next();
            if (pass.equals("1")) {
                copy();
                System.out.println("Copiado para area de transferencia");
                Thread.sleep(1000);
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                System.exit(0);
            }else if(pass.equals("2")){
                 Thread.sleep(100);
                 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                 lim = 0;
                _init_();
            }else if(pass.equals("3") || !pass.equals("3")){
                Thread.sleep(100);
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                System.exit(0);
            }
        } catch (Exception e) {
            System.err.println("Digite apenas 1,2 ou 3");
            opcoes();
        }
    }

    public static void _init_() throws InterruptedException, IOException {
        sc = new Scanner(System.in);
        try {
            while(lim <= 0){
                System.out.println("Quantos caracteres serao inseridos? ");
                lim = sc.nextInt();
            }
            System.out.println("Password: "+generatePassword());  
            opcoes(); 
        } catch (Exception e) {
           System.err.println("Digite apenas numeros ");
           Thread.sleep(1000);
           _init_();
        }
    }
    public static void main(String[] args) throws InterruptedException, IOException
    {
         _init_();
    }
}