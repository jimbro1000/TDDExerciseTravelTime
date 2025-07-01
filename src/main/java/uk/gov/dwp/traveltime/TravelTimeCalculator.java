package uk.gov.dwp.traveltime;

public class TravelTimeCalculator {

    private final LocationStoreInterface locations;
    private final RouteStoreInterface routes;

    public TravelTimeCalculator(final LocationStoreInterface locationStore,
                                final RouteStoreInterface routeStore) {
        this.locations = locationStore;
        this.routes = routeStore;
    }

    public int setTravelTime(final String fromLocation, final String toLocation, final String travelTime) {
        if (!this.locations.hasLocation(fromLocation)) {
            this.locations.addLocation(fromLocation);
        }
        if (!this.locations.hasLocation(toLocation)) {
            this.locations.addLocation(toLocation);
        }
        RouteInterface route = this.routes.addRoute(fromLocation,toLocation);
        route.setRouteTime(travelTime);
        return 0;
    }

    public String getTravelTime(String fromLocation, String toLocation) {
        if (this.locations.hasLocation(fromLocation) && this.locations.hasLocation(toLocation)) {
            RouteInterface route = this.routes.getRoute(fromLocation, toLocation);
            return route.getAverage();
        }
        return "N/A";
    }
}
