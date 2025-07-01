package uk.gov.dwp.traveltime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimpleAverageRouteTest {
    public RouteTimeInterface averageRoute;

    @ParameterizedTest
    @CsvSource(
            value = {"not a time", "ab:34", "12:34:56"}
    )
    public void itReturnsMinusOneIfGivenAnInvalidTime(String sample) {
        averageRoute = new SimpleAverageRoute();
        int result = averageRoute.addSample(sample);
        assertEquals(-1,result);
    }

    @Test
    public void itReturnsMinusOneIfGivenAnEmptyString() {
        averageRoute = new SimpleAverageRoute();
        int result = averageRoute.addSample("");
        assertEquals(-1,result);
    }

    @Test
    public void itReturnsMinusOneIfGivenATimeThatIncludesDays() {
        averageRoute = new SimpleAverageRoute();
        int result = averageRoute.addSample("02:14:30");
        assertEquals(-1,result);
    }

    @ParameterizedTest
    @CsvSource(
            {"1:00","01:00","10:00","10","01","0"}
    )
    public void itReturnsAPositiveIfTheInputTimeIsValid(final String sample) {
        averageRoute = new SimpleAverageRoute();
        int result = averageRoute.addSample(sample);
        assertTrue(result >= 0);
    }

    @Test
    public void getAverageReturnsZeroIfThereAreNoSamples() {
        averageRoute = new SimpleAverageRoute();
        assertEquals("00:00", averageRoute.getAverage());
    }

    @ParameterizedTest
    @CsvSource(
            value = {"04:00/04:00","02:00,03:00/02:30"},
            delimiter = '/'
    )
    public void getAverageReturnsTheSimpleMeanOfAllSamples(String sampleList, String expected) {
        averageRoute = new SimpleAverageRoute();
        String[] samples = sampleList.split(",");
        for (String sample : samples) {
            averageRoute.addSample(sample);
        }
        assertEquals(expected, averageRoute.getAverage());
    }
}
