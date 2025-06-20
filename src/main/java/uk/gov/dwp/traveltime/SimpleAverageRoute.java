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
        int result = 0;
        try {
            for (int i = 0; i < parts.length; ++i) {
                Integer.parseInt(parts[i]);
            }
        } catch (NumberFormatException ex) {
            result = -1;
        }
        return result;
    }
}
