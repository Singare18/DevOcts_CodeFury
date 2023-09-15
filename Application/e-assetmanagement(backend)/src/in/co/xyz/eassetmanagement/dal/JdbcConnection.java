package in.co.xyz.eassetmanagement.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Since have to use this connection in every CRUD operation of both borrower and asset 
//so rather then repeating the code I have made a separate class 
public class JdbcConnection {

	static Connection con = null;
	
	public static Connection openConnection() {
		try {			Class.forName("com.mysql.jdbc.Driver"); //load the driver

		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/eassetmanagement_db","root","root");
		System.out.println("Connected");
		return con;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void closeConnection() throws SQLException {
		con.close();
	}
}

