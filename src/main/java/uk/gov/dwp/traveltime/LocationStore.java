package uk.gov.dwp.traveltime;

public class LocationStore implements LocationStoreInterface {
    @Override
    public int addLocation(final String LocationName) {
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
