package uk.gov.dwp.traveltime;

import java.util.Objects;

public class Route implements RouteInterface {
    private final RouteTimeInterface routeSamples;
    private final String destination;
    private final String start;

    public Route(final String locationA, final String locationB, final RouteTimeInterface routeTimeCalculator) {
        Objects.requireNonNull(routeTimeCalculator);
        Objects.requireNonNull(locationB);
        this.routeSamples = routeTimeCalculator;
        this.destination = locationB;
        this.start = locationA;
    }

    public String getStart() {
        return this.start;
    }

    public String getDestination() {
        return this.destination;
    }

    public void setRouteTime(String elapsedTime) {
        routeSamples.addSample(elapsedTime);
    }

    public String getAverage() {
        return routeSamples.getAverage();
    }
}
