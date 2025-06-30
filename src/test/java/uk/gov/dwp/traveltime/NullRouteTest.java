package uk.gov.dwp.traveltime;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NullRouteTest {
    @Test
    public void nullRouteReturnsAnEmptyStringForStartLocation() {
        assertEquals("", NullRoute.getInstance().getStart());
    }

    @Test
    public void nullRouteReturnsAnEmptyStringForDestinationLocation() {
        assertEquals("", NullRoute.getInstance().getDestination());
    }

    @Test
    public void nullRouteReturnsNAForAverageRouteTimeElapsed() {
        assertEquals("N/A", NullRoute.getInstance().getAverage());
    }

    @Test
    public void nullRouteImplementsRouteInterface() {
        // must implement missing coverage
        NullRoute.getInstance().setRouteTime("anything");
        assertInstanceOf(RouteInterface.class, NullRoute.getInstance());
    }
}
