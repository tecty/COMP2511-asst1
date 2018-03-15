import java.util.HashMap;
import java.util.Map;

public class Cinema {
	// record this cinema's situation
	private final int id;
	// this map take string to a cinema row
	private Map<String, CinemaRow> rows_map;
	public Cinema(int id) {
		// assign this cinema's id
		this.id = id;
		// initial the map for the rows
		this.rows_map = new HashMap<>();

	}
	public int getId() {
		return id;
	}
	public CinemaRow getRow(String row_name) throws RejectExcption{
		if (this.rows_map.containsKey(row_name)) {
			return (CinemaRow) this.rows_map.get(row_name);
		}
		// doesn't have this location, coudln't do this request
		throw new RejectExcption();
	}

	public void setRows(String row_name, int capacity) {
		if (!this.rows_map.containsKey(row_name)) {
			
			this.rows_map.put(row_name,new CinemaRow(row_name, capacity));
		}
	}

}
