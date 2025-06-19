package uk.gov.dwp.traveltime;

public interface LocationStoreInterface {
    int addLocation(String LocationName);
    boolean hasLocation(String LocationName);
    int addRoute(String from, String to, String travelTime);
}
