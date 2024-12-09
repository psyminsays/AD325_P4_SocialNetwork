package ADTPackage;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Creating a class for an unsorted linked dictionary.
 *
 * @param <K> data type for the key.
 * @param <V> data type for the value.
 */
public class UnsortedLinkedDictionary<K, V> implements DictionaryInterface<K, V>
{
    private Node firstNode;   // Reference to first node of chain
    private int  numberOfEntries;

    /**
     * Constructor for the unsorted link dictionary.
     */
    public UnsortedLinkedDictionary()
    {
        initializeDataFields();
    } // end default constructor

    /** {@inheritDoc} */
    public V add(K key, V value)
    {
        if ((key == null) || (value == null))
            throw new IllegalArgumentException("Cannot add null to a dictionary.");
        else
        {
            V result = null;

            // Search chain for a node containing key
            Node currentNode = firstNode;
            while ( (currentNode != null) && !key.equals(currentNode.getKey()) )
            {
                currentNode = currentNode.getNextNode();
            } // end while

            if (currentNode == null)
            {
                // Key not in dictionary; add new node at beginning of chain
                Node newNode = new Node(key, value);
                newNode.setNextNode(firstNode);
                firstNode = newNode;
                numberOfEntries++;
            }
            else
            {
                // Key in dictionary; replace corresponding value
                result = currentNode.getValue();
                currentNode.setValue(value); // Replace value
            } // end if

            return result;
        } // end if
    } // end add

    /** {@inheritDoc} */
    public V remove(K key)
    {
        V result = null;  // Return value

        if (!isEmpty())
        {
            // Search chain for a node containing key;
            // save reference to preceding node
            Node currentNode = firstNode;
            Node nodeBefore = null;

            while ( (currentNode != null) && !key.equals(currentNode.getKey()) )
            {
                nodeBefore = currentNode;
                currentNode = currentNode.getNextNode();
            } // end while

            if (currentNode != null)
            {
                // node found; remove it
                Node nodeAfter = currentNode.getNextNode();  // Node after the one to be removed

                if (nodeBefore == null)
                    firstNode = nodeAfter;
                else
                    nodeBefore.setNextNode(nodeAfter);        // Disconnect the node to be removed

                result = currentNode.getValue();             // Get ready to return removed entry
                numberOfEntries--;                            // Decrease length for both cases
            } // end if
        } // end if

        return result;
    } // end remove

    /** {@inheritDoc} */
    public V getValue(K key)
    {
        V result = null;

        // Search for a node that contains key
        Node currentNode = firstNode;

        while ( (currentNode != null) && !key.equals(currentNode.getKey()) )
        {
            currentNode = currentNode.getNextNode();
        } // end while

        if (currentNode != null)
        {
            // Search key found
            result = currentNode.getValue();
        } // end if

        return result;
    } // end getValue

    /** {@inheritDoc} */
    public boolean contains(K key)
    {
        return getValue(key) != null;
    } // end contains

    /** {@inheritDoc} */
    public boolean isEmpty()
    {
        return numberOfEntries == 0;
    } // end isEmpty

    /** {@inheritDoc} */
    public int getSize()
    {
        return numberOfEntries;
    } // end getSize

    /** {@inheritDoc} */
    public final void clear()
    {
        initializeDataFields();
    } // end clear

    /** {@inheritDoc} */
    public Iterator<K> getKeyIterator()
    {
        return new KeyIterator();
    } // end getKeyIterator

    /** {@inheritDoc} */
    public Iterator<V> getValueIterator()
    {
        return new ValueIterator();
    } // end getValueIterator

    /**
     * Initializes the class's data fields to indicate an empty list.
     */
    private void initializeDataFields()
    {
        firstNode = null;
        numberOfEntries = 0;
    } // end initializeDataFields

    // Same as in SortedLinkedDictionary.
    // Since iterators implement Iterator, methods must be public.

    /**
     * Iterator that iterates over the keys.
     */
    private class KeyIterator implements Iterator<K>
    {
        private Node nextNode;

        /**
         * Constructor for key iterator.
         */
        private KeyIterator()
        {
            nextNode = firstNode;
        } // end default constructor

        /**
         * Checking if there is a next key.
         * @return true if there is a next key, false if not.
         */
        public boolean hasNext()
        {
            return nextNode != null;
        } // end hasNext

        /**
         * Returning the next key.
         * @return the next key.
         * @throws NoSuchElementException if there is no next key,
         */
        public K next()
        {
            K result;

            if (hasNext())
            {
                result = nextNode.getKey();
                nextNode = nextNode.getNextNode();
            }
            else
            {
                throw new NoSuchElementException();
            } // end if

            return result;
        } // end next

        /**
         * Remove the key.
         * @throws UnsupportedOperationException if called.
         */
        public void remove()
        {
            throw new UnsupportedOperationException();
        } // end remove
    } // end KeyIterator

    /**
     * Iterator for values.
     *
     */
    private class ValueIterator implements Iterator<V>
    {
        private Node nextNode;

        /**
         * Constructor for value iterator.
         */
        private ValueIterator()
        {
            nextNode = firstNode;
        } // end default constructor

        /**
         * Checking if there is a next value.
         * @return true if has next value, otherwise false.
         */
        public boolean hasNext()
        {
            return nextNode != null;
        } // end hasNext

        /**
         * Getting the next value.
         * @return the next value.
         * @throws NoSuchElementException if there is not a next value.
         */
        public V next()
        {
            V result;

            if (hasNext())
            {
                result = nextNode.getValue();
                nextNode = nextNode.getNextNode();
            }
            else
            {
                throw new NoSuchElementException();
            } // end if

            return result;
        } // end next

        /**
         * Removing the value.
         * @throws UnsupportedOperationException if called.
         */
        public void remove()
        {
            throw new UnsupportedOperationException();
        } // end remove
    } // end getValueIterator

    /**
     * Implementing a Node with key, value, and next Node.
     */
    private class Node
    {
        private K key;
        private V value;
        private Node next;

        /**
         * Constructor for Node.
         * @param searchKey the search key.
         * @param dataValue the data value.
         */
        private Node(K searchKey, V dataValue)
        {
            key = searchKey;
            value = dataValue;
            next = null;
        } // end constructor

        /**
         * Constructor containing the next Node.
         * @param searchKey the search key.
         * @param dataValue the data value.
         * @param nextNode the next Node.
         */
        private Node(K searchKey, V dataValue, Node nextNode)
        {
            key = searchKey;
            value = dataValue;
            next = nextNode;
        } // end constructor

        /**
         * Getting the key.
         * @return the key.
         */
        private K getKey()
        {
            return key;
        } // end getKey

        /**
         * Getting the value.
         * @return the value.
         */
        private V getValue()
        {
            return value;
        } // end getValue

        /**
         * Setting the value.
         * @param newValue the new value.
         */
        private void setValue(V newValue)
        {
            value = newValue;
        } // end setValue

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
} // end UnsortedLinkedDictionary


