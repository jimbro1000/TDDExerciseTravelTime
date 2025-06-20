package uk.gov.dwp.traveltime;

import java.util.Objects;

public class RouteStore {
    private final RouteTimeInterface routeSamples;
    private final String destination;

    public RouteStore(final String locationB, final RouteTimeInterface routeTimeCalculator) {
        Objects.requireNonNull(routeTimeCalculator);
        Objects.requireNonNull(locationB);
        this.routeSamples = routeTimeCalculator;
        this.destination = locationB;
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
