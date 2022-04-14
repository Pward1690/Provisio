package Provisio;

public class ReservationService {
	ReservationDAO dao;
	public ReservationService()
	{
		dao = new ReservationDAO();
	}
	public int save (ReservationBean bean)
	{
		return dao.save(bean);
	}
}
