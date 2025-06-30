package uk.gov.dwp.traveltime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LocationStoreTest {
    LocationStore locations;

    @Mock
    Map<String, Map<String, Route>> mockSites;
    @Mock
    Map<String, Route> mockRoute;
    @Mock
    Route mockTime;

    @BeforeEach
    public void setup() {
        //noinspection unchecked
        reset(mockSites);
        //noinspection unchecked
        reset(mockRoute);
        //noinspection unchecked
        reset(mockTime);
    }

    @Test
    public void addLocationAddsEntryToLocationList() {
        List<String> sites = new ArrayList<>();
        locations = new LocationStore(sites);
        locations.addLocation("testSite");
        assertTrue(sites.contains("testSite"));
    }

    @Test
    public void addLocationReturnsZeroOnSuccess() {
        List<String> sites = new ArrayList<>();
        locations = new LocationStore(sites);
        assertEquals(0,locations.addLocation("testSite"));
    }

    @Test
    public void addLocationReturnsNegativeOneOnFailure() {
        List<String> sites = new ArrayList<>();
        locations = new LocationStore(sites);

        assertEquals(-1,locations.addLocation(null));
    }

    @Test
    public void hasLocationReturnsFalseIfLocationIsNotRecorded() {
        List<String> sites = new ArrayList<>();
        locations = new LocationStore(sites);
        assertFalse(locations.hasLocation("Leeds"));
    }

    @Test
    public void hasLocationReturnsTrueIfLocationIsRecorded() {
        List<String> sites = new ArrayList<>();
        sites.add("Blackpool");
        locations = new LocationStore(sites);
        assertTrue(locations.hasLocation("Blackpool"));
    }

    @Test
    public void getLocationsReturnsAnEmptyListOfAllLocationsWhenNoneAreKnown() {
        locations = new LocationStore(new ArrayList<>());
        String[] result = locations.getLocations();
        assertEquals(0, result.length);
    }

    @Test
    public void getLocationsReturnsAListOfAllKnownLocations() {
        List<String> sites = new ArrayList<>();
        sites.add("Leeds");
        sites.add("Newcastle");
        locations = new LocationStore(sites);
        String[] result = locations.getLocations();
        assertEquals(2, result.length);
    }

}
