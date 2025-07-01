package uk.gov.dwp.traveltime;

public interface RouteTimeInterface {
    /**
     * Add elapsed time sample.
     * @param elapsedTime String elapsed time sample in HH:MM
     * @return status outcome, 0=success
     */
    int addSample(String elapsedTime);

    /**
     * Get average elapsed time for route.
     * @return String elapsed time in HH:MM
     */
    String getAverage();
}
