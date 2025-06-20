package uk.gov.dwp.traveltime;

import java.util.Map;
import java.util.Set;

public class LocationStore implements LocationStoreInterface {
    private final Map<String, RouteStore> routes;
    private final Set<String> locations;

    public LocationStore(final Set<String> locationRepository,
                         final Map<String, RouteStore> routeRepository) {
        this.locations = locationRepository;
        this.routes = routeRepository;
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
    public int addRoute(final String from, final String to, final String travelTime) {
        return -1;
    }
}
