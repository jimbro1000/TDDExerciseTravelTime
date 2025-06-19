package uk.gov.dwp.traveltime;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LocationStoreTest {
    LocationStore locations;

    @Test
    public void addLocationAddsEntryToLocationList() {
        Map<String, String> repository = new HashMap<>();
        locations = new LocationStore(repository);
        locations.addLocation("testSite");
        assertTrue(repository.containsKey("testSite"));
    }

    @Test
    public void addLocationReturnsZeroOnSuccess() {
        Map<String, String> repository = new HashMap<>();
        locations = new LocationStore(repository);
        assertEquals(0,locations.addLocation("testSite"));
    }

    @Test
    public void addLocationReturnsNegativeOneOnFailure() {
        Map<String, String> repository = new HashMap<>();
        locations = new LocationStore(repository);
        assertEquals(-1,locations.addLocation(null));
    }
}
