import java.util.ArrayList;
/**
 * @author Juan Andres Galicia 20298
 * @author Elisa Samayoa 20710
 * @author Jonathan Espinoza 20022
 * 
 * @version 5-4-2021 
 * 
 * 
 */
public class Logic {
    
    
    /** 
     * @param line
     * @return boolean
     */
    public boolean calculateLogic(ArrayList<String> line) {
        String typeOfOperation = line.get(1);
        LispMemory memoria = new LispMemory();

        switch (typeOfOperation) {

            case ">":
                double nextNumber;
                for(int i = 2; i < line.size() - 1; i ++) {
                    double number = Double.parseDouble(line.get(i));
                    if(i < line.size() - 2) {
                        nextNumber = Double.parseDouble(line.get(i + 1));
                    } else {
                        nextNumber = number - 10;
                    }
                    if(number > nextNumber) {
                        continue;
                    } else {
                        return false;
                    }
                }
                return true;

            case "<":
                for(int i = 2; i < line.size() - 1; i ++) {
                    double number = Double.parseDouble(line.get(i));
                    if(i < line.size() - 2) {
                        nextNumber = Double.parseDouble(line.get(i + 1));
                    } else {
                        nextNumber = number + 10;
                    }
                    if(number < nextNumber) {
                        continue;
                    } else {
                        return false;
                    }
                }
                return true;

            case "equal":

                if(memoria.getVariables().containsKey(line.get(2)) &&
                     memoria.getVariables().containsKey(line.get(3))) {
                    if(memoria.getVariables().get(line.get(2)).equals(memoria.getVariables().get(line.get(3)))) {
                        return true;
                    } else {
                        return false;
                    }
                } else if (memoria.getVariables().containsKey((line.get(2)))) {
                    String comparison = "\"" + memoria.getVariables().get(line.get(2)).get(1) +"\""; // Extend this idea to the others
                    if(memoria.getVariables().get(line.get(2)).get(1).equals(line.get(3)) || comparison.equals(line.get(3))) {
                        return true;
                    } else {
                        return false;
                    }
                } else if  (memoria.getVariables().containsKey((line.get(3)))) {
                    if(memoria.getVariables().get(line.get(3)).equals(line.get(2))) {
                        return true;
                    } else {
                        return false;
                    }
                }
                if(line.get(2).equals(line.get(3))) {
                    return true;
                } else {
                    return false;
                }
        }
        return true;
    }
}