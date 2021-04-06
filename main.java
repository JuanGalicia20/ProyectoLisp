import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Juan Andres Galicia 20298
 * @author Elisa Samayoa 20710
 * @author Jonathan Espinoza 20022
 * 
 * @version 5-4-2021 
 * 
 * Programa principal 
 */
public class main{

    private static Parser parse = new Parser();
    
    /** 
     * @param args
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Boolean seguir = true;
        
        
        do{
            System.out.println("Ingrese el codigo lisp: ");
            String text = leerIngreso(scan);
            if (text.equals("")) {
                seguir = false;
            } else {
                Verificar verificar = new Verificar(text);
                if (verificar.verif1()) 
                {
                    System.out.println("Expresion balanceada, continuando...");
                    System.out.println("\n---------------------------------------------------\n");
                    try{
                        ArrayList<String> texto = parse.funcion(text);
                        for(String t : texto)
                        {
                            System.out.println( "Resultado Final: " + String.valueOf(t));
                        }
                        System.out.println("\n---------------------------------------------------");
                    }catch(Exception e){
                        System.out.println(e);
                        System.out.println("Parece que ocurrio un error, intenta de nuevo");
                        System.out.println("\n---------------------------------------------------");
                    }
                } 
                else 
                {
                    System.out.println("La expresion no está balanceada en parentesis");
                }
            }
        }
        while(seguir);
        
        //Funciones fun = new Funciones();
        //fun.quote(codigo);
        
    }

    
    /** 
     * @param scan
     * @return String
     */
    private static String leerIngreso(Scanner scan){
        String text = "";
        while(scan.hasNextLine()){
            String input = scan.nextLine();
            if(input == null || input.isEmpty()){
                break; 
            }
            text += " " +input;
        }
        return text;
    }
}
