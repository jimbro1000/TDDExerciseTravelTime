package uk.gov.dwp.traveltime;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class LocationStoreTest {
    LocationStore locations;

    @Test
    public void addLocationAddsEntryToLocationList() {
        Map<String, Map<String, RouteStore>> sites = new HashMap<>();
        locations = new LocationStore(sites);
        locations.addLocation("testSite");
        assertTrue(sites.containsKey("testSite"));
    }

    @Test
    public void addLocationReturnsZeroOnSuccess() {
        Map<String, Map<String, RouteStore>> sites = new HashMap<>();
        locations = new LocationStore(sites);
        assertEquals(0,locations.addLocation("testSite"));
    }

    @Test
    public void addLocationReturnsNegativeOneOnFailure() {
        Map<String, Map<String, RouteStore>> sites = new HashMap<>();
        locations = new LocationStore(sites);

        assertEquals(-1,locations.addLocation(null));
    }

    @Test
    public void hasLocationReturnsFalseIfLocationIsNotRecorded() {
        Map<String, Map<String, RouteStore>> sites = new HashMap<>();
        locations = new LocationStore(sites);
        assertFalse(locations.hasLocation("Leeds"));
    }

    @Test
    public void hasLocationReturnsTrueIfLocationIsRecorded() {
        Map<String, Map<String, RouteStore>> sites = new HashMap<>();
        sites.put("Blackpool", null);
        locations = new LocationStore(sites);
        assertTrue(locations.hasLocation("Blackpool"));
    }

    /**
     * This test knows too much about the implementation, multiple layers deep
     */
    @Test
    public void addRouteIntroducesRouteAndTimeInformation() {
        Map<String, Map<String, RouteStore>> sites = new HashMap<>();
        locations = new LocationStore(sites);
        locations.addRoute("London", "Paris", "03:20");
        assertTrue(sites.containsKey("London"));
        assertTrue(sites.containsKey("Paris"));
        Map<String, RouteStore> destinations = sites.get("London");
        RouteStore destination = destinations.get("Paris");
        assertEquals("03:20",destination.getAverage());
    }
}
