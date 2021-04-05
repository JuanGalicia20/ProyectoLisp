import java.util.*;

public class Funciones {


    private LinkedHashMap <String, ArrayList<String>> listadoVariables;
    Verificar verificar = new Verificar();

    public Funciones(){
        listadoVariables = new LinkedHashMap<>();
        ArrayList<String> prvalue1 = new ArrayList<>();
        prvalue1.add("1");
        prvalue1.add("2");
        prvalue1.add("3");
        listadoVariables.put("pr1", prvalue1);
    }

    public LinkedHashMap<String, ArrayList<String>> getListadoVariables()
    {
        return listadoVariables;
    }

   
    public String quote (ArrayList<ArrayList<String>> codigo){
        String finalString="( ";
        ArrayList<String> newList = new ArrayList<String>();
        boolean hasList = false;
        int pos;

        for(int i=0;i<codigo.size();i++){
            for(int j = 0; j<codigo.get(i).size();j++)
            {
                if(codigo.get(i).get(j).contains("quote")||codigo.get(i).get(j).contains(" quote")){
                    pos=j;
                    hasList = true;
                }

                else if(codigo.get(i).get(j).contains("'")||codigo.get(i).get(j).contains(" '")){
                    pos=j;
                    hasList = true;
                }


            }
        }

        if (hasList){
            for(int i=0;i<codigo.size();i++){
                for(int j = 0; j< (codigo.get(i).size());j++)
                {
                    newList.add(codigo.get(i).get(j));  
                }
            }

            
            for(int i=0;i<codigo.size();i++){
                for(int j = 0; j<codigo.get(i).size();j++)
                {
                    if(codigo.get(i).get(j).contains("quote")||codigo.get(i).get(j).contains(" quote")){
                        newList.remove(new String("quote"));
                        newList.remove(new String("("));
                        newList.remove(new String(")"));
                    }

                    else if(codigo.get(i).get(j).contains("'")||codigo.get(i).get(j).contains(" '")){
                        newList.remove(new String("'"));
                        newList.remove(new String("("));
                        newList.remove(new String(")"));
                    }
                }
            }          

            for(String a: newList)
            {
                finalString+=a+" ";
            }
            finalString+=")";
            
        }

        else{
            newList.add("No es quote");
        }

        System.out.println("QUOTE: "+finalString);
        return finalString;        
    
    }



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




    public String ListValue(ArrayList<String> lista)
    {
        String posicion = lista.get(1);
        String var = lista.get(2);
        int pos=0;
        String valor="";

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
                    pos = Integer.parseInt(lista.get(2));
                }
                catch(Exception exception)
                {
                    System.out.println("SYNTAX ERROR, VALOR INVALIDO");
                }
                    
                var = lista.get(3);
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
        return valor;
    }


     public ArrayList<ArrayList<String>> setQ (ArrayList<ArrayList<String>> codigo){
        ArrayList<String> newList = new ArrayList<String>();
        ArrayList<ArrayList<String>> enviar = new ArrayList<ArrayList<String>>();
        boolean hasSet = false;
        int pos=0;
        String variable="";
        String valor="";
        ArrayList<String> valor2= new ArrayList<String>();
        boolean hasList=false;

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
        
        
        if(hasPosition)
        {
            String resultadoF = ListValue(codigo.get(0));
            //codigo.get(0).clear();
            //codigo.get(0).add(resultadoF);
            codigo.get(0).add(resultadoF);
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



    public String atom (ArrayList<ArrayList<String>> codigo){
        int size = codigo.size();
        boolean hascons=false;
        boolean haslist=false;
        for(int i=0;i<size;i++){
            for(int j = 0; j<codigo.get(i).size();j++){
                if(codigo.get(i).get(j).contains("atom")){
                    if (codigo.get(i-1).get(j-1).contains("cons")||codigo.get(i-1).get(j-1).contains("list")){
                        System.out.println("false");
                        return "false";
                    }else{
                        System.out.println("true");
                        return "true";
                    }
                } 
            }
        }
        System.out.println("false");
        return "false";		
    }

    
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

    public String cond(ArrayList<ArrayList<String>> listado){
		String s = "";
		int operador = 0;
		int t = 0;
		for(int i=0; i<listado.size();i++){
			if(listado.get(i).contains("cond")){
				
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
		
		return "Fallo funcion cond";
        }
}


