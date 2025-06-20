package uk.gov.dwp.traveltime;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleAverageRouteTest {
    public RouteTimeInterface averageRoute;

    @Test
    public void itReturnsMinusOneIfGivenAnInvalidTime() {
        averageRoute = new SimpleAverageRoute();
        int result = averageRoute.addSample("not a time");
        assertEquals(-1,result);
    }

    @Test
    public void itReturnsZeroIfTheInputTimeIsValid() {
        averageRoute = new SimpleAverageRoute();
        int result = averageRoute.addSample("10:00");
        assertEquals(0,result);
    }

    @Test
    public void getAverageReturnsZeroIfThereAreNoSamples() {
        averageRoute = new SimpleAverageRoute();
        assertEquals("00:00", averageRoute.getAverage());
    }
}
