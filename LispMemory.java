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
public class LispMemory {
    private static LinkedHashMap<String, ArrayList<String>> variables = new LinkedHashMap<>();
    private static LinkedHashMap<String, ArrayList<String>> funciones = new LinkedHashMap<>();

    public LispMemory() {
        
    }

    
    /** 
     * @param variable
     * @return double
     */
    public double getVariableValue(String variable) {
        try {
            return Double.parseDouble(variables.get(variable).get(1));
        } catch (Exception E) {
            System.err.println("Variable was not numeric");
            return -1;
        }
    }
    
    
    /** 
     * @param key
     * @return boolean
     */
    public boolean arithmeticOperator(String key) {
        if(funciones.containsKey(key) && funciones.get(key) != null && funciones.get(key).get(0).equals("arithmetic")) {
            return true;
        }
        return false;
    }

    
    /** 
     * @return LinkedHashMap<String, ArrayList<String>>
     */
    public LinkedHashMap<String, ArrayList<String>> getVariables(){
        return variables;
    }
     
}