package uk.gov.dwp.traveltime;

import java.util.Map;

public class LocationStore implements LocationStoreInterface {
    private final Map<String, String> locations;

    public LocationStore(final Map<String, String> repository) {
        this.locations = repository;
    }

    @Override
    public int addLocation(final String locationName) {
        this.locations.put(locationName, null);
        return -1;
    }

    @Override
    public boolean hasLocation(final String LocationName) {
        return false;
    }

    @Override
    public int addRoute(final String from, final String to, final String travelTime) {
        return -1;
    }
}
