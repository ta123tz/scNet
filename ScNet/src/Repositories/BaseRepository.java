package Repositories;

import java.sql.*;

public class BaseRepository {
	private static Connection con = null;
	private static CallableStatement  stat = null;
	/** This function is for getting connection, so as not to write in every database call
	 */
	private static void getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");  
			con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/scnet2?verifyServerCertificate=false&useSSL=true&requireSSL=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","root");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	/** This function is to prepare statement, so as not to write in every database call
	 */
	protected static CallableStatement getStatement(String query) {
		try {
			BaseRepository.getConnection();
			stat = con.prepareCall(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stat;
	}
}
