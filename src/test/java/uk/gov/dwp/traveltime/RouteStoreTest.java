package uk.gov.dwp.traveltime;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RouteStoreTest {
    @Test
    public void itCreatesNewRoutesWithZeroSamples() {
        RouteStore subject = new RouteStore("destination", new SimpleAverageRoute());
        assertEquals("destination", subject.getDestination());
        assertEquals("00:00", subject.getAverage());
    }

    @Test
    public void itAcceptsNewRouteSamples() {
        RouteStore subject = new RouteStore("destination", new SimpleAverageRoute());
        subject.setRouteTime("04:30");
        assertEquals("04:30", subject.getAverage());
    }

    @Test
    public void itFindsAnAverageOfRouteSamples() {
        RouteStore subject = new RouteStore("destination", new SimpleAverageRoute());
        subject.setRouteTime("04:30");
        subject.setRouteTime("04:00");
        assertEquals("04:15", subject.getAverage());
    }
}
