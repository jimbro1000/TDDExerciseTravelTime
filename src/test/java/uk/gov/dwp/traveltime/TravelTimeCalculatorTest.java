package uk.gov.dwp.traveltime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TravelTimeCalculatorTest {

    @Mock
    LocationStore locationStore;
    @Mock
    RouteStore routeStore;
    @Mock
    Route route;

    private TravelTimeCalculator calculator;

    @BeforeEach
    public void setup() {
        //noinspection unchecked
        reset(locationStore);
        calculator = new TravelTimeCalculator(locationStore, routeStore);
    }

    @Test
    @DisplayName("Given a new pair of locations, set travel time adds them to the store")
    public void setTravelTimeAddsNewLocationDestinationPair() {
        when(routeStore.addRoute("Manchester", "Leeds")).thenReturn(route);
        int result = calculator.setTravelTime("Manchester", "Leeds", "02:00");
        assertEquals(0, result);
        verify(locationStore, times(1)).addLocation("Manchester");
        verify(locationStore, times(1)).addLocation("Leeds");
        verify(routeStore, times(1)).addRoute("Manchester","Leeds");
        verify(route, times(1)).setRouteTime("02:00");
    }

    @Test
    @DisplayName("Given an existing pair of locations, a new average is calculated")
    public void setTravelTimeRecalculatesAverageForExistingRoute() {
        when(locationStore.hasLocation("Manchester")).thenReturn(true);
        when(locationStore.hasLocation("Leeds")).thenReturn(true);
        when(routeStore.addRoute("Manchester", "Leeds")).thenReturn(route);
        int result = calculator.setTravelTime("Manchester", "Leeds", "03:00");
        assertEquals(0, result);
        verify(locationStore, times(0)).addLocation("Manchester");
        verify(locationStore, times(0)).addLocation("Leeds");
        verify(routeStore, times(1)).addRoute("Manchester", "Leeds");
        verify(route).setRouteTime("03:00");
    }

    @Test
    @DisplayName("Given an existing pair of locations, the average travel time is returned")
    public void getTravelTimeReturnsAverageJourneyTime() {
        when(locationStore.hasLocation("London")).thenReturn(true);
        when(locationStore.hasLocation("Blackpool")).thenReturn(true);
        when(routeStore.getRoute("London","Blackpool")).thenReturn(route);
        when(route.getAverage()).thenReturn("03:21");
        String result = calculator.getTravelTime("London", "Blackpool");
        assertEquals("03:21", result);
        verify(locationStore, times(1)).hasLocation("London");
        verify(locationStore, times(1)).hasLocation("Blackpool");
    }

    /**
     * Really awful mocking but Mockito forces this approach without using lenientMocking flag
     */
    @ParameterizedTest
    @CsvSource(
            {
                    "London,Oxford,true,false",
                    "Oxford,Banbury,false,false",
                    "Oxford,Manchester,false,true"
            }
    )
    @DisplayName("Given one or both of the location pair is unknown, the travel time returned is N/A")
    public void getTravelTimeReturnsEmptyTimeWhenOneLocationIsNotKnown(String from, String to, boolean fromKnown, boolean toKnown) {
        when(locationStore.hasLocation(from)).thenReturn(fromKnown);
        if (fromKnown) {
            when(locationStore.hasLocation(to)).thenReturn(toKnown);
        }
        String result = calculator.getTravelTime(from, to);
        assertEquals("N/A", result);
        verify(locationStore, times(1)).hasLocation(from);
        if (fromKnown) {
            verify(locationStore, times(1)).hasLocation(to);
        }
    }
}
