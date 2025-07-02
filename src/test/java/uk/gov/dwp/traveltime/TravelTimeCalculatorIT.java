package uk.gov.dwp.traveltime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TravelTimeCalculatorIT {
    private TravelTimeCalculator calculator;
    private LocationStoreInterface locations;
    private RouteStoreInterface routes;

    @BeforeEach
    public void setup() {
        locations = new LocationStore(new ArrayList<>());
        routes = new RouteStore(new HashMap<>());
        calculator = new TravelTimeCalculator(
                locations,
                routes
        );
        prepareInitialDataSet();
    }

    @Test void verifyGetTravelTimeForUnknownRoute() {
        assertEquals("N/A", calculator.getTravelTime("London", "Sheffield"));
    }

    @Test
    public void verifySetTravelTime() {
         int result = calculator.setTravelTime("Leeds", "London", "02:17");
         assertEquals(0,result);
         String time = calculator.getTravelTime("Leeds", "London");
         assertEquals("02:16", time);
    }

    @Test
    public void verifyGetTravelTime() {
        assertEquals("02:15", calculator.getTravelTime("Leeds", "London"));
    }

    private void prepareInitialDataSet() {
        locations.addLocation("London");
        locations.addLocation("Birmingham");
        locations.addLocation("Manchester");
        locations.addLocation("Sheffield");
        locations.addLocation("Leeds");
        locations.addLocation("Blackpool");
        locations.addLocation("Newcastle");
        routes.addRoute("Blackpool", "Leeds").setRouteTime("01:39");
        routes.addRoute("Blackpool", "London").setRouteTime("03:21");
        routes.addRoute("Blackpool", "Manchester").setRouteTime("01:06");
        routes.addRoute("Blackpool", "Newcastle").setRouteTime("02:37");
        routes.addRoute("Leeds", "London").setRouteTime("02:15");
        routes.addRoute("Leeds", "Manchester").setRouteTime("00:56");
        routes.addRoute("Leeds", "Newcastle").setRouteTime("01:24");
        routes.addRoute("London", "Manchester").setRouteTime("01:00");
        routes.addRoute("London", "Newcastle").setRouteTime("01:10");
        routes.addRoute("Manchester", "Newcastle").setRouteTime("02:28");
    }
}
