package uk.gov.dwp.traveltime;

public class TravelTimeCalculator {

    private final LocationStore locations;

    public TravelTimeCalculator(LocationStore store) {
        this.locations = store;
    }

    public int setTravelTime(final String fromLocation, final String toLocation, final String travelTime) {
        if (!this.locations.hasLocation(fromLocation)) {
            this.locations.addLocation(fromLocation);
        }
        if (!this.locations.hasLocation(toLocation)) {
            this.locations.addLocation(toLocation);
        }
        this.locations.addRoute(fromLocation,toLocation,travelTime);
        return 0;
    }

    public String getTravelTime(String fromLocation, String toLocation) {
        if (this.locations.hasLocation(fromLocation)){
            if (this.locations.hasLocation(toLocation)) {
                return this.locations.getRouteTime(fromLocation, toLocation);
            }
        }
        return "N/A";
    }
}
