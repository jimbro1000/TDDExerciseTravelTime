package uk.gov.dwp.traveltime;

import java.util.Map;

public final class RouteStore implements RouteStoreInterface {
    /**
     * Route builder instance.
     */
    private final RouteBuilder routeBuilder;
    /**
     * Storage instance for routes.
     */
    private final Map<String, RouteInterface> routes;

    /**
     * Constructor.
     * @param routeContainer storage instance (can be pre-populated)
     */
    public RouteStore(final Map<String, RouteInterface> routeContainer) {
        this.routeBuilder = RouteBuilder.getRouteBuilder();
        this.routeBuilder.setDefaultTimeCalculator(SimpleAverageRoute.class);
        this.routes = routeContainer;
    }

    /**
     * @param from start of route
     * @param to end of route
     * @return route object for new or existing route
     */
    @Override
    public RouteInterface addRoute(final String from, final String to) {
        return routeBuilder.getNewRoute(from, to);
    }

    /**
     * @param from start of route
     * @param to end of route
     * @return existing route object
     */
    @Override
    public RouteInterface getRoute(final String from, final String to) {
        String key = from + ":" + to;
        if (routes.containsKey(key)) {
            return routes.get(key);
        }
        return NullRoute.getInstance();
    }
}
