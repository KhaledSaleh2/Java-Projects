// --== CS400 File Header Information ==--
// Name: Connor Hughes
// Email: chughes25@wisc.edu
// Group and Team: DK_blue
// Group TA: Yuye Jiang
// Lecturer: Florian
// Notes to Grader: <optional extra notes>
import java.util.List;
import java.util.Scanner;

/**
 * Frontend class for P3 Dijkstra's algorithm
 * @author Connor
 *
 */
public class DijkstraFrontend implements FrontendInterface{

    public Scanner input; //scanner used in class
    public BackendInterface backend; //backend object
    
    public DijkstraFrontend() {
        this.input = new Scanner(System.in);
        this.backend = new Backend(new LocationReader(new Scanner(System.in)), new AlgorithmEngineer<String, Double>());
    }
    
    /**
     * Runs the loop for the whole program
     */
    @Override
    public void runCommandLoop() {
        System.out.println("Welcome to the map of Madison!\n");
        char loop = 'P';
        
        while(loop != 'Q') {
            loop = mainMenuPrompt();
            System.out.println(loop);
            //Checks the input character for the different cases
            switch(loop) {
            case 'A':
                addLocationCommand();
                loop = quitPrompt();
                break;
            case 'R':
                removeLocationCommand();
                loop = quitPrompt();
                break;
            case 'I':
                insertPathCommand();
                loop = quitPrompt();
                break;
            case 'P':
                removePathCommand();
                loop = quitPrompt();
                break;
            case 'G':
                String directions = getDirectionsCommand();
                System.out.println(directions);
                loop = quitPrompt();
                break;
            case 'D':
                String distance = getDistanceCommand();
                System.out.println(distance);
                loop = quitPrompt();
                break;
            case 'H':
                String dws = getDirectionsWithStop();
                System.out.println(dws);
                loop = quitPrompt();
                break;
            case'E':
                String distanceWithStop = getDistanceCommand();
                System.out.println(distanceWithStop);
                loop = quitPrompt();
                break;
            case'S':
                System.out.println(getStatisticsCommand());
                loop = quitPrompt();
                break;
            case 'Q':
                break;
            default:
                System.out.println("Not an available command, re-enter command: ");
                String i = input.nextLine().trim();
                if(i.length()>0) {
                    loop = i.charAt(0);
                }
                else {
                    loop = 'O';
                }
            }
        }
        System.out.println("\nThanks for using our map!");
    }

    /**
     * Provides the prompt for the user to select a char
     * @return a char that the user selected
     */
    @Override
    public char mainMenuPrompt() {
        @SuppressWarnings("resource")
        Scanner a = new Scanner(System.in);
        // A to add a location
        // R to remove a location
        // I to add a path
        // P to remove a path
        // G to get directions
        // D to get the distance
        // read in user's choice, and trim away any leading or trailing whitespace
        System.out.println("Here are the possible operations:\n"
            + "A: add location\n"
            + "R: remove location\n"
            + "I: insert path\n"
            + "P: remove path\n"
            + "G: get directions between two locations\n"
            + "H: get directions between two locations including a stop\n"
            + "D: get distance between locations\n"
            + "E: get distance between locations including a stop\n"
            + "S: get statistics of graph\n"
            + "\n"
            + "Choose command: ");
        String i = a.nextLine().trim();
        if (i.length() == 0) // if user's choice is blank, return null character
          return 'O';
        // otherwise, return an uppercase version of the first character in input
        return Character.toUpperCase(i.charAt(0));
    }

    /**
     * Calls the backend method to add a location to the graph
     * @return true if the backend method returns true
     */
    @Override
    public boolean addLocationCommand() {
        System.out.println("What is the name of the location you would like to add? ");
        String name = chooseWordPrompt().toLowerCase();
        boolean res = backend.addLocation(name);
        return res;
    }

    /**
     * Calls the backend method to remove a location from the graph
     */
    @Override
    public void removeLocationCommand() {
        System.out.println("What is the name of the location you would like to remove? ");
        String name = chooseWordPrompt().toLowerCase();
        backend.removeLocation(name);       
    }

    /**
     * Calls the backend method to add a path to the graph
     */
    @Override
    public boolean insertPathCommand() {
        System.out.println("What is the name of the location that is the predecessor? ");
        String pred = chooseWordPrompt().toLowerCase();
        System.out.println("What is the name of the location that is the successor? ");
        String succ = chooseWordPrompt().toLowerCase();
        System.out.println("What is the distance of the path? ");
        Double weight = input.nextDouble();
        boolean r = backend.insertPath(pred, succ, weight);
        return r;
    }

    /**
     * Uses backend method to remove a path
     */
    @Override
    public void removePathCommand() {
        System.out.println("What is the name of the location that is the predecessor of the path that is being removed? ");
        String pred = chooseWordPrompt().toLowerCase();
        System.out.println("What is the name of the location that is the successor? ");
        String succ = chooseWordPrompt().toLowerCase();
        backend.removePath(pred, succ); 
    }

    /**
     * Gets the List of directions from the backend method
     * and creates a String formatted as a -> b
     * @return the String of directions
     */
    @Override
    public String getDirectionsCommand() {
        System.out.println("What is the starting location? ");
        String start = chooseWordPrompt().toLowerCase();
        System.out.println("What is the ending location? ");
        String end = chooseWordPrompt().toLowerCase();
        List<String> directions = backend.getDirections(start, end);
        String result = "";
        for(int i = 0; i<directions.size(); i++) {
            if(i == 0) {
                result = directions.get(i);
            }
            else {
                result = result + " -> " + directions.get(i);
            }
        }
        return result + "\n";
    }

    /**
     * Gets the distance between two places using backend's method
     * @return a string saying the distance between point a and b
     */
    @Override
    public String getDistanceCommand() {
        System.out.println("What is the starting location? ");
        String start = chooseWordPrompt().toLowerCase();
        System.out.println("What is the ending location? ");
        String end = chooseWordPrompt().toLowerCase();
        Double distance = backend.getDistance(start, end);
        return "Distance between " + start +" and " + end + " is " +distance + " miles.\n";
    }
    
    /**
     * Gets the statistics of the graph from the backend
     * @return a String of the statistics
     */
    @Override
    public String getStatisticsCommand() {
        return backend.getStatistics();
    }
    
    /**
     * Helper method to provide the quit prompt
     * @return the user's input in response
     */
    public char quitPrompt() {
        Scanner q = new Scanner(System.in);
        System.out.println("\nType Q if you would like to quit");
        System.out.println("Type another character if you want to continue:");
        String i = q.nextLine().trim();
        if (i.length() == 0) // if user's choice is blank, return null character
          return '\0';
        // otherwise, return an uppercase version of the first character in input
        return Character.toUpperCase(i.charAt(0));
    }
    
    /**
     * Helper method to get a String from user
     * @return the String
     */
    public String chooseWordPrompt() {
        Scanner word = new Scanner(System.in);
        return word.nextLine().trim();
    }
    
    @Override
    public String getDirectionsWithStop() {
        System.out.println("What is the starting location? ");
        String start = chooseWordPrompt().toLowerCase();
        System.out.println("What is the ending location? ");
        String end = chooseWordPrompt().toLowerCase();
        System.out.println("What is the location to stop at? ");
        String stop = chooseWordPrompt().toLowerCase();
        List<String> directions = backend.shortestPathWithStop(start, end, stop);
        String result = "";
        for(int i = 0; i<directions.size(); i++) {
            if(i == 0) {
                result = directions.get(i);
            }
            else {
                result = result + " -> " + directions.get(i);
            }
        }
        return result + "\n";
    }
    
    @Override
    public String getDistanceWithStopCommand() {
        System.out.println("What is the starting location? ");
        String start = chooseWordPrompt().toLowerCase();
        System.out.println("What is the ending location? ");
        String end = chooseWordPrompt().toLowerCase();
        System.out.println("What is the location to stop at? ");
        String stop = chooseWordPrompt().toLowerCase();
        Double distance = backend.shortestPathWithStopCost(start, end, stop);
        return "Distance between " + start +" and " + end + " is " +distance + " miles.\n";
    }
    
    public static void main(String[]args) {
         Scanner in = new Scanner(System.in);
         DijkstraFrontend test = new DijkstraFrontend();//in, new Backend(new LocationReader(new Scanner(System.in)), new AlgorithmEngineer<String, Double>()));
         test.runCommandLoop();
    }
}
