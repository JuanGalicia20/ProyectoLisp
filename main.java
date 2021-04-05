import java.util.Scanner;


public class main{

    private static Parser parse = new Parser();
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
                        System.out.println( "Resultado Final: " + String.valueOf(parse.funcion(text).get(0)) );
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
