package uk.gov.dwp.traveltime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.UUID;

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
     * Logger instance.
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(TravelTimeCalculator.class);

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
        int result = 0;
        MDC.put("correlationId", raiseCorrelationId().toString());
        MDC.put("method", "setTravelTime");
        MDC.put("from", fromLocation);
        MDC.put("to", toLocation);
        LOGGER.info("add elapsed time sample to route");
        if (!this.locations.hasLocation(fromLocation)) {
            this.locations.addLocation(fromLocation);
        }
        if (!this.locations.hasLocation(toLocation)) {
            this.locations.addLocation(toLocation);
        }
        RouteInterface route = this.routes.addRoute(fromLocation, toLocation);
        if (route instanceof NullRoute) {
            LOGGER.info("failed to acquire route");
            result = -1;
        } else {
            route.setRouteTime(travelTime);
        }
        MDC.clear();
        return result;
    }

    /**
     * Get recorded travel time for given route.
     * @param fromLocation String start location
     * @param toLocation String end location
     * @return String average elapsed travel time as HH:MM or N/A if no record
     */
    public String getTravelTime(final String fromLocation,
                                final String toLocation) {
        MDC.put("correlationId", raiseCorrelationId().toString());
        MDC.put("method", "getTravelTime");
        MDC.put("from", fromLocation);
        MDC.put("to", toLocation);
        LOGGER.info("obtain average travel time for route");
        if (this.locations.hasLocation(fromLocation)
                && this.locations.hasLocation(toLocation)) {
            RouteInterface route = this.routes
                    .getRoute(fromLocation, toLocation);
            LOGGER.info("average time found");
            MDC.clear();
            return route.getAverage();
        }
        LOGGER.info("unknown route");
        MDC.clear();
        return "N/A";
    }

    private UUID raiseCorrelationId() {
        return UUID.randomUUID();
    }
}
