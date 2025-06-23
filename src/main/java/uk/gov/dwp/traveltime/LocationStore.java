package uk.gov.dwp.traveltime;

import java.util.HashMap;
import java.util.Map;

public class LocationStore implements LocationStoreInterface {
    private final Map<String, Map<String, RouteStore>> locations;

    public LocationStore(final Map<String, Map<String, RouteStore>> locationRepository) {
        this.locations = locationRepository;
    }

    @Override
    public int addLocation(final String locationName) {
        if (locationName == null) {
            return -1;
        }
        this.locations.put(locationName, null);
        return 0;
    }

    @Override
    public boolean hasLocation(final String LocationName) {
        return locations.containsKey(LocationName);
    }

    @Override
    public String[] getLocations() {
        return new String[0];
    }

    @Override
    public int addRoute(final String from, final String to, final String travelTime) {
        Map<String, RouteStore> routes;
        if (!locations.containsKey(from)) {
            locations.put(from, new HashMap<>());
        }
        if (!locations.containsKey(to)) {
            this.addLocation(to);
        }
        routes = locations.get(from);
        if (!routes.containsKey(to)) {
            routes.put(to, new RouteStore(to, new SimpleAverageRoute()));
        }
        RouteStore destination = routes.get(to);
        destination.setRouteTime(travelTime);
        return 0;
    }

    @Override
    public String getRouteTime(String from, String to) {
        return "";
    }
}
