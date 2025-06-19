package uk.gov.dwp.traveltime;

public class TravelTimeCalculator {

    private final LocationStore locations;

    public TravelTimeCalculator(LocationStore store) {
        this.locations = store;
    }

    public int setTravelTime(final String fromLocation, final String toLocation, final String travelTime) {
        this.locations.addLocation(fromLocation);
        this.locations.addLocation(toLocation);
        this.locations.addRoute(fromLocation,toLocation,travelTime);
        return 0;
    }
}
