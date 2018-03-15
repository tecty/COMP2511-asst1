import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CinemaRowTest {

	@Test
	void testCinemaRow() {
		// create a cinema row to get it's details
		CinemaRow rowAA = new CinemaRow("AA", 10);
		// check whether the info is correctly set 
		assertEquals("AA", rowAA.getRow());
		assertEquals(10, rowAA.getCapacity());
	}

}
