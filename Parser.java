import java.util.ArrayList;
import java.util.*;
/**
 * @author Juan Andres Galicia 20298
 * @author Elisa Samayoa 20710
 * @author Jonathan Espinoza 20022
 * 
 * @version 5-4-2021 
 * 
 * Clase controladora
 * 
 * Separa el ingreso del usuario por espacios, analiza el ingreso del usuario y lo envia segun el metodo.
 */
public class Parser {
	static int actual = 0;
	static  ArrayList<ArrayList<String>>lispEntry = new ArrayList<ArrayList<String>>();
	static Funciones run = new Funciones();
	private ArrayList<String> list = new ArrayList<String>();
	private String[] palabrasClave = { "defun", "setq","atom","cond","equal","+","-","/","*","list","quote","<",">"};
	Funciones fun = new Funciones();
	Arithmetic ops = new Arithmetic();
	Declaracion dec = new Declaracion();


	
	/** 
	 * @param codigo
	 * @return ArrayList<String>
	 * 
	 * 
	 * Analiza el ingreso por posicion y si identifica algun metodo, corre el programa.
	 */
	public ArrayList<String> funcion(String codigo)
	{
		ArrayList<String> finalArray = new ArrayList<String>();
		ArrayList<String> fixedArray = new ArrayList<String>();
		Verificar verif = new Verificar(codigo);
		
		lispEntry = getStack(codigo);
		
		System.out.println(lispEntry);
		
		
		
		if (dec.hasKey(lispEntry)) {
			finalArray.add(dec.runFuncion(lispEntry));
			return finalArray;
		}
		else {
			if(verif.VerifVariable(lispEntry, fun.getListadoVariables()))
			{
				//finalArray.add(fun.BuscarVar(lispEntry));
				lispEntry=fun.BuscarVar(lispEntry);
				finalArray.add(lispEntry.toString());
			}
			else if(verif.VerifList(lispEntry))
			{
				finalArray.add(fun.ListValue((lispEntry).get(0)));
			}
			
			for(int i=0; i<lispEntry.size();i++){
				fixedArray = lispEntry.get(i);
				for(int k = 0; k < fixedArray.size(); k++){
					for(int j=0; j < palabrasClave.length;j++){
						if(fixedArray.get(k).contains(palabrasClave[j]))
						{
							switch (j)
							{
								case 0:
									String def = dec.setFuncion(lispEntry); 
									finalArray.add(def);
									i = lispEntry.size();
									k = fixedArray.size();
									j = palabrasClave.length;
									break;
								case 1:
									lispEntry=(fun.setQ(lispEntry));
									finalArray.add(lispEntry.toString());
									break;
								case 2:
									finalArray.add(fun.atom(lispEntry));
									break;
								case 3:
									try{
										lispEntry = convertirArray(codigo);
										finalArray.add(fun.cond(lispEntry));
									} catch (Exception e) {
										finalArray.add("Debes de ingresar 3 condiciones");
									}
									i = lispEntry.size();
									k = fixedArray.size();
									j = palabrasClave.length;
								break;															//"defun", "atom","list","cond","equal","+","-","/","*","setq","quote"
								case 4:
									finalArray.add(fun.equal(lispEntry));
									break;
								case 5:
									case 6:
										case 7:
											case 8:
												finalArray.add(String.valueOf(ops.calculateArithmetic(fixedArray)));
												System.out.println(finalArray);
												break;
								case 9:
									finalArray = fun.listLisp(lispEntry);
									break;
								case 10:
									finalArray.add(fun.quote(lispEntry));
									i = lispEntry.size();
									k = fixedArray.size();
									j = palabrasClave.length;
									break;
								case 11:
									case 12:
										finalArray.add(fun.mayorMenor(lispEntry));
									break;
								default:
									finalArray.add("Error en el ingreso");
									break;
							}
							break;
						}
					}
				}
			}
			if(finalArray.size() == 0) {
				finalArray.add("Error en el ingreso, intentalo de nuevo");
			}
		return finalArray;
		}

	}
	
	/** 
	 * @param entry
	 * @return ArrayList<ArrayList<String>>
	 * 
	 * Separa la entrada del usuario por espacio
	 */
	public ArrayList<ArrayList<String>> getStack(String entry) {
		entry = entry.toLowerCase();
        ArrayList<ArrayList<String>> stack = new ArrayList<>();
		
        try {
            int openParentheses = 0;
            int closedParantheses = 0;
            String elementToPush = "";
            ArrayList<String> lineToCalculate = new ArrayList<>();

            // Generates the stack
            if(!entry.isEmpty()) {
				// Scanner.hasNext() != false;

                // Runs through every character in the line
                for(int i = 0; i < entry.length(); i ++) {
                    char currentCharacter = entry.charAt(i);
                    char nextCharacter = ' ';
                    if(i < entry.length() - 1){
                        nextCharacter = entry.charAt(i+1);
                    } else if (i == entry.length() - 1) {
                        nextCharacter = ' ';
                    }
                    // Takes action according to the character

                    

					switch (currentCharacter){
						// Characters that define division or actions
						case '(': 
							case '<': 
								case ' ': 
									case '>': 
										case '+': 
											case '-': 
												case '*': 
													case ')': 
														case '"':
															case '/':
																if(currentCharacter == '(') {
																	openParentheses ++;
																} else if(currentCharacter == ')') {
																	closedParantheses ++;
																}

																if(currentCharacter == '<' || currentCharacter == '>' || currentCharacter == '=') {
																	if(nextCharacter == '=') {
																		elementToPush += currentCharacter;
																		break;
																	}
																} else if(currentCharacter == '-') {
																	if(nextCharacter != ' ') {
																		elementToPush += currentCharacter;
																		break;
																	}
																}

																if(currentCharacter == '=') {
																	if(nextCharacter =='=') {
																		elementToPush += currentCharacter;
																	}
																}

																if(elementToPush.length() > 0 && elementToPush.charAt(0) != '"' && currentCharacter != '"') {
																	lineToCalculate.add(elementToPush);
																	elementToPush = "";
																}

																if(currentCharacter != ' ' && currentCharacter != '"' && elementToPush.length() == 0){
																	elementToPush += currentCharacter;
																	lineToCalculate.add(elementToPush);
																	elementToPush = "";
																}

																if(currentCharacter == '"'){
																	if(elementToPush.length() > 0) {
																		elementToPush += currentCharacter;
																		lineToCalculate.add(elementToPush);
																		elementToPush = "";
																	} else {
																		elementToPush += currentCharacter;
																	}
																}

																if(elementToPush.length() > 0 && elementToPush.charAt(0) == '"') {
																	if(elementToPush.length() > 1) {
																		elementToPush += currentCharacter;
																	}
																}
																break;

						default:    // Gets any character that doesn't define an operation
							elementToPush += currentCharacter;
							if(i == entry.length() - 1) {
								lineToCalculate.add(elementToPush);
							}
							break;
					}
					if(openParentheses == closedParantheses && lineToCalculate.size() > 0) {
						ArrayList<String> lineToAdd = new ArrayList<>(lineToCalculate);
						stack.add(lineToAdd);
						lineToCalculate.clear();
						openParentheses = 0;
						closedParantheses = 0;
					}

                }

            }

        } catch (Exception E) {
            System.err.println("Error, no se puede leer el codigo ingresado");
        }
		
        return stack;
        
    }
		
	
	/** 
	 * @param input
	 * @return ArrayList<ArrayList<String>>
	 * 
	 * Separa la entrada del usuario por par de parentesis
	 */
	public ArrayList<ArrayList<String>> convertirArray(String input){
		input = input.toLowerCase();

		actual = 0;
		lispEntry = new ArrayList<ArrayList<String>>();
		ArrayList<String> invertido = new ArrayList<String>(); //Arraylist temporal de invertidos

		String[] split_text = input.split(" "); //Se convierte a vector por espacio
		input = addSpace(split_text);
		split_text = input.split(" "); //Se convierte a vector por espacio



		
		for (int i=0; i<split_text.length; i++) {
			invertido.add(split_text[i]); //Se crea un array con todas las letras
		}
		
		Collections.reverse(invertido); //Se invierte el orden

		for (int j=0; j< invertido.size(); j++) {
			ArrayList<String> temporal = new ArrayList<String>(); //Se genera un nuevo arraylist
			if (invertido.get(j).equals("(")) { //Se busca el abierto
				for (int k=j; k>0; k--) {  //Se busca el de cerrar apartir de la posicion del abierto
					if (invertido.get(k).equals(")")){ //Se busca el de cerrar
						for (int l=j; (l+1)!=k; l--) { //Se comienza en la posicion del ( y se realiza el loop hasta llegar al )
							temporal.add(invertido.get(l)); //Se agregan las letras dentro de los parentesis
							invertido.remove(l); //Se quita la operacion encontrada
						}

						j = 0; //Se regresa j a cero para reiniciar el ciclo
						agregar(temporal);
						break;

					}

				}
			}
		}
		
		Collections.reverse(invertido); //Se revierte el orden
		agregar(invertido); //Se agrega lo ultimo dejado en invertidos
		System.out.print(lispEntry);
		return lispEntry;
		
	}

	/**
	* @param temporal 	
	* 
	* Agrega cada uno de par de parentesis al arraylist
	*/
	private void agregar(ArrayList<String> temporal){
		StringBuilder  s=new StringBuilder();
		list = new ArrayList<String>();
		boolean first = true;

		if (temporal.size()>0) {
			//Se regresa el array a string
			String complete_word = "";
			for (String i : temporal) {
				if (first) {
					s.append(i); //No agrega espacio a la primera letra
					first = false;
				} else {
					s.append(" " + i); //Agrega esoacios entre las palabras
				}
			}
			complete_word = s.toString(); //Se convierte el builder a String
			String[] complete = complete_word.split(" ");

			for (int i = 0; i<complete.length; i++) {
				list.add(complete[i]); //Se agrega la primer operacion al list					
			}


			lispEntry.add(actual, list);
			actual++;
			
		}
	}

	
	/** 
	 * @param input
	 * @return String
	 * 
	 * Agrega espacios antes de cada palabra
	 */
	private String addSpace(String[] input){
		String[] temporal;
		String str = "";
		String temporal2 = "";

		for (int j=0; j<input.length; j++) {
			str = input[j];
			temporal = str.split("");
			
			str = str.replaceAll("\\)"," ) ");
			str = str.replaceAll("\\("," ( ");

			temporal2 += " " + str;

		}

		StringTokenizer st = new StringTokenizer(temporal2, " ");
		StringBuffer sb = new StringBuffer();
		//Se quitan espacios extras
		while(st.hasMoreElements()){
		    sb.append(st.nextElement()).append(" ");
		}
		//Se regresa bien escrito lo ingresado
		temporal2 = sb.toString();
		return temporal2;
	}

}
