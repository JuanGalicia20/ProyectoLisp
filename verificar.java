import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;
import java.util.ArrayList;
//import com.sun.org.apache.xalan.internal.xsltc.compiler.Pattern;



public class verificar {

    private String[] codigo2;
    private String codigo;
    public verificar(String codigo)
    {
        this.codigo = codigo;
        codigo2 = codigo.replace("("," ( ").replace(")"," ) ").split(" ");
    }
    

    //Verifica que sea par el numero de parentesis
    public boolean verif1()
    {
        String cod = "";
        for(int i = 0; i < codigo2.length; i++)
        {
            if(!codigo2[i].equals("(") && !codigo2[i].equals(")"))
            {
                codigo2[i]="";
            }
            else
            {
                cod+=codigo2[i];
            }
        }

        Stack<Character> pila = new Stack<>();
        for (int i = 0; i < cod.length(); i++)
        {
            if (cod.charAt(i) == '(') 
            {
                pila.push(cod.charAt(i));
            }

            if (cod.charAt(i) == ')')
            {
                if (pila.empty()) 
                {
                    return false;
                }

                Character top = pila.pop();

                if (top == '(' && cod.charAt(i) != ')')
                {
                    return false;
                }
            }
        }
        return pila.empty();
    }

        

    public void verifOperacion()
    {
        char c1 = codigo.charAt(1);
        char op1 = '+';     
        char op2 = '-';  
        char op3 = '/';  
        char op4 = '*';  
        if(c1 == op1 || c1 == op2 || c1 == op3 || c1 == op4)
        {
            System.out.println("OPERACION VALIDA");
            verifFuncion();
        }
        else
        {
            System.out.println("OPERACION MALA");
            verifFuncion();
        }
    }

    public String verifFuncion(){
        String[] letras = codigo.split("");
        String ver = (letras[1] + letras[2] + letras[3] + letras [4] + letras[5]);
        if(ver.equals("DEFUN") || ver.equals("defun")){
            System.out.println("Declaracion de funcion valida");
            return codigo;
        }
        else{
            System.out.println("Funcion no valida");
            return codigo;
        }
        
    }

    public String verifQuote(){
        String[] letras = codigo.split("");
        String ver = (letras[0] + letras[1] + letras[2] + letras[3] + letras [4] + letras[5]); //verificación por si el usuario lo ingresa con paréntesis
        if(ver.equals("(QUOTE") || ver.equals("(quote")){ 
            System.out.println("Declaracion de quote valida");
            return codigo;
        }
        else if(letras[1].equals("('")){
            System.out.println("Declaracion de quote valida");
            return codigo;
        }

        String ver2 = (letras[0] + letras[1] + letras[2] + letras[3] + letras [4] ); //verficiación si no se escribe con paréntesis
           
        else if(ver.equals("QUOTE") || ver.equals("quote")){
            System.out.println("Declaracion de quote valida");
            return codigo;
        }
        else if(letras[0].equals("'")){
            System.out.println("Declaracion de quote valida");
            return codigo;

        else{
            System.out.println("quote no valida");
            return codigo;
        }
        
    }

    public void verifList (){
        String[] letras = codigo.split("");
        String ver = (letras[0] + letras[1] + letras[2] + letras[3] + letras [4] ); //verificación por si el usuario lo ingresa con paréntesis
        int size= letras.length;
        if(ver.equalsIgnoreCase("(list")){ 
            System.out.println("Declaracion de list valida");
            return codigo;           
        }
        else{
            System.out.println("list no valida");
            return codigo;
    }

    
    public String verifSetQ(){
        String[] letras = codigo.split("");
        String ver = (letras[1] + letras[2] + letras[3] + letras [4]);
        if(ver.equals("SETQ") || ver.equals("SETQ")){
            System.out.println("Declaracion de SETQ valida");
            return codigo;
        }
        else{
            System.out.println("SETQ no valida");
            return codigo;
        }
        
    }

    public String verfAtom(){
        String[] letras = codigo.split("");
        String ver = (letras[1] + letras[2] + letras[3] + letras [4]);
        if(ver.equals("ATOM") || ver.equals("atom")){
            System.out.println("Declaracion de ATOM valida");
            return codigo;


            for(int i = 0; i < codigo.length(); i++){
                codigo[i] + codigo{i+1}.....
            }
        }
        else{
            System.out.println("ATOM no valida");
            return codigo;
        }
        
    }


}

