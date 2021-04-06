import java.util.ArrayList;
import java.util.*;

import junit.framework.TestCase;

import java.util.ArrayList;
import junit.framework.TestCase;

public class quoteTest extends TestCase {

	Funciones func = new Funciones();
	ArrayList<ArrayList<String>> codigo2 = new ArrayList<ArrayList<String>>();
	ArrayList<String> codigo1 = new ArrayList<String>();

	
	private void convertirArrayList() {
				
		codigo1.add("(");
		codigo1.add("quote");
		codigo1.add("piña"); 
		codigo1.add("melón"); 
		codigo1.add("papaya"); 
		codigo1.add(")");
		codigo2.add(codigo1);
	}
	
		
	public void testQuote() {
		convertirArrayList();
		assertEquals("( piña melón papaya )", func.quote(codigo2));
	}

}