import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 
 */

/**
 * @author tecty
 *
 */
class SessionTest {
	// preserve a session in the system
	Session session;
	ArrayList<Row> rows;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		// create the rows in this session 
		rows = new ArrayList<Row>();
		rows.add(new Row("A",5));
		rows.add(new Row("B",10));
		
		// create the session in for the test
		session = new Session(rows, "12:30","Inception");
	}

	/**
	 * Test method for {@link Session#Session(java.util.ArrayList, java.lang.String)}.
	 */
	@Test
	void testSession() {
		// check out whether the time and rows is correct 
		assertEquals("12:30", session.getTime());
		assertEquals(rows, session.getRow_list());
	}

	/**
	 * Test method for {@link Session#book_ticket(int, int)}.
	 */
	@Test
	void testBook_ticket() {
		// only can get 3 * 5 ticket in the system;
		try {
			
			Ticket ticket1 = session.book_ticket(1, 5);
			Ticket ticket2 = session.book_ticket(2, 5);
			Ticket ticket3 = session.book_ticket(3, 5);
			
			// check whether the ticket is distributed by rules
			assertEquals(1,ticket1.getId());
			assertEquals("A", ticket1.getRow().getName());
			assertEquals(1, ticket1.getStart_no());
			assertEquals(5, ticket1.getEnd_no());
			assertEquals(2,ticket2.getId());
			assertEquals("B", ticket2.getRow().getName());
			assertEquals(1, ticket2.getStart_no());
			assertEquals(5, ticket2.getEnd_no());
			assertEquals(3,ticket3.getId());
			assertEquals("B", ticket3.getRow().getName());
			assertEquals(6, ticket3.getStart_no());
			assertEquals(10, ticket3.getEnd_no());
		} catch (RejectExcption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			session.book_ticket(3, 5).getId();
			// shouldn't be here 
			assertEquals(true, false);
		} catch (RejectExcption e) {
			// TODO Auto-generated catch block
			// pass
		}
	}

	/**
	 * Test method for {@link Session#change_ticket(int, int)}.
	 */
	@Test
	void testChange_ticket_to_bigger() {
		try {
			assertEquals(1,session.book_ticket(1, 5).getId());
			// this ticket should be assigned to another row
			session.change_ticket(1, 7);
			// the ticket change to another row 
			Ticket this_ticket = session.get_ticket_by_id(1);
			assertEquals("B", this_ticket.getRow().getName());
			assertEquals(1, this_ticket.getStart_no());
			assertEquals(7, this_ticket.getEnd_no());
			
			// I can assign a new ticket in row A
			assertEquals(2,session.book_ticket(2, 5).getId());

			
		} catch (RejectExcption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link Session#change_ticket(int, int)}.
	 */
	@Test
	void testChange_ticket_to_bigger_with_canceled_space() {
		try {
			assertEquals(1,session.book_ticket(1, 5).getId());
			assertEquals(2, session.book_ticket(2, 4).getId());
			assertEquals(3, session.book_ticket(3, 2).getId());
			assertEquals(4, session.book_ticket(4, 2).getId());
			
			// cancel ticket 1
			session.cancel_ticket(1);
			session.cancel_ticket(3);
			
			
			Ticket this_ticket = session.get_ticket_by_id(2);
			assertEquals("B", this_ticket.getRow().getName());
			assertEquals(1, this_ticket.getStart_no());
			assertEquals(4, this_ticket.getEnd_no());
			
			
			
			
			// ticket2 should be assigned to current row 
			session.change_ticket(2, 5);
			// the ticket change to B1-5 
			assertEquals("B", this_ticket.getRow().getName());
			assertEquals(1, this_ticket.getStart_no());
			assertEquals(5, this_ticket.getEnd_no());
			
			// I can assign a new ticket in row A
			assertEquals(2,session.book_ticket(2, 5).getId());

			
		} catch (RejectExcption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link Session#change_ticket(int, int)}.
	 */
	@Test
	void testChange_ticket_to_smaller() {
		try {
			assertEquals(1,session.book_ticket(1, 5).getId());
			// this ticket should be assigned to another row
			session.change_ticket(1, 3);
			// I can assign a new ticket in row A
			assertEquals(2,session.book_ticket(2, 5).getId());

			// the ticket change to another row 
			Ticket this_ticket = session.get_ticket_by_id(1);
			assertEquals("A", this_ticket.getRow().getName());
			assertEquals(1, this_ticket.getStart_no());
			assertEquals(3, this_ticket.getEnd_no());
			
			
			
		} catch (RejectExcption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link Session#cancel_ticket(int)}.
	 */
	@Test
	void testCancel_ticket() {
		try {
			assertEquals(1,session.book_ticket(1, 5).getId());
			assertEquals(2,session.book_ticket(2, 5).getId());
			assertEquals(3,session.book_ticket(3, 5).getId());
			
			// cancel all the ticket in this session
			session.cancel_ticket(1);
			session.cancel_ticket(2);
			session.cancel_ticket(3);
			
			
		} catch (RejectExcption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			// now the session is empty, I can add some ticket
			assertEquals(1,session.book_ticket(1, 5).getId());
		} catch (RejectExcption e) {
			// TODO Auto-generated catch block
			// pass
		}
	}

	/**
	 * Test method for {@link Session#get_ticket_by_id(int)}.
	 */
	@Test
	void testGet_ticket_by_id() {
		try {
			assertEquals(1,session.book_ticket(1, 5).getId());
			assertEquals(2,session.book_ticket(2, 5).getId());
			assertEquals(3,session.book_ticket(3, 5).getId());
			
			assertEquals(1, session.get_ticket_by_id(1).getId());
			assertEquals(2, session.get_ticket_by_id(2).getId());
			assertEquals(3, session.get_ticket_by_id(3).getId());
		} catch (RejectExcption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Test method for {@link Session#getPrint(int)}.
	 */
	@Test
	void test_get_print() {
		try {
			assertEquals(1,session.book_ticket(1, 5).getId());
			assertEquals(2,session.book_ticket(2, 5).getId());
			assertEquals(3,session.book_ticket(3, 5).getId());
		} catch (RejectExcption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("Inception\n" + 
				"A: 1-5\n" + 
				"B: 1-5,6-10", session.getPrint());
	}
}
