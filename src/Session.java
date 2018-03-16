import java.util.Date;
import java.util.List;
import java.util.Map;

public class Session {
	private final Date time;
	private final String movie;
	private List<Ticket> ticket_list;
	private final Cinema cinema;
	private final Map<String, int> capacity;
	public Session(Date time, String movie, Cinema cinema) {
		// setup the basic information for this system
		this.time = time;
		this.movie = movie;
		this.cinema = cinema;
	}
	
	
	


	public void setTicket(int ticket_id, int count) {
		
	}


	public Date getTime() {
		return time;
	}


	public String getMovie() {
		return movie;
	}


	public Cinema getCinema() {
		return cinema;
	}


	/**
	 * Print this session's information
	 * @pre == @post
	 */
	public void printSession() {
		// TODO Auto-generated method stub
		System.out.println();
	}
}
