package uk.gov.dwp.traveltime;

public interface RouteTimeInterface {
    int addSample(String elapsedTime);
    String getAverage();
}
