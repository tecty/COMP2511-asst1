import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * 
 */

/**
 * @author tecty
 *
 */
class CinemaTest {

	/**
	 * Test method for {@link Cinema#Cinema(int)}.
	 */
	@Test
	void testCinema() {
		// create a Cinema 
		Cinema cinema1 = new Cinema(1);
		assertEquals(1, cinema1.getId());
	}

	/**
	 * Test method for {@link Cinema#setRows(java.lang.String, int)}.
	 * Test method for {@link Cinema#getRow(java.lang.String)}.
	 */
	@Test
	void testGetRow() {
		Cinema cinema1 = new Cinema(1);
		cinema1.setRows("AA", 12);
		try {
			// try to get the aa row from cinema
			assertEquals("AA", cinema1.getRow("AA").getRow());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


}
