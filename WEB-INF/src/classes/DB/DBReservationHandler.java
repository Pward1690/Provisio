/**
 * Capstone Provisio Project
 * Green Team
 * 04/14/2022
 */

package Provisio;

import java.util.ArrayList;
import java.util.Arrays;

public class DBReservationHandler {
	public DBReservationHandler(){}

	public Long save(ReservationBean bean) {
		System.out.println("RES inserting values.");
		DBInsertStatement insert_statement = new DBInsertStatement();

		ArrayList<String> column_names = new ArrayList<String>(
			Arrays.asList(
				"user_id",
				"provisio_location_id",
				"amenity_id",
				"guest_cost_id",
				"bedding_id",
				"check_in_date",
				"check_out_date",
				"reservation_date",
				"ip",
				"number_of_nights"
			)
		);

		ArrayList<String> values = new ArrayList<String>(
			Arrays.asList(
				Integer.toString(bean.getUser_id()),
				Integer.toString(bean.getProvisio_location_id()),
				Integer.toString(bean.getAmenity_id()),
				Integer.toString(bean.getGuest_cost_id()),
				Integer.toString(bean.getBedding_id()),
				bean.getCheck_in_date(),
				bean.getCheck_out_date(),
				bean.getReservation_date(),
				bean.getIp(),
				Integer.toString(bean.getNumber_of_nights())
			)
		);

		insert_statement
				.intoTable("user_reservations")
				.columns(column_names)
				.values(values);

		Long insert_id = null;
		try {
			insert_id = DBHelper.insertStatement(insert_statement);
		} catch (Exception e){
			System.out.println("Failed to run reservation queries: ");
			e.printStackTrace();
			return Long.valueOf(-1);
		}

		try {
			DBUpdateStatement update_statement = new DBUpdateStatement();

			update_statement
				.table("user_provisio_points")
				.set(
					new String[][] {
						new String[] {
							"points", Integer.toString(bean.getNumber_of_nights() * 150)
						}
					}
				)
				.where(
					new String[] {
						"user_id = ?", Integer.toString(bean.getUser_id())
					}
				);
			
			DBHelper.updateStatement(update_statement);
		} catch (Exception e){
			System.out.println("Failed to run reservation queries: ");
			e.printStackTrace();
			return Long.valueOf(-1);
		}

		return insert_id;
	}
}
