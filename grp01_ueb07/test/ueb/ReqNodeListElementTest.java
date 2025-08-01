package ueb;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ueb.nodelist.NodeListEmpty;
import ueb.nodelist.NodeListInterface;

/**
 * Test for NodeListElement
 *
 * @author ##### & Christoph
 */
public class ReqNodeListElementTest {

    @Test
    public void testThreeNodesZeroNeighbours(){
        NodeListInterface list = new NodeListEmpty();
        ListGraphNode A = new ListGraphNode("A");
        ListGraphNode B = new ListGraphNode("B");
        ListGraphNode C = new ListGraphNode("C");

        list = list.append(A);
        list = list.append(B);
        list = list.append(C);
        NodeListInterface result = list;


        String expected = "A->, B->, C->";
        assertEquals(expected, result.toString(), "Expected: "+ expected + ", but got:" + result);
    }
    @Test
    public void testThreeNodesTwoNeighbours(){
        NodeListInterface list = new NodeListEmpty();
        ListGraphNode A = new ListGraphNode("A", "B", "C");
        ListGraphNode B = new ListGraphNode("B", "A");
        ListGraphNode C = new ListGraphNode("C", "A");

        list = list.append(A);
        list = list.append(B);
        list = list.append(C);
        NodeListInterface result = list;


        String expected = "A->B, C, B->A, C->A";
        assertEquals(expected, result.toString(), "Expected: " + expected + ", but got: " + result);


    }


}
