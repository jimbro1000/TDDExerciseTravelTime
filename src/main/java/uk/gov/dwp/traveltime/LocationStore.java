package uk.gov.dwp.traveltime;

import java.util.List;

public class LocationStore implements LocationStoreInterface {
    private final List<String> locations;

    public LocationStore(final List<String> locationRepository) {
        this.locations = locationRepository;
    }

    @Override
    public int addLocation(final String locationName) {
        if (locationName == null) {
            return -1;
        }
        this.locations.add(locationName);
        return 0;
    }

    @Override
    public boolean hasLocation(final String LocationName) {
        return locations.contains(LocationName);
    }

    @Override
    public String[] getLocations() {
        return locations.toArray(new String[0]);
    }
}
