package GraphPackage;

import ADTPackage.LinkedStack;
import ADTPackage.StackInterface;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class UndirectedGraphTest {

    @Test
    void testAddEdge() {
        UndirectedGraph<Integer> undirectedGraph = new UndirectedGraph<>();
        undirectedGraph.addVertex(1);
        undirectedGraph.addVertex(2);
        assertTrue(undirectedGraph.addEdge(1, 2));
        assertFalse(undirectedGraph.addEdge(1, 3));
        assertFalse(undirectedGraph.addEdge(1, 2));
        assertTrue(undirectedGraph.hasEdge(1, 2));
    }

    @Test
    void testAddEdgeWithWeights() {
        UndirectedGraph<Integer> undirectedGraph = new UndirectedGraph<>();
        undirectedGraph.addVertex(1);
        undirectedGraph.addVertex(2);
        undirectedGraph.addVertex(3);
        undirectedGraph.addVertex(4);
        assertTrue(undirectedGraph.addEdge(1, 2, 5));
        assertTrue(undirectedGraph.addEdge(1, 3, 1));
        assertTrue(undirectedGraph.addEdge(2, 4, 10));
        assertTrue(undirectedGraph.addEdge(3, 4, 2));

        assertFalse(undirectedGraph.addEdge(1, 100, 10));
        assertFalse(undirectedGraph.addEdge(1, 2, 10));
        assertTrue(undirectedGraph.hasEdge(1, 2));

        LinkedStack<Integer> stack = new LinkedStack<>();
        assertEquals(3, undirectedGraph.getCheapestPath(1, 4, (StackInterface<Integer>) stack));
    }

    @Test
    void getNumberOfEdges() {
        UndirectedGraph<Integer> undirectedGraph = new UndirectedGraph<>();
        undirectedGraph.addVertex(1);
        assertEquals(0, undirectedGraph.getNumberOfEdges());

        undirectedGraph.addVertex(2);
        undirectedGraph.addEdge(1, 2);
        assertEquals(1, undirectedGraph.getNumberOfEdges());

        undirectedGraph.addVertex(2);
        undirectedGraph.addVertex(3);
        undirectedGraph.addEdge(2, 3);
        assertEquals(2, undirectedGraph.getNumberOfEdges());

        undirectedGraph.addEdge(1, 1);
        undirectedGraph.addEdge(1, 2);
        undirectedGraph.addEdge(2, 3);
        assertEquals(2, undirectedGraph.getNumberOfEdges());
    }

    @Test
    void getTopologicalOrder() {
        UndirectedGraph<Integer> undirectedGraph = new UndirectedGraph<>();
        assertThrows(UnsupportedOperationException.class, () -> undirectedGraph.getTopologicalOrder());
    }
}