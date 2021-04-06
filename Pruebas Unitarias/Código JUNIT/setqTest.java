import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import junit.framework.TestCase;

public class setqTest extends TestCase{

	Funciones func = new Funciones();
	ArrayList<ArrayList<String>> codigo2 = new ArrayList<ArrayList<String>>();
	ArrayList<String> codigo1 = new ArrayList<String>();
	LinkedHashMap <String, ArrayList<String>> miHash = new LinkedHashMap <String, ArrayList<String>>();
	ArrayList<String> valores = new ArrayList<String>();
	

	
	private void convertirArrayList() {
		codigo1.add("(");
		codigo1.add("setq");
		codigo1.add("estudiantes");
		codigo1.add("Elisa"); 
		codigo1.add(")");
		
		codigo2.add(codigo1);

		
	}
	
	private ArrayList<String> crearValores() {
		valores.add("Elisa");
		return valores;
		
	}
		
	private LinkedHashMap <String, ArrayList<String>> llenarHash() {
		crearValores();
        miHash.put("estudiantes", valores);
        return miHash;
	}  
		
	public void testEqual() {
		convertirArrayList();
		assertEquals("estudiantes-->>[Elisa]", func.setQ(codigo2));
		System.out.println(llenarHash());
	}