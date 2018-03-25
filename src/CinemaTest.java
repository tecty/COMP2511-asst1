import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CinemaTest {
	private Cinema cinema;
	@BeforeEach
	void setUp() throws Exception {
		this.cinema = new Cinema();
	
	}

	@Test
	void testCreate_row() {
		// create two standard row
		this.cinema.create_row("A", 10);
		this.cinema.create_row("B", 12);
		
		// get the capacity from the cinema
		try {
			assertEquals(10, this.cinema.get_row("A").getCapacity() );
			assertEquals(12, this.cinema.get_row("B").getCapacity() );
		} catch (RejectExcption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Test
	void testCreate_session() {
		// create two standard row
		this.cinema.create_row("A", 10);
		this.cinema.create_row("B", 12);
		
		// create a session on noon
		this.cinema.create_session("12:20","Inception");
		
		// get the session on noon 
		try {
			Session the_session =  this.cinema.get_session("12:20");
			
			assertEquals("Inception", the_session.getTitle());
			assertEquals("12:20", the_session.getTime());
		} catch (RejectExcption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	void test_get_session() {
		// create two standard row
		this.cinema.create_row("A", 10);
		this.cinema.create_row("B", 12);
		
		// create a session on noon
		Session the_session =this.cinema.create_session("12:20","Inception");
		
		// get the session on noon 
		try {
			assertEquals(the_session, this.cinema.get_session("12:20"));
		} catch (RejectExcption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
