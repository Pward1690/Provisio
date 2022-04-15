/**
 * Capstone Provisio Project
 * Green Team
 * 04/14/2022
 */

package Provisio;

public class ReservationService {
	DBReservationHandler reservation_handler;
	public ReservationService()
	{
		this.reservation_handler = new DBReservationHandler();
	}
	public Long save (ReservationBean bean)
	{
		return this.reservation_handler.save(bean);
	}
}
