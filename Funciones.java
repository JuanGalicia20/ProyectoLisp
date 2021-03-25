public class Funciones {

    public Funciones(){
    }


    /*
        * Metodo quote
        *@param codigo
        *@return void
    */
    

    public void quote (String codigo){
        String[] letras = codigo.split("");
        String ver = (letras[0] + letras[1] + letras[2] + letras[3] + letras [4] + letras[5]); //verificación por si el usuario lo ingresa con paréntesis
        int size = letras.length; //ver tamaño
        if(ver.equals("(QUOTE") || ver.equals("(quote")){ 
            String  subCodigo= codigo.substring(6,size-1); //imprime la cadena menos el último paréntesis
            System.out.println(subCodigo);
        }
        else if(letras[1].equals("('")){
            String  subCodigo= codigo.substring(2,size-1); //imprime cadena menos último paréntesis
            System.out.println(subCodigo);
        }

        String ver2 = (letras[0] + letras[1] + letras[2] + letras[3] + letras [4] ); //verficiación sin paréntesis

        if(ver.equalsIgnoreCase("quote")){ //verificacion para quote
            String  subCodigo= codigo.substring(5,size);
            System.out.println(subCodigo);
        }
        else if(letras[0].equals("'")){ //verificación para '      
            String  subCodigo= codigo.substring(1,size);
            System.out.println(subCodigo);
        }        
    }


    /*
        * Metodo listLisp
        *@param codigo
        *@return void
    */

    public void listLisp (String codigo){
        String[] letras = codigo.split("");
        String ver = (letras[0] + letras[1] + letras[2] + letras[3] + letras [4] ); //verificación por si el usuario lo ingresa con paréntesis
        int size= letras.length;
        if(ver.equalsIgnoreCase("(list")){ 
            if (letras[5].equals("(")){
                String  subCodigo= codigo.substring(6,size-2); 
                String[] subLetras = subCodigo.split("");
                System.out.println("("+subCodigo+")");

            }
            else{
                String  subCodigo= codigo.substring(5,size-1); 
                String[] subLetras = subCodigo.split("");
                System.out.println("("+subCodigo+")");
            }
        }
    }

      /*
        * Metodo setQ
        *@param codigo
        *@return void
    */

    public void setQ (String codigo){
        String[] letras = codigo.split("");
        String ver = (letras[1] + letras[2] + letras[3] + letras [4]);
        if(ver.equals("SETQ") || ver.equals("SETQ")){
            System.out.println("Declaracion de SETQ valida");
            return codigo;
        }
        else{
            System.out.println("SETQ no valida");
            return codigo;
        }
        
    }
}