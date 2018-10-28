import java.util.*;

/**
 * LinkedList is a singly linked list that implements the List interface.
 * A dummy head node is included to avoid special cases for insertion and
 * deletion at the front of the list.
 * 
 * @author HIENDANG
 * 
 *@version February 28th 2017
 *
 */

public class LinkedList implements List
{
	/**
	 * Inner class
	 * CNode is used so that the LinkedList methods can access the instance
	 * variables of the nodes.
	 * item has an Object type parameter
	 * CNode has next pointer
	 */
	private class CNode
	{
		private Object item;
		private CNode next;
		
		private CNode (Object i, CNode n){
			item = i;
			next = n;
		}
	}
	// Dummy head node
	private CNode head;
	
	// Number of items in the list
	private int size;
	
	/**
	 * Constructor for the LinkedList object
	 */
	public LinkedList()
	{
		head = new CNode (null, null);
		size = 0;
	}
	
	/**
	 * This method is a private helper method that returns a reference
	 * to the node at position i in the linked list.
	 * @param i
	 * the node at position i in the linked list
	 * @precondition
	 *   i is valid
	 * @postcondition
	 *   i == -1 will return reference to the dummy head node
	 */
	private CNode getNode(int i)
	{
		CNode temp = head;
		int tempIndex = -1;
		while (tempIndex < i)
		{
			tempIndex++;
			temp = temp.next;
		}
		return temp;
	}
	/**
	 * This method returns the item at position i in the list
	 * @param i
	 *   the ith position of the item in the list
	 * @exception IndexOutOfBoundsException
	 *   indicates that i is less than 0 or greater than or 
	 *   equal to the length of the list
	 * @return
	 *   the item at position i in the list
	 */
	public Object getItem(int i)
	{
		if (i < 0 || i >= size)
			throw new IndexOutOfBoundsException();
		CNode n = getNode(i);
		return n.item;
	}
	
	/**
	 * This method adds the specified item at position i in the list,
	 * shifting the items that are currently in ith position to the right by one
	 * @param item
	 * @param i
	 * @exception IndexOutOfBoundsException
	 *   indicates that i is less than 0 or greater than or 
	 *   equal to the length of the list
	 * @return
	 *   always return true since the list is never full
	 */
	public boolean addItem(Object item, int i)
	{
		if (i < 0 || i > size)
			throw new IndexOutOfBoundsException();
		CNode tempNode = new CNode(item, null);
		CNode previousNode = getNode(i -1);
		tempNode.next = previousNode.next;
		previousNode.next = tempNode;
		
		size++;
		return true;
	}
	
	/**
	 * This method deletes the item at position i in the list,
	 * shifting the items that are currently in ith position to the left by one
	 * @param i
	 * @exception IndexOutOfBoundsException
	 *   indicates that i is less than 0 or greater than or
	 *   equal to the length of the list
	 * @return
	 *   a reference to the deleted object.
	 */
	public Object deleteItem(int i)
	{
		if (i < 0 || i >= size)
			throw new IndexOutOfBoundsException();
		
		CNode previousNode = getNode(i - 1);
		Object deleted = previousNode.next.item;
		previousNode.next = previousNode.next.next;
		
		size--;
		return deleted;
	}
	
	/**
	 * This method returns the number of items in the list
	 */
	public int size(){
		return size;
	}
	
	/**
	 * This method always returns false since the linked list can never be full
	 */
	public boolean isFull(){
		return false;
	}
	
	/**
	 * This method converts the linked list into a String form format
	 */
	public String toString()
	{
		String string = "{";
		
		// Skip the dummy head node
		CNode temp = head.next;
		while (temp != null)
		{
			string = string + temp.item;
			if (temp.next != null)
				string = string + ", ";
			temp = temp.next;
		}
		string = string + "}";
		return string;
	}
	
	/**
	 * This method returns an iterator for this list
	 */
	public ListIterator iterator()
	{
		return new LinkedListIterator();
	}
	
	/**
	 * Inner class for an iterator over this LinkedList
	 */
	private class LinkedListIterator implements ListIterator
	{
		// The next node to iterate
		private CNode nextNode;
		// The most recently iterated node
		private CNode lastIteratedNode;
		
		// Constructor
		public LinkedListIterator()
		{
			nextNode = head.next;
			lastIteratedNode = null;
		}
		
		/**
		 * This method checks if the iterator has additional items to iterate
		 */
		public boolean hasNext()
		{
			return (nextNode != null);
		}
		
		/**
		 * This method returns a reference to the next Object in the iteration
		 * @exception NoSuchElementException
		 *   indicates that the reference of the next node is pointed to null
		 */
		public Object next()
		{
			if (nextNode == null)
				throw new NoSuchElementException();
			Object item = nextNode.item;
			lastIteratedNode = nextNode;
			nextNode = nextNode.next;
			return item;
		}
	}

}
