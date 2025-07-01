package uk.gov.dwp.traveltime;

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
        }
        return instance;
    }

    /**
     * Set the default elapsed time container class.
     * @param routeTimeClass class reference for RouteTimeInterface
     */
    public void setDefaultTimeCalculator(final Class routeTimeClass) {
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
        try {
            timeType = (RouteTimeInterface) defaultTimeClass
                    .getDeclaredConstructor()
                    .newInstance();
            return new Route(from, to, timeType);
        } catch (ClassCastException
                 | InvocationTargetException
                 | IllegalAccessException
                 | InstantiationException
                 | NoSuchMethodException e) {
            return NullRoute.getInstance();
        }
    }
}
