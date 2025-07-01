package uk.gov.dwp.traveltime;

public interface RouteStoreInterface {
    /**
     * Add route to repository.
     * @param from String start location
     * @param to String end location
     * @return created route instance
     */
    RouteInterface addRoute(String from, String to);

    /**
     * Get route from repository.
     * @param from String start location
     * @param to String end location
     * @return identified route instance or NullRoute
     */
    RouteInterface getRoute(String from, String to);
}
