package uk.gov.dwp.traveltime;

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
     * Add sample elapsed time to container.
     * @param elapsedTime String elapsed time sample in HH:MM
     * @return status 0=success, -1=failure
     */
    @Override
    public int addSample(final String elapsedTime) {
         int time = ingestTime(elapsedTime);
         if (time < 0) {
             return -1;
         }
         this.sampleSum += time;
         this.sampleCount++;
         return 0;
    }

    /**
     * Get average elapsed time for route.
     * @return String elapsed time as HH:MM
     */
    @Override
    public String getAverage() {
        if (sampleCount > 0) {
            int average = sampleSum / sampleCount;
            return intToTime(average);
        }
        return "00:00";
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
