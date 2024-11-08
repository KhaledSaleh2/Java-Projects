import java.io.FileNotFoundException;
import java.util.List;

/**
 * Class that represents the backend of a GPS, which works around inserting locations on a graph and finding the
 * shortest path between them
 */
public class Backend implements BackendInterface {
    LocationReaderInterface reader; //Reader that reads locations from a file
    AlgorithmEngineerInterface<String, Double> graph;
    
    /**
     * Creates a new backend
     * @param reader - Reader that reads locations from a file
     * @param graph - The graph that stores locations
     */
	public Backend(LocationReaderInterface reader, AlgorithmEngineerInterface<String, Double> graph) {
        this.graph = graph;
        this.reader = reader;
    }

    /**
     * Adds a location to the GPS
     * @param location - the location to be added
     * @return true if the location is unique and can be inserted or false
     */
    @Override
    public boolean addLocation(String location) {
        return graph.insertNode(location);
    }

    /**
     * Removes a location from the GPS
     * @param location - the location to be removed
     * @return true if the location is in the graph and can be removed
     */
    @Override
    public boolean removeLocation(String location) {
        return graph.removeNode(location);
    }

    /**
     * Inserts a path into the GPS
     * @param pred - the first location connected by the path
     * @param succ - the second location connected by the path
     * @param weight - the weight of the path
     * @return true if the predecessor and successor were both found or false
     */
    @Override
    public boolean insertPath(String pred, String succ, double weight) {
        return graph.insertEdge(pred, succ, weight);
    }

    /**
     * Removes a path from the GPS
     * @param pred - the first location connected by the path
     * @param succ - the second location connected by the path
     * @return true if the predecessor and successor were both found or false
     */
    @Override
    public boolean removePath(String pred, String succ) {
        return graph.removeEdge(pred, succ);
    }

    /**
     * Use shortestPathData to get directions from the first node to intermediary nodes to the final node
     * @param pred - the first location to start searching from
     * @param succ - the final location to get directions to
     * @return list of locations along this shortest path
     */
    @Override
    public List<String> getDirections(String start, String end) {
        return graph.shortestPathData(start, end);
    }

    /**
     * Gets the distance from the start to the end
     * @param pred - the first location to start searching from
     * @param succ - the final location to get the distance to
     * @return the cost of the shortest path between the locations
     */
    @Override
    public double getDistance(String start, String end) {
        return graph.shortestPathCost(start, end);
    }

    /**
     * Gets a string representation of statistics of the GPS
     */
    @Override
    public String getStatistics() {
        int nodeCount = graph.getNodeCount();
        int edgeCount = graph.getEdgeCount();
        return "Edges: " + edgeCount +
                "\nNodes: " + nodeCount;
    }

    /**
     * Loads data from a file into the graph
     * @param fileName - the name of the file that data will be read from
     */
    @Override
    public void loadData(String fileName) {      
        try {
            reader.readGraphFromFile(fileName);
            List<String> nodes = reader.getNodes();
            List<List<String>> edges = reader.getEdges();
            for(int i = 0; i < nodes.size(); i ++) {
                addLocation(nodes.get(i));
            }
            for(int e = 0; e < edges.size(); e ++) {
                insertPath(edges.get(e).get(0).trim(), edges.get(e).get(1).trim(), Double.parseDouble(edges.get(e).get(2)));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets a string representation of the locations within the graph and the locations they are each adjacent to
     * @param start - the location to start printing from
     */
    @Override
    public String printGraph(String start) {
        return graph.depthFirstTraversal(start);
    }

    /**
     *  Gets the shortest path from one location to a stop to a final destination
     *  @param start - the first location to start searching from
     *  @param end - the final location to get directions to
     *  @param stop - the location that the direction will pass through on the way to the destination
     * @return a list of string representations of locations from the start to the stop to the end
     */
    @Override
    public List<String> shortestPathWithStop(String start, String end, String stop) {
        return graph.shortestPathWithStop(start, end, stop);
    }

    /**
     *  Gets the cost of the shortest path from one location to a stop to a final destination
     *  @param start - the first location to start searching from
     *  @param end - the final location to get directions to
     *  @param stop - the location that the direction will pass through on the way to the destination
     * @return a numerical cost of taking the shortest path from the start to the stop to the end
     */

    @Override
    public double shortestPathWithStopCost(String start, String end, String stop) {
        return graph.shortestPathWithStopCost(start, end, stop);
    }
    
}
