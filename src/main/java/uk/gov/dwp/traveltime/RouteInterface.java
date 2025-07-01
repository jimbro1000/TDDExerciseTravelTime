package uk.gov.dwp.traveltime;

public interface RouteInterface {
    /**
     * Get destination of route.
     * @return String location name of destination
     */
    String getDestination();

    /**
     * Set the elapsed route transit time.
     * @param elapsedTime String representation of elapsed time in HH:MM
     */
    void setRouteTime(String elapsedTime);

    /**
     * Get the average elapsed route transit time.
     * @return String representation of elapsed time in HH:MM
     */
    String getAverage();

    /**
     * Get start of route.
     * @return String location name of route start
     */
    String getStart();
}
