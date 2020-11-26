package api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DWGraph_DSTest {

    @Test
    void getNode() {
        directed_weighted_graph graph = new DWGraph_DS();
        node_data node = new NodeData(2);
        node_data node1 = new NodeData(10);
        node_data node2 = new NodeData(20);
        node_data node3 = new NodeData(22);
        node_data node4 = new NodeData(3);
        assertNull(graph.getNode(node.getKey()));
        graph.addNode(node);
        assertEquals(graph.getNode(node.getKey()),node);
        assertNull(graph.getNode(node1.getKey()));
        graph.addNode(node1);
        assertEquals(graph.getNode(node1.getKey()),node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);
        assertEquals(graph.getNode(node.getKey()),node);
        assertEquals(graph.getNode(node1.getKey()),node1);
        assertEquals(graph.getNode(node2.getKey()),node2);
        assertEquals(graph.getNode(node3.getKey()),node3);
        assertEquals(graph.getNode(node4.getKey()),node4);


    }

    @Test
    void getEdge() {
        directed_weighted_graph graph = new DWGraph_DS();
        node_data node0 = new NodeData(0);
        node_data node1 = new NodeData(1);
        node_data node2 = new NodeData(2);
        node_data node3 = new NodeData(3);
        node_data node4 = new NodeData(4);
        node_data node5 = new NodeData(5);
        assertNull(graph.getEdge(node0.getKey(),node1.getKey()));
        graph.connect(node0.getKey(),node1.getKey(),20);
        edge_data edge = graph.getEdge(node0.getKey(),node1.getKey());
        assertTrue(edge.getSrc() == node0.getKey());
        assertTrue(edge.getDest() == node1.getKey());
        assertTrue(edge.getSrc() == node0.getKey());

    }

    @Test
    void addNode() {
        directed_weighted_graph graph = new DWGraph_DS();
        node_data node;
        int countnodes = 10000;
        for(int i = 0; i < countnodes; i++)
        {
            node = new NodeData(i);
            graph.addNode(node);
        }
        assertTrue(graph.nodeSize() == countnodes);
        node = new NodeData(20000000);
        graph.addNode(node);
        assertTrue(graph.nodeSize() == (countnodes+1));
    }

    @Test
    void connect() {
        directed_weighted_graph graph = new DWGraph_DS();
        node_data node1 = new NodeData(1);
        node_data node2 = new NodeData(2);
        graph.connect(node1.getKey(),node2.getKey(),50);
        edge_data edge = graph.getEdge(node1.getKey(),node2.getKey());
        System.out.println(edge.getWeight());

    }

    @Test
    void getV() {
    }

    @Test
    void getE() {
    }

    @Test
    void removeNode() {
    }

    @Test
    void removeEdge() {
    }

    @Test
    void nodeSize() {
    }

    @Test
    void edgeSize() {
    }

    @Test
    void getMC() {
    }
}