import java.util.ArrayList;
import junit.framework.TestCase;

public class mayorMenorTest extends TestCase{

	Funciones func = new Funciones();
	ArrayList<ArrayList<String>> codigo2 = new ArrayList<ArrayList<String>>();
	ArrayList<String> codigo1 = new ArrayList<String>();
	ArrayList<ArrayList<String>> codigo4 = new ArrayList<ArrayList<String>>();
	ArrayList<String> codigo3 = new ArrayList<String>();
	ArrayList<String> listaFinal = new ArrayList<String>();

	
	private void convertirArrayList1() {
				
		codigo1.add("(");
		codigo1.add("<");
		codigo1.add("1"); 
		codigo1.add("2"); 
		codigo1.add(")");
		codigo2.add(codigo1);
	}
	
	private void convertirArrayList2() {
		
		codigo3.add("(");
		codigo3.add(">");
		codigo3.add("1"); 
		codigo3.add("2"); 
		codigo3.add(")");
		codigo4.add(codigo3);
	}
	
		
	public void testMenor() {
		convertirArrayList1();
		assertEquals("true", func.mayorMenor(codigo2));
	}
	
	public void testMayor() {
		convertirArrayList2();
		assertEquals("false", func.mayorMenor(codigo4));
	}
	
	

}