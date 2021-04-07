import java.util.ArrayList;
/**
 * @author Juan Andres Galicia 20298
 * @author Elisa Samayoa 20710
 * @author Jonathan Espinoza 20022
 * 
 * @version 5-4-2021 
 * 
 * Realiza todas las operaciones aritmeticas que ingrese el usuario
 * ademas de aquellas necesarias para otros metodos. 
 */
public class Arithmetic {

    
    /** 
     * @param line
     * @return double
     * 
     * Calcula la cantidad de operaciones aritmeticas dentro del ingreso
     */
    public double calculateArithmetic(ArrayList<String> line) {
        LispMemory variables = new LispMemory();
        double result = 1;
        if(line.size() == 1) {
            try {
                return Double.parseDouble(line.get(0));
            } catch (Exception E) {
                return variables.getVariableValue(line.get(0));
            }
        }

        int index = 0;
        while(line.size() > 1) {
            int openParentheses = 0;
            int closedParentheses = 0;

            //se ordenan las operaciones por parentesis para poder operar
            //por ejemplo (+ 1 (* 3 (/ 8 4)))
            for(int i = 0; i < line.size(); i ++) {
                String currentWord = line.get(i);
                if(currentWord.equals("(")) {
                    openParentheses = i;
                } else if (currentWord.equals(")")) {
                    closedParentheses = i + 1;
                    break;
                }
            }
            calculateParenthesis(openParentheses,closedParentheses,line);
            index++;
        }

        
        result = Double.parseDouble(line.get(0));
        //System.out.println("::::"+result);
        return result;
    }

    
    /** 
     * @param open
     * @param closed
     * @param line
     * 
     * Realiza la operacion aritmetica
     *
     */
    public void calculateParenthesis(int open, int closed, ArrayList<String> line) {
        double result = 0;
        String operator = line.get(open + 1);
        LispMemory memoria = new LispMemory();
        switch (operator) {
            case "+":

                for(int i = open + 2; i < closed - 1; i ++) {
                    double number;
                    try {
                        number = Double.parseDouble(line.get(i));
                    } catch (Exception E) {
                        number = Double.parseDouble(memoria.getVariables().get(line.get(i)).get(1));
                    }
                    result += number;
                }
                break;

            case "-":

                result = Double.parseDouble(line.get(open + 2));
                for(int i = open + 3; i < closed - 1; i ++) {
                    double number;
                    try {
                        number = Double.parseDouble(line.get(i));
                    } catch (Exception E) {
                        number = Double.parseDouble(memoria.getVariables().get(line.get(i)).get(1));
                    }
                    result -= number;
                }
                break;

            case "*":
                result = 1;
                for(int i = open + 2; i < closed - 1; i ++) {
                    double number;
                    try {
                        number = Double.parseDouble(line.get(i));
                    } catch (Exception E) {
                        number = Double.parseDouble(memoria.getVariables().get(line.get(i)).get(1));
                    }
                    result *= number;
                }
                break;

            case "/":
                result = Double.parseDouble(line.get(open + 2));
                for(int i = open + 3; i < closed - 1; i ++) {
                    double number;
                    try {
                        number = Double.parseDouble(line.get(i));
                    } catch (Exception E) {
                        number = Double.parseDouble(memoria.getVariables().get(line.get(i)).get(1));
                    }
                    result /= number;
                }
                break;
        }

        for(int i = closed - 1; i > open; i --) {
            try {
                line.remove(i);
            } catch (Exception E) {
                System.err.println("Ocurrio un error en la operacion");
            }
        }
        line.set(open, String.valueOf(result));
        
    }
}
