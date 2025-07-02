package uk.gov.dwp.traveltime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.lang.reflect.InvocationTargetException;

public final class RouteBuilder {
    /**
     * Singleton instance record.
     */
    private static RouteBuilder instance;
    /**
     * Default elapsed time container class to use in each route instance.
     */
    private Class defaultTimeClass;

    private static final Logger logger = LoggerFactory.getLogger(RouteBuilder.class);

    // Hide default constructor
    private RouteBuilder() {
        // No action required
    }

    /**
     * Obtain singleton route builder instance.
     * @return RouteBuilder instance
     */
    public static RouteBuilder getRouteBuilder() {
        if (instance == null) {
            instance = new RouteBuilder();
            logger.info("RouteBuilder instantiated");
        }
        return instance;
    }

    /**
     * Set the default elapsed time container class.
     * @param routeTimeClass class reference for RouteTimeInterface
     */
    public void setDefaultTimeCalculator(final Class routeTimeClass) {
        MDC.put("routeTimeClass", routeTimeClass.getCanonicalName());
        logger.info("Default RouteTimeInterface set");
        MDC.remove("routeTimeClass");
        this.defaultTimeClass = routeTimeClass;
    }

    /**
     * Build a new route instance.
     * @param from String start location
     * @param to String end location
     * @return route instance with elapsed time container
     */
    public RouteInterface getNewRoute(final String from, final String to) {
        RouteTimeInterface timeType;
        MDC.put("method", "getNewRoute");
        logger.info("creating new route");
        try {
            timeType = (RouteTimeInterface) defaultTimeClass
                    .getDeclaredConstructor()
                    .newInstance();
            logger.info("route created");
            return new Route(from, to, timeType);
        } catch (ClassCastException
                 | InvocationTargetException
                 | IllegalAccessException
                 | InstantiationException
                 | NoSuchMethodException e) {
            logger.error("failed to create RouteTime instance");
            return NullRoute.getInstance();
        } finally {
            MDC.remove("method");
        }
    }
}
