package uk.gov.dwp.traveltime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TravelTimeCalculatorTest {

    @Mock
    LocationStore locationStore;

    private TravelTimeCalculator calculator;

    @BeforeEach
    public void setup() {
        calculator = new TravelTimeCalculator(locationStore);
    }

    @Test
    @DisplayName("Given a new pair of locations, set travel time adds them to the store")
    public void setTravelTimeAddsNewLocationDestinationPair() {
        int result = calculator.setTravelTime("Manchester", "Leeds", "02:00");
        assertEquals(0, result);
        verify(locationStore, times(1)).addLocation("Manchester");
        verify(locationStore, times(1)).addLocation("Leeds");
        verify(locationStore, times(1)).addRoute("Manchester","Leeds","02:00");
    }
}
