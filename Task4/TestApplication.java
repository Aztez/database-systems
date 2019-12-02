import java.io.*;
import java.util.*;
import java.sql.*;
 
public class TestApplication {
	
	public static void main ( String [] args ) {
		
		Scanner input = new Scanner(System.in);
		Connection conn = null;
		
		try {
			
			DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
			
			String dbURL = "jdbc:oracle:thin:@toldb.oulu.fi:1521:toldb11";
			String username = "stu15";
			String password = "db2018";
		
			conn = DriverManager.getConnection(dbURL, username, password);
  
			if (conn != null) {
				System.out.println("\nConnected to the database");
			}
			
		}
		
		catch (SQLException ex) {
            ex.printStackTrace();
        } 
		
		try {
		
			int arvo;
		
			System.out.print("\n1: Show Items / 2: Show Customers\nInput number: ");
			arvo = input.nextInt();
		
			if (arvo == 1) {
				System.out.println("\nShowing Items data\n");
				String sql = "SELECT * FROM O_items";
				Statement statement = conn.createStatement();
				ResultSet result = statement.executeQuery(sql);
				
				int count = 0;

				while (result.next()){
					String id = result.getString("id");
					String title = result.getString("title");
					
					String output = "Item id %s - %s";
					System.out.println(String.format(output, id, title));
				}
				
			}
	
			else if (arvo == 2) {
				System.out.println("\nShowing Customers data\n");
				String sql = "SELECT * FROM O_customers";
				Statement statement = conn.createStatement();
				ResultSet result = statement.executeQuery(sql);
				
				int count = 0;

				while (result.next()){
					String name = result.getString("name");
					String email = result.getString("email");
					
					String output = "Customer #%d: %s - %s";
					System.out.println(String.format(output, ++count, name, email));
				}
			}
		
			else {	
				System.out.println("Invalid command.");	
			}
			
		}
		catch (SQLException ex) {
				ex.printStackTrace();
			}
		finally {
		
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
					System.out.println("\nDisconnected from the database");
				}
			}
			
			catch (SQLException ex) {
				ex.printStackTrace();
			}
			
		}
		
	}
	
}