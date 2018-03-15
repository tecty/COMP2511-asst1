import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author tecty
 *
 */
public class CinemaBookingSystem {

	/**
	 * main method to interact with this system
	 * @param args
	 */
	public static void main(String[] args) {
	      Scanner sc = null;
	      try
	      {
	          sc = new Scanner(new File(args[0]));    // args[0] is the first command line argument
	          // Read input from the scanner here
	          // scan all the line form the file
	          while (sc.hasNextLine()) {
	        	// get the input by line 
				String line = (String) sc.nextLine();
				// split it as command array
				String[] command = line.split(" ");
				// read the command to call correspond function
				switch (command[0]) {
				case "Cinema":
					
					break;
				case "Session":
					
					break;
				case "Request":
					
					break;
				case "Change":
					
					break;
				case "Cancel":
					
					break;
				case "Print":
					
					break;

				default:
					break;
				}
				
				
				
			}
	      }
	      catch (FileNotFoundException e)
	      {
	          System.out.println(e.getMessage());
	      }
	      finally
	      {
	          if (sc != null) sc.close();
	      }
	}

}
