package uk.gov.dwp.traveltime;

public final class NullRoute implements RouteInterface {
    private static NullRoute instance;

    // hide default constructor
    private NullRoute() {}

    public static NullRoute getInstance() {
        if (instance == null) {
            instance = new NullRoute();
        }
        return instance;
    }

    /**
     * @return empty destination
     */
    public String getDestination() {
        return "";
    }

    /**
     * @param elapsedTime sample elapsed time for route
     */
    public void setRouteTime(String elapsedTime) {
        // ignore sample
    }

    /**
     * @return unavailable average time
     */
    public String getAverage() {
        return "N/A";
    }

    /**
     * @return empty start location
     */
    public String getStart() {
        return "";
    }
}
