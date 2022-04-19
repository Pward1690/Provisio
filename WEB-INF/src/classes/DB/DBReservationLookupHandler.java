/**
 * Capstone Provisio Project
 * Green Team
 * 04/18/2022
 */

package Provisio;

import java.util.Hashtable;
import java.util.ArrayList;

class DBReservationLookupHandler {
    /**
     * Retrieve reservation with consideration
     * that they must own the reservation
     */
    public static DBReservationLookupResult getReservation(
        String reservation_id,
        String user_id
    ){
        DBReservationLookupResult reservation_result = new DBReservationLookupResult();

        try {
            DBSelectStatement select_statement = new DBSelectStatement();
            select_statement
                .fromTable("user_reservations")
                .columns(
                    new String[] {
                        "reservation_locations.location_name AS location",
                        "reservation_amenities.amenity_cost AS amenity_cost",
                        "reservation_amenities.amenity_name AS amenity_name",
                        "reservation_guest_costs.number_of_guests AS number_of_guests",
                        "user_reservations.number_of_nights AS number_of_nights",
                        "user_reservations.id AS reservation_id",
                        "user_reservations.check_in_date AS check_in_date",
                        "user_reservations.check_out_date AS check_out_date",
                        "user_provisio_points.points AS points",
                        "bedding_rates.bedding_name",
                        "bedding_rates.bedding_cost"
                    }
                )
                .join(
                    "users",
                    new String[] {
                        "users.id", "user_reservations.user_id"
                    }
                )
                .join(
                    "reservation_locations",
                    new String[] {
                        "user_reservations.provisio_location_id", "reservation_locations.id"
                    }
                )
                .join(
                    "reservation_amenities",
                    new String[] {
                        "user_reservations.amenity_id", "reservation_amenities.id"
                    }
                )
                .join(
                    "reservation_guest_costs",
                    new String[] {
                        "user_reservations.guest_cost_id", "reservation_guest_costs.id"
                    }
                )
                .join(
                    "user_provisio_points",
                    new String[] {
                        "user_reservations.user_id", "user_provisio_points.user_id"
                    }
                )
                .join(
                    "bedding_rates",
                    new String[] {
                        "user_reservations.bedding_id", "bedding_rates.id"
                    }
                )
                .where(
                    new String[] {
                        "users.id = ?", user_id
                    }
                )
                .where(
                    new String[] {
                        "user_reservations.id = ?", reservation_id
                    }
                );

            DBResult result = DBHelper.selectStatement(select_statement);
            ArrayList<Hashtable<String, String>> records = result.getRecords();

            if (
                records.size() == 0 ||
                records.get(0).size() == 0
            )
                return reservation_result;

            Hashtable<String, String> reservation_record = result.getRecords().get(0);

            return reservation_result.setReservationLookupResult(reservation_record);
        } catch (Exception e){
            System.out.println("Failed to gather reservation:");
            e.printStackTrace();

            return reservation_result;
        }
    }
}