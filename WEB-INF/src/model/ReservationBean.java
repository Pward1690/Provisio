package Provisio;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.util.*;

public class ReservationBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private int reservation_id;
	private int user_id;
	private int  provisio_location_id;
	private int amenity_id; // 1 + 2 + 4
	private int bedding_id;
	private int guest_cost_id;
	private String check_in_date;
	private String check_out_date;
	private int reservation_date;
	private int ip;
	private int number_of_nights;	

// No-arg constructor
	public ReservationBean() {
	}

	public int getAmenity_id() {
		return amenity_id;
	}

	public void setAmenity_id(int amenity_id) {
		this.amenity_id = amenity_id;
	}

	public int getBedding_id() {
		return bedding_id;
	}

	public void setBedding_id(int bedding_id) {
		this.bedding_id = bedding_id;
	}

	public String getCheck_in_date() {
		return check_in_date;
	}

	public void setCheck_in_date(String check_in_date) {
		this.check_in_date = check_in_date;
	}

	public String getCheck_out_date() {
		return check_out_date;
	}

	public void setCheck_out_date(String check_out_date) {
		this.check_out_date = check_out_date; // YYYY-MM-DD
	}

	public int getNumber_of_nights() {
		return number_of_nights;
	}

	public void setNumber_of_nights(int number_of_nights) {
		this.number_of_nights = number_of_nights;
	}

	public int getReservation_id() {
		return reservation_id;
	}

	public void setReservation_id(int reservation_id) {
		this.reservation_id = reservation_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getProvisio_location_id() {
		return provisio_location_id;
	}

	public void setProvisio_location_id(int provisio_location_id) {
		this.provisio_location_id = provisio_location_id;
	}

	public int getGuest_cost_id() {
		return guest_cost_id;
	}

	public void setGuest_cost_id(int guest_cost_id) {
		this.guest_cost_id = guest_cost_id;
	}

	public int getReservation_date() {
		return reservation_date;
	}

	public void setReservation_date(int reservation_date) {
		this.reservation_date = reservation_date;
	}

	public int getIp() {
		return ip;
	}

	public void setIp(int ip) {
		this.ip = ip;
	}

}
