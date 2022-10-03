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
       if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            Runtime.getRuntime().exec("clear");
    }

    public static void confirmar() throws InterruptedException, IOException {
            System.out.print("Certeza que desseja sair? [y/n] ");
            certeza = sc.next().toLowerCase().substring(0, 1);
        if (certeza.equals("y")) {
            Thread.sleep(1000);
            clear();
            System.exit(0); 
        }else if(certeza.toLowerCase().equals("n")){
            opcoes();
        }else{
            confirmar();
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
        
        System.out.println(ANSI_GREEN+"Copiado"+ANSI_RESET);
        Thread.sleep(1000);
        clear();
        System.exit(0);
    }

    public static void opcoes() throws InterruptedException, IOException
    {
        
            System.out.println("\n[1] - Copiar senha\n[2] - Repetir senha\n[3] - Gerar outro tamanho\n[Outro] - Sair ");
            option = sc.nextInt();
            try{
                switch (option) {
                case 1: copy();
                case 2: {
                    clear();
                    System.out.println("Password: "+ANSI_GREEN+generatePassword()+ANSI_RESET); 
                    opcoes();
                };
                case 3: {
                    clear();
                    _init_();
                };
                default: confirmar();
                }
            }catch(Exception e){}
        
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
