package GraphPackage;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class VertexTest {

    @Test
    void getLabel() {
        Vertex<Integer> vertex = new Vertex<>(4);
        assertEquals(4, vertex.getLabel());
    }

    @Test
    void hasPredecessor() {
        Vertex<Integer> vertex = new Vertex<>(2);
        Vertex<Integer> vertexPredecessor = new Vertex<>(1);
        vertex.setPredecessor(vertexPredecessor);
        assertTrue(vertex.hasPredecessor());
    }

    @Test
    void setPredecessor() {
        Vertex<Integer> vertex = new Vertex<>(2);
        Vertex<Integer> vertexPredecessor = new Vertex<>(1);
        vertex.setPredecessor(vertexPredecessor);
        assertEquals(vertexPredecessor, vertex.getPredecessor());
    }

    @Test
    void getPredecessor() {
        Vertex<Integer> vertex = new Vertex<>(2);
        assertNull(vertex.getPredecessor());
        Vertex<Integer> vertexPredecessor = new Vertex<>(1);
        vertex.setPredecessor(vertexPredecessor);
        assertEquals(vertexPredecessor, vertex.getPredecessor());
    }

    @Test
    void visit() {
        Vertex<Integer> vertex = new Vertex<>(1);
        vertex.visit();
        assertTrue(vertex.isVisited());
    }

    @Test
    void unvisit() {
        Vertex<Integer> vertex = new Vertex<>(1);
        assertFalse(vertex.isVisited());
        vertex.visit();
        vertex.unvisit();
        assertFalse(vertex.isVisited());
    }

    @Test
    void isVisited() {
        Vertex<Integer> vertex = new Vertex<>(1);
        assertFalse(vertex.isVisited());
        vertex.visit();
        assertTrue(vertex.isVisited());
        vertex.unvisit();
        assertFalse(vertex.isVisited());
    }

    @Test
    void getCost() {
        Vertex<Integer> vertex = new Vertex<>(1);
        assertEquals(0, vertex.getCost());
        vertex.setCost(1);
        assertEquals(1, vertex.getCost());
    }

    @Test
    void setCost() {
        Vertex<Integer> vertex = new Vertex<>(1);
        vertex.setCost(1);
        assertEquals(1, vertex.getCost());
    }

    @Test
    void testToString() {
        Vertex<Integer> vertex = new Vertex<>(1);
        assertEquals("1", vertex.toString());
    }

    @Test
    void getWeightIterator() {
        Vertex<Integer> vertex = new Vertex<>(0);
        Iterator<Double> weightIteratorNoEdges = vertex.getWeightIterator();
        assertFalse(weightIteratorNoEdges.hasNext());
        assertThrows(NoSuchElementException.class, () -> weightIteratorNoEdges.next());
        assertThrows(UnsupportedOperationException.class, () -> weightIteratorNoEdges.remove());

        Vertex<Integer> vertex1 = new Vertex<>(1);
        Vertex<Integer> vertex2 = new Vertex<>(2);
        Vertex<Integer> vertex3 = new Vertex<>(3);
        Vertex<Integer> vertex4 = new Vertex<>(4);
        Vertex<Integer> vertex5 = new Vertex<>(5);
        vertex1.connect(vertex2, 1);
        vertex1.connect(vertex3, 5);
        vertex1.connect(vertex4, 6);
        vertex1.connect(vertex5, 10);
        Iterator<Double> weightIteratorWithEdges = vertex1.getWeightIterator();
        assertTrue(weightIteratorWithEdges.hasNext());
        assertEquals(1, weightIteratorWithEdges.next());
        assertEquals(5, weightIteratorWithEdges.next());
        assertEquals(6, weightIteratorWithEdges.next());
        assertEquals(10, weightIteratorWithEdges.next());
    }

    @Test
    void testConnect() {
        Vertex<Integer> vertex1 = new Vertex<>(1);
        Vertex<Integer> vertex2 = new Vertex<>(2);
        assertFalse(vertex1.connect(vertex1));
        assertTrue(vertex1.connect(vertex2));
        assertFalse(vertex1.connect(vertex2));
    }

    @Test
    void testConnectWithWeight() {
        Vertex<Integer> vertex1 = new Vertex<>(1);
        Vertex<Integer> vertex2 = new Vertex<>(2);
        assertFalse(vertex1.connect(vertex1, 5));
        assertTrue(vertex1.connect(vertex2, 10));
        assertFalse(vertex1.connect(vertex2, 3));
        Iterator<Double> weightIterator = vertex1.getWeightIterator();
        assertEquals(10, weightIterator.next());
    }

    @Test
    void testDisconnect() {
        Vertex<Integer> vertex1 = new Vertex<>(1);
        Vertex<Integer> vertex2 = new Vertex<>(2);
        vertex1.connect(vertex2);
        assertTrue(vertex1.hasNeighbor());
        assertEquals(vertex2, vertex1.getUnvisitedNeighbor());
        assertTrue(vertex1.disconnect(vertex2));
        assertFalse(vertex1.hasNeighbor());
        assertNull(vertex1.getUnvisitedNeighbor());
        assertFalse(vertex1.disconnect(vertex1));
        assertFalse(vertex1.disconnect(vertex2));
    }

    @Test
    void getNeighborIterator() {
        Vertex<Integer> vertex = new Vertex<>(1);
        Iterator<VertexInterface<Integer>> neighborIteratorNoNeighbors = vertex.getNeighborIterator();
        assertFalse(neighborIteratorNoNeighbors.hasNext());
        assertThrows(NoSuchElementException.class, () -> neighborIteratorNoNeighbors.next());
        assertThrows(UnsupportedOperationException.class, () -> neighborIteratorNoNeighbors.remove());

        Vertex<Integer> vertex1 = new Vertex<>(1);
        Vertex<Integer> vertex2 = new Vertex<>(2);
        vertex1.connect(vertex2);
        Vertex<Integer> vertex3 = new Vertex<>(3);
        vertex1.connect(vertex3);

        Iterator<VertexInterface<Integer>> neighborIteratorWithNeighbors = vertex1.getNeighborIterator();
        assertTrue(neighborIteratorWithNeighbors.hasNext());
        assertEquals(vertex2, neighborIteratorWithNeighbors.next());
        assertEquals(vertex3, neighborIteratorWithNeighbors.next());
    }

    @Test
    void hasNeighbor() {
        Vertex<Integer> vertex1 = new Vertex<>(1);
        assertFalse(vertex1.hasNeighbor());

        Vertex<Integer> vertex2 = new Vertex<>(2);
        vertex1.connect(vertex2);
        assertTrue(vertex1.hasNeighbor());
    }

    @Test
    void getUnvisitedNeighbor() {
        Vertex<Integer> vertex1 = new Vertex<>(1);
        assertNull(vertex1.getUnvisitedNeighbor());
        Vertex<Integer> vertex2 = new Vertex<>(2);
        vertex1.connect(vertex2);
        assertEquals(vertex2, vertex1.getUnvisitedNeighbor());
    }

    @Test
    void testEquals() {
        Vertex<Integer> vertex1 = new Vertex<>(1);
        String test = "test";
        assertFalse(vertex1.equals("test"));
        Vertex<Integer> vertex2 = new Vertex<>(1);
        assertTrue(vertex1.equals(vertex2));
    }

    @Test
    void testDisplay() {
        Vertex<Integer> vertex1 = new Vertex<>(1);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream capturedOut = new PrintStream(byteArrayOutputStream);
        System.setOut(capturedOut);

        vertex1.display();
        assertEquals("1 \n", byteArrayOutputStream.toString());

        byteArrayOutputStream.reset();

        Vertex<Integer> vertex2 = new Vertex<>(2);
        Vertex<Integer> vertex3 = new Vertex<>(3);
        vertex1.connect(vertex2, 5);
        vertex1.connect(vertex3);
        vertex1.display();
        assertEquals("1 2 5.0 3 0.0 \n", byteArrayOutputStream.toString());
    }
}