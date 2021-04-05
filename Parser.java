import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Parser {
	static int actual = 0;
	static  ArrayList<ArrayList<String>>lispEntry = new ArrayList<ArrayList<String>>();
	static Funciones run = new Funciones();
	private ArrayList<String> list = new ArrayList<String>();
	private String[] palabrasClave = { "defun", "atom","list","cond","equal","+","-","/","*","setq","quote","<",">"};
	Funciones fun = new Funciones();
	Arithmetic ops = new Arithmetic();
	Declaracion dec = new Declaracion();


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
				lispEntry=fun.BuscarVar(lispEntry);
				System.out.println("nnnnnnnnnnnn"+lispEntry);
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
									finalArray.add(fun.atom(lispEntry));
									break;
								case 2:
									finalArray = fun.listLisp(lispEntry);
									break;
								case 3:
									/*try{
										finalArray.add(predicados.funCond(ingresoLisp));
									} catch (Exception e) {
										finalArray.add("Debes de ingresar 3 condiciones");
									}*/
									break;									//"defun", "atom","list","cond","equal","+","-","/","*","setq","quote"
								case 4:
									finalArray.add(fun.equal(lispEntry));
									break;
								case 5:
									case 6:
										case 7:
											case 8:
												finalArray.add(String.valueOf(ops.calculateArithmetic(fixedArray)));
												break;
								case 9:
									finalArray.add(fun.setQ(lispEntry));
									System.out.println("xxxx"+lispEntry);
									break;
								case 10:
									finalArray.add(fun.quote(lispEntry));
									break;
								case 11:
									case 12:
										finalArray.add(fun.mayorMenor(lispEntry));
									break;
								default:
									break;
							}
							break;
						}
					}
				}
			}

			
		return finalArray;
		}
	}
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
}
