package uk.gov.dwp.traveltime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

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
     * Logger instance.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Route.class);
    /**
     * Set the elapsed route transit time.
     * @param elapsedTime String representation of elapsed time in HH:MM
     */
    public void setRouteTime(final String elapsedTime) {
        MDC.put("method", "setRouteTime");
        LOGGER.info("add elapsed time sample {}", elapsedTime);
        routeSamples.addSample(elapsedTime);
        MDC.remove("method");
    }
    /**
     * Get the average elapsed route transit time.
     * @return String representation of elapsed time in HH:MM
     */
    public String getAverage() {
        MDC.put("method", "getAverage");
        LOGGER.info("get average time");
        MDC.remove("method");
        return routeSamples.getAverage();
    }
}
