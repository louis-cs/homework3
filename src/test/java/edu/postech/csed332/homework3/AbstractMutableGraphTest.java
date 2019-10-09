package edu.postech.csed332.homework3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Set;

/**
 * An abstract test class for MutableGraph with blackbox test methods
 *
 * @param <V> type of vertices
 * @param <G> type of Graph
 */
@Disabled
public abstract class AbstractMutableGraphTest<V extends Comparable<V>, G extends MutableGraph<V>> {

    G graph;
    V v1, v2, v3, v4, v5, v6, v7, v8;

    abstract boolean checkInv();    // call checkInv of graph

    @Test
    void testAddVertex() {
        Assertions.assertTrue(graph.addVertex(v1));
        Assertions.assertTrue(graph.containsVertex(v1));
        Assertions.assertTrue(checkInv());
    }

    @Test
    void testAddDuplicateVertices() {
        Assertions.assertTrue(graph.addVertex(v6));
        Assertions.assertTrue(graph.addVertex(v7));
        Assertions.assertFalse(graph.addVertex(v6));
        Assertions.assertTrue(graph.containsVertex(v6));
        Assertions.assertTrue(graph.containsVertex(v7));
        Assertions.assertTrue(checkInv());
    }

    @Test
    void testFindReachableVertices() {
        graph.addEdge(v1, v1);
        graph.addEdge(v1, v2);
        graph.addEdge(v3, v1);
        graph.addEdge(v3, v2);
        graph.addVertex(v4);

        Assertions.assertEquals(graph.findReachableVertices(v1), Set.of(v1, v2));
        Assertions.assertEquals(graph.findReachableVertices(v2), Set.of(v2));
        Assertions.assertEquals(graph.findReachableVertices(v3), Set.of(v1, v2, v3));
        Assertions.assertEquals(graph.findReachableVertices(v4), Set.of(v4));
        Assertions.assertEquals(graph.findReachableVertices(v5), Collections.emptySet());
        Assertions.assertTrue(checkInv());
    }

    // TODO: write black-box test cases for each method of MutableGraph with respect to
    //  the specification, including the methods of Graph that MutableGraph extends.

    @Test
    void testContainsVertexTrue(){
        graph.addVertex(v1);

        Assertions.assertTrue(graph.containsVertex(v1));
    }

    @Test
    void testContainsVertexFalse(){
        graph.addVertex(v1);

        Assertions.assertFalse(graph.containsVertex(v2));
    }

    @Test
    void testContainsEdgeTrue(){
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addEdge(v1,v2);

        Assertions.assertTrue(graph.containsEdge(v1,v2));
    }

    @Test
    void testContainsEdgeFalse(){
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addEdge(v1,v2);

        Assertions.assertFalse(graph.containsEdge(v1,v3));
    }

    @Test
    void testGetSourcesMultiple(){
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addEdge(v1,v3);
        graph.addEdge(v2,v3);

        Assertions.assertEquals(graph.getSources(v3).size(), 2);
    }

    @Test
    void testGetSourcesNotFound(){
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addEdge(v1,v3);
        graph.addEdge(v2,v3);

        Assertions.assertTrue(graph.getSources(v4).isEmpty());
    }

    @Test
    void testGetSourcesNoParent(){
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addEdge(v1,v3);
        graph.addEdge(v2,v3);

        Assertions.assertTrue(graph.getSources(v1).isEmpty());
    }

    @Test
    void testGetTargetsMultiple(){
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addEdge(v1,v3);
        graph.addEdge(v1,v3);

        Assertions.assertEquals(graph.getTargets(v1).size(), 2);
    }

    @Test
    void testGetTargetsNotFound(){
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addEdge(v1,v3);
        graph.addEdge(v1,v3);

        Assertions.assertTrue(graph.getTargets(v4).isEmpty());
    }

    @Test
    void testGetTargetsNoChildren(){
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addEdge(v1,v3);
        graph.addEdge(v1,v3);

        Assertions.assertTrue(graph.getTargets(v2).isEmpty());
    }

    @Test
    void testGetVerticesMultiple(){
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addEdge(v1,v3);

        Assertions.assertEquals(graph.getVertices().size(), 3);
    }

    @Test
    void testGetVerticesEmpty(){

        Assertions.assertTrue(graph.getVertices().isEmpty());
    }

    @Test
    void testGetEdgesMultiple(){
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addEdge(v1,v3);
        graph.addEdge(v1,v3);
        graph.addEdge(v2,v3);

        Assertions.assertEquals(graph.getEdges().size(), 3);
    }

    @Test
    void testGetEdgesEmpty(){

        Assertions.assertTrue(graph.getEdges().isEmpty());
    }

}
