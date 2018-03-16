import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author tecty
 *
 */
public class CinemaBookingSystem {
	private List<Cinema> cinema_list;
	private List<Session> ticket_to_session;
	
	/**
	 * main method to interact with this system
	 * @param args
	 */
	public static void main(String[] args) {
	      Scanner sc = null;
	      try
	      {
	          sc = new Scanner(new File(args[0]));    // args[0] is the first command line argument

	          
	          // read the input file by scan all the lines
	          while (sc.hasNextLine()) {
	        	// get the input by line 
				String line = (String) sc.nextLine();
				// split it as command array
				String[] command = line.split(" ");
				// read the command to call correspond function
				switch (command[0]) {
				case "Cinema":
					
					break;
				case "Session":
					
					break;
				case "Request":
					
					break;
				case "Change":
					
					break;
				case "Cancel":
					
					break;
				case "Print":
					
					break;

				default:
					break;
				}
				
				
				
			}
	      }
	      catch (FileNotFoundException e)
	      {
	          System.out.println(e.getMessage());
	      }
	      finally
	      {
	          if (sc != null) sc.close();
	      }
	}
	public Date getDateByInput(String input) throws RejectExcption {
        // the imput format of time
        SimpleDateFormat time_formate = new SimpleDateFormat("HH:MM");
        //phrase the input to return 
		try {
			return time_formate.parse(input);
		} catch (ParseException e) {
			// panic: couldn't phrase the time
			throw new RejectExcption();
		}
	}
	public Session getSessionByTicketID(int ticket_id) {
		// because ticket is start from 1;
		// the index would need to reduce 1 in the system
		return ticket_to_session.get(ticket_id);
	}
	public void setSessionToTicket(int ticket_id, Session this_session) {
		// because ticket is start from 1;
		// the index would need to reduce 1 in the system
		this.ticket_to_session.add(ticket_id, this_session);
	}
	public Cinema getCinemaByCinemaID(int cinema_id) {
		// quickly get cinema by its id
		return cinema_list.get(cinema_id);
	}
	public void setCinema_list(int cinema_id,Cinema cinema) {
		// set a relationship by cinema and its id in the system
		this.cinema_list.add(cinema_id, cinema);
	}
	
}
