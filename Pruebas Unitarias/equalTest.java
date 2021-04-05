import java.util.ArrayList;
import junit.framework.TestCase;

public class equalTest extends TestCase {

	Funciones func = new Funciones();
	ArrayList<ArrayList<String>> codigo2 = new ArrayList<ArrayList<String>>();
	ArrayList<String> codigo1 = new ArrayList<String>();

	
	private void convertirArrayList() {
		codigo1.add("("); 
		codigo1.add("esto");
		codigo1.add("es");
		codigo1.add("una");
		codigo1.add("prueba");
		codigo1.add(")");
		codigo2.add(codigo1);
		codigo1.clear();
		
		codigo1.add("(");
		codigo1.add("otra");
		codigo1.add("prueba");
		codigo2.add(codigo1);
		codigo1.clear();
		
		codigo1.add("(");
		codigo1.add("equal");
		codigo1.add(")");
		codigo2.add(codigo1);
		codigo1.clear();
	}
		
	public void testEqual() {
		convertirArrayList();	
		assertEquals("false", func.equal(codigo2));
	}
}

		
