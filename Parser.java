import java.util.ArrayList;
import java.util.*;

public class Parser {
    static int actual = 0;
	static ArrayList<ArrayList<String>> lispEntry = new ArrayList<ArrayList<String>>();
	private ArrayList<String> list = new ArrayList<String>();
	private ArrayList<String> lispVocabulary = new ArrayList<String>();
	
    public ArrayList<ArrayList<String>> separateString(String entry) {
		ArrayList<String> reversed = new ArrayList<String>(); 
		entry = entry.toLowerCase();

		String[] splitedStrings = entry.split(" "); //Se convierte a vector por espacio
		entry = addSpace(splitedStrings);
		splitedStrings = entry.split(" ");

		for (int i=0 ; i < splitedStrings.length; i++) {
			reversed.add(splitedStrings[i]); 
		}

		Collections.reverse(reversed);

		for(int i=0; i < reversed.size(); i++){
			ArrayList<String> temp = new ArrayList<String>();
			if(("(").equals(reversed.get(i))){
				for(int j = i; j > 0; j--){
					if((")").equals(reversed.get(j))){
						for(int k = i; (k + 1) != j; k--){
							temp.add(reversed.get(k));
							reversed.remove(k);
						}
						i = 0;
						addString(temp);
						break;
				}
			}
		}
	}
	Collections.reverse(reversed); //Se revierte el orden
	addString(reversed); //Se agrega lo ultimo dejado en invertidos
	return lispEntry;
}

	private void addString(ArrayList<String> temp){
		StringBuilder newString =new StringBuilder();
		boolean bool = true;
		if (temp.size()>0) {
			//Se regresa el array a string
			String interpretation = "";
			for (String i : temp) {
				if (bool) {
					newString.append(i); //No agrega espacio a la primera letra
					bool = false;
				} else {
					newString.append(" " + i); //Agrega esoacios entre las palabras
				}
			}
			interpretation = newString.toString(); //Se convierte el builder a String
			String[] complete = interpretation.split(" ");
			for (int i = 0; i<complete.length; i++) {
				list.add(complete[i]); //Se agrega la primer operacion al listado					
			}
			lispEntry.add(actual, list);
			actual++;

		}
	}

	private String addSpace(String[] entry){
		String[] temp;

		String str = "";
		String temp2 = "";

	
		for (int j=0; j<entry.length; j++) {
			str = entry[j];
			temp = str.split("");
			
			str = str.replaceAll("\\)"," ) ");
			str = str.replaceAll("\\("," ( ");

			temp2 += " " + str;

		}

		StringTokenizer st = new StringTokenizer(temp2, " ");
		StringBuffer sb = new StringBuffer();
		//Se quitan espacios extras
		while(st.hasMoreElements()){
		    sb.append(st.nextElement()).append(" ");
		}
		//Se regresa bien escrito lo ingresado
		temp2 = sb.toString();
		return temp2;
	}

}