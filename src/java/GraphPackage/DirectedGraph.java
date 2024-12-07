package GraphPackage;

import ADTPackage.*;

import java.util.Iterator;

/**
 * Implements a directed graph.
 *
 * @author Eric Lloyd
 * @author Aune Mitchell
 * @param <T> The data type for the vertices.
 */
public class DirectedGraph<T> implements GraphInterface<T>
{
    private DictionaryInterface<T, VertexInterface<T>> vertices;
    private int edgeCount;

    /** Default constructor. */
    public DirectedGraph()
    {
        vertices = new UnsortedLinkedDictionary<>();
        edgeCount = 0;
    } // end default constructor

    /** {@inheritDoc} */
    public boolean addVertex(T vertexLabel)
    {
        VertexInterface<T> addOutcome = vertices.add(vertexLabel, new Vertex<>(vertexLabel));
        return addOutcome == null; // Was addition to dictionary successful?
    } // end addVertex

    /**
     * Removes a vertex from the graph.
     *
     * @param vertexLabel The value for the vertex to remove.
     * @return True if the vertex was removed, false if vertex value is not in graph.
     */
    public boolean removeVertex(T vertexLabel) {
        // Gets the vertex to remove's value using its label
        VertexInterface<T> vertexToRemove = vertices.getValue(vertexLabel);

        // If it's null, the vertex does not exist in the graph, so return false
        if (vertexToRemove == null) {
            return false;
        }

        // Get a neighbor iterator for the vertex to be removed
        Iterator<VertexInterface<T>> neighborIterator = vertexToRemove.getNeighborIterator();

        // While there's a neighbor remaining
        while (neighborIterator.hasNext()) {
            // Get the next neighbor
            VertexInterface<T> neighbor = neighborIterator.next();

            // If there's an edge between the vertex to remove and the neighbor,
            // remove the edge
            if (hasEdge(vertexToRemove.getLabel(), neighbor.getLabel())) {
                removeEdge(vertexToRemove.getLabel(), neighbor.getLabel());
            }
        }

        // Get a vertices value iterator for all vertices
        Iterator<VertexInterface<T>> verticesValueInterator = vertices.getValueIterator();

        // While there's a vertex remaining
        while (verticesValueInterator.hasNext()) {
            // Get the next vertex
            VertexInterface<T> vertex = verticesValueInterator.next();

            // Get the neighbor iterator for the current vertex
            Iterator<VertexInterface<T>> currentNeighborIterator = vertex.getNeighborIterator();

            // While there's a neighbor remaining for the current vertex
            while (currentNeighborIterator.hasNext()) {
                // Get the next neighbor
                VertexInterface<T> neighbor = currentNeighborIterator.next();

                // If the neighbor is the vertex to remove
                if (neighbor.equals(vertexToRemove)) {
                    // Remove the edge between the current vertex and the neighbor
                    removeEdge(vertex.getLabel(), neighbor.getLabel());
                }
            }
        }

        // Remove the vertex from the dictionary of vertices and return true
        vertices.remove(vertexLabel);
        return true;
    }

    /** {@inheritDoc} */
    public boolean addEdge(T begin, T end, double edgeWeight)
    {
        boolean result = false;
        VertexInterface<T> beginVertex = vertices.getValue(begin);
        VertexInterface<T> endVertex = vertices.getValue(end);
        if ( (beginVertex != null) && (endVertex != null) )
            result = beginVertex.connect(endVertex, edgeWeight);
        if (result)
            edgeCount++;
        return result;
    } // end addEdge

    /** {@inheritDoc} */
    public boolean addEdge(T begin, T end)
    {
        return addEdge(begin, end, 0);
    } // end addEdge

    /**
     * Removes an edge between two provided vertices.
     *
     * @param begin The starting vertex for the edge.
     * @param end   The ending vertex for the edge.
     * @return      True if the edge was removed, false otherwise (i.e. edge doesn't exist).
     */
    public boolean removeEdge(T begin, T end) {
        // Assume a false result
        boolean result = false;

        // Get the starting and ending vertices
        VertexInterface<T> beginVertex = vertices.getValue(begin);
        VertexInterface<T> endVertex = vertices.getValue(end);

        // If there is an edge, disconnect it
        if (hasEdge(begin, end)) {
            result = beginVertex.disconnect(endVertex);
        }

        // If the edge was removed, decrement count of edges
        if (result)
            edgeCount--;

        // Return the result
        return result;
    }

    /** {@inheritDoc} */
    public boolean hasEdge(T begin, T end)
    {
        boolean found = false;
        VertexInterface<T> beginVertex = vertices.getValue(begin);
        VertexInterface<T> endVertex = vertices.getValue(end);
        if ( (beginVertex != null) && (endVertex != null) )
        {
            Iterator<VertexInterface<T>> neighbors = beginVertex.getNeighborIterator();
            while (!found && neighbors.hasNext())
            {
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (endVertex.equals(nextNeighbor))
                    found = true;
            } // end while
        } // end if

        return found;
    } // end hasEdge

    /** {@inheritDoc} */
    public boolean isEmpty()
    {
        return vertices.isEmpty();
    } // end isEmpty

    /** {@inheritDoc} */
    public void clear()
    {
        vertices.clear();
        edgeCount = 0;
    } // end clear

    /** {@inheritDoc} */
    public int getNumberOfVertices()
    {
        return vertices.getSize();
    } // end getNumberOfVertices

    /** {@inheritDoc} */
    public int getNumberOfEdges()
    {
        return edgeCount;
    } // end getNumberOfEdges

    /** Resets the vertices. */
    protected void resetVertices()
    {
        Iterator<VertexInterface<T>> vertexIterator = vertices.getValueIterator();
        while (vertexIterator.hasNext())
        {
            VertexInterface<T> nextVertex = vertexIterator.next();
            nextVertex.unvisit();
            nextVertex.setCost(0);
            nextVertex.setPredecessor(null);
        } // end while
    } // end resetVertices

    /** {@inheritDoc} */
    public QueueInterface<T> getBreadthFirstTraversal(T origin)
    {
        // Reset the vertices
        resetVertices();

        // Queue of vertices labels to represent the traversal order
        QueueInterface<T> traversalOrder = new LinkedQueue<>();

        // Queue of visited vertices
        QueueInterface<VertexInterface<T>> visitedQueue = new LinkedQueue<>();

        // Get the origin vertex from the provided value
        VertexInterface<T> originVertex = vertices.getValue(origin);

        // If the original vertex exists
        if (originVertex != null) {

            // Visit the original vertex
            originVertex.visit();

            // Enqueue the label to the traversal order queue
            traversalOrder.enqueue(origin);

            // Enqueue the vertex to the visited queue
            visitedQueue.enqueue(originVertex);

            // While the visited queue isn't empty
            while (!visitedQueue.isEmpty()) {
                // Dequeue the next vertex
                VertexInterface<T> vertex = visitedQueue.dequeue();

                // While the vertex has unvisited neighbors
                while (vertex.getUnvisitedNeighbor() != null) {
                    // Get the neighbor vertex
                    VertexInterface<T> neighbor = vertex.getUnvisitedNeighbor();
                    // If the neighbor isn't null and isn't visited
                    if (!(neighbor == null) && !neighbor.isVisited()) {
                        // Visit the neighbor vertex
                        neighbor.visit();
                        // Enqueue the vertex label to the traversal order queue
                        traversalOrder.enqueue(neighbor.getLabel());
                        // Enqueue the vertex to the visited queue
                        visitedQueue.enqueue(neighbor);
                    }
                }
            }
        }

        // Return the traversal order
        return traversalOrder;
    } // end getBreadthFirstTraversal

    /** {@inheritDoc} */
    public QueueInterface<T> getDepthFirstTraversal(T origin)
    {
        // Assumes graph is not empty
        resetVertices();
        QueueInterface<T> traversalOrder = new LinkedQueue<T>();
        StackInterface<VertexInterface<T>> vertexStack = new LinkedStack<>();

        VertexInterface<T> originVertex = vertices.getValue(origin);
        originVertex.visit();
        traversalOrder.enqueue(origin); // Enqueue vertex label
        vertexStack.push(originVertex); // Enqueue vertex

        while (!vertexStack.isEmpty())
        {
            VertexInterface<T> topVertex = vertexStack.peek();
            VertexInterface<T> nextNeighbor = topVertex.getUnvisitedNeighbor();

            if (nextNeighbor != null)
            {
                nextNeighbor.visit();
                traversalOrder.enqueue(nextNeighbor.getLabel());
                vertexStack.push(nextNeighbor);
            }
            else // All neighbors are visited
                vertexStack.pop();
        } // end while

        return traversalOrder;
    } // end getDepthFirstTraversal

    /** {@inheritDoc} */
    public StackInterface<T> getTopologicalOrder()
    {
        resetVertices();

        StackInterface<T> vertexStack = new LinkedStack<>();
        int numberOfVertices = getNumberOfVertices();
        for (int counter = 1; counter <= numberOfVertices; counter++)
        {
            VertexInterface<T> nextVertex = findTerminal();
            nextVertex.visit();
            vertexStack.push(nextVertex.getLabel());
        } // end for

        return vertexStack;
    } // end getTopologicalOrder

    /** {@inheritDoc} */
    public int getShortestPath(T begin, T end, StackInterface<T> path)
    {
        resetVertices();
        boolean done = false;
        QueueInterface<VertexInterface<T>> vertexQueue = new LinkedQueue<>();
        VertexInterface<T> originVertex = vertices.getValue(begin);
        VertexInterface<T> endVertex = vertices.getValue(end);
        originVertex.visit();

        // Assertion: resetVertices() has executed setCost(0)
        // and setPredecessor(null) for originVertex

        vertexQueue.enqueue(originVertex);
        while (!done && !vertexQueue.isEmpty())
        {
            VertexInterface<T> frontVertex = vertexQueue.dequeue();
            Iterator<VertexInterface<T>> neighbors = frontVertex.getNeighborIterator();
            while (!done && neighbors.hasNext())
            {
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (!nextNeighbor.isVisited())
                {
                    nextNeighbor.visit();
                    nextNeighbor.setCost(1 + frontVertex.getCost());
                    nextNeighbor.setPredecessor(frontVertex);
                    vertexQueue.enqueue(nextNeighbor);
                } // end if

                if (nextNeighbor.equals(endVertex))
                    done = true;
            } // end while
        } // end while

        // Traversal ends; construct shortest path
        int pathLength = (int)endVertex.getCost();
        path.push(endVertex.getLabel());

        VertexInterface<T> vertex = endVertex;
        while (vertex.hasPredecessor())
        {
            vertex = vertex.getPredecessor();
            path.push(vertex.getLabel());
        } // end while

        return pathLength;
    } // end getShortestPath

    // Exercise 15, Chapter 29
    /**
     * {@inheritDoc}
     *
     * Precondition: path is an empty stack (NOT null)
     */
    public double getCheapestPath(T begin, T end, StackInterface<T> path) // STUDENT EXERCISE
    {
        resetVertices();
        boolean done = false;

        // Use EntryPQ instead of Vertex because multiple entries contain
        // the same vertex but different costs - cost of path to vertex is EntryPQ's priority value
        PriorityQueueInterface<EntryPQ> priorityQueue = new HeapPriorityQueue<>();

        VertexInterface<T> originVertex = vertices.getValue(begin);
        VertexInterface<T> endVertex = vertices.getValue(end);

        priorityQueue.add(new EntryPQ(originVertex, 0, null));

        while (!done && !priorityQueue.isEmpty())
        {
            EntryPQ frontEntry = priorityQueue.remove();
            VertexInterface<T> frontVertex = frontEntry.getVertex();

            if (!frontVertex.isVisited())
            {
                frontVertex.visit();
                frontVertex.setCost(frontEntry.getCost());
                frontVertex.setPredecessor(frontEntry.getPredecessor());

                if (frontVertex.equals(endVertex))
                    done = true;
                else
                {
                    Iterator<VertexInterface<T>> neighbors = frontVertex.getNeighborIterator();
                    Iterator<Double> edgeWeights = frontVertex.getWeightIterator();
                    while (neighbors.hasNext())
                    {
                        VertexInterface<T> nextNeighbor = neighbors.next();
                        Double weightOfEdgeToNeighbor = edgeWeights.next();

                        if (!nextNeighbor.isVisited())
                        {
                            double nextCost = weightOfEdgeToNeighbor + frontVertex.getCost();
                            priorityQueue.add(new EntryPQ(nextNeighbor, nextCost, frontVertex));
                        } // end if
                    } // end while
                } // end if
            } // end if
        } // end while

        // Traversal ends, construct cheapest path
        double pathCost = endVertex.getCost();
        path.push(endVertex.getLabel());

        VertexInterface<T> vertex = endVertex;
        while (vertex.hasPredecessor())
        {
            vertex = vertex.getPredecessor();
            path.push(vertex.getLabel());
        } // end while

        return pathCost;
    } // end getCheapestPath

    /**
     * Finds the last vertex.
     *
     * @return The last vertex.
     */
    protected VertexInterface<T> findTerminal()
    {
        boolean found = false;
        VertexInterface<T> result = null;

        Iterator<VertexInterface<T>> vertexIterator = vertices.getValueIterator();

        while (!found && vertexIterator.hasNext())
        {
            VertexInterface<T> nextVertex = vertexIterator.next();

            // If nextVertex is unvisited AND has only visited neighbors)
            if (!nextVertex.isVisited())
            {
                if (nextVertex.getUnvisitedNeighbor() == null )
                {
                    found = true;
                    result = nextVertex;
                } // end if
            } // end if
        } // end while

        return result;
    } // end findTerminal

    /** Displays the edges for the vertices. */
    public void displayEdges()
    {
        System.out.println("\nEdges exist from the first vertex in each line to the other vertices in the line.");
        System.out.println("(Edge weights are given; weights are zero for unweighted graphs):\n");
        Iterator<VertexInterface<T>> vertexIterator = vertices.getValueIterator();
        while (vertexIterator.hasNext())
        {
            ((Vertex<T>)(vertexIterator.next())).display();
        } // end while
    } // end displayEdges

    /** Implements a priority queue entry for vertices. */
    private class EntryPQ implements Comparable<EntryPQ>
    {
        private VertexInterface<T> vertex;
        private VertexInterface<T> previousVertex;
        private double cost; // cost to nextVertex

        /**
         * Constructor.
         *
         * @param vertex         The vertex.
         * @param cost           The cost to the next vertex.
         * @param previousVertex The previous vertex.
         */
        private EntryPQ(VertexInterface<T> vertex, double cost, VertexInterface<T> previousVertex)
        {
            this.vertex = vertex;
            this.previousVertex = previousVertex;
            this.cost = cost;
        } // end constructor

        /**
         * Gets the vertex.
         *
         * @return The vertex.
         */
        public VertexInterface<T> getVertex()
        {
            return vertex;
        } // end getVertex

        /**
         * Gets the previous vertex.
         *
         * @return The previous vertex.
         */
        public VertexInterface<T> getPredecessor()
        {
            return previousVertex;
        } // end getPredecessor

        /**
         * Gets the cost to the next vertex.
         *
         * @return The cost to the next vertex.
         */
        public double getCost()
        {
            return cost;
        } // end getCost

        /**
         * Compares priority queue entries.
         *
         * @param otherEntry the object to be compared.
         * @return 1 if otherEntry's cost is greater than this cost,
         *         -1 if otherEntry's cost is less than this cost,
         *         or 0 if the costs are equal.
         */
        public int compareTo(EntryPQ otherEntry)
        {
            // Using opposite of reality since our priority queue uses a maxHeap;
            // could revise using a minheap
            return (int)Math.signum(otherEntry.cost - cost);
        } // end compareTo

        /** {@inheritDoc} */
        public String toString()
        {
            return vertex.toString() + " " + cost;
        } // end toString
    } // end EntryPQ
} // end DirectedGraph
