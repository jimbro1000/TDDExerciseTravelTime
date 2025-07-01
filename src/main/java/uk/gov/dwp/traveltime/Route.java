package uk.gov.dwp.traveltime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
public final class Route implements RouteInterface {
    /**
     * Start location name.
     */
    @Getter
    @NonNull
    private final String start;
    /**
     * Destination location name.
     */
    @Getter
    @NonNull
    private final String destination;
    /**
     * Route elapsed time sample container.
     */
    @NonNull
    private final RouteTimeInterface routeSamples;
    /**
     * Set the elapsed route transit time.
     * @param elapsedTime String representation of elapsed time in HH:MM
     */
    public void setRouteTime(final String elapsedTime) {
        routeSamples.addSample(elapsedTime);
    }
    /**
     * Get the average elapsed route transit time.
     * @return String representation of elapsed time in HH:MM
     */
    public String getAverage() {
        return routeSamples.getAverage();
    }
}
