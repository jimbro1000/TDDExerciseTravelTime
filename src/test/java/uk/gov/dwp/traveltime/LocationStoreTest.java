package uk.gov.dwp.traveltime;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

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
}
