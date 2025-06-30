package uk.gov.dwp.traveltime;

import java.lang.reflect.InvocationTargetException;

public class RouteBuilder {
    private static RouteBuilder instance;
    private Class defaultTimeClass;

    private RouteBuilder() {
    }

    public static RouteBuilder getRouteBuilder() {
        if (instance == null) {
            instance = new RouteBuilder();
        }
        return instance;
    }

    public void setDefaultTimeCalculator(final Class routeTimeClass) {
        this.defaultTimeClass = routeTimeClass;
    }

    public RouteInterface getNewRoute(final String from, final String to) {
        RouteTimeInterface timeType;
        try {
            timeType = (RouteTimeInterface) defaultTimeClass.getDeclaredConstructor().newInstance();
            return new Route(from, to, timeType);
        } catch (ClassCastException | InvocationTargetException | IllegalAccessException | InstantiationException | NoSuchMethodException e) {
            return NullRoute.getInstance();
        }
    }
}
