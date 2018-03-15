
public class CinemaRow {
	private final String row;
	private final int capacity;
	public CinemaRow(String row, int capacity) {
		// create the row and capacity for this cinema
		this.row = row;
		this.capacity = capacity;
	}
	public String getRow() {
		return row;
	}
	public int getCapacity() {
		return capacity;
	}
}
