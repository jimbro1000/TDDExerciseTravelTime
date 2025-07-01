package uk.gov.dwp.traveltime;

public class TravelTimeCalculator {
    /**
     * Location store.
     */
    private final LocationStoreInterface locations;
    /**
     * Route store.
     */
    private final RouteStoreInterface routes;

    /**
     * Constructor.
     * @param locationStore location store implementation
     * @param routeStore route store implementation
     */
    public TravelTimeCalculator(final LocationStoreInterface locationStore,
                                final RouteStoreInterface routeStore) {
        this.locations = locationStore;
        this.routes = routeStore;
    }

    /**
     * Set travel time for given route.
     * <p>
     * Creates a new route and locations if any are not recorded
     * </p>
     * @param fromLocation String start location
     * @param toLocation String end location
     * @param travelTime String elapsed travel time as HH:MM
     * @return status 0=success
     */
    public int setTravelTime(final String fromLocation,
                             final String toLocation,
                             final String travelTime) {
        if (!this.locations.hasLocation(fromLocation)) {
            this.locations.addLocation(fromLocation);
        }
        if (!this.locations.hasLocation(toLocation)) {
            this.locations.addLocation(toLocation);
        }
        RouteInterface route = this.routes.addRoute(fromLocation, toLocation);
        if (route instanceof NullRoute) {
            return -1;
        }
        route.setRouteTime(travelTime);
        return 0;
    }

    /**
     * Get recorded travel time for given route.
     * @param fromLocation String start location
     * @param toLocation String end location
     * @return String average elapsed travel time as HH:MM or N/A if no record
     */
    public String getTravelTime(final String fromLocation,
                                final String toLocation) {
        if (this.locations.hasLocation(fromLocation)
                && this.locations.hasLocation(toLocation)) {
            RouteInterface route = this.routes
                    .getRoute(fromLocation, toLocation);
            return route.getAverage();
        }
        return "N/A";
    }
}
