package GraphPackage;

import ADTPackage.LinkedStack;
import ADTPackage.QueueInterface;
import ADTPackage.StackInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DirectedGraphTest {
    private DirectedGraph<String> graph;

    @BeforeEach
    void setUp() {
        graph = new DirectedGraph<>();
    }


    @Test
    void addVertex() {
        assertTrue(graph.addVertex("A"));
        assertTrue(graph.addVertex("B"));
        assertTrue(graph.addVertex("C"));
        assertFalse(graph.addVertex("C"));
    }

    @Test
    void removeVertex() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");
        graph.addEdge("B", "A");

        assertTrue(graph.removeVertex("A"));
        assertFalse(graph.removeVertex("C"));
        assertFalse(graph.hasEdge("A", "B"));
        assertFalse(graph.hasEdge("B", "A"));


        // Verify that "A" is removed and "B" remains
        assertEquals(1, graph.getNumberOfVertices());
    }

    @Test
    void addEdge() {
        graph.addVertex("A");
        graph.addVertex("B");

        assertTrue(graph.addEdge("A", "B", 5.0)); // Successfully adds edge A -> B with weight 5.0
        assertTrue(graph.hasEdge("A", "B")); // Edge should exist
        assertFalse(graph.addEdge("A", "B", 5.0)); // Edge already exists
    }

    @Test
    void testAddEdge() {
        graph.addVertex("A");
        graph.addVertex("B");

        assertTrue(graph.addEdge("A", "B")); // Adds edge with default weight (0)
        assertFalse(graph.hasEdge("A", "C"));
    }

    @Test
    void removeEdge() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");

        assertTrue(graph.removeEdge("A", "B"));  // Successfully removes edge A -> B
        assertFalse(graph.hasEdge("A", "B"));   // Edge should no longer exist

        assertFalse(graph.removeEdge("A", "C"));  // Edge "A" -> "C" doesn't exist, should return false
    }

    @Test
    void hasEdge() {
        graph.addVertex("A");
        graph.addVertex("B");

        assertFalse(graph.hasEdge("A", "B")); // No edge yet
        graph.addEdge("A", "B", 10.0);
        assertTrue(graph.hasEdge("A", "B"));  // Edge should now exist
    }

    @Test
    void isEmpty() {
        assertTrue(graph.isEmpty());  // Graph should be empty initially

        graph.addVertex("A");
        assertFalse(graph.isEmpty()); // Graph should not be empty after adding a vertex
    }

    @Test
    void clear() {
        graph.addVertex("A");
        graph.addVertex("B");

        graph.clear();
        assertTrue(graph.isEmpty());  // Graph should be empty after clear
        assertEquals(0, graph.getNumberOfVertices());  // No vertices should remain
    }

    @Test
    void getNumberOfVertices() {

        assertEquals(0, graph.getNumberOfVertices());
        graph.addVertex("A");
        graph.addVertex("B");

        assertEquals(2, graph.getNumberOfVertices());
    }

    @Test
    void getNumberOfEdges() {
        graph.addVertex("A");
        graph.addVertex("B");

        assertEquals(0, graph.getNumberOfEdges()); // No edges initially

        graph.addEdge("A", "B");
        assertEquals(1, graph.getNumberOfEdges()); // One edge should now exist
    }

    @Test
    void getBreadthFirstTraversal() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addVertex("D");
        graph.addEdge("A", "D");

        QueueInterface<String> bfsOrder = graph.getBreadthFirstTraversal("A");

        assertEquals("A", bfsOrder.dequeue());
        assertEquals("B", bfsOrder.dequeue());
        assertEquals("D", bfsOrder.dequeue());
        assertEquals("C", bfsOrder.dequeue());
    }

    @Test
    void getDepthFirstTraversal() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addVertex("D");
        graph.addEdge("A", "D");

        QueueInterface<String> dfsOrder = graph.getDepthFirstTraversal("A");

        assertEquals("A", dfsOrder.dequeue());
        assertEquals("B", dfsOrder.dequeue());
        assertEquals("C", dfsOrder.dequeue());
        assertEquals("D", dfsOrder.dequeue());
    }

    @Test
    void getTopologicalOrder() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");
        graph.addEdge("F", "A");
        graph.addEdge("E", "A");
        graph.addEdge("E", "B");
        graph.addEdge("D", "B");
        graph.addEdge("F", "C");
        graph.addEdge("C", "D");

        StackInterface<String> topoOrder = graph.getTopologicalOrder();

        assertEquals("E",topoOrder.pop());
        assertEquals("F",topoOrder.pop());
        assertEquals("A",topoOrder.pop());
        assertEquals("C",topoOrder.pop());
        assertEquals("D",topoOrder.pop());
        assertEquals("B",topoOrder.pop());
    }

    @Test
    void getShortestPath() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "E");
        graph.addEdge("E", "F");
        graph.addEdge("A", "D");
        graph.addEdge("D", "F");

        StackInterface<String> path = new LinkedStack<>();
        int pathLength = graph.getShortestPath("A", "F", path);

        assertEquals(2, pathLength);  // Path length should be 2 (A -> B -> C)
    }

    @Test
    void getCheapestPath() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");
        graph.addEdge("A", "B", 5.0);
        graph.addEdge("B", "C", 3.0);
        graph.addEdge("C", "F", 2.0);
        graph.addEdge("A", "D", 1.0);
        graph.addEdge("D", "F", 2.0);

        StackInterface<String> path = new LinkedStack<>();
        double pathCost = graph.getCheapestPath("A", "F", path);

        assertEquals(3.0, pathCost, 0.001);

    }

    @Test
    void displayEdges() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");

        graph.displayEdges();  // Manually inspect the output to ensure correct display of edges
    }
}