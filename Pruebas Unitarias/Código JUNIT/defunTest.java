import java.util.ArrayList;
import junit.framework.TestCase;

public class defunTest extends TestCase {

	Declaracion decl = new Declaracion();
	ArrayList<ArrayList<String>> codigo2 = new ArrayList<ArrayList<String>>();
	ArrayList<String> codigo1 = new ArrayList<String>();

	
	private void convertirArrayList() {
		codigo1.add("(");
		codigo1.add("defun");
		codigo1.add("ftoc");
		codigo1.add("("); 
		codigo1.add("temp");
		codigo1.add(")");
		codigo1.add("(");
		codigo1.add("/");
		codigo1.add("(");
		codigo1.add("-"); 
		codigo1.add("TEMP");
		codigo1.add("32");
		codigo1.add(")");
		codigo1.add("1.8"); 
		codigo1.add(")");
		codigo1.add(")");
	
		
		
		
		//( DEFUN FTOC(TEMP)
			//	 ( / ( - TEMP 32 ) 1.8 ) )
		
		/*(defun area-circle(rad)
				   "Calculates area of a circle with given radius"
				   (terpri)
				   (format t "Radius: ~5f" rad)
				   (format t "~%Area: ~10f" (* 3.141592 rad rad))
				)
				(area-circle 10)*/
		codigo2.add(codigo1);
		System.out.println(codigo2);
	}
		
	public void testDEFUN() {
		convertirArrayList();	
		assertEquals("FTOC", decl.setFuncion(codigo2));
	}

}
