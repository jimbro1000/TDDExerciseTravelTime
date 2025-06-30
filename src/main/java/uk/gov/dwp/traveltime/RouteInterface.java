package uk.gov.dwp.traveltime;

public interface RouteInterface {
    String getDestination();

    void setRouteTime(String elapsedTime);

    String getAverage();

    String getStart();
}
