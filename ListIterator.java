/*
 * An interface for an iterator that iterates over a List.
 */
public interface ListIterator 
{
	// Check if the iterator has additional items to iterate
	boolean hasNext();
	
	// Return a reference to the next Object in the iteration
	Object next();

}
