import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Test;
import junit.framework.TestCase;

public class listValueTest extends TestCase{

    Funciones func = new Funciones();

    @Test
    public void testListValue() {
        ArrayList<String> lista = new ArrayList<>();
        lista.add("(");
        lista.add("first");
        lista.add("pr1");
        lista.add(")");
        assertEquals("1", func.ListValue(lista));
    }
}