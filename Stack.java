import java.util.ArrayList;
import java.util.Vector;
/**
 * @author Juan Andres Galicia 20298
 * @author Elisa Samayoa 20710
 * @author Jonathan Espinoza 20022
 * 
 * @version 5-4-2021 
 * 
 *  Implementacion de la interfaz stack
 */
public class Stack<E>
implements IStack<E>
{
	protected Vector<E> data;

	public Stack()
	// post: constructs a new, empty stack
	{
		data = new Vector<E>(10,2);
	}

	
	/** 
	 * @param item
	 */
	public void push(E item)
	// post: the value is added to the stack
	//          will be popped next if no intervening push
	{
		data.add(item);
	}

	
	/** 
	 * @return E
	 */
	public E pop()
	// pre: stack is not empty
	// post: most recently pushed item is removed and returned
	{
		return data.remove(size()-1);
	}

	
	/** 
	 * @return E
	 */
	public E peek()
	// pre: stack is not empty
	// post: top value (next to be popped) is returned
	{
		return data.get(size() - 1);
	}
	
	
	/** 
	 * @return int
	 */
	public int size()
	// post: returns the number of elements in the stack
	{
		return data.size();
	}
 
	
	/** 
	 * @return boolean
	 */
	public boolean empty()
	// post: returns true if and only if the stack is empty
	{
		return size() == 0;
	}
}