// --== CS400 File Header Information ==--
// Name: Naman Parekh
// Email: ncparekh@wisc.edu
// Group and Team: DT
// Group TA: Daniel Finer
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * This class provides JUnit testers for the Algorithm Engineer's respective role code
 */
public class AlgorithmEngineerTests {

    /**
     * Tester that checks if the shortestPathCost() method works correctly while taking into
     * account any nodes the user wants to disable
     *
     */
    @Test
    void Test1() {

        NPFDijkstraGraph graph = new NPFDijkstraGraph();

        // inserting nodes into graph
        graph.insertNode("Madison");
        graph.insertNode("Chicago");
        graph.insertNode("Las Vegas");
        graph.insertNode("Boston");
        graph.insertNode("Miami");
        graph.insertNode("New York");
        graph.insertNode("New Jersey");
        graph.insertNode("Los Angeles");

        // inserting edges with weights into graph
        graph.insertEdge("Madison", "Chicago", 4);
        graph.insertEdge("Madison", "Las Vegas", 2);
        graph.insertEdge("Madison", "Miami", 15);
        graph.insertEdge("Las Vegas", "Boston", 5);
        graph.insertEdge("Chicago", "Boston", 1);
        graph.insertEdge("Boston", "Miami", 3);
        graph.insertEdge("Boston", "New York", 0);
        graph.insertEdge("New York", "Boston", 2);
        graph.insertEdge("New York", "Los Angeles", 4);
        graph.insertEdge("New Jersey", "New York", 4);
        graph.insertEdge("Chicago", "Miami", 10);

        List<String> dc = new ArrayList<>();
        dc.add("Boston");

        // checks cost of shortest path between Madison to Miami while not
        // passing through disabled node (Boston)
        assertEquals(14, graph.shortestPathCost("Madison", "Miami", dc));

        // checks if normal functionality works
        assertEquals(2, graph.shortestPathCost("Madison", "Las Vegas", dc));

    }

    /**
     * Tester that checks if the shortestPathData() method works correctly while taking into
     * account any nodes the user wants to disable
     *
     */
    @Test
    void Test2() {

        NPFDijkstraGraph graph = new NPFDijkstraGraph();

        // inserting nodes into graph
        graph.insertNode("Madison");     //A
        graph.insertNode("Chicago");     //B
        graph.insertNode("Las Vegas");   //C
        graph.insertNode("Boston");      //D
        graph.insertNode("Miami");       //E
        graph.insertNode("New York");    //F
        graph.insertNode("New Jersey");  //G
        graph.insertNode("Los Angeles"); //H

        // inserting edges with weights into graph
        graph.insertEdge("Madison", "Chicago", 4);
        graph.insertEdge("Madison", "Las Vegas", 2);
        graph.insertEdge("Madison", "Miami", 15);
        graph.insertEdge("Las Vegas", "Boston", 5);
        graph.insertEdge("Chicago", "Boston", 1);
        graph.insertEdge("Boston", "Miami", 3);
        graph.insertEdge("Boston", "New York", 0);
        graph.insertEdge("New York", "Boston", 2);
        graph.insertEdge("New York", "Los Angeles", 4);
        graph.insertEdge("New Jersey", "New York", 4);
        graph.insertEdge("Chicago", "Miami", 10);

        List<String> dc = new ArrayList<>();
        dc.add("Boston");

        // checks sequence of data along the shortest path from Madison to Miami while taking into
        // account of the disabled node
        assertEquals("[Madison, Chicago, Miami]", graph.shortestPathData
                ("Madison", "Miami", dc).toString());

        // checks if normal functionality works
        assertEquals("[Madison, Las Vegas]", graph.shortestPathData
                ("Madison", "Las Vegas", dc).toString());
    }

    /**
     * Tester that checks if the shortestPathCost(), shortestPathData() methods throw respective
     * exceptions when user enters incorrect data.
     *
     */
    @Test
    void Test3() {

        NPFDijkstraGraph graph = new NPFDijkstraGraph();

        // inserting nodes into graph
        graph.insertNode("Madison");     //A
        graph.insertNode("Chicago");     //B
        graph.insertNode("Las Vegas");   //C
        graph.insertNode("Boston");      //D
        graph.insertNode("Miami");       //E
        graph.insertNode("New York");    //F
        graph.insertNode("New Jersey");  //G
        graph.insertNode("Los Angeles"); //H

        // inserting edges with weights into graph
        graph.insertEdge("Madison", "Chicago", 4);
        graph.insertEdge("Madison", "Las Vegas", 2);
        graph.insertEdge("Madison", "Miami", 15);
        graph.insertEdge("Las Vegas", "Boston", 5);
        graph.insertEdge("Chicago", "Boston", 1);
        graph.insertEdge("Boston", "Miami", 3);
        graph.insertEdge("Boston", "New York", 0);
        graph.insertEdge("New York", "Boston", 2);
        graph.insertEdge("New York", "Los Angeles", 4);
        graph.insertEdge("New Jersey", "New York", 4);
        graph.insertEdge("Chicago", "Miami", 10);

        List<String> dc = new ArrayList<>();
        dc.add("Boston");
        dc.add("Chicago");

        // checks if exception is thrown when trying to visit node that is only accessible by
        // first passing through the disabled node
        assertThrows(NoSuchElementException.class, () -> {
            graph.findPathWithDisabledNodes("Las Vegas", "New York", dc);
        });

        // checks if exception is thrown when trying to find the shortest path between two nodes
        // with no directed edges
        assertThrows(NoSuchElementException.class, () -> {
            graph.findPathWithDisabledNodes("Los Angeles", "Miami", dc);
        });

        // checks if exception is thrown when startNode is included in the list of nodes user
        // wants to disable
        assertThrows(NoSuchElementException.class, () -> {
            graph.findPathWithDisabledNodes("Boston", "Los Angeles", dc);
        });
    }

    /**
     * Tester that uses a more comprehensive example to check if the
     * shortestPathCost() method works correctly while taking into account any nodes
     * the user wants to disable.
     *
     */
    @Test
    void Test4() {

        NPFDijkstraGraph graph = new NPFDijkstraGraph();

        // inserting nodes into graph
        graph.insertNode("US");
        graph.insertNode("Canada");
        graph.insertNode("Spain");
        graph.insertNode("Italy");
        graph.insertNode("Brazil");
        graph.insertNode("Korea");
        graph.insertNode("India");
        graph.insertNode("Russia");

        // inserting edges with weights into graph
        graph.insertEdge("US", "Canada", 3);
        graph.insertEdge("US", "Spain", 7);
        graph.insertEdge("US", "Italy", 6);
        graph.insertEdge("Canada", "US", 4);
        graph.insertEdge("Canada", "Brazil", 4);
        graph.insertEdge("Spain", "Russia", 13);
        graph.insertEdge("Spain", "Italy", 1);
        graph.insertEdge("Italy", "Spain", 0);
        graph.insertEdge("Italy", "India", 6);
        graph.insertEdge("Italy", "Korea", 7);
        graph.insertEdge("Russia", "Korea", 20);
        graph.insertEdge("India", "Russia", 5);
        graph.insertEdge("India", "Korea", 10);
        graph.insertEdge("Korea", "India", 8);
        graph.insertEdge("Korea", "Russia", 17);

        // checks if shortest path cost is found without disabling nodes
        assertEquals(17, graph.shortestPathCost("US", "Russia"));
        assertEquals(7, graph.shortestPathCost("Spain", "India"));

        List<String> dc = new ArrayList<>();
        dc.add("Italy");
        dc.add("Brazil");

        // checks if cost returned is not the minimum due to disabled nodes
        assertEquals(20, graph.shortestPathCost("US", "Russia", dc));
        assertEquals(41, graph.shortestPathCost("Spain", "India", dc));

        List<String> emptyList = new ArrayList<>();

        // checks if AE method works as expected even when an empty list is passed
        assertEquals(7, graph.shortestPathCost("Spain", "India", emptyList));
    }

    /**
     * Tester that uses a more comprehensive example to check if the
     * shortestPathData() method works correctly while taking into account any nodes
     * the user wants to disable.
     *
     */
    @Test
    void Test5() {

        NPFDijkstraGraph graph = new NPFDijkstraGraph();

        // inserting nodes into graph
        graph.insertNode("US");
        graph.insertNode("Canada");
        graph.insertNode("Spain");
        graph.insertNode("Italy");
        graph.insertNode("Brazil");
        graph.insertNode("Korea");
        graph.insertNode("India");
        graph.insertNode("Russia");

        // inserting edges with weights into graph
        graph.insertEdge("US", "Canada", 3);
        graph.insertEdge("US", "Spain", 7);
        graph.insertEdge("US", "Italy", 6);
        graph.insertEdge("Canada", "US", 4);
        graph.insertEdge("Canada", "Brazil", 4);
        graph.insertEdge("Spain", "Russia", 13);
        graph.insertEdge("Spain", "Italy", 1);
        graph.insertEdge("Italy", "Spain", 0);
        graph.insertEdge("Italy", "India", 6);
        graph.insertEdge("Italy", "Korea", 7);
        graph.insertEdge("Russia", "Korea", 20);
        graph.insertEdge("India", "Russia", 5);
        graph.insertEdge("India", "Korea", 10);
        graph.insertEdge("Korea", "India", 8);
        graph.insertEdge("Korea", "Russia", 17);

        List<String> dc = new ArrayList<>();
        dc.add("Italy");
        dc.add("Brazil");

        // checks sequence of data along the shortest path from US to Russia while taking into
        // account of the disabled node
        assertEquals("[US, Spain, Russia]", graph.shortestPathData
                ("US", "Russia", dc).toString());

        assertEquals("[Spain, Russia, Korea, India]", graph.shortestPathData
                ("Spain", "India", dc).toString());
    }

    /**
     * Tester that tests the functionality of the Data Wrangler's role code, specifically the
     * getter methods
     *
     */
    @Test
    void CodeReviewOfDataWranglerTest1() {

        networkPathFinderEdgeDW test1 =
                new networkPathFinderEdgeDW("Austin", "Dallas", 3);

        // checks the getters, setters and field variables
        assertTrue(test1.getStart().equals("Austin")); // start node
        assertTrue(test1.getEnd().equals("Dallas")); // end node
        assertTrue(test1.getWeight() == 3); // weight

        networkPathFinderEdgeDW test2 =
                new networkPathFinderEdgeDW("Seattle", "Madison", 7);

        // checks the getters, setters and field variables
        assertTrue(test2.getStart().equals("Seattle")); // start node
        assertTrue(test2.getEnd().equals("Madison")); // end node
        assertTrue(test2.getWeight() == 7); // weight

    }

    /**
     * Tester that tests the functionality of the Data Wrangler's role code, specifically reading a file
     * and parsing the values correctly
     *
     */
    @Test
    void CodeReviewOfDataWranglerTest2() throws FileNotFoundException {

        DotReaderDW readerObj = new DotReaderDW();

        List<String> readNodes = readerObj.readNodesFromDOT("cities2023.dot");
        List<networkPathFinderEdgeInterfaceDW> readEdges
                = readerObj.readEdgesFromDOT("cities2023.dot");

        // checks if List is computed with correct data
        assertEquals(15, readNodes.size());
        assertEquals(22, readEdges.size());

        // checks if an exception is thrown when fed a fake file
        assertThrows(FileNotFoundException.class, () -> {
            List<String> readFakeNodes = readerObj.readNodesFromDOT("fake.txt");
        });
    }

    /**
     * Tester that tests integrating all the components together and calculating the correct cost
     * and output. Uses the Data Wrangler's files, the AE's extended Dijkstra Graph, the frontend's call-back
     * functions and the backend's call-back functions to AE and DW.
     *
     */
    @Test
    void IntegrationTest1() {

        NPFDijkstraGraphInterface djkGraph = new NPFDijkstraGraph();
        DotReaderInterfaceDW readerObj = new DotReaderDW();
        Backend backEnd = new Backend(djkGraph, readerObj);


        try {
            String fileName = "cities2023.dot";
            backEnd.loadData(fileName);
            TextUITester tester1 =
                    new TextUITester("d\ncities2023.dot\np\nSeattle\nBoston\nq\n");
            Scanner input = new Scanner(System.in);
            NetworkPathFrontendFD fd = new NetworkPathFrontendFD(input, backEnd);
            fd.runCommandLoop();
            String output = tester1.checkOutput();
            String checker = "Seattle -> Austin -> Chicago -> Denver -> Boston";
            String secondCheck = "The cost of this path is: 11.0";

            assertTrue(output.contains(checker));
            assertTrue(output.contains(secondCheck));
            input.close();

        } catch (FileNotFoundException e) {
        }



        // using a fake file

        try {
            String fileName1 = "faketxt.dot";
            backEnd.loadData(fileName1);
            TextUITester tester2 =
                    new TextUITester("d\nfaketxt.dot\np\nSeattle\nBoston\nq\n");
            Scanner input1 = new Scanner(System.in);
            NetworkPathFrontendFD fd1 = new NetworkPathFrontendFD(input1, backEnd);
            fd1.runCommandLoop();
            String output = tester2.checkOutput();
            String checker = "Error: Could not find or load file: fake.txt";

            assertTrue(output.contains(checker));
            input1.close();

        } catch (FileNotFoundException e) {
        }
    }

    /**
     * Tester that tests integrating all the components together and calculating the correct cost
     * and output after disabling nodes. Uses the Data Wrangler's files,
     * the AE's extended Dijkstra Graph, the frontend's call-back functions and the backend's
     * call-back functions to AE and DW.
     *
     */
    @Test
    void IntegrationTest2() {

        NPFDijkstraGraphInterface djkGraph = new NPFDijkstraGraph();
        DotReaderInterfaceDW readerObj = new DotReaderDW();
        Backend backEnd = new Backend(djkGraph, readerObj);

        try {
            String fileName = "cities2023.dot";
            backEnd.loadData(fileName);
            TextUITester tester1 = new TextUITester
                    ("d\ncities2023.dot\nc\nChicago\ndone\np\nSeattle\nBoston\nq\n");
            Scanner input = new Scanner(System.in);
            NetworkPathFrontendFD fd = new NetworkPathFrontendFD(input, backEnd);
            fd.runCommandLoop();
            String output = tester1.checkOutput();
            String checker = "Seattle -> Austin -> Dallas -> Denver -> Boston";
            String secondCheck = "The cost of this path is: 17.0";

            assertTrue(output.contains(checker));
            assertTrue(output.contains(secondCheck));
            input.close();

        } catch (FileNotFoundException e) {
        }

    }

}
