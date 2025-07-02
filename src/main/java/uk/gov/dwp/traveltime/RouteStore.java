package uk.gov.dwp.traveltime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

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
     * Logger instance.
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(RouteStore.class);
    /**
     * Constructor.
     * @param routeContainer storage instance (can be pre-populated)
     */
    public RouteStore(final Map<String, RouteInterface> routeContainer) {
        LOGGER.info("Created simple in-memory route store");
        this.routeBuilder = RouteBuilder.getRouteBuilder();
        this.routeBuilder.setDefaultTimeCalculator(SimpleAverageRoute.class);
        this.routes = routeContainer;
        LOGGER.info("Initial container size is {}", this.routes.size());
    }

    /**
     * @param from start of route
     * @param to end of route
     * @return route object for new or existing route
     */
    @Override
    public RouteInterface addRoute(final String from, final String to) {
        MDC.put("method", "addRoute");
        LOGGER.info("add route to store");
        RouteInterface route = routeBuilder.getNewRoute(from, to);
        String key = from + ":" + to;
        routes.put(key, route);
        MDC.remove("method");
        return route;
    }

    /**
     * @param from start of route
     * @param to end of route
     * @return existing route object
     */
    @Override
    public RouteInterface getRoute(final String from, final String to) {
        String key = from + ":" + to;
        RouteInterface result = NullRoute.getInstance();
        MDC.put("method", "getRoute");
        MDC.put("route key", key);
        if (routes.containsKey(key)) {
            result = routes.get(key);
            LOGGER.info("retrieved route from store");
        } else {
            LOGGER.info("key not found");
        }
        MDC.remove("method");
        return result;
    }

    /**
     * Test if route exists in store.
     *
     * @param from String start location
     * @param to   String end location
     * @return true if route is known, otherwise false
     */
    @Override
    public boolean hasRoute(final String from, final String to) {
        boolean result;
        String key = from + ":" + to;
        MDC.put("method", "hasRoute");
        MDC.put("route key", key);
        result = routes.containsKey(key);
        MDC.remove("method");
        MDC.remove("key");
        return result;
    }
}
