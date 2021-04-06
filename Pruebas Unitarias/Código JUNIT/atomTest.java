import java.util.ArrayList;
import junit.framework.TestCase;

public class atomTest extends TestCase{

	Funciones func = new Funciones();
	ArrayList<ArrayList<String>> codigo2 = new ArrayList<ArrayList<String>>();
	ArrayList<String> codigo1 = new ArrayList<String>();

	
	private void convertirArrayList() {
		
		codigo1.add("(");
		codigo1.add("atom");
		codigo1.add("(");
		codigo1.add("123"); 
		codigo1.add(")");
		codigo1.add(")");
		codigo2.add(codigo1);
		System.out.println(codigo2);

	}
		
	public void testAtom() {
		convertirArrayList();	
		assertEquals("true", func.atom(codigo2));
	}
}


		
