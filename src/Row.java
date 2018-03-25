/**
 * 
 */

/**
 * @author tecty
 * simple class for record row name and capacity
 */
public class Row {
	private final String name;
	private final int capacity;
	public Row(String name, int capacity) {
		// create the name and capacity for the current line
		this.name = name;
		this.capacity = capacity;
		
	}
	public String getName() {
		return name;
	}
	public int getCapacity() {
		return capacity;
	}
}
