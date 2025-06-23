package uk.gov.dwp.traveltime;

public interface LocationStoreInterface {
    int addLocation(String LocationName);
    boolean hasLocation(String LocationName);
    String[] getLocations();
    int addRoute(String from, String to, String travelTime);
    String getRouteTime(String from, String to);
}
