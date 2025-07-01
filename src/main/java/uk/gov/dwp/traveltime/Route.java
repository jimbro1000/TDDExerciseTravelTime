package uk.gov.dwp.traveltime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
public class Route implements RouteInterface {
    @Getter
    @NonNull
    private final String start;
    @Getter
    @NonNull
    private final String destination;
    @NonNull
    private final RouteTimeInterface routeSamples;

    public void setRouteTime(String elapsedTime) {
        routeSamples.addSample(elapsedTime);
    }

    public String getAverage() {
        return routeSamples.getAverage();
    }
}
