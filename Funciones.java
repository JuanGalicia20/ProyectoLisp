import java.util.*;

public class Funciones {

    //atributos
    private LinkedHashMap <String, ArrayList<String>> listadoVariables;

    //Constructor
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

    /**
     * Metodo quote
     * @param ArrayList<ArrayList<String>> codigo
     * @return String
    */
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
                    newList.add(codigo.get(i).get(j)); //crea un array para conservar resultado    
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

        return finalString;        
    
    }


    /** 
     * Metodo listLisp 
     * @param ArrayList<ArrayList<String>> codigo 
     * @return <ArrayList<String>
    */

    public ArrayList<String> listLisp (ArrayList<ArrayList<String>> codigo){
        ArrayList<String> newList = new ArrayList<String>();
        boolean hasList = false;

        for(int i=0;i<codigo.size();i++){
            System.out.println(codigo.get(i));
            if(codigo.get(i).contains("list")||codigo.get(i).contains(" list")){
                hasList = true;
            }
            
        }

        if (hasList){
            for(int i=0;i<codigo.size();i++){
                for(int j = 0; j< (codigo.get(i).size()-1);j++)
                {
                    newList.add(codigo.get(i).get(j)); //crea un array para conservar resultado    
                }
            }

            
            newList.remove(new String("list"));
            newList.remove(new String("("));
            newList.remove(new String(")"));
            
        }

        else{
            newList.add("No es list");
        }

        
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
        return valor;
    }


    /**
     * Metodo setQ
     * @param ArrayList<ArrayList<String>> codigo
     * @return String 
    */
     public String setQ (ArrayList<ArrayList<String>> codigo){
        ArrayList<String> newList = new ArrayList<String>();
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
                        newList.add(codigo.get(i).get(j)); //crea un array para conservar resultado    
                    }
                }
    
                variable = newList.get(pos +1);
    
                System.out.println(codigo);
                newList.remove(new String("setq"));
                newList.remove (new String(variable));
                newList.remove(new String("("));
                newList.remove(new String(")"));
                System.out.println("NEW LIST ACA"+newList);
                ArrayList<ArrayList<String>> lista= new ArrayList<>();
                lista.add(newList);

                valor2 = listLisp(lista);
                System.out.println("______"+valor2);
                listadoVariables.put(variable, valor2);
            }
            else{
                for(int i=0;i<codigo.size();i++){
                    for(int j = 0; j< (codigo.get(i).size());j++)
                    {
                        newList.add(codigo.get(i).get(j)); //crea un array para conservar resultado    
                    }
                }
    
                variable = newList.get(pos +1);
                System.out.println("(::"+variable+"::)");
    
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
                System.out.println("______"+valor2);
                listadoVariables.put(variable, valor2);
            }
        }

        else{
            newList.add("ERROR..., No se ha podido conservar la variable");
        }

        System.out.println(variable + "-->>" + valor2);
        codigo = new ArrayList<ArrayList<String>>();
        return variable + "-->>" + valor2;    
    }   
    


    public ArrayList<ArrayList<String>> BuscarVar(ArrayList<ArrayList<String>> codigo)
    {
        for(String element : listadoVariables.keySet())
        {
            for(int i = 0; i < codigo.size(); i++) {
                for(int j =0; j<codigo.get(i).size();j++)
                {
                    if(element.equals(codigo.get(i).get(j)))
                    {
                        for(String a: listadoVariables.get(element))
                        {
                            codigo.get(i).set(j,a);
                            System.out.println(a);
                        }
                    }
                }
            }
        }
        
        System.out.println(codigo);
        return codigo;
    }


    
    /**
     * Metodo Atom
     * @param ArrayList<ArrayList<String>> codigo
     * @return boolean 
     */

    public String atom (ArrayList<ArrayList<String>> codigo){
        int size = codigo.size();
        for(int i=0;i<size;i++){
            for(int j = 0; j<codigo.get(i).size();j++){
                if(codigo.get(i).get(j).contains("atom")){
                    try {
                        if (codigo.get(i-1).get(j-1).contains("cons")||codigo.get(i-1).get(j-1).contains("list")){
                            return "false";
                        }else{
                            return "true";
                        }

                    }catch (Exception e){
                        return "true";
                    }
                } 
            }
        }return "false";
				
    }

    /**
     * Metodo equal
     * @param Arraylist<Arraylist<String>> codigo
     * @return String
     */ 
    
    public String  equal (ArrayList<ArrayList<String>> codigo){
        int pos = 0; 
        String result = "false";

        
        for(int i=0;i<codigo.size();i++){ //recorre el array hasta encontrar un equals
            for(int j = 0; j<codigo.get(i).size();j++){
                System.out.println(codigo.get(i).get(j));
                if (codigo.get(i).get(j).equals("equal") || codigo.get(i).get(i).equals( " equal")){ 
                    pos = j; //conserva la posicion del equals
                    if(codigo.get(i).size()<5){ //evalúa dos parametros de otras listas
                        if(codigo.get(i).get(pos+1).equals(codigo.get(i).get(pos+2))){
                            System.out.println(codigo.get(i).get(pos+1));
                            System.out.println(codigo.get(i).get(pos+2));
                            result = "true";
                            
                        }
                    }else if(codigo.get(i).size() == 5){
                        if(codigo.get(i).get(pos+1).equals(codigo.get(i).get(pos+2))){
                            return "true";
                        }
                    }
                }
            }
        } return result;
    }
    

    /**
     * Metodo mayorMenor
     * @param Arraylist<Arraylist<String>> codigo
     * @return String
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
        }return estado;       
    }

    
    
}
