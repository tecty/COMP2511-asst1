
public class RowCapacity {
	private int capacity_array[];
	public RowCapacity(int capacity) {
		// set the array by the capacity in this row
		this.capacity_array = new int[capacity];
		for (int i = 0; i < capacity_array.length; i++) {
			// Initialize this array by no one seated here.
			capacity_array[i] = -1;
		}
	}
	
	public int checkCapacity(int amount) {
		// decrease till 0 means this row has required capacity
		int satisfy_counter = amount;
		
		// possible location that has required capacity
		int start_location = 0;
		
		// check whether this row has that much capacity.
		for (int i = 0; i < capacity_array.length; i++) {
			if (capacity_array[i] == -1) {
				// this seat hasn't booked
				satisfy_counter --;
			}else {
				// this has booked, reset the counter and start location.
				start_location = i+1;
				satisfy_counter = amount;
			}
			
			if (satisfy_counter ==0) {
				// the location has the long enough seat.
				return start_location;
			}			
			
		}
		return -1;
	}
	
	public boolean setTicket(int ticket_id, int amount) {
		// calculating the start location
		int start_location = checkCapacity(amount);
		if (start_location<0) {
			// couldn't have location meets the requirement;
			return false;
		}
		for (int i = start_location; i < amount +start_location ; i++) {
			// book the seat for that ticket from start location to amount 
			capacity_array[i] = ticket_id;
		}
		return true;
	}
	
	public boolean changeTicket(int ticket_id, int amount) {
		
	}
	
}
