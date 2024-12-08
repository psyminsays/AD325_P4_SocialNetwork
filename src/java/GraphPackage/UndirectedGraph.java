package GraphPackage;

import ADTPackage.StackInterface;

/**
 * Implements an undirected graph.
 *
 * @param <T> The data type for the vertex label.
 */
public class UndirectedGraph<T> extends DirectedGraph<T> implements GraphInterface<T>
{
    /** Constructor. */
    public UndirectedGraph()
    {
        super();
    } // end default constructor

    /** {@inheritDoc} */
    public boolean addEdge(T begin, T end, double edgeWeight)
    {
        return super.addEdge(begin, end, edgeWeight) &&
                super.addEdge(end, begin, edgeWeight);
        // Assertion: edge count is twice its correct value due to calling addEdge twice
    } // end addEdge

    /** {@inheritDoc} */
    public boolean addEdge(T begin, T end)
    {
        return this.addEdge(begin, end, 0);
    } // end addEdge

    /** {@inheritDoc} */
    public int getNumberOfEdges()
    {
        return super.getNumberOfEdges() / 2;
    } // end getNumberOfEdges

    /**
     * {@inheritDoc}
     *
     * @throws UnsupportedOperationException if called.
     */
    public StackInterface<T> getTopologicalOrder()
    {
        throw new UnsupportedOperationException("Topological sort is illegal in an undirected graph.");
    } // end getTopologicalOrder
} // end UndirectedGraph