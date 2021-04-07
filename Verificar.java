import java.util.ArrayList;
import java.util.LinkedHashMap;
/**
 * @author Juan Andres Galicia 20298
 * @author Elisa Samayoa 20710
 * @author Jonathan Espinoza 20022
 * 
 * @version 5-4-2021 
 * 
 * 
 */
public class Verificar {

    //atributos
    private String[] codigo2;
    private String codigo;
    public Verificar()
    {

    }

    //constructor
    public Verificar(String codigo)
    {
        this.codigo = codigo;
        codigo2 = codigo.replace("("," ( ").replace(")"," ) ").split(" ");
    }

    

    /** 
     * Metodo Verif1
     * @param void
     * @return boolean
     */
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



    
    /** 
     * @param codigo
     * @return boolean
     */
    public boolean VerifList(ArrayList<ArrayList<String>> codigo)
    {
        boolean hasWord = false; 
        for(int i = 0; i < codigo.size(); i++) { //ciclo para recorrer el array y buscar palabras existentes para realizar el listValue en Funciones
            for(int j =0; j<codigo.get(i).size();j++)
            {
                String currentWord = codigo.get(i).get(j);
                if(currentWord.equals("first") || currentWord.equals("second")
                || currentWord.equals("third") || currentWord.equals("fourth") || currentWord.equals("fifth")
                || currentWord.equals("sixth") || currentWord.equals("seventh")|| currentWord.equals("eighth")
                || currentWord.equals("ninth") || currentWord.equals("tenth") || currentWord.equals("nth"))
                {
                    hasWord=true;
                    break;
                }
            }
        }
        return hasWord;
    }

    
    /** 
     * @param codigo
     * @param LinkedHashMap<String
     * @param variables
     * @return boolean
     */
    public boolean VerifVariable(ArrayList<ArrayList<String>> codigo, LinkedHashMap<String, ArrayList<String>> variables)
    {
        for(String element : variables.keySet()) //recorre elementos para buscar los guardados con la función setq
        {
            for(int i = 0; i < codigo.size(); i++) {
                for(int j =0; j<codigo.get(i).size();j++)
                {
                    if(element.equals(codigo.get(i).get(j)))
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

}

