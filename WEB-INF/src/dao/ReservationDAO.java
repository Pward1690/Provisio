package Provisio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReservationDAO {
	Database db;
	public ReservationDAO()
	{
		
	}
	public int save(ReservationBean bean) {
		try {
			Database db = new Database();
			if(bean.getReservation_id()==0) // insert new reservation
			{
				String insert = "INSERT INTO `provisio`.`user_reservations` ("
						+ "`user_id`,"
						+ "`provisio_location_id`,"
						+ "`amenity_id`,"
						+ "`guest_cost_id`,"
						+ "`bedding_id`,"
						+ "`check_in_date`,"
						+ "`check_out_date`,"
						+ "`reservation_date`,"
						+ "`ip`,"
						+ "`number_of_nights`)"
						+ "VALUES"
						+ "(?,?,?,?,?,?,?,?,?,?)";  //VALUES(0,1,2,0,null,null,0,0,1)
				PreparedStatement ps = db.prepare(insert);
				ps.setInt(1, bean.getUser_id());
				ps.setInt(2, bean.getProvisio_location_id());
				ps.setInt(3, bean.getAmenity_id());
				ps.setInt(4, bean.getGuest_cost_id());
				ps.setInt(5, bean.getBedding_id());
				ps.setString(6, bean.getCheck_in_date());  // "2022-04-07"
				ps.setString(7, bean.getCheck_out_date());
				ps.setInt(8, bean.getReservation_date());
				ps.setInt(9, bean.getIp());
				ps.setInt(10, bean.getNumber_of_nights());
				ps.execute();
				ResultSet rs = ps.getGeneratedKeys();
				
				int id= 0;
				if (rs.next())
				{
					id= rs.getInt(1);
				}
				ps.close();
				insert = "INSERT INTO `provisio`.`user_provisio_points`(user_id, points) VALUES (?,?)";
				ps = db.prepare(insert);
				ps.setInt(1,  bean.getUser_id());
				ps.setInt(2,150 * bean.getNumber_of_nights());
				ps.execute();

				return id;
			}else // update
			{
				// implementation pending if needed.
			}
			return 1;
		} catch (Exception ex) {
			ex.printStackTrace();
			return -1;
		}
	}
}
