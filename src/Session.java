import java.util.ArrayList;

/**
 * 
 */

/**
 * @author tecty
 *
 */
public class Session {

	// record the ticket and row for this class
	private final ArrayList<Row> row_list;
	private ArrayList<Ticket> ticket_list;
	// record the time of this session
	private final String time;
	// record the title show in this session
	private final String title;
	
	
	public Session(ArrayList<Row> rows, String time, String title) {
		// assign the basic information about this session
		this.row_list = rows;
		this.time = time;
		this.title = title;
		
		//initial the ticket list for storing
		ticket_list = new ArrayList<Ticket>();
		
	}



	
	public Ticket book_ticket(int ticket_id, int amount) throws RejectExcption{
		for (int i = 0; i < row_list.size(); i++) {
			// record the possible start number and end number of that line.
			int start_no = 1;
			Row this_row = row_list.get(i);
			
			for (int end_no = 1; end_no < this_row.getCapacity()+1; end_no++) {
				// because the ticket number is start form 1
				if (is_free(this_row,end_no)) {

					// check the whether the amount is enough
					if(amount  ==  end_no - start_no +1) {
						// the amount is enough for current session
						// book current location for a new ticket
						Ticket ticket = new Ticket(ticket_id, this,
								this_row, start_no, end_no);
						
						// record this ticket in to this session's list
						ticket_list.add(ticket);
						
						// break the loop of searching slot
						return ticket;
					}
				} else {
					// reset the counter for search
					// start position is the next position,
					// because the current position has been occupied
					start_no = end_no +1;
				}
			}
			
		}
		
		// couln't find the enough capacity
		throw new RejectExcption();
	}
	public void change_ticket(int ticket_id, int amount) throws RejectExcption{
		// read the ticket from this session
		Ticket this_ticket = get_ticket_by_id(ticket_id);
		if(this_ticket.get_amount() >= amount) {
			// no need for reassign the location of end number
			this_ticket.setEnd_no(amount + this_ticket.getStart_no()-1);
			// break the function
			return ;
		}
		
		// cancel the ticket in the system
		// so the system can reassign the location
		cancel_ticket(this_ticket.getId());
		
		for (int i = 0; i < row_list.size(); i++) {
			// record the possible start number and end number of that line.
			int start_no = 1;
			Row this_row = row_list.get(i);
			
			for (int end_no = 1; end_no < this_row.getCapacity()+1; end_no++) {

				
				// because the ticket number is start form 1
				if (is_free(this_row,end_no)) {
//					print out the debug info
//					System.out.println("The empty amount is " + (end_no - start_no +1));
//					System.out.println("Start " + start_no+" end " + end_no);

					// check the whether the amount is enough
					if(amount  ==  end_no - start_no +1) {
						// the amount is enough for current session
						// renew the ticket's location
						this_ticket.change(this_row, start_no, end_no);
						
						// record this ticket in to this session's list
						ticket_list.add(this_ticket);
						
						// break the function of searching slot successfully;
						return ;
					}
					
				} else {
					// reset the counter for search
					// start position is the next position,
					// because the current position has been occupied
					start_no = end_no +1;
				}
			}
			
		}
		
		
		// this ticket should be preserve in the system
		ticket_list.add(this_ticket);
		
		// couln't find the enough capacity
		throw new RejectExcption();
	}
	public void cancel_ticket(int ticket_id) throws RejectExcption{
		for (int i = 0; i < ticket_list.size(); i++) {
			Ticket this_ticket	= ticket_list.get(i);
			if (this_ticket.getId() == ticket_id) {
				// remove the record from the ticket list
				ticket_list.remove(i);
				// break the function because it has successfully cancel it 
				return ;
			}
		}
		// system doesn't have this ticket
		throw new RejectExcption();
	}
	
	public Ticket get_ticket_by_id(int ticket_id) throws RejectExcption {
		for (int i = 0; i < ticket_list.size(); i++) {
			Ticket this_ticket	= ticket_list.get(i);
			if (this_ticket.getId() == ticket_id) {
				// this is the ticket we search for.
				return this_ticket;
			}
		}
		throw new RejectExcption();
	}
	
	private boolean is_free(Row row,int num) {
		// check whether the seat is occupied by another ticket
		for (int i = 0; i < ticket_list.size();i++) {
			// check whether this ticket has occupied this slot
			Ticket this_ticket = ticket_list.get(i);
			if ( this_ticket.getRow()== row && num <= this_ticket.getEnd_no() 
					&& num >= this_ticket.getStart_no()) {
				return false;
			}
		}
		return true;
	}
	
	public String getTime() {
		return time;
	}

	public ArrayList<Row> getRow_list() {
		return row_list;
	}

	public String getTitle() {
		return title;
	}
	
	public String getPrint() {
		// print the time
		String str = this.getTitle();
		// get the print info;
		for (int i = 0; i < row_list.size(); i++) {
			Row this_row = row_list.get(i);
			// append the row info 
			str += "\n"+this_row.getName()+": ";
			
			// a flag use for whether the string is printed of some ticket
			int printed = 0;
			
			for (int j = 0; j < ticket_list.size(); j++) {
				Ticket this_ticket = ticket_list.get(j);
				if (this_ticket.getRow() == this_row) {
					if (printed== 1) {
						// print the comma before the ticket information is printed
						str += ",";
					}
					
					// this ticket is belong to this row
					// try to print it out
					str +=this_ticket.getStart_no() +"-" +this_ticket.getEnd_no();
					
					// the flag is true
					printed = 1;
				}
			}
			
		}
		
		return str;
		
	}
	
}
