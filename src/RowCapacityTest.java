import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RowCapacityTest {



	@Test
	void testCheckCapacity() {
		//settup a Row has 10 seat
		RowCapacity r1 = new RowCapacity(10);
		// this row can meet the requirement. because require < capacity
		assertEquals(0, r1.checkCapacity(10));
		assertEquals(0, r1.checkCapacity(5));
		// this row couldn't meet the requirement
		assertEquals(-1, r1.checkCapacity(13));
		
	}

	@Test
	void testSetTicket() {
		//settup a Row has 10 seat
		RowCapacity r1 = new RowCapacity(10);
		// I only can set two 5 seat ticket, no more
		assertEquals(true, r1.setTicket(1, 5));
		assertEquals(5, r1.checkCapacity(5));
		assertEquals(true, r1.setTicket(2, 5));
		// i couldn't set the ticket
		assertEquals(false, r1.setTicket(3, 1));
		
	}

}
