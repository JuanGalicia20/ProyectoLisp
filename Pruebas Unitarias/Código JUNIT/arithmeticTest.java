import java.util.ArrayList;

import org.junit.Test;

import junit.framework.TestCase;

public class arithmeticTest extends TestCase{

	Arithmetic ar = new Arithmetic();

    @Test
    public void testCalcultateSuma() {
        ArrayList<String> lista = new ArrayList<>();
        lista.add("(");
        lista.add("+");
        lista.add("1");
        lista.add("2");
        lista.add(")");

        assertEquals(3.0, ar.calculateArithmetic(lista));
    }
    
    @Test
    public void testCalcultateResta() {
        ArrayList<String> lista = new ArrayList<>();
        lista.add("(");
        lista.add("-");
        lista.add("1");
        lista.add("2");
        lista.add(")");

        assertEquals(-1.0, ar.calculateArithmetic(lista));
    }
    
    @Test
    public void testCalcultateMultiplicacion() {
        ArrayList<String> lista = new ArrayList<>();
        lista.add("(");
        lista.add("*");
        lista.add("1");
        lista.add("2");
        lista.add(")");

        assertEquals(2.0, ar.calculateArithmetic(lista));
    }
    
    @Test
    public void testCalcultateDivision() {
        ArrayList<String> lista = new ArrayList<>();
        lista.add("(");
        lista.add("/");
        lista.add("2");
        lista.add("2");
        lista.add(")");

        assertEquals(1.0, ar.calculateArithmetic(lista));
    }
  
}