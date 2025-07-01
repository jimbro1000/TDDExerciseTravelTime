# Travel Time Service

This is an example implementation of the Travel Time Service kata as
described in my TDD training kata suite -
https://github.com/jimbro1000/tdd-training-katas/tree/master/src/site/markdown/katas/TravelTimeCalculator

## Requirements

The calculator accepts two locations and returns the predicted travel time
between those two points. The interface for finding the travel time is a
method "getTravelTime" and accepts two string values:

    TravelTimeCalculator.getTravelTime(
        travelFromLocation, 
        travelToLocation
    )

It is assumed that the method of travel is simply the fastest option and that
the time to travel remains the same regardless of the time of day. Travel time
is the same regardless of direction (A to B or B to A).

The travel time is returned as a string in the format "hh:mm"

If a travel time for the given destinations has not been previously submitted or
the from and to locations are the same the result is a zero time ("00:00").

A list of all recognised locations can be obtained from the calculator through
a method "getTravelLocations" that returns string with a comma separated list
of locations in alphabetical order:

    TravelTimeCalculator.getTravelLocations()

A list of possible destinations for a given location can be obtained by using a
method "getTravelDestinations". This method accepts the queried location as a
string. The output is a comma-separated list of destination locations:

    TravelTimeCalculator.getTravelDestinations(location)

The calculator also has a method to update the travel time based on history
"setTravelTime" that accepts two string values for locations and a third string
value holding the travel time in the format "hh:mm":

    TravelTimeCalculator.setTravelTime(
        travelFromLocation, 
        travelToLocation, 
        travelTime
    )

When setting the travel time, if a location is not recognised it is added to the
dictionary of locations and the time taken stored verbatim. If both locations
are recognised the average of the existing and the supplied travel times are
kept. The calculated average is always rounded up to the nearest minute.

An attempt to set the from and to locations the same is ignored.

To illustrate this, given the travel time data:

| Locations  | Blackpool | Leeds | London | Manchester | Newcastle |
| ---------- | --------- | ----- | ------ | ---------- | --------- |
| Blackpool  | NA        | 1:39  | 3:21   | 1:06       | 2:37      |
| Leeds      |           | NA    | 2:15   | 0:56       | 1:24      |
| London     |           |       | NA     | 1:00       | 1:10      |
| Manchester |           |       |        | NA         | 2:28      |
| Newcastle  |           |       |        |            | NA        |

Calling `getTravelTime("Leeds","Manchester")` returns the value `"0:56"`

Calling `getTravelLocations()` returns the value
`"Blackpool,Leeds,London,Manchester,Newcastle"`

Calling `setTravelTime("Leeds","London","2:17")` modifies the data such that
calling `getTravelTime("Leeds","London")` returns the value `"2:16"`

Calling `setTravelTime("London","Birmingham","1:22")` adds Birmingham as a
destination so that `getTravelLocations()` returns the value
`"Birmingham,Blackpool,Leeds,London,Manchester,Newcastle"`. Calling
`getTravelTime("Birmingham","London")` returns the value `"1:22"`. Calling
`getTravelTime("Leeds","Birmingham")` returns the value `"0:00"`.

## Implementation

The implementation is written in Java (using JDK-20 at the time), with
an aim to illustrating evolving design while adhering to design standards
and patterns.

Maven has been used as the dependency manager and build orchestrator.
The build outcome includes a site report detailing code style compliance,
code coverage, PMD compliance, Java Docs, etc. The regular build can
be exercised using `mvn clean compile test site`.

The build can optionally perform mutation testing to verify test
composure. This can be exercised using `mvn test-compile 
org.pitest:pitest-maven:mutationCoverage`.

### Patterns used

For the purposes of explicit demonstration, a number of different
design patterns have been used in the implementation

* Strategy Pattern
* Builder Pattern
* NullObject Pattern
* Singleton Pattern

## Notes

The result of getting an unknown route is "N/A" instead of "0:00".
Returning the default zero time is misleading.

The requirements do not specify the handling of type case in location
names or if routes are reciprocal (the same in both directions). The
assumption to this point is that locations are case-sensitive and
routes are non-reciprocal.
