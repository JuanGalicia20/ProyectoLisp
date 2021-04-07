import java.util.*;

/**
 * @author Juan Andres Galicia 20298
 * @author Elisa Samayoa 20710
 * @author Jonathan Espinoza 20022
 * 
 * @version 5-4-2021 
 * 
 * Guarda y corre las funciones definidas por el usuario
 * ademas de verificar que las funciones existan. 
 * 
 */

public class Declaracion {
	private ArrayList<ArrayList<String>> nuevaFuncion = new  ArrayList<ArrayList<String>>();
	private Map<String, ArrayList<String>> functions = new HashMap<>();
	private Map<String, ArrayList<String>> variables = new HashMap<>();
	private Map<String, ArrayList<ArrayList<String>>> oldFunction = new HashMap<>();
	private ArrayDeque<String> container = new ArrayDeque<>();
	private int open = 0;
	private int closed = 0;
	
    
	/** 
	 * @param nuevaFuncion
	 * @return String
	 * 
	 * Separa el nombre de la funcion de la funcionalidad y la guarda
	 * en un hashmap
	 */
	public String setFuncion(ArrayList<ArrayList<String>> nuevaFuncion){
		ArrayList<String> variablesF = new ArrayList<>();
		ArrayList<String> funtion = new ArrayList<>();
		String functionName;

		variablesF = setNombreFuncion(nuevaFuncion);
		functionName = variablesF.get(0);
		
		variablesF.remove(0);
		functionName = functionName.toUpperCase();

		funtion = setFuncionalidadFuncion(nuevaFuncion); 
		 
		this.functions.put(functionName, funtion);
		this.variables.put(functionName, variablesF);
		this.oldFunction.put(functionName, nuevaFuncion);
		
		return functionName;
	}
    
    
	/** 
	 * @param nombre
	 * @return ArrayList<String>
	 * 
	 * Guarda el nombre de la funcion definida
	 */
	private ArrayList<String> setNombreFuncion(ArrayList<ArrayList<String>> nombre) {
		this.nuevaFuncion = nombre;
		ArrayList<String> nombre2 = nombre.get(nombre.size() - 1);
		ArrayList<String> temporal = new ArrayList<String>();
		Integer index = 0;
		String nombreAux = "", nombreAux2 = "";
		String[] aux = new String[2];

		for(int i = 0; i < nombre2.size(); i++){
			nombreAux = nombre2.get(i).replace(" ", "");
			if(nombreAux.equalsIgnoreCase("defun")){
				index = i;
			}
		}

		nombreAux2 = nombre2.get(index + 1);

		nombreAux = "";	
		open = 0;
		closed = 0;
		if (nombreAux.equals("")) {
			for(int i = 0; i < nombre2.size() - 1; i++) {
				if(closed == 0) {
					if(nombre2.get(i).equals("(")) {
						open = i;
					}
					else if(nombre2.get(i).equals(")")) {
						closed = i;
					}
				}
				else {
					i = nombre2.size();
				}
			}
			
			for(int j = open; j < closed; j++) {
					nombreAux += nombre2.get(j) +" ";
			}
		}
		
		nombreAux = nombreAux.replaceAll("\\)", "");
		nombreAux = nombreAux.replaceAll("\\(", "");

		aux[0] = nombreAux2;
		aux[1] = nombreAux;
	
		temporal.add(aux[0]);

		aux = aux[1].split(",");
		aux = aux[0].split(" ");
		
		
		for(int i = 0; i < aux.length; i++) {
			if(!aux[i].isEmpty() || !aux[i].equals("")) {
				temporal.add(aux[i]);
			}
		}

		return temporal;
	}
    
    
	/** 
	 * @param funtionality
	 * @return ArrayList<String>
	 * 
	 * Guarda la funcionalidad de la funcion definida
	 */
	private ArrayList<String> setFuncionalidadFuncion(ArrayList<ArrayList<String>> funtionality){
		funtionality = this.nuevaFuncion;
		ArrayList<ArrayList<String>> temp = new ArrayList<>();
		ArrayList<String> aux = new ArrayList<>();
		for(int i = 0; i < funtionality.size(); i++){
			for(int j = closed + 1; j < funtionality.get(i).size() - 1; j++){
				
				aux.add(funtionality.get(i).get(j));
			}
			temp.add(aux);
		}
		while(temp.size() != 1){
			temp.remove(0);
		}
		
		aux = temp.get(0);

		return aux;
	}
    
    
	/** 
	 * @param function
	 * @return String
	 * 
	 * Corre la funcion llamada por el usuario. 
	 */
	public String runFuncion(ArrayList<ArrayList<String>> function){
    	Arithmetic calc = new Arithmetic();
		ArrayList<String> funtionality = new ArrayList<>();
		ArrayList<String> savedVariables = new ArrayList<>(); 
		ArrayList<String> replaceVariables = new ArrayList<>(); 
		ArrayList<String> recursiveContainer = new ArrayList<>(); 
		ArrayList<String> recursiveTemp = new ArrayList<>(); 
		ArrayList<ArrayList<String>> recursiveFinal = new ArrayList<>();
		ArrayList<String> operadoresAritmeticos = new ArrayList<>();
		Boolean logic = false, aritmetico = false; 
		String resultado = "";
		operadoresAritmeticos.add("+");
		operadoresAritmeticos.add("-");
		operadoresAritmeticos.add("*");
		operadoresAritmeticos.add("/");

		
		String functionName = function.get(0).get(1).toUpperCase(); 
		function.get(0).remove(1);
		function.get(0).remove("(");
		function.get(0).remove(")");
		

		
		funtionality = (ArrayList)functions.get(functionName).clone(); 
		savedVariables = (ArrayList)variables.get(functionName).clone();

		for(int i = 0; i < function.get(0).size(); i++){
			replaceVariables.add(function.get(0).get(i).replace(" ", ""));
		}

		
		if(replaceVariables.size() == savedVariables.size()){
			for(int i = 0; i < funtionality.size(); i++){ 
				for(int j = 0; j < replaceVariables.size(); j++){ 
					if(funtionality.get(i).equalsIgnoreCase(savedVariables.get(j))){
						funtionality.set(i, replaceVariables.get(j));
					}

					if(funtionality.get(i).equalsIgnoreCase("cond")){ 
						logic = true;
					}

				}
			}
		}else{
			System.err.print("Fallo en: " + functionName + " \n cantidad de parametros erronea");
			return resultado;
		}
			if(logic){
				funtionality = functionRunning(functionName, replaceVariables);				
				if(funtionality.contains(functionName) || funtionality.contains(functionName.toLowerCase())){
					for(int i = 0; i < funtionality.size(); i++){						
						if(funtionality.get(i).equalsIgnoreCase(functionName)){
							funtionality.remove(i); 
							for(int j = 0; j < 3; j++){
								container.add(funtionality.remove(i));
							}
							container.addFirst("(");
							container.addLast(")");
							recursiveContainer.add(String.valueOf((calc.calculateArithmetic(convertArrayList(5)))));
							i--;
						}

					}

					for(int i = 0; i < recursiveContainer.size(); i++){
						recursiveTemp.add("(");
						recursiveTemp.add(functionName);
						recursiveTemp.add(recursiveContainer.remove(0)); 
						recursiveTemp.add(")");

						recursiveFinal.add(recursiveTemp); 
						recursiveContainer.add(runFuncion(recursiveFinal));
						recursiveTemp.clear();	
						recursiveFinal.clear();
					}

					for(int j = funtionality.size()-1; j > -1; j--) {
						recursiveContainer.add(0, funtionality.get(j));
					}
					recursiveContainer.add(0, "(");
					recursiveContainer.add(")");
					recursiveFinal.add(recursiveContainer);

				
					resultado = String.valueOf(calc.calculateArithmetic(recursiveContainer));
					

				}else if(funtionality.size() == 1){
					return funtionality.get(0);
				}		

				return resultado;

			}else{

				for(int i = 0; i < funtionality.size(); i++){

					container.add(funtionality.get(i));
					
					if(operadoresAritmeticos.contains(funtionality.get(i)) || aritmetico){ 
						aritmetico = true; 
						resultado = String.valueOf(calc.calculateArithmetic(funtionality));
					}
					

				}
				
			}
			container.clear();
			return resultado; 
		
	}
    
	/** 
	 * @param cant
	 * @return ArrayList<String>
	 * 
	 * Convierte de Arraydeque a Arraylist
	 */
	private ArrayList<String> convertArrayList(Integer cant){
		ArrayList<String> aux = new ArrayList<>();

		for(int i = 0; i < cant; i++){
			aux.add(container.removeFirst());			
		}

		return aux;
	} 

    
    
	/** 
	 * @param functionName
	 * @param replaceVariables
	 * @return ArrayList<String>
	 * 
	 * Si la funcion utiliza cond, se envia a funciones
	 */
	private ArrayList<String> functionRunning(String functionName, ArrayList<String> replaceVariables){
		Funciones fun = new Funciones();
		Parser parser = new Parser();
		ArrayList<ArrayList<String>> communicate = new ArrayList<>();
		ArrayList<String> aux = new ArrayList<>();
		ArrayList<Integer> position = new ArrayList<>();
		Integer auxiliar;
		String[] temp;
		String response, temporal;
		String entry = "";

		communicate = (ArrayList)this.oldFunction.get(functionName).clone();
		entry = String.join(" ", communicate.get(0));
		communicate = parser.convertirArray(entry);
		

		
		for(int i = 0; i < communicate.size(); i++){ 
			for(int j = 0; j < communicate.get(i).size(); j++){ 

				for(int k = 0; k < replaceVariables.size(); k++){ 

					if(communicate.get(i).get(j).equalsIgnoreCase(variables.get(functionName).get(k))){
						auxiliar = (int)Float.parseFloat(replaceVariables.get(k));
						temporal =  Integer.toString(auxiliar);
						communicate.get(i).set(j, temporal); 
						position.add(i);
						position.add(j);
						position.add(k);
					}

				}

			}
			
		}
		
		response = fun.cond(communicate);

		temp = response.split(" ");

		for(int i = 0; i < temp.length; i++){
			aux.add(temp[i]);
		}

		while(position.size() > 0){
			communicate.get(position.remove(0)).set(position.remove(0), variables.get(functionName).get(position.remove(0)));
		}
		return aux;
	}
    
    
	/** 
	 * @param function
	 * @return Boolean
	 * 
	 * Verifica si la funcion ingresada por el usuario existe
	 */
	public int hasKey(ArrayList<ArrayList<String>> function){
    	int numFun=0;
    	if(functions.size() > 0) {
			for(int i = 0; i < function.size(); i++) {
                for(int j =0; j<function.get(i).size();j++)
                {
					if(functions.containsKey(function.get(i).get(j).toUpperCase()))
					{
						numFun++;
					}
				}
			}
			return numFun;
    		 
    	}
    	else {
    		return numFun;
    	}
	}


	public Map<String, ArrayList<String>> getFunctions() {
		return functions;
	}

}