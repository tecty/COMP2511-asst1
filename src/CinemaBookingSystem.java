import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author tecty
 *
 */
public class CinemaBookingSystem {
	private static ArrayList<Cinema> cinema_list;
	private static ArrayList<Ticket> ticket_list;
	
	/**
	 * main method to interact with this system
	 * @param args
	 */
	public static void main(String[] args) {
		
	      Scanner sc = null;
	      // initial the list store the system's information
	      cinema_list = new ArrayList<Cinema>();
	      ticket_list = new ArrayList<Ticket>();
	      
	      try
	      {
	    	  // args[0] is the first command line argument
	          sc = new Scanner(new File(args[0]));    

	          
	          // read the input file by scan all the lines
	          while (sc.hasNextLine()) {
	        	// get the input by line 
				String line = (String) sc.nextLine();
				
//				System.out.println("ThisLine: "+ line );
				// split it as command array
				String[] command = line.split(" ");
				// read the command to call correspond function
				switch (command[0]) {
				case "Cinema":
					// call the wrapper from this class
					// add cinema and add a row
					addCinema(Integer.parseInt(command[1]),
							command[2],
							Integer.parseInt(command[3]));
					break;
				case "Session":
					// call the wrapper from this class
					// add a session of specified cinema
					addSession(Integer.parseInt(command[1]),
							command[2], command[3]);
					break;
				case "Request":
					// booking a new ticket into the system
					bookTicket(Integer.parseInt(command[1]),
							Integer.parseInt(command[2]),
							command[3],
							Integer.parseInt(command[4]));
					break;
				case "Change":
					// call the change wrapper
					changeTicket(Integer.parseInt(command[1]), 
							Integer.parseInt(command[2]),
							command[3],
							Integer.parseInt(command[4]));
					break;
				case "Cancel":
					// call the cancel wrapper
					cancelTicket(Integer.parseInt(command[1]));
					
					break;
				case "Print":
					// call the print session wrapper
					printSession(
							Integer.parseInt(command[1]),
							command[2]
					);
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

	
	public static void addCinema(int id, String row, int capacity) {
		Cinema the_cinema =  null;
		if(id > cinema_list.size()) {
			// create a new cinema and store it into this system
			the_cinema = new Cinema();
			cinema_list.add(id -1, the_cinema);
			
		}
		else {
			// the sys has this cinema;
			// Note: the id is start from 1
			the_cinema = cinema_list.get(id-1);
		}
		
		// store the rows information into the system
		the_cinema.create_row(row, capacity);
		
	}

	public static void addSession(int cinema_id, String time, String title) {
		// find the cinema by the id
		Cinema the_cinema = cinema_list.get(cinema_id-1);
		// in the cinema, we create a session
		the_cinema.create_session(time, title);
		
		try {
			the_cinema.get_session(time).getPrint();
		} catch (RejectExcption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void bookTicket(int ticket_id, int cinema_id,  String time, int amount ) {
		
		try {
			// find the session then create the ticket
			Ticket the_ticket = cinema_list.get(cinema_id-1).get_session(time).book_ticket(ticket_id, amount);
			
			// save the ticket into system
			// NOTE: the ticket id start from 1
			ticket_list.add(the_ticket);
			
			// print the information of the ticket
			System.out.println("Booking "+the_ticket.getPrint());
			
		} catch (RejectExcption e) {
			// reject by the system
			System.out.println("Booking rejected");
		}
	}
	
	public static void changeTicket(int ticket_id, int cinema_id,  String time, int amount ) {
		Session to_session;
		try {
			to_session = cinema_list.get(cinema_id-1).get_session(time);
		} catch (RejectExcption e1) {
			// reject the change  
			System.out.println("Change rejected");
			
			return ;
		}
		
		for (int i = 0; i < ticket_list.size(); i++) {
			Ticket the_ticket = ticket_list.get(i);
			if (the_ticket.getId() == ticket_id) {
				if (to_session == the_ticket.getSession()) {
					// ticket change within a session
					try {
						to_session.change_ticket(ticket_id, amount);
					} catch (RejectExcption e) {
						// reject the change  
						System.out.println("Change rejected");
						
						return ;
					}
					
				} else {
					try {
						// ticket change across a session
						// try to book a new ticket for that session
						Ticket new_ticket = to_session.book_ticket(ticket_id, amount);
						// new ticket success, cancel the old ticket from original session
						the_ticket.getSession().cancel_ticket(ticket_id);
						// store the new_ticket into ticket list
						ticket_list.add(i, new_ticket);
						
					} catch (RejectExcption e) {
						// reject the change  
						System.out.println("Change rejected");
						
						return ;
					}
					
					
				}
				
				// print the information of the ticket change
				System.out.println("Change "+the_ticket.getPrint());
				
				// break the function
				return ;
			}
		}
	}
	
	public static void cancelTicket(int ticket_id) {
		for (int i = 0; i < ticket_list.size(); i++) {
			Ticket the_ticket = ticket_list.get(i);
			if (the_ticket.getId() == ticket_id) {
				try {
					// remove this ticket in session 
					the_ticket.getSession().cancel_ticket(ticket_id);
				} catch (RejectExcption e) {
					// do nothing
				}
				// remove the ticket in the system
				ticket_list.remove(i);
				
				// print the successfully cancel
				System.out.println("Cancel "+ticket_id);
				
				// break the function
				return ;
			}
		}
		// Reject since it don't have this ticket
		System.out.println("Cancel rejected");
	}
	
	public static void printSession(int cinema_id, String time) {
		// find the session then print the session
		try {
			Session the_session = cinema_list.get(cinema_id-1).get_session(time);
			System.out.println(
				the_session.getPrint()
			);
		} catch (RejectExcption e) {
			// do nothing
		}
	}
}