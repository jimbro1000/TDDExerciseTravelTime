package uk.gov.dwp.traveltime;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RouteTest {
    @Test
    public void itCreatesNewRoutesWithZeroSamples() {
        Route subject = new Route( "start", "destination", new SimpleAverageRoute());
        assertEquals("start", subject.getStart());
        assertEquals("destination", subject.getDestination());
        assertEquals("00:00", subject.getAverage());
    }

    @Test
    public void itAcceptsNewRouteSamples() {
        Route subject = new Route("start","destination", new SimpleAverageRoute());
        subject.setRouteTime("04:30");
        assertEquals("04:30", subject.getAverage());
    }

    @Test
    public void itFindsAnAverageOfRouteSamples() {
        Route subject = new Route("start","destination", new SimpleAverageRoute());
        subject.setRouteTime("04:30");
        subject.setRouteTime("04:00");
        assertEquals("04:15", subject.getAverage());
    }
}
