package uk.gov.dwp.traveltime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RouteStoreTest {

    @Test
    public void routeStoreAddsANewRouteAndReturnsTheResultingRoute() {
        Map<String,RouteInterface> routeContainer = new HashMap<>();
        RouteStore routes = new RouteStore(routeContainer);
        RouteInterface route = routes.addRoute("Manchester", "Blackpool");
        assertEquals("Manchester", route.getStart());
        assertEquals("Blackpool", route.getDestination());
    }

    @ParameterizedTest
    @CsvSource({
            "London,Leeds","Manchester,Newcastle"
    })
    public void routeStoreReturnsARouteForAGivenLocationPair(final String start, final String destination) {
        RouteInterface example = RouteBuilder.getRouteBuilder().getNewRoute(start,destination);
        Map<String,RouteInterface> routeContainer = new HashMap<>();
        routeContainer.put(start + ":" + destination,example);
        RouteStore routes = new RouteStore(routeContainer);
        RouteInterface route = routes.getRoute(start,destination);
        assertEquals(start, route.getStart());
        assertEquals(destination, route.getDestination());
    }

    @Test
    public void routeStoreReturnsANullRouteForAnUnknownLocationPair() {
        Map<String,RouteInterface> routeContainer = new HashMap<>();
        RouteStore routes = new RouteStore(routeContainer);
        RouteInterface route = routes.getRoute("London","Leeds");
        assertEquals("", route.getStart());
        assertEquals("", route.getDestination());
    }
}
