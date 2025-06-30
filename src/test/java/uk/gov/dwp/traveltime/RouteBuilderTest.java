package uk.gov.dwp.traveltime;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class RouteBuilderTest {
    @Test
    public void returnsNullRouteIfProvidedRouteTimeIsInvalid() {
        RouteBuilder builder = RouteBuilder.getRouteBuilder();
        builder.setDefaultTimeCalculator(RouteBuilderTest.class);
        assertInstanceOf(NullRoute.class, builder.getNewRoute("any","any"));
    }
}
