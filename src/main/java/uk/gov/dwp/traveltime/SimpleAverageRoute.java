package uk.gov.dwp.traveltime;

public class SimpleAverageRoute implements RouteTimeInterface {
    @Override
    public int addSample(String elapsedTime) {
        return ingestTime(elapsedTime);
    }

    @Override
    public String getAverage() {
        return "00:00";
    }

    private int ingestTime(String time) {
        String[] parts = time.split(":");
        int result;
        if (parts.length > 2 || parts.length == 0) {
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
}
