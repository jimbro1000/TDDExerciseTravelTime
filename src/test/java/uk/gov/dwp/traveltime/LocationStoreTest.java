package uk.gov.dwp.traveltime;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class LocationStoreTest {
    LocationStore locations;

    @Test
    public void addLocationAddsEntryToLocationList() {
        Set<String> sites = new HashSet<>();
        Map<String, String> routes = new HashMap<>();
        locations = new LocationStore(sites,routes);
        locations.addLocation("testSite");
        assertTrue(sites.contains("testSite"));
    }

    @Test
    public void addLocationReturnsZeroOnSuccess() {
        Set<String> sites = new HashSet<>();
        Map<String, String> routes = new HashMap<>();
        locations = new LocationStore(sites,routes);

        assertEquals(0,locations.addLocation("testSite"));
    }

    @Test
    public void addLocationReturnsNegativeOneOnFailure() {
        Set<String> sites = new HashSet<>();
        Map<String, String> routes = new HashMap<>();
        locations = new LocationStore(sites,routes);

        assertEquals(-1,locations.addLocation(null));
    }

    @Test
    public void hasLocationReturnsFalseIfLocationIsNotRecorded() {
        Set<String> sites = new HashSet<>();
        Map<String, String> routes = new HashMap<>();
        locations = new LocationStore(sites,routes);
        assertFalse(locations.hasLocation("Leeds"));
    }

    @Test
    public void hasLocationReturnsTrueIfLocationIsRecorded() {
        Set<String> sites = new HashSet<>();
        Map<String, String> routes = new HashMap<>();
        sites.add("Blackpool");
        locations = new LocationStore(sites,routes);
        assertTrue(locations.hasLocation("Blackpool"));
    }
}
