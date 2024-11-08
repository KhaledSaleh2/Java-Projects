public interface FrontendInterface{
    //Frontend(Scanner input, BackendInterface backend)
    public void runCommandLoop();
    public char mainMenuPrompt();
    public boolean addLocationCommand();
    public void removeLocationCommand();
    public boolean insertPathCommand();
    public void removePathCommand();
    public String getDirectionsCommand();
    public String getDistanceCommand();
    public String getStatisticsCommand();
    public String getDirectionsWithStop();
    public String getDistanceWithStopCommand();
}
