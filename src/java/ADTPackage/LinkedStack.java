package ADTPackage;
import java.util.EmptyStackException;
/**
   A class of stacks whose entries are stored in a chain of nodes.
   
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 5.0
*/
public final class LinkedStack<T> implements StackInterface<T>
{
	private Node topNode; // References the first node in the chain

	/**
	 * Constructor for Linked Stack.
	 */
	public LinkedStack()
	{
		topNode = null;
	} // end default constructor

	/** {@inheritDoc} */
	public void push(T newEntry)
	{
		Node newNode = new Node(newEntry, topNode);
		topNode = newNode;
//    topNode = new Node(newEntry, topNode); // Alternate code
	} // end push

	/** {@inheritDoc} */
	public T peek()
	{
		if (isEmpty())
			throw new EmptyStackException();
		else
         return topNode.getData();
	} // end peek

	/** {@inheritDoc} */
	public T pop()
	{
	   T top = peek();  // Might throw EmptyStackException
   // Assertion: topNode != null
      topNode = topNode.getNextNode();

	   return top;
	} // end pop

/*
// Question 1, Chapter 6: Does not call peek 
	public T pop()
	{
      if (isEmpty())
         throw new EmptyStackException();
      else
		{
         assert (topNode != null);
			top = topNode.getData();
			topNode = topNode.getNextNode();
		} // end if
		
		return top;
	} // end pop
*/

	/** {@inheritDoc} */
	public boolean isEmpty()
	{
		return topNode == null;
	} // end isEmpty

	/** {@inheritDoc} */
	public void clear()
	{
		topNode = null;  // Causes deallocation of nodes in the chain
	} // end clear

	/**
	 * Node class which contains data and next Node.
	 */
	private class Node
	{
      private T    data; // Entry in stack
      private Node next; // Link to next node

		/**
		 * Constuctor for Node with data portion.
 		 * @param dataPortion data portion.
		 */
		private Node(T dataPortion)
      {
         this(dataPortion, null);
      } // end constructor

		/**
		 * Constructor for Node with data portion and next Node.
 		 * @param dataPortion data portion.
		 * @param linkPortion link portion.
		 */
      private Node(T dataPortion, Node linkPortion)
      {
         data = dataPortion;
         next = linkPortion;	
      } // end constructor

		/**
		 * Get data for Node.
 		 * @return data for Node.
		 */
		private T getData()
      {
         return data;
      } // end getData

		/**
		 * Set data for Node.
 		 * @param newData new data for Node.
		 */
      private void setData(T newData)
      {
         data = newData;
      } // end setData

		/**
		 * Getting the next Node.
 		 * @return the next Node.
		 */
      private Node getNextNode()
      {
         return next;
      } // end getNextNode

		/**
		 * Setting the next Node.
 		 * @param nextNode the next Node.
		 */
      private void setNextNode(Node nextNode)
      {
         next = nextNode;
      } // end setNextNode
	} // end Node
} // end LinkedStack
