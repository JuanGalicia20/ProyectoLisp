import java.util.ArrayList;
import junit.framework.TestCase;

public class equalTest extends TestCase {

	Funciones func = new Funciones();
	ArrayList<ArrayList<String>> codigo2 = new ArrayList<ArrayList<String>>();
	ArrayList<String> codigo1 = new ArrayList<String>();

	
	private void convertirArrayList() {
		codigo1.add("(");
		codigo1.add("equal");
		codigo1.add("3");
		codigo1.add("3"); 
		codigo1.add(")");
		codigo2.add(codigo1);
	}
		
	public void testEqual() {
		convertirArrayList();	
		assertEquals("false", func.equal(codigo2));
	}
}

		
