package uk.gov.dwp.traveltime;

public class SimpleAverageRoute implements RouteTimeInterface {
    private int sampleSum = 0;
    private int sampleCount = 0;

    @Override
    public int addSample(String elapsedTime) {
         int time = ingestTime(elapsedTime);
         if (time < 0) return -1;
         this.sampleSum += time;
         this.sampleCount++;
         return 0;
    }

    @Override
    public String getAverage() {
        if (sampleCount > 0) {
            int average = sampleSum / sampleCount;
            return intToTime(average);
        }
        return "00:00";
    }

    private int ingestTime(String time) {
        String[] parts = time.split(":");
        int result;
        if (parts.length > 2) {
            return -1;
        }
        try {
            if (parts.length == 2) {
                result = Integer.parseInt(parts[1]);
                result += Integer.parseInt(parts[0]) * 60;
            } else {
                result = Integer.parseInt(parts[0]);
            }
        } catch (NumberFormatException ex) {
            result = -1;
        }
        return result;
    }

    private String intToTime(int value) {
        int minutes = value % 60;
        int hours = (value - minutes) / 60;
        String hourString = "00" + hours;
        String minuteString = "00" + minutes;
        return hourString.substring(hourString.length()-2)
                + ":"
                + minuteString.substring(minuteString.length()-2);
    }
}
