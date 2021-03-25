import java.util.Scanner;
import javax.swing.JFileChooser;

import org.w3c.dom.UserDataHandler;

import java.io.File;

public class main{
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        System.out.println("Ingrese el codigo lisp");
        String codigo = leer.nextLine();
        verificar verificar = new verificar(codigo);
        //Funciones fun = new Funciones();
        //fun.quote(codigo);
        if (verificar.verif1()) 
            {
                System.out.println("The expression is balanced");
            } 
                
            else 
            {
                System.out.println("The expression is not balanced");
            }
    }
} 