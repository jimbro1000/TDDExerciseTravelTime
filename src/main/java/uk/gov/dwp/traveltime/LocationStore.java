package uk.gov.dwp.traveltime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.List;

public final class LocationStore implements LocationStoreInterface {
    /**
     * Simple list of locations - could be a set instead.
     */
    private final List<String> locations;
    /**
     * Logger instance.
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(LocationStore.class);
    /**
     * Constructor.
     * @param locationRepository inject storage object (can be prepopulated)
     */
    public LocationStore(final List<String> locationRepository) {
        this.locations = locationRepository;
    }

    @Override
    public int addLocation(final String locationName) {
        int result = 0;
        MDC.put("method", "addLocation");
        MDC.put("locationName", locationName);
        if (locationName == null) {
            LOGGER.warn("invalid location name provided");
            result = -1;
        } else {
            this.locations.add(locationName);
            LOGGER.info("location added to store");
        }
        MDC.remove("locationName");
        MDC.remove("method");
        return result;
    }

    @Override
    public boolean hasLocation(final String locationName) {
        MDC.put("method", "hasLocation");
        MDC.put("locationName", locationName);
        LOGGER.info("test for location in store");
        MDC.remove("method");
        MDC.remove("locationName");
        return locations.contains(locationName);
    }

    @Override
    public String[] getLocations() {
        MDC.put("method", "getLocations");
        LOGGER.info("get all locations in store");
        MDC.remove("method");
        return locations.toArray(new String[0]);
    }
}
