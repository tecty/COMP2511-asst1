import java.util.ArrayList;

/**
 * 
 */

/**
 * @author tecty
 *
 */
public class Cinema {
	ArrayList<Row> row_list;
	ArrayList<Session> session_list;
	
	public Cinema() {
		// initial the array for record row and session
		row_list = new ArrayList<Row>();
		session_list = new ArrayList<Session>();
	}
	
	public void create_row(String row_name, int capacity) {
		// add a new row in this cinema;
		row_list.add(new Row(row_name, capacity));
	}
	public Row get_row(String row_name) throws RejectExcption {
		for (int i = 0; i < row_list.size(); i++) {
			if(row_list.get(i).getName() == row_name) {
				return row_list.get(i);
			}
		}
		throw new RejectExcption();
	} 
	
	public Session create_session(String session_time,String title) {
		// create a session for this cinema
		Session ret_session =new Session(this.row_list, session_time,title);
		session_list.add(ret_session);
		return ret_session;
	}

	public Session get_session(String session_time) throws RejectExcption {
//		System.out.println("session size "+ session_list.size()+" input time("+session_time+")");
		for (int i = 0; i < session_list.size(); i++) {
			if(session_time.equals(session_list.get(i).getTime()) ) {
//				System.out.println("got the required session");
				return session_list.get(i);
			}
		}
		throw new RejectExcption();
	}
	
	
}
