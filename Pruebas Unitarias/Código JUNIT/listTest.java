import java.util.ArrayList;
import junit.framework.TestCase;

public class listTest extends TestCase {

	Funciones func = new Funciones();
	ArrayList<ArrayList<String>> codigo2 = new ArrayList<ArrayList<String>>();
	ArrayList<String> codigo1 = new ArrayList<String>();
	ArrayList<String> listaFinal = new ArrayList<String>();

	
	private void convertirArrayList() {
				
		codigo1.add("(");
		codigo1.add("list");
		codigo1.add("piña"); 
		codigo1.add("melón"); 
		codigo1.add("papaya"); 
		codigo1.add(")");
		codigo2.add(codigo1);
	}
	
	private ArrayList<String> convListaFinal() {
	
		listaFinal.add("piña"); 
		listaFinal.add("melón"); 
		listaFinal.add("papaya"); 
		System.out.println(listaFinal);
		return listaFinal;
	}
		
	public void testList() {
		convertirArrayList();
		assertEquals(convListaFinal(), func.listLisp(codigo2));
	}

}