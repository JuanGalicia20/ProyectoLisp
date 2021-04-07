import java.util.*;
/**
 * @author Juan Andres Galicia 20298
 * @author Elisa Samayoa 20710
 * @author Jonathan Espinoza 20022
 * 
 * @version 5-4-2021 
 * 
 * Metodos para cada uno de los posibles metodos ingresados por el usuario
 */
public class Funciones {
    private LinkedHashMap <String, ArrayList<String>> listadoVariables;
    private Verificar verificar = new Verificar();

    public Funciones(){
        listadoVariables = new LinkedHashMap<>();
        ArrayList<String> prvalue1 = new ArrayList<>();
        prvalue1.add("1");
        prvalue1.add("2");
        prvalue1.add("3");
        listadoVariables.put("pr1", prvalue1);
    }

    
    /** 
     * @return LinkedHashMap<String, ArrayList<String>>
     */
    public LinkedHashMap<String, ArrayList<String>> getListadoVariables()
    {
        return listadoVariables;
    }

   
    
    /** 
     * @param codigo
     * @return String
     * 
     * Devuelve el ingreso sin evaluarlo
     */
    public String quote (ArrayList<ArrayList<String>> codigo){
        String finalString="( ";
        ArrayList<String> newList = new ArrayList<String>();
        boolean hasQuote = false;
        int pos1=0;

        for(int i=0;i<codigo.size();i++){
            for(int j = 0; j<codigo.get(i).size();j++)
            {
                if(codigo.get(i).get(j).equals("quote")||codigo.get(i).get(j).equals(" quote")||
                codigo.get(i).get(j).equals("'")||codigo.get(i).get(j).equals(" '")){
                    pos1=i;
                    hasQuote = true;  
                    break;
                }
            }
        }


        if(hasQuote)
        {
            for(int j = 0; j<codigo.get(pos1).size();j++)
            {
                newList.add(codigo.get(pos1).get(j));
            }
        }
    
        newList.remove(new String("quote"));
        newList.remove(new String("("));
        newList.remove(new String(")"));
        newList.remove(new String("'"));
        for(String a: newList)
        {
            finalString+=a+" ";
        }
        finalString+=")";

        return finalString;        
    
    }



    
    /** 
     * @param codigo
     * @return ArrayList<String>
     * 
     * Crea una lista con los valores ingresados por el usuario
     */
    public ArrayList<String> listLisp (ArrayList<ArrayList<String>> codigo){
        ArrayList<String> newList = new ArrayList<String>();
        boolean hasList = false;

        for(int i=0;i<codigo.size();i++){
            if(codigo.get(i).contains("list")||codigo.get(i).contains(" list")){
                hasList = true;
            }
            
        }

        if (hasList){
            for(int i=0;i<codigo.size();i++){
                for(int j = 0; j< (codigo.get(i).size()-1);j++)
                {
                    newList.add(codigo.get(i).get(j)); 
                }
            }

            
            newList.remove(new String("list"));
            newList.remove(new String("("));
            newList.remove(new String(")"));
            
        }

        else{
            newList.add("No es list");
        }

        System.out.println("LISTA CREADA: "+newList);
        return newList;        
    }




    
    /** 
     * @param lista
     * @return String
     * 
     * Es una clase que obtiene el valor en una posicion determinada de una lista 
     */
    public ArrayList<String> ListValue(ArrayList<String> lista)
    {
        
        int posIndex = 0;
       
        int pos=0;
        String valor="";

        for(int i = 0; i < lista.size(); i++){
            if(lista.get(i).equals("first") || lista.get(i).equals("second") || lista.get(i).equals("third") || lista.get(i).equals("fourth") || lista.get(i).equals("fifth") 
            || lista.get(i).equals("sixth") || lista.get(i).equals("seventh") || lista.get(i).equals("eighth") || lista.get(i).equals("ninth") || lista.get(i).equals("tenth") 
            || lista.get(i).equals("nth")){
                posIndex = i;
                i = lista.size();
            }
        }
        String posicion = lista.get(posIndex);
        String var = lista.get(posIndex + 1);
        
        switch(posicion)
        {
            case "first":
                pos = 0;
                break;
            case "second":
                pos = 1;
                break;
            case "third":
                pos = 2;
                break;
            case "fourth":
                pos = 3;
                break;
            case "fifth":
                pos = 4;
                break;
            case "sixth":
                pos = 5;
                break;
            case "seventh":
                pos = 6;
                break;
            case "eighth":
                pos = 7;
                break;
            case "ninth":
                pos = 8;
                break;
            case "tenth":
                pos = 9;
                break;
            case "nth":
                try{
                    pos = Integer.parseInt(lista.get(posIndex + 1));
                }
                catch(Exception exception)
                {
                    System.out.println("SYNTAX ERROR, VALOR INVALIDO");
                }
                    
                var = lista.get(posIndex + 2);
                break;
            default:
            {
                valor="VALOR INCORRECTO";
                break;
            }
        }

        try{
            valor = listadoVariables.get(var).get(pos);
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
            System.out.println("VALOR NO VALIDO PARA LA LISTA");
        }
        System.out.println("VALOR RETORNADO: "+valor);

        //se utiliza cuando se desea utilizar el valor de una lista guardada en una variable para cualquier operacion
        int j ;

        if(posicion.equals("nth")){
            j = posIndex + 3;
        }
        else{
            j = posIndex + 2;
        }

        for(int k = j; k >= posIndex - 1; k--){
            if(k == posIndex - 1){
                lista.remove(k);
                lista.add(k, valor);
            }
            else{
                lista.remove(k);
            } 
        }

        return lista;
    }


     
     /** 
      * @param codigo
      * @return ArrayList<ArrayList<String>> el codigo ingresado por el usuario pero con la variable cambiada por su simbolo
      * por ejemplo  si definimos (setq a 12) -> (+ 1 a) -> (+ 1 12)
      *
      * Guarda el ingreso del usuario en una variable con el nombre que ingreso el usuario.
      */
     public ArrayList<ArrayList<String>> setQ (ArrayList<ArrayList<String>> codigo){
        ArrayList<String> newList = new ArrayList<String>();
        ArrayList<ArrayList<String>> enviar = new ArrayList<ArrayList<String>>();
        boolean hasSet = false;
        int pos=0;
        String variable="";
        String valor="";
        ArrayList<String> valor2= new ArrayList<String>();
        boolean hasList=false;
        Declaracion dec = new Declaracion();

        for(int i=0;i<codigo.size();i++){
            if(codigo.get(i).contains("list")||codigo.get(i).contains(" list"))
            {
                hasList=true;
            }
            for(int j = 0; j<codigo.get(i).size();j++)
            {
                if(codigo.get(i).get(j).contains("setq")||codigo.get(i).get(j).contains(" setq")){
                    pos=j;
                    hasSet = true;
                }
            }
        }

        if (hasSet){
            if(hasList)
            {
                //si existe una lista ->se crea una y se le asignan los valores en el linkedhasmap de la variable
                for(int i=0;i<codigo.size();i++){
                    for(int j = 0; j< (codigo.get(i).size());j++)
                    {
                        newList.add(codigo.get(i).get(j)); 
                    }
                }
    
                variable = newList.get(pos +1);
    
                newList.remove(new String("setq"));
                newList.remove (new String(variable));
                newList.remove(new String("("));
                newList.remove(new String(")"));
                ArrayList<ArrayList<String>> lista= new ArrayList<>();
                lista.add(newList);

                valor2 = listLisp(lista);
                listadoVariables.put(variable, valor2);
            }
            else{
                for(int i=0;i<codigo.size();i++){
                    for(int j = 0; j< (codigo.get(i).size());j++)
                    {
                        newList.add(codigo.get(i).get(j));
                    }
                }
    
                variable = newList.get(pos +1);
    
                newList.remove(new String("setq"));
                newList.remove (new String(variable));
                newList.remove(new String("("));
                newList.remove(new String(")"));
    
                for(String a: newList)
                {
                    valor+=a;
                }
                
                valor2 = new ArrayList<>();
                valor2.add(valor);
                listadoVariables.put(variable, valor2);
            }
        }

        else{
            newList.add("ERROR..., No se ha podido conservar la variable");
        }

        enviar.add(newList);

        System.out.println("VARIABLE "+variable+" CREADA: "+valor2);
        return  enviar;
    }   
    


    /**
     * Es un metodo que se encarga de realizar operaciones donde se involucran varias funciones
     * Y el resultado de estas es el parametro de otras
     * por ejemplo (fibonacci (fibonacci (factorial 3)))
     * @param codigo es el codigo separado ingresado por el usuario
     * @param declaracion es el objeto instanciado previamente en Parser de Declaracion
     * @return el resultado final del calculo
     */
    public String MultiFun(ArrayList<ArrayList<String>> codigo, Declaracion declaracion)
    {
        ArrayList<Integer> posiciones = new ArrayList<>();
        ArrayList<Integer> posicionesJ = new ArrayList<>();
        ArrayList<ArrayList<String>> nuevoCod = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> resultCod = new ArrayList<ArrayList<String>>();
        nuevoCod.add(new ArrayList<>());
        //int j = list.size() - 1; j >= 0; j--
        for(int i=codigo.size()-1;i>=0;i--)
        {
            for(int j = codigo.get(i).size()-1; j>=0;j--)
            {
                if(declaracion.getFunctions().containsKey(codigo.get(i).get(j).toUpperCase()))
                {
                    posiciones.add(j);
                    posicionesJ.add(j+2);
                }
            }
        }


        //luego de identificar las posiciones donde estan las funciones se busca ingresar unicamente esa "parte" del codigo y operarlo
        //recorriendo el codigo alrevez para operar primero las interiores y luego las exteriores
        for(int i = 0; i < posiciones.size(); i++)
        {
            for(int k = posicionesJ.get(i); k >= posiciones.get(i) - 1; k--){
                if(k == posiciones.get(i) - 1){
                    nuevoCod.get(0).add(codigo.get(0).get(k));
                    codigo.get(0).remove(k);
                    ArrayList<String> revArrayList = new ArrayList<String>();
                    for (int q = nuevoCod.get(0).size() - 1; q >= 0; q--) {
            
                        // Ingresa los elementos al reves
                        revArrayList.add(nuevoCod.get(0).get(q));
                    }
                    resultCod.add(revArrayList);
                    //envia el trozo de codigo a evaluar y sustituye su resultado en el codigo completo
                    codigo.get(0).add(k, declaracion.runFuncion(resultCod));
                    resultCod.clear();
                    revArrayList.clear();
                    nuevoCod.get(0).clear();
                }
                else{
                    nuevoCod.get(0).add(codigo.get(0).get(k));
                    codigo.get(0).remove(k);
                } 
            }
        }
        System.out.println(codigo);
        return codigo.toString();
    }

    
    /** 
     * @param codigo
     * @return ArrayList<ArrayList<String>>
     * 
     * Busca entre las variables ya creadas por el usuario
     */
    public ArrayList<ArrayList<String>> BuscarVar(ArrayList<ArrayList<String>> codigo)
    {
        boolean hasPosition=false;
        ArrayList<String> result=new ArrayList<>();
        int pos1=0;
        int pos2=0;
        if(verificar.VerifList(codigo))
        {
            hasPosition=true;
        }
        else
        {
            for(String element : listadoVariables.keySet())
            {
                for(int i = 0; i < codigo.size(); i++) {
                    for(int j =0; j<codigo.get(i).size();j++)
                    {
                        if(element.equals(codigo.get(i).get(j)))
                        {
                            pos1=i;
                            pos2=j;
                            for(String a: listadoVariables.get(element))
                            {
                                result.add(a);
                            }
                        }
                    }
                }
            }
        }
        
        
        //si es una posicion de una lista -> se busca utilizando el metodo listValue()
        if(hasPosition)
        {
            ArrayList<String> resultadoF = ListValue(codigo.get(0));
            codigo.clear();
            codigo.add(resultadoF);
            
            return codigo;
        }
        else
        {
            if(result.size()>1)
            {
                String env = "[";
                for(String a : result)
                {
                    env+=a+", ";
                }
                codigo.get(0).add(env);
                return codigo;
            }
            else
            {
                codigo.get(pos1).set(pos2, result.get(0));
                return codigo;
            }
        }
    }



    
    /** 
     * @param codigo
     * @return String
     * 
     * Es un metodo que verifica si lo ingresado y solicitado es un objeto de tipo atom
     * 
     * 
     */
    public String atom (ArrayList<ArrayList<String>> codigo){
        int size = codigo.size();
        boolean hascons=false;
        boolean haslist=false;

        for(String element : listadoVariables.keySet())
        {
            for(int i = 0; i < codigo.size(); i++) {
                for(int j =0; j<codigo.get(i).size();j++)
                {
                    if(element.equals(codigo.get(i).get(j)))
                    {
                        if(listadoVariables.get(element).size()>1)
                        {
                            haslist=true;
                        }
                    }
                }
            }
        }

        for(int i=0;i<size;i++){
            for(int j = 0; j<codigo.get(i).size();j++){
                if(codigo.get(i).get(j).equals("atom")){
                    if (codigo.get(i).contains("cons")||codigo.get(i).contains("list") || haslist || hascons){
                        return "Atom: false";
                    }else{

                        return "Atom: true";
                    }
                } 
            }
        }
        return "Atom: false";		
    }

    
    
    /** 
     * Es un metodo que se encarga de verificar que dos objetos son iguales o no
     * @param codigo codigo ingresado por el usuario
     * @return String resultado de la comparacion
     */
    public String  equal (ArrayList<ArrayList<String>> codigo){
        int pos = 0; 
        String result = "false";
        
        for(int i=0;i<codigo.size();i++){ //recorre el array hasta encontrar un equals
            for(int j = 0; j<codigo.get(i).size();j++){
                if (codigo.get(i).get(j).equals("equal") || codigo.get(i).get(i).equals( " equal")){ 
                    pos = j; //conserva la posicion del equals
                    if(codigo.get(i).size()<5){ //evalúa dos parametros de otras listas
                        if(codigo.get(i).get(pos+1).equals(codigo.get(i).get(pos+2))){
                            System.out.println("true");
                            result = "true";   
                        }
                    }else if(codigo.get(i).size() == 5){
                        if(codigo.get(i).get(pos+1).equals(codigo.get(i).get(pos+2))){
                            System.out.println("true");
                            return "true";
                        }
                    }
                }
            }
        } 
        System.out.println("false");
        return result;
    }
    

    
    
    /** 
     * Metodo que verifica si es correcto que un parametro es o no mayor o menor al otro
     * @param codigo codigo ingresado por el usuario
     * @return String resultado de la comparacion
     */
    public String mayorMenor(ArrayList<ArrayList<String>> codigo){

        float comp1 = 0;
        float comp2 = 0;
        int pos = 0;
        String estado = "";
        
        for(int i=0;i<codigo.size();i++){ //recorre el array hasta encontrar un mayor que
            for(int j = 0; j<codigo.get(i).size();j++){
                if(codigo.get(i).get(j).contains(">")||codigo.get(i).get(i).contains( " >")){ 
                    pos = j; //conserva la posicion del "mayor que"
                    String a = codigo.get(i).get(pos+1);
                    String b = codigo.get(i).get(pos+2);
                    comp1 = Float.parseFloat(a);
                    comp2 = Float.parseFloat(b);

                    if(comp1>comp2){ 
                        estado = "true";
                    }else{
                        estado = "false";
                    }                    
                }else if(codigo.get(i).get(j).contains("<")||codigo.get(i).get(i).contains( " <")){ 
                    pos = j; //conserva la posicion del "menor que"
                    String a = codigo.get(i).get(pos+1);
                    String b = codigo.get(i).get(pos+2);
                    comp1 = Float.parseFloat(a);
                    comp2 = Float.parseFloat(b);

                    if(comp1<comp2){ 
                        estado = "true";
                    }else{
                        estado = "false";
                    }                    
                }
            }
        }
        System.out.println(estado);
        return estado;       
    }

    
    /** 
     * Metodo que se encarga de administrar las condicionales 
     * @param listado codigo ingresado por el usuario
     * @return String Resultado
     * 
     * Analiza las condiciones ingresadas por el usuario y evalua segun el resultado.
     * 
     * 
     */
    public String cond(ArrayList<ArrayList<String>> listado){
		String s = "";
		int operador = 0;
		int t = 0;
		for(int i=0; i<listado.size();i++){
			if(listado.get(i).contains("cond") ){
				
				for(int j=0; j< listado.size();j++) {
					if(listado.get(j).contains("<") || listado.get(j).contains("=") || listado.get(j).contains(">") ){
						operador++;
					}
				}
				
				for(int k=0; k< listado.size();k++) {
					if(listado.get(k).contains("t") || listado.get(k).contains(" t") ){
						t = k;
					}
				}
				
				Float a = 0.0f;
				Float b = 0.0f;
				String c = "";

				a = Float.parseFloat(listado.get(i-2).get(2));
				b = Float.parseFloat(listado.get(i-2).get(3));
				c = listado.get(i-2).get(1);
			
				switch(c){
					case ">":
					if(a > b && operador > 0){

						for(int j=1;j<listado.get(i-1).size()-1;j++){
							s+= listado.get(i-1).get(j);
						}
						return s;
					}else if(operador > 1){
						
						a = Float.parseFloat(listado.get(i-4).get(2));
						b = Float.parseFloat(listado.get(i-4).get(3));
						c = listado.get(i-4).get(1);
						switch(c){
							case ">":
								if(a > b){
								for(int j=1;j<listado.get(i-3).size()-1;j++){
									s+= listado.get(i-3).get(j);
									}
									return s;	
								}else{
									if(listado.get(i-5).size()>3){
										for(int j=2;j<listado.get(i-5).size()-1;j++){
												s+= listado.get(i-5).get(j)+" ";
											}
											return s;
										}
									else{
										for(int j=i-6;j>-1;j--){
											for(int k=1; k<listado.get(j).size()-1;k++){
												s+= listado.get(j).get(k)+" ";
											}
										}
										return s;
									}
								}
							 
							case "<":
								if(a < b){
								for(int j=1;j<listado.get(i-3).size()-1;j++){
									s+= listado.get(i-3).get(j);
									}	
									return s;
								}else{
								if(listado.get(i-5).size()>3){
										for(int j=2;j<listado.get(i-5).size()-1;j++){
												s+= listado.get(i-5).get(j)+" ";
											}
											return s;
										}
									else{
										for(int j=i-6;j>-1;j--){
											for(int k=1; k<listado.get(j).size()-1;k++){
												s+= listado.get(j).get(k)+" ";
											}
										}
										return s;
									}
								}
							 

							case "=":
								if(Math.abs(a-b)<1.0){
								for(int j=1;j<listado.get(i-3).size()-1;j++){
									s+= listado.get(i-3).get(j);
									}	
									return s;
								}else{
								if(listado.get(i-5).size()>3){
										for(int j=2;j<listado.get(i-5).size()-1;j++){
												s+= listado.get(i-5).get(j)+" ";
											}
											return s;
										}
									else{
										for(int j=i-6;j>-1;j--){
											for(int k=1; k<listado.get(j).size()-1;k++){
												s+= listado.get(j).get(k)+" ";
											}
										}
										return s;
									}
								}
							 
						}
					}else if(operador == 1) {
						for(int j = t-1; j > -1; j-- ) {
							for(int k=1; k<listado.get(j).size()-1;k++){
								s+= listado.get(j).get(k)+" ";
							}
						}
						return s;
						
					}
					
					
					 

					case "<":
					if(a < b){
						for(int j=1;j<listado.get(i-1).size()-1;j++){
						s+= listado.get(i-1).get(j);
						}
						return s;
					}else if(operador > 1){
						a = Float.parseFloat(listado.get(i-4).get(2));
						b = Float.parseFloat(listado.get(i-4).get(3));
						c = listado.get(i-4).get(1);
						switch(c){
							case ">":
								if(a > b){
								for(int j=1;j<listado.get(i-3).size()-1;j++){
									s+= listado.get(i-3).get(j);
									}	
									return s;
								}else{
								if(listado.get(i-5).size()>3){
										for(int j=2;j<listado.get(i-5).size()-1;j++){
												s+= listado.get(i-5).get(j)+" ";
											}
											return s;
										}
									else{
										for(int j=i-6;j>-1;j--){
											for(int k=1; k<listado.get(j).size()-1;k++){
												s+= listado.get(j).get(k)+" ";
											}
										}
										return s;
									}
								}
							case "<":
								if(a < b){
								for(int j=1;j<listado.get(i-3).size()-1;j++){
									s+= listado.get(i-3).get(j);
									}	
									return s;
								}else{
								if(listado.get(i-5).size()>3){
										for(int j=2;j<listado.get(i-5).size()-1;j++){
												s+= listado.get(i-5).get(j)+" ";
											}
											return s;
										}
									else{
										for(int j=i-6;j>-1;j--){
											for(int k=1; k<listado.get(j).size()-1;k++){
												s+= listado.get(j).get(k)+" ";
											}
										}
										return s;
									}
								}
							 

							case "=":
								if(Math.abs(a-b)<1.0){
								for(int j=1;j<listado.get(i-3).size()-1;j++){
									s+= listado.get(i-3).get(j);
									}
									return s;	
								}else{
								if(listado.get(i-5).size()>3){
										for(int j=2;j<listado.get(i-5).size()-1;j++){
												s+= listado.get(i-5).get(j)+" ";
											}
											return s;
										}
									else{
										for(int j=i-6;j>-1;j--){
											for(int k=1; k<listado.get(j).size()-1;k++){
												s+= listado.get(j).get(k)+" ";
											}
										}
										return s;
									}
								}
							 
						}
					}else if(operador == 1) {
						for(int j = t-1; j > -1; j-- ) {
							for(int k=1; k<listado.get(j).size()-1;k++){
								s+= listado.get(j).get(k)+" ";
							}
						}
						return s;
						
					}
					

					case "=":
					if(Math.abs(a-b)<1){
						for(int j=1;j<listado.get(i-1).size()-1;j++){
						s+= listado.get(i-1).get(j);
						}
						return s;
					}else if(operador > 1){
						a = Float.parseFloat(listado.get(i-4).get(2));
						b = Float.parseFloat(listado.get(i-4).get(3));
						c = listado.get(i-4).get(1);
						switch(c){
							case ">":
								if(a > b){
								for(int j=1;j<listado.get(i-3).size()-1;j++){
									s+= listado.get(i-3).get(j);
									}	
									return s;
								}else{
								if(listado.get(i-5).size()>3){
										for(int j=2;j<listado.get(i-5).size()-1;j++){
												s+= listado.get(i-5).get(j)+" ";
											}
											return s;
										}
									else{
										for(int j=i-6;j>-1;j--){
											for(int k=1; k<listado.get(j).size()-1;k++){
												s+= listado.get(j).get(k)+" ";
											}
										}
										return s;
									}
								}
								
							 
							case "<":
								if(a < b){
								for(int j=1;j<listado.get(i-3).size()-1;j++){
									s+= listado.get(i-3).get(j);
									}	
									return s;
								}else{
								if(listado.get(i-5).size()>3){
										for(int j=2;j<listado.get(i-5).size()-1;j++){
												s+= listado.get(i-5).get(j)+" ";
											}
											return s;
										}
									else{
										for(int j=i-6;j>-1;j--){
											for(int k=1; k<listado.get(j).size()-1;k++){
												s+= listado.get(j).get(k)+" ";
											}
										}
										return s;
									}
								}
							 
							case "=":
								if(Math.abs(a-b)<1){
								for(int j=1;j<listado.get(i-3).size()-1;j++){
									s+= listado.get(i-3).get(j);
									}	
									return s;
								}else{
								if(listado.get(i-5).size()>3){
										for(int j=2;j<listado.get(i-5).size()-1;j++){
												s+= listado.get(i-5).get(j)+" ";
											}
											return s;
										}
									else{
										for(int j=i-6;j>-1;j--){
											for(int k=1; k<listado.get(j).size()-1;k++){
												s+= listado.get(j).get(k)+" ";
											}
										}
										return s;
									}
								}
							 
						}
					}else if(operador == 1) {
						
						for(int j = t-1; j > -1; j-- ) {
							for(int k=1; k<listado.get(j).size()-1;k++){
								s+= listado.get(j).get(k)+" ";
							}
						}
						
							return s;
						}
						
					}
				}

				
			}	
		
		return "Error..., cond no valido";
        }
}


