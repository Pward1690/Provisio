/**
 * Capstone Provisio Project
 * Green Team
 * 04/18/2022
 * ---------------------------
 * 
 * Helper class that contains the structure of a returned reservation
 */

package Provisio;

import java.util.Hashtable;

class DBReservationLookupResult {
    private Hashtable<String, String> lookup_result;

    public DBReservationLookupResult(){}

    public DBReservationLookupResult(
        Hashtable<String, String> lookup_result
    ){
        this.lookup_result = lookup_result;
    }

    /**
     * Set reservation lookup result
     * @return this
     */
    public DBReservationLookupResult setReservationLookupResult(
        Hashtable<String, String> lookup_result
    ){
        this.lookup_result = lookup_result;
        return this;
    }

    /**
     * Check if any results were returned
     * @return Boolean
     */
    public Boolean hasResults(){
        return (
            this.lookup_result != null &&
            this.lookup_result.size() != 0
        );
    }

    /**
     * Return results
     * @return null | Hashtable<String, String>
     */
    public Hashtable<String, String> getReservationLookupResult(){
        return this.lookup_result;
    }
}