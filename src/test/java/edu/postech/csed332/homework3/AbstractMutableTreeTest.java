package edu.postech.csed332.homework3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Set;

/**
 * An abstract test class for MutableTree with blackbox test methods
 *
 * @param <V> type of vertices
 * @param <T> type of Tree
 */
@Disabled
public abstract class AbstractMutableTreeTest<V extends Comparable<V>, T extends MutableTree<V>> {

    T tree;
    V v1, v2, v3, v4, v5, v6, v7, v8;

    abstract boolean checkInv();    // call checkInv of tree

    @Test
    void testGetDepthRoot() {
        tree.addVertex(v1);
        Assertions.assertEquals(tree.getDepth(v1), 0);
    }

    @Test
    void testGetDepthTwo() {
        tree.addVertex(v1);
        tree.addEdge(v1, v2);
        Assertions.assertEquals(tree.getDepth(v2), 1);
    }

    @Test
    void testGetDepthNoRoot() {
        Assertions.assertThrows(IllegalStateException.class, () -> tree.getDepth(v1));
    }

    @Test
    void testGetDepthNoVertex() {
        Assertions.assertTrue(tree.addVertex(v1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> tree.getDepth(v2));
    }

    // TODO: write black-box test cases for each method of MutableTree with respect to
    //  the specification, including the methods of Tree that MutableTree extends.

    @Test
    void testGetHeight(){
        tree.addVertex(v1);
        tree.addEdge(v1, v2);

        Assertions.assertEquals(tree.getHeight(), 1);
    }

    @Test
    void testGetHeightNoRoot(){
        Assertions.assertThrows(IllegalStateException.class, () -> tree.getHeight());
    }

    @Test
    void testGetChildren(){
        tree.addVertex(v1);
        tree.addEdge(v1,v2);
        tree.addEdge(v1,v3);

        Assertions.assertEquals(tree.getChildren(v1).size(), 2);
    }

    @Test
    void testGetChildrenEmpty(){
        tree.addVertex(v1);
        Assertions.assertEquals(tree.getChildren(v1).size(), 0);
    }

    @Test
    void testGetChildrenNotFound(){
        Assertions.assertEquals(tree.getChildren(v1).size(), 0);
    }

    @Test
    void testGetParent(){
        tree.addVertex(v1);
        tree.addEdge(v1,v2);

        Assertions.assertTrue(tree.getParent(v2).isPresent());
    }

    @Test
    void testGetParentEmpty(){
        tree.addVertex(v1);
        Assertions.assertFalse(tree.getParent(v1).isPresent());
    }

    @Test
    void testGetParentNotFound(){
        Assertions.assertFalse(tree.getParent(v1).isPresent());
    }

    @Test
    void testAddVertex(){
        Assertions.assertTrue(tree.addVertex(v1));
        Assertions.assertTrue(tree.containsVertex(v1));
        Assertions.assertTrue(tree.getRoot().isPresent());
        Assertions.assertEquals(tree.getRoot().get(), v1);
    }

    @Test
    void testAddVertexNotEmpty(){
        Assertions.assertTrue(tree.addVertex(v1));
        Assertions.assertTrue(tree.containsVertex(v1));
        Assertions.assertFalse(tree.addVertex(v1));
        Assertions.assertTrue(tree.containsVertex(v1));
        Assertions.assertTrue(tree.getRoot().isPresent());
        Assertions.assertEquals(tree.getRoot().get(), v1);
    }

    @Test
    void testRemoveVertex(){
        tree.addVertex(v1);

        Assertions.assertTrue(tree.removeVertex(v1));
        Assertions.assertFalse(tree.containsVertex(v1));
    }

    @Test
    void testRemoveVertexMultiple(){
        tree.addVertex(v1);
        tree.addEdge(v1,v2);

        Assertions.assertTrue(tree.removeVertex(v1));
        Assertions.assertFalse(tree.containsVertex(v1));
        Assertions.assertFalse(tree.containsVertex(v2));
    }

    @Test
    void testRemoveVertexNotFound(){
        Assertions.assertFalse(tree.removeVertex(v1));
        Assertions.assertFalse(tree.containsVertex(v1));
    }

    @Test
    void testAddEdge(){
        tree.addVertex(v1);

        Assertions.assertTrue(tree.addEdge(v1,v2));
        Assertions.assertTrue(tree.containsVertex(v2));
    }

    @Test
    void testAddEdgeDuplicate(){
        tree.addVertex(v1);

        Assertions.assertTrue(tree.addEdge(v1,v2));
        Assertions.assertTrue(tree.containsVertex(v2));
        Assertions.assertFalse(tree.addEdge(v1,v2));
        Assertions.assertTrue(tree.containsVertex(v2));
    }

    @Test
    void testAddEdgeExistingTarget(){
        tree.addVertex(v1);

        Assertions.assertTrue(tree.addEdge(v1,v2));
        Assertions.assertTrue(tree.containsVertex(v2));
        Assertions.assertTrue(tree.addEdge(v1,v3));
        Assertions.assertTrue(tree.containsVertex(v3));
        Assertions.assertFalse(tree.addEdge(v2,v3));
    }


    @Test
    void testAddEdgeNoSource(){
        Assertions.assertFalse(tree.addEdge(v1,v2));
        Assertions.assertFalse(tree.containsVertex(v1));
        Assertions.assertFalse(tree.containsVertex(v2));
    }

    @Test
    void testRemoveEdge(){
        tree.addVertex(v1);
        tree.addEdge(v1,v2);

        Assertions.assertTrue(tree.removeEdge(v1,v2));
        Assertions.assertFalse(tree.containsEdge(v1,v2));
        Assertions.assertFalse(tree.containsVertex(v2));
    }

    @Test
    void testRemoveEdgeMultiple(){
        tree.addVertex(v1);
        tree.addEdge(v1,v2);
        tree.addEdge(v2,v3);

        Assertions.assertTrue(tree.removeEdge(v1,v2));
        Assertions.assertFalse(tree.containsEdge(v1,v2));
        Assertions.assertFalse(tree.containsVertex(v2));
        Assertions.assertFalse(tree.containsVertex(v3));
    }


    @Test
    void testRemoveEdgeEmpty(){
        Assertions.assertFalse(tree.removeEdge(v1,v2));
    }

    @Test
    void testRemoveEdgeWrongTarget(){
        tree.addVertex(v1);
        tree.addEdge(v1,v2);
        tree.addEdge(v2,v3);

        Assertions.assertFalse(tree.removeEdge(v1,v3));
        Assertions.assertTrue(tree.containsEdge(v1,v2));
        Assertions.assertTrue(tree.containsVertex(v2));
        Assertions.assertTrue(tree.containsVertex(v3));
    }

}
