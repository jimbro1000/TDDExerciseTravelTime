package uk.gov.dwp.traveltime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public final class SimpleAverageRoute implements RouteTimeInterface {
    /**
     * Minutes per hour for conversion and formatting.
     */
    public static final int MINUTES_FACTOR = 60;
    /**
     * Sum of all samples provided.
     */
    private int sampleSum = 0;
    /**
     * Counter of samples provided.
     */
    private int sampleCount = 0;
    /**
     * Logger instance.
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(SimpleAverageRoute.class);

    /**
     * Add sample elapsed time to container.
     * @param elapsedTime String elapsed time sample in HH:MM
     * @return status 0=success, -1=failure
     */
    @Override
    public int addSample(final String elapsedTime) {
        int result = 0;
        MDC.put("method", "addSample");
        LOGGER.info("add sample to route elapsed time history");
        int time = ingestTime(elapsedTime);
         if (time < 0) {
             LOGGER.warn("invalid time format submitted {}", elapsedTime);
             result = -1;
         } else {
             this.sampleSum += time;
             this.sampleCount++;
             MDC.put("samples", String.valueOf(this.sampleCount));
             LOGGER.info("elapsed time sample accepted");
             MDC.remove("samples");
         }
         MDC.remove("method");
         return result;
    }

    /**
     * Get average elapsed time for route.
     * @return String elapsed time as HH:MM
     */
    @Override
    public String getAverage() {
        String result = "00:00";
        MDC.put("method", "getAverage");
        LOGGER.info("get mean average of elapsed time samples for route");
        if (sampleCount > 0) {
            int average = sampleSum / sampleCount;
            result = intToTime(average);
        }
        MDC.remove("method");
        return result;
    }

    private int ingestTime(final String time) {
        String[] parts = time.split(":");
        int result;
        if (parts.length > 2) {
            return -1;
        }
        try {
            if (parts.length == 2) {
                result = Integer.parseInt(parts[1]);
                result += Integer.parseInt(parts[0]) * MINUTES_FACTOR;
            } else {
                result = Integer.parseInt(parts[0]);
            }
        } catch (NumberFormatException ex) {
            result = -1;
        }
        return result;
    }

    private String intToTime(final int value) {
        int minutes = value % MINUTES_FACTOR;
        int hours = (value - minutes) / MINUTES_FACTOR;
        String hourString = "00" + hours;
        String minuteString = "00" + minutes;
        return hourString.substring(hourString.length() - 2)
                + ":"
                + minuteString.substring(minuteString.length() - 2);
    }
}
