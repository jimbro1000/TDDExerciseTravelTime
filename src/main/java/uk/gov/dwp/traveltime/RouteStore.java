package uk.gov.dwp.traveltime;

import java.util.Map;

public class RouteStore implements RouteStoreInterface {
    private final RouteBuilder routeBuilder;
    private final Map<String,RouteInterface> routes;

    public RouteStore(final Map<String,RouteInterface> routeContainer) {
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
    public RouteInterface addRoute(String from, String to) {
        return routeBuilder.getNewRoute(from, to);
    }

    /**
     * @param from start of route
     * @param to end of route
     * @return existing route object
     */
    @Override
    public RouteInterface getRoute(String from, String to) {
        String key = from + ":" + to;
        if (routes.containsKey(key)) {
            return routes.get(key);
        }
        return NullRoute.getInstance();
    }
}
