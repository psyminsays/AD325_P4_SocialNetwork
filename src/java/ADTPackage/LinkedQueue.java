package ADTPackage;
/**
 A class that implements the ADT queue by using a chain of nodes
 that has both head and tail references.

 @author Frank M. Carrano
 @version 4.0
 */
public class LinkedQueue<T> implements QueueInterface<T>
{
   private Node firstNode; // references node at front of queue
   private Node lastNode;  // references node at back of queue

   /**
    * Constructor for Linked Queue.
    */
   public LinkedQueue()
   {
      firstNode = null;
      lastNode = null;
   } // end default constructor

   /** {@inheritDoc} */
   public void enqueue(T newEntry)
   {
      Node newNode = new Node(newEntry, null);

      if (isEmpty())
         firstNode = newNode;
      else
         lastNode.setNextNode(newNode);

      lastNode = newNode;
   } // end enqueue

   /** {@inheritDoc} */
   public T getFront()
   {
      T front = null;

      if (!isEmpty())
         front = firstNode.getData();

      return front;
   } // end getFront

   /** {@inheritDoc} */
   public T dequeue()
   {
      T front = null;

      if (!isEmpty())
      {
         front = firstNode.getData();
         firstNode = firstNode.getNextNode();

         if (firstNode == null)
            lastNode = null;
      } // end if

      return front;
   } // end dequeue

   /** {@inheritDoc} */
   public boolean isEmpty()
   {
      return (firstNode == null) && (lastNode == null);
   } // end isEmpty

   /** {@inheritDoc} */
   public void clear()
   {
      firstNode = null;
      lastNode = null;
   } // end clear

   /**
    * Node with data and next Node.
    */
   private class Node
   {
      private T    data; // entry in queue
      private Node next; // link to next node

      /**
       * Constructor for Node with data portion.
       * @param dataPortion data portion for Node.
       */
      private Node(T dataPortion)
      {
         data = dataPortion;
         next = null;
      } // end constructor

      /**
       * Constructor for Node with data portion and next Node,
       * @param dataPortion data portion for Node.
       * @param linkPortion next Node.
       */
      private Node(T dataPortion, Node linkPortion)
      {
         data = dataPortion;
         next = linkPortion;
      } // end constructor

      /**
       * Get data for the Node.
       * @return the data for the Node.
       */
      private T getData()
      {
         return data;
      } // end getData

      /**
       * Set data for the Node.
       * @param newData new data for the Node.
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
} // end Linkedqueue