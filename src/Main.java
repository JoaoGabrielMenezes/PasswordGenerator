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
    private static int lim,randomIndex,pass;
    final private static String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789\\/+=-_()[]*&%$#@!";
    private static Scanner sc;
    private static SecureRandom random;
    private static StringBuilder builder;
    private static StringSelection stringSelection;
    private static Clipboard clipboard;
    
    public static void clear() throws InterruptedException, IOException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
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
        //seleciona a String
        stringSelection = new StringSelection(builder.toString());
        //adiciona a var clipboard um sistema para copiar
        clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        //atribui a essa var a string desejada a ser copiada
        clipboard.setContents(stringSelection, null);
        /***/
        System.out.println("Copiado para area de transferencia");
        Thread.sleep(1000);
        clear();
        lim = 0;
        _init_();
    }

    public static void opcoes() throws InterruptedException, IOException
    {
         try {
            System.out.println("\n[1] - Copiar para area de transferencia?\n[2] - Repetir senha\n[3] - Repetir gerador\n[4] - Sair ");
            pass = sc.nextInt();
            if (pass == 1) {
                copy();
            }else if(pass == 2){
                clear();
                System.out.println("Password: "+generatePassword()); 
            }else if(pass == 3){
                Thread.sleep(500);
                clear();
                lim = 0;
                _init_();
            }else if(pass == 4){
                Thread.sleep(1000);
                clear();
                System.exit(0);
            }

            while (pass != 1 || pass != 2 || pass != 3) {
                clear();
                if (pass == 2) {
                    System.out.println("Password: "+generatePassword()); 
                }
                opcoes();
            }
        } catch (Exception e) {}
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
           System.err.println("Digite apenas numeros inteiros");
           Thread.sleep(1000);
           _init_();
        }
    }
    public static void main(String[] args) throws InterruptedException, IOException
    {
         _init_();
    }
}
