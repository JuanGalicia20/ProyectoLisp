import java.util.*;

public class Declaracion {
	private ArrayList<ArrayList<String>> nuevaFuncion = new  ArrayList<ArrayList<String>>();
	private Map<String, ArrayList<String>> functions = new HashMap<>();
	private Map<String, ArrayList<String>> variables = new HashMap<>();
	private Map<String, ArrayList<ArrayList<String>>> oldFunction = new HashMap<>();
	private ArrayDeque<String> container = new ArrayDeque<>();
	private int open = 0;
	private int closed = 0;

    public String setFuncion(ArrayList<ArrayList<String>> nuevaFuncion){
		ArrayList<String> variablesF = new ArrayList<>();
		ArrayList<String> funtion = new ArrayList<>();
		String functionName, funtionality;

		// Consiguiendo el nombre y las funciones
		variablesF = setNombreFuncion(nuevaFuncion);
		functionName = variablesF.get(0);
		
		// Eliminando el nombre y cambiandolo a mayuscular
		variablesF.remove(0);
		functionName = functionName.toUpperCase();

		funtion = setFuncionalidadFuncion(nuevaFuncion); // Consiguiendo el funcionamiento de la funciÃƒÂ³n
		 // Eliminando la ultima posicion

		// Agregando las funciones 
		this.functions.put(functionName, funtion);
		this.variables.put(functionName, variablesF);
		this.oldFunction.put(functionName, nuevaFuncion);
		
		return functionName;
	}
    
    private ArrayList<String> setNombreFuncion(ArrayList<ArrayList<String>> nombre) {
		this.nuevaFuncion = nombre;
		ArrayList<String> nombre2 = nombre.get(nombre.size() - 1);
		ArrayList<String> temporal = new ArrayList<String>();
		Integer index = 0;
		String nombreAux = "", nombreAux2 = "";
		String[] aux = new String[2];
		Boolean flag = false;

		// Encontrando el nombre con base al defun
		for(int i = 0; i < nombre2.size(); i++){
			nombreAux = nombre2.get(i).replace(" ", "");
			if(nombreAux.equalsIgnoreCase("defun")){
				index = i;
			}
		}

		// Asignandole el nombre
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

		// Agregando a las listas
		aux[0] = nombreAux2;
		aux[1] = nombreAux;

		// Agregando el nombre
		temporal.add(aux[0]);

		// Separando todas las variables
		aux = aux[1].split(",");
		aux = aux[0].split(" ");
		
		
		for(int i = 0; i < aux.length; i++) {
			if(!aux[i].isEmpty() || !aux[i].equals("")) {
				temporal.add(aux[i]);
			}
		}

		// Agregando todas las variables
		//for(int i = 0; i < temp1.size(); i++){
		//	temporal.add(temp1.get(i));
		//}

		return temporal;
	}
    
    private ArrayList<String> setFuncionalidadFuncion(ArrayList<ArrayList<String>> funtionality){
		funtionality = this.nuevaFuncion;
		ArrayList<ArrayList<String>> temp = new ArrayList<>();
		ArrayList<String> aux = new ArrayList<>();

		// Agregando a una arraylist 
		for(int i = 0; i < funtionality.size(); i++){
			for(int j = closed + 1; j < funtionality.get(i).size() - 1; j++){
				
				aux.add(funtionality.get(i).get(j));
			}
			temp.add(aux);
		}

		// Quitando hasta que solo quede un funcionamiento 
		while(temp.size() != 1){
			temp.remove(0);
		}
		
		aux = temp.get(0);

		return aux;
	}
    
    public String runFuncion(ArrayList<ArrayList<String>> function){

		//Calcular calc = new Calcular();
    	Arithmetic calc = new Arithmetic();
		ArrayList<String> funtionality = new ArrayList<>();
		ArrayList<String> variablesM = new ArrayList<>(); 
		ArrayList<String> replaceVariables = new ArrayList<>(); 

		
		ArrayList<String> recursiveContainer = new ArrayList<>(); // Info recursiva 
		ArrayList<String> recursiveGanas = new ArrayList<>(); // Funcion 
		ArrayList<ArrayList<String>> recursiveMagic = new ArrayList<>();

		// Para realizar las operaciones matematicas
		ArrayList<String> operadoresAritmeticos = new ArrayList<>();

		// Banderas
		Boolean logic = false, aritmetico = false; // Verifica si hay un cond()
		String resultado = "";

		// Agregando los operadores matematicos
		operadoresAritmeticos.add("+");
		operadoresAritmeticos.add("-");
		operadoresAritmeticos.add("*");
		operadoresAritmeticos.add("/");
		//-------------------------------------	

		//---------------------------------------------------------
		String functionName = function.get(0).get(1).toUpperCase(); // Nombre en mayusculas de la funcion
		function.get(0).remove(1);
		function.get(0).remove("(");
		function.get(0).remove(")");
		//---------------------------------------------------------

		// Consiguiendo los elementos necesarios para poder realizar la funcion
		funtionality = (ArrayList)functions.get(functionName).clone(); // Copiando lo que hay en el mapa
		variablesM = (ArrayList)variables.get(functionName).clone();

		// Consiguiendo las variables del arraylist de arraylist
		for(int i = 0; i < function.get(0).size(); i++){
			replaceVariables.add(function.get(0).get(i).replace(" ", ""));
		}

		// Verificando que tenga la misma longitud de parametros que variables en la funcion
		if(replaceVariables.size() == variablesM.size()){
			
			// Cambiando todas las variables por nÃºmeros mandados por el usuario
			for(int i = 0; i < funtionality.size(); i++){ // Recorriendo el arraylist
				
				for(int j = 0; j < replaceVariables.size(); j++){ // Trata de remplazar si es una variable
					if(funtionality.get(i).equalsIgnoreCase(variablesM.get(j))){
						funtionality.set(i, replaceVariables.get(j));
					}

					if(funtionality.get(i).equalsIgnoreCase("cond")){ // Verificando si tiene un cond
						logic = true;
					}

				}
			}
		}

			// Empieza la ejecuciÃ³n del programa verificando en donde empieza
			if(logic){
				funtionality = predicateCommunication(functionName, replaceVariables);				

				// Verificando si posee recursividad el metodo
				if(funtionality.contains(functionName) || funtionality.contains(functionName.toLowerCase())){
					
					// Haciendo la magia de la recursividad 
					for(int i = 0; i < funtionality.size(); i++){
						// Si es igual al nombre de la funcion se vuelve a llamar
						if(funtionality.get(i).equalsIgnoreCase(functionName)){
							funtionality.remove(i); // Eliminando el nombre
							for(int j = 0; j < 3; j++){
								container.add(funtionality.remove(i));
							}
							container.addFirst("(");
							container.addLast(")");
							recursiveContainer.add(String.valueOf((calc.calculateArithmetic(convertArrayList(5)))));
							i--;
						}

					}

					// La magia de la recursividad
					for(int i = 0; i < recursiveContainer.size(); i++){
						recursiveGanas.add("(");
						recursiveGanas.add(functionName);
						recursiveGanas.add(recursiveContainer.remove(0)); 
						recursiveGanas.add(")");

						recursiveMagic.add(recursiveGanas); // Agregando para que pueda operar
						recursiveContainer.add(runFuncion(recursiveMagic));
						recursiveGanas.clear();	
						recursiveMagic.clear();
					}

					// Agregando los ultimos valores
					for(int j = funtionality.size()-1; j > -1; j--) {
						recursiveContainer.add(0, funtionality.get(j));
					}
					recursiveContainer.add(0, "(");
					recursiveContainer.add(")");
					recursiveMagic.add(recursiveContainer);

					// Haciendo la ultima operaciones
				
					resultado = String.valueOf(calc.calculateArithmetic(recursiveContainer));
					

				}else if(funtionality.size() == 1){
					return funtionality.get(0);
				}		

				return resultado;

			}else{

				// Realizando sin recursividad 
				for(int i = 0; i < funtionality.size(); i++){

					// Verificando si ya paso por un operador aritmetico o ya esta pasando
					container.add(funtionality.get(i));
					
					if(operadoresAritmeticos.contains(funtionality.get(i)) || aritmetico){ 
						aritmetico = true; // Ya hubo un operador aritmetico
						resultado = String.valueOf(calc.calculateArithmetic(funtionality));
					}
					

				}
				
			}
			container.clear();
			return resultado; 
		
	}
    private ArrayList<String> convertArrayList(Integer cant){
		ArrayList<String> aux = new ArrayList<>();

		// Metiendo los datos
		for(int i = 0; i < cant; i++){
			aux.add(container.removeFirst());			
		}

		return aux;
	} 

    
    private ArrayList<String> predicateCommunication(String functionName, ArrayList<String> replaceVariables){
		Predicados predicate = new Predicados();
		Parser parser = new Parser();
		ArrayList<ArrayList<String>> communicate = new ArrayList<>();
		ArrayList<String> aux = new ArrayList<>();
		ArrayList<Integer> position = new ArrayList<>();
		Integer auxiliar;
		String[] temp;
		String response, temporal;
		String entry = "";

		// Separando el string
		communicate = (ArrayList)this.oldFunction.get(functionName).clone();
		entry = String.join(" ", communicate.get(0));
		communicate = parser.convertirArray(entry);
		

		// Remplazando todas las variables por nÃºmeros
		for(int i = 0; i < communicate.size(); i++){ // Por cada elemento del arraylist
			for(int j = 0; j < communicate.get(i).size(); j++){ // Por cada string en el arraylist

				for(int k = 0; k < replaceVariables.size(); k++){ // Por cada variable

					if(communicate.get(i).get(j).equalsIgnoreCase(variables.get(functionName).get(k))){
						auxiliar = (int)Float.parseFloat(replaceVariables.get(k));
						temporal =  Integer.toString(auxiliar);
						communicate.get(i).set(j, temporal); // Remplazando las variables por nÃºmeros
						position.add(i);
						position.add(j);
						position.add(k);
					}

				}

			}
			
		}
		
		response = predicate.funCond(communicate);

		temp = response.split(" ");

		for(int i = 0; i < temp.length; i++){
			aux.add(temp[i]);
		}


		// Regresando a communicate a su estado anterior
		while(position.size() > 0){
			communicate.get(position.remove(0)).set(position.remove(0), variables.get(functionName).get(position.remove(0)));
		}
		return aux;
	}
    
    private void refresh(){
		String temp, aux;

		try{
			Float.parseFloat(container.element()); // Verificando si es numero u operador

			// Cambiando los valores para que quede el operador aritmetico en la primera posicion
			temp = container.removeFirst(); // Numero
			aux = container.removeFirst(); // Operador aritmetico
 
			container.addFirst(temp);
			container.addFirst(aux);

		}catch(Exception e){
			return;
		}
	}
    
    public Boolean hasKey(ArrayList<ArrayList<String>> function){
    	
    	if(functions.size() > 0) {
    		return functions.containsKey(function.get(0).get(1).toUpperCase()); 
    	}
    	else {
    		return false;
    	}
	}

}
