package uk.gov.dwp.traveltime;

public interface RouteStoreInterface {
    RouteInterface addRoute(String from, String to);
    RouteInterface getRoute(String from, String to);
}
