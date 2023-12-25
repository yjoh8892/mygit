// --== CS400 File Header Information ==--
// Name: Yoo Jin Oh
// Email: yoh56@wisc.edu
// Team: JC
// TA: Harper
// Lecturer: Gary Dahl

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests the implementation of CS400Graph for the individual component of
 * Project Three: the implementation of Dijsktra's Shortest Path algorithm.
 */
public class GraphTest {

    private CS400Graph<Integer> graph;
    private CS400Graph<Integer> graph1;
    private CS400Graph<Integer> graph2;
    @BeforeEach
    /**
     * Instantiate graph from last week's shortest path activity.
     */
    public void createGraph() {
        graph = new CS400Graph<>();
        // insert verticies 0-9
        for(int i=0;i<10;i++)
            graph.insertVertex(i);
        // insert edges from Week 08. Dijkstra's Activity
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,7,2);
        graph.insertEdge(1,8,4);
        graph.insertEdge(2,4,4);
        graph.insertEdge(2,6,3);
        graph.insertEdge(3,1,6);
        graph.insertEdge(3,7,2);
        graph.insertEdge(4,5,4);
        graph.insertEdge(5,0,2);
        graph.insertEdge(5,1,4);
        graph.insertEdge(5,9,1);
        graph.insertEdge(6,3,1);
        graph.insertEdge(7,0,3);
        graph.insertEdge(7,6,1);
        graph.insertEdge(8,9,3);
        graph.insertEdge(9,4,5);


        graph1 = new CS400Graph<>();
        // insert verticies 0-9
        for(int i=0;i<10;i++)
            graph1.insertVertex(i);

        for(int i=1; i<10;i++) {
            graph1.insertEdge(0,i,i);
        }


        graph2 = new CS400Graph<>();
        // insert verticies 0-9
        for(int i=0;i<10;i++)
            graph2.insertVertex(i);

        for(int i=1; i<10;i++) {
            graph2.insertEdge(i-1,i,1);
        }
    }

    /**
     * Checks the distance/total weight cost from the vertex labelled 0 to 8
     * (should be 15), and from the vertex labelled 9 to 8 (should be 17).
     */
    @Test
    public void providedTestToCheckPathCosts() {
        assertTrue(graph.getPathCost(0,8) == 15);    
        assertTrue(graph.getPathCost(9,8) == 17);
    }

    /**
     * Checks the ordered sequence of data within vertices from the vertex 
     * labelled 0 to 8, and from the vertex labelled 9 to 8.
     */
    @Test
    public void providedTestToCheckPathContents() {
        assertTrue(graph.shortestPath(0, 8).toString().equals(
            "[0, 2, 6, 3, 1, 8]"
        ));
        assertTrue(graph.shortestPath(9, 8).toString().equals(
            "[9, 4, 5, 1, 8]"
        ));
    }

    /**
     * Checks the distance/total weight cost from the vertex to the same vertex is relevant:
     * Path must contain only one element and have 0 distance.
     */
    @Test
    public void testEmptyPath() {
        assertTrue(graph.shortestPath(0, 0).toString().equals(
                "[0]"
        ));
        assertTrue(graph.getPathCost(0,0) == 0);


        assertTrue(graph.shortestPath(9, 9).toString().equals(
                "[9]"
        ));
        assertTrue(graph.getPathCost(9,9) == 0);
    }

    /**
     * Checks the distance/total weight cost from the for star graph,
     * where all target paths contain two vertices, and length numerically equal to number of target vertex.
     */
    @Test
    public void testStarGraph() {
        for(int i=1; i<10;i++) {
            assertTrue(graph1.shortestPath(0, i).toString().equals(
                    "[0, " + i + "]"
            ));

            assertTrue(graph1.getPathCost(0,i) == i);
        }
    }

    /**
     * Checks the distance/total weight cost from the for chain graph,
     * where all target path to i-th vertex contains (i+1) vertex,
     * and length numerically equal to number of target vertex.
     */
    @Test
    public void testChainGraph() {
        StringBuilder builder = new StringBuilder();

        builder.append("[");
        for(int i=0; i<10;i++) {
            if(i > 0) {
                builder.append(", ");
            }
            builder.append(i);
            assertTrue(graph2.shortestPath(0, i).toString().equals(
                builder.toString() + "]"
            ));

            assertTrue(graph2.getPathCost(0,i) == i);
        }
    }
}
