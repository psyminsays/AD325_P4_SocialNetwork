package GraphPackage;

import ADTPackage.LinkedListWithIterator;
import ADTPackage.ListWithIteratorInterface;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implements a vertex.
 *
 * @param <T> The data type for the vertex label.
 */
class Vertex<T> implements VertexInterface<T>
{
    private T label;
    private ListWithIteratorInterface<Edge> edgeList; // Edges to neighbors
    private boolean visited;                          // True if visited
    private VertexInterface<T> previousVertex;        // On path to this vertex
    private double cost;                              // Of path to this vertex

    /**
     * Constructor.
     *
     * @param vertexLabel The label for the vertex.
     */
    public Vertex(T vertexLabel)
    {
        label = vertexLabel;
        edgeList = new LinkedListWithIterator<>();
        visited = false;
        previousVertex = null;
        cost = 0;
    } // end constructor

    /**
     * Gets the label of the vertex.
     *
     * @return The label of the vertex.
     */
    public T getLabel()
    {
        return label;
    } // end getLabel

    /**
     * Checks if the vertex has a predecessor (previous vertex).
     *
     * @return True if the vertex has a predecessor, otherwise false.
     */
    public boolean hasPredecessor()
    {
        return previousVertex != null;
    } // end hasPredecessor

    /**
     * Sets the predecessor (previous vertex).
     *
     * @param predecessor  The predecessor (previous vertex).
     */
    public void setPredecessor(VertexInterface<T> predecessor)
    {
        previousVertex = predecessor;
    } // end setPredecessor

    /**
     * Gets the predecessor (previous vertex).
     *
     * @return The predecessor (previous vertex).
     */
    public VertexInterface<T> getPredecessor()
    {
        return previousVertex;
    } // end getPredecessor

    /** Mark the vertex as visited. */
    public void visit()
    {
        visited = true;
    } // end visit

    /** Mark the vertex as unvisited. */
    public void unvisit()
    {
        visited = false;
    } // end unvisit

    /**
     * Checks if the vertex is visited.
     *
     * @return True if the vertex is visited, otherwise false.
     */
    public boolean isVisited()
    {
        return visited;
    } // end isVisited

    /**
     * Gets the cost of the path to this vertex.
     *
     * @return The cost of the path to this vertex.
     */
    public double getCost()
    {
        return cost;
    } // end getCost

    /**
     * Sets the cost of the path to this vertex.
     *
     * @param newCost  The cost of the path to this vertex.
     */
    public void setCost(double newCost)
    {
        cost = newCost;
    } // end setCost

    /** {@inheritDoc} */
    public String toString()
    {
        return label.toString();
    } // end toString

    /** Implements a weight iterator. */
    private class WeightIterator implements Iterator<Double>
    {
        private Iterator<Edge> edges;

        /** Constructor. */
        private WeightIterator()
        {
            edges = edgeList.getIterator();
        } // end default constructor

        /**
         * Checks if there is a next edge.
         *
         * @return True if there is a next edge, otherwise false.
         */
        public boolean hasNext()
        {
            return edges.hasNext();
        } // end hasNext

        /**
         * Gets the next edge weight.
         *
         * @return The next edge weight.
         * @throws NoSuchElementException if there is no next edge weight.
         */
        public Double next()
        {
            Double edgeWeight = 0.0;
            if (edges.hasNext())
            {
                Edge edgeToNextNeighbor = edges.next();
                edgeWeight = edgeToNextNeighbor.getWeight();
            }
            else
                throw new NoSuchElementException();

            return edgeWeight;
        } // end next

        /**
         * Removes the weight.
         *
         * @throws UnsupportedOperationException if called.
         */
        public void remove()
        {
            throw new UnsupportedOperationException();
        } // end remove
    } // end WeightIterator

    /**
     * Gets the weight iterator.
     *
     * @return A new weight iterator object.
     */
    public Iterator<Double> getWeightIterator()
    {
        return new WeightIterator();
    } // end getWeightIterator

    /**
     * Connects the vertex to the provided end vertex using the provided weight.
     *
     * @param endVertex   The end vertex to connect to.
     * @param edgeWeight  The weight of the edge.
     * @return            True if the vertices were connected, otherwise false.
     */
    public boolean connect(VertexInterface<T> endVertex, double edgeWeight)
    {
        boolean result = false;

        if (!this.equals(endVertex))
        {  // Vertices are distinct
            Iterator<VertexInterface<T>> neighbors = getNeighborIterator();
            boolean duplicateEdge = false;

            while (!duplicateEdge && neighbors.hasNext())
            {
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (endVertex.equals(nextNeighbor))
                    duplicateEdge = true;
            } // end while

            if (!duplicateEdge)
            {
                edgeList.add(new Edge(endVertex, edgeWeight));
                result = true;
            } // end if
        } // end if

        return result;
    } // end connect

    /**
     * Connects the vertex to the provided end vertex using weight 0.
     *
     * @param endVertex   The end vertex to connect to.
     * @return            True if the vertices were connected, otherwise false.
     */
    public boolean connect(VertexInterface<T> endVertex)
    {
        return connect(endVertex, 0);
    } // end connect

    /** {@inheritDoc} */
    public boolean disconnect(VertexInterface<T> endVertex) {
        // If the vertex is not the same vertex as the end vertex
        if (!this.equals(endVertex)) {
            // Iterates through the edges list, starting at 1 instead of 0
            // because edgeList is a ListWithIteratorInterface
            // which starts the entry at 1
            for (int i = 1; i <= edgeList.getLength(); i++) {
                // If the edge's end vertex is equal to the provided end vertex
                if (edgeList.getEntry(i).getEndVertex() == endVertex) {
                    // Remove it and return true
                    edgeList.remove(i);
                    return true;
                }
            }
        }
        // Otherwise return false
        return false;
    }

    /**
     * Gets a neighbor iterator.
     *
     * @return A neighbor iterator object.
     */
    public Iterator<VertexInterface<T>> getNeighborIterator()
    {
        return new NeighborIterator();
    } // end getNeighborIterator

    /**
     * Checks if the vertex has a neighbor.
     *
     * @return True if the vertex has a neighbor, otherwise false.
     */
    public boolean hasNeighbor()
    {
        return !edgeList.isEmpty();
    } // end hasNeighbor

    /**
     * Gets an unvisited neighbor.
     *
     * @return The unvisited neighbor.
     */
    public VertexInterface<T> getUnvisitedNeighbor()
    {
        VertexInterface<T> result = null;

        Iterator<VertexInterface<T>> neighbors = getNeighborIterator();
        while ( neighbors.hasNext() && (result == null) )
        {
            VertexInterface<T> nextNeighbor = neighbors.next();
            if (!nextNeighbor.isVisited())
                result = nextNeighbor;
        } // end while

        return result;
    } // end getUnvisitedNeighbor

    /**
     * Checks if the vertex is equal to the provided object.
     *
     * @param other The other object.
     * @return      True if equal, otherwise false.
     */
    public boolean equals(Object other)
    {
        boolean result;

        if ((other == null) || (getClass() != other.getClass()))
            result = false;
        else
        {
            // The cast is safe within this else clause
            @SuppressWarnings("unchecked")
            Vertex<T> otherVertex = (Vertex<T>)other;
            result = label.equals(otherVertex.label);
        } // end if

        return result;
    } // end equals

    /** Implements a vertex neighbor iterator. */
    private class NeighborIterator implements Iterator<VertexInterface<T>>
    {
        private Iterator<Edge> edges;

        /** Constructor. */
        private NeighborIterator()
        {
            edges = edgeList.getIterator();
        } // end default constructor

        /**
         * Checks if there is a next neighbor vertex.
         *
         * @return True if there is a next neighbor vertex, otherwise false.
         */
        public boolean hasNext()
        {
            return edges.hasNext();
        } // end hasNext

        /**
         * Gets the next neighbor vertex.
         *
         * @return the next neighbor vertex.
         * @throws NoSuchElementException if there is no next neighbor.
         */
        public VertexInterface<T> next()
        {
            VertexInterface<T> nextNeighbor = null;

            if (edges.hasNext())
            {
                Edge edgeToNextNeighbor = edges.next();
                nextNeighbor = edgeToNextNeighbor.getEndVertex();
            }
            else
                throw new NoSuchElementException();

            return nextNeighbor;
        } // end next

        /**
         * Removes the neighbor vertex.
         *
         * @throws UnsupportedOperationException if called.
         */
        public void remove()
        {
            throw new UnsupportedOperationException();
        } // end remove
    } // end NeighborIterator

    /** Implements an edge. */
    protected class Edge
    {
        private VertexInterface<T> vertex; // Vertex at end of edge
        private double weight;

        /**
         * Constructor with provided end vertex and edge weight.
         *
         * @param endVertex  The vertex at the end of the edge.
         * @param edgeWeight The weight of the edge.
         */
        protected Edge(VertexInterface<T> endVertex, double edgeWeight)
        {
            vertex = endVertex;
            weight = edgeWeight;
        } // end constructor

        /**
         * Constructor with provided end vertex and weight of 0.
         *
         * @param endVertex The vertex at the end of the edge.
         */
        protected Edge(VertexInterface<T> endVertex)
        {
            vertex = endVertex;
            weight = 0;
        } // end constructor

        /**
         * Gets vertex at the end of the edge.
         *
         * @return The vertex at the end of the edge.
         */
        protected VertexInterface<T> getEndVertex()
        {
            return vertex;
        } // end getEndVertex

        /**
         * Gets the weight of the edge.
         *
         * @return The weight of the edge.
         */
        protected double getWeight()
        {
            return weight;
        } // end getWeight
    } // end Edge

    /** Displays the vertex. */
    public void display()
    {
        System.out.print(label + " " );
        Iterator<VertexInterface<T>> i = getNeighborIterator();
        Iterator<Double> w = getWeightIterator();

        while (i.hasNext())
        {
            Vertex<T> v = (Vertex<T>)i.next();
            System.out.print(v + " " + w.next() + " ");
        } // end while

        System.out.println();
    } // end display
} // end Vertex