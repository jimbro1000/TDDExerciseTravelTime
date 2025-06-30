package uk.gov.dwp.traveltime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
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

    /*
     * This test knows too much about the implementation, multiple layers deep
//     */
//    @Test
//    public void addRouteIntroducesRouteAndTimeInformation() {
//        Map<String, Map<String, Route>> sites = new HashMap<>();
//        locations = new LocationStore(sites);
//        locations.addRoute("London", "Paris", "03:20");
//        assertTrue(sites.containsKey("London"));
//        assertTrue(sites.containsKey("Paris"));
//        Map<String, Route> destinations = sites.get("London");
//        Route destination = destinations.get("Paris");
//        assertEquals("03:20",destination.getAverage());
//    }

//    @Test
//    public void addRouteAmendsExistingToLocations() {
//        String fromSite = "Manchester";
//        String toSite = "London";
//        HashMap<String, Route> dummy = new HashMap<>();
//        dummy.put(toSite, new Route(toSite, new SimpleAverageRoute()));
//        when(mockSites.containsKey(fromSite)).thenReturn(true);
//        when(mockSites.get(fromSite)).thenReturn(dummy);
//        locations = new LocationStore(mockSites);
//        locations.addRoute(fromSite, toSite, "03:20");
//        verify(mockSites, times(0)).put(fromSite, null);
//        verify(mockSites, times(1)).put(toSite, null);
//    }

//    @Test
//    public void addRouteAmendsExistingFromLocations() {
//        String fromSite = "Manchester";
//        String toSite = "London";
//        HashMap<String, Route> dummy = new HashMap<>();
//        dummy.put(toSite, new Route(toSite, new SimpleAverageRoute()));
//        when(mockSites.containsKey(toSite)).thenReturn(true);
//        when(mockSites.containsKey(fromSite)).thenReturn(false);
//        when(mockSites.get(fromSite)).thenReturn(dummy);
//        locations = new LocationStore(mockSites);
//        locations.addRoute(fromSite, toSite, "03:20");
//        verify(mockSites, times(1)).put(anyString(), any());
//        verify(mockSites, times(0)).put(toSite, null);
//    }

    /*
     * Fragile Test - knows too much about implementation
     */
//    @Test
//    public void getRouteTimeReturnsStoredAverageSampleTime() {
//        when(mockSites.containsKey("London")).thenReturn(true);
//        when(mockSites.containsKey("Oxford")).thenReturn(true);
//        when(mockSites.get("London")).thenReturn(mockRoute);
//        when(mockRoute.containsKey("Oxford")).thenReturn(true);
//        when(mockRoute.get("Oxford")).thenReturn(mockTime);
//        when(mockTime.getAverage()).thenReturn("01:10");
//        locations = new LocationStore(mockSites);
//        String result = locations.getRouteTime("London", "Oxford");
//        assertEquals("01:10", result);
//    }

//    @Test
//    public void getRouteTimeReturnsEmptyStringIfStartIsNotKnown() {
//        when(mockSites.containsKey("Banbury")).thenReturn(false);
//        locations = new LocationStore(mockSites);
//        String result = locations.getRouteTime("Banbury", "Oxford");
//        assertEquals("", result);
//    }

//    @Test
//    public void getRouteTimeReturnsEmptyStringIfTheDestinationIsNotKnown() {
//        when(mockSites.containsKey("Banbury")).thenReturn(true);
//        when(mockSites.containsKey("Oxford")).thenReturn(false);
//        locations = new LocationStore(mockSites);
//        String result = locations.getRouteTime("Banbury", "Oxford");
//        assertEquals("", result);
//    }

//    @Test
//    public void getRouteTimeReturnsEmptyStringIfStartAndDestinationDoNotHaveASampledRoute() {
//        when(mockSites.containsKey("London")).thenReturn(true);
//        when(mockSites.containsKey("Oxford")).thenReturn(true);
//        when(mockSites.get("London")).thenReturn(mockRoute);
//        when(mockRoute.containsKey("Oxford")).thenReturn(false);
//        locations = new LocationStore(mockSites);
//        String result = locations.getRouteTime("London", "Oxford");
//        assertEquals("", result);
//    }
}
