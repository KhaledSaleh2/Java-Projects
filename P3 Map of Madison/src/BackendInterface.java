import java.util.List;

interface BackendInterface {

	public boolean addLocation(String location); //Adds a location to the GPS

	public boolean removeLocation(String location); //Removes a location from the GPS

	public boolean insertPath(String pred, String succ, double weight); //Inserts a path into the GPS

	public boolean removePath(String pred, String succ); //Removes a path from the GPS

	public List<String> getDirections(String start, String end); //Use shortestPathData to
		//get directions from the first node to intermediary nodes to the final node

	public List<String> shortestPathWithStop(String start, String end, String stop);

	public double shortestPathWithStopCost(String start, String end, String stop);

	public double getDistance(String start, String end); //Gets the distance from the start to the end

	public String getStatistics(); //Gets a string representation of statistics of the GPS

	public String printGraph(String start); //Gets a string representation of the locations within the graph and the
		//locations they are each adjacent to

	public void loadData(String filename); //Loads data from a file into the graph

}