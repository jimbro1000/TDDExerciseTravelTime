package uk.gov.dwp.traveltime;

import java.util.List;

public final class LocationStore implements LocationStoreInterface {
    /**
     * Simple list of locations - could be a set instead.
     */
    private final List<String> locations;

    /**
     * Constructor.
     * @param locationRepository inject storage object (can be prepopulated)
     */
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
    public boolean hasLocation(final String locationName) {
        return locations.contains(locationName);
    }

    @Override
    public String[] getLocations() {
        return locations.toArray(new String[0]);
    }
}
