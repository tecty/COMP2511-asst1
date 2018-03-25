/**
 * 
 */

/**
 * @author tecty
 *
 */
public class Ticket {
	private final int id;
	private final Session session;
	private Row row;
	private int start_no;
	private int end_no;
	public Ticket(int id, Session session,
			Row row, int start_no, int end_no) {
		// set the id and session's information
		this.id = id;
		this.session = session;
		// set the booking information about this ticket by calling the change function
		this.change(row, start_no, end_no);
	}
	
	public void change(Row row, int start_no, int end_no) {
		// change the information about this ticket
		// set the booked information about this ticket
		this.row = row;
		this.start_no = start_no;
		this.end_no = end_no;
	}
	
	public Row getRow() {
		return row;
	}
	public int getStart_no() {
		return start_no;
	}
	public void setEnd_no(int end_no) {
		// simply set the end number is provided
		this.end_no = end_no;
	}
	
	public int getEnd_no() {
		return end_no;
	}
	public int get_amount() {
		// return how much capacity that occupied by this ticket
		return this.start_no - this.end_no +1;
	}
	
	public int getId() {
		return id;
	}
	public Session getSession() {
		return session;
	}
	
	public String getPrint(){
		
		// return the print info of this ticket 
		String ret_str = getId()+" ";
		ret_str       += getRow().getName()+getStart_no()+"-";
		ret_str       += getRow().getName()+getEnd_no();
		return ret_str ;
	}
}
