package uk.gov.dwp.traveltime;

/**
 * Repository specification for locations.
 */
public interface LocationStoreInterface {
    /**
     * Add location to repository.
     * @param locationName location name
     * @return result status, 0 = success
     */
    int addLocation(String locationName);

    /**
     * Check if location exists in repository.
     * @param locationName location name
     * @return true when present
     */
    boolean hasLocation(String locationName);

    /**
     * Get list of all stored locations.
     * @return unsorted string array of locations
     */
    String[] getLocations();
}
