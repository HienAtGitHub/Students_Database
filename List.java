/**
 * An interface that defines a simple ADT for a list of objects of any types.
 */
public interface List 
{
	/**
	 * This method returns the item at position i in the list.
	 */
	Object getItem(int i);
	
	/**
	 * This method adds the specified item at position i in the list,
	 * shifting the items that are currently in ith position to the right by one
	 */
	boolean addItem(Object item, int i);
	
	/**
	 * This method deleted the item at position i in the list,
	 * shifting the items at are currently in ith position to the left by one
	 */
	Object deleteItem(int i);
	
	// Return the number of items in the list
	int size();
	
	// Return true if the list is full, and false otherwise
	boolean isFull();
	
	// Return an iterator object for this list
	ListIterator iterator();
}
