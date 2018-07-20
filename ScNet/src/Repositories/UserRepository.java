package Repositories;

import java.sql.*;

import Model.User;

public class UserRepository extends BaseRepository implements IUserRepository {
	/** To validate where user exists in the database
	 * @param user - user must contains email for searching
	 */
	@Override
	public boolean validateUser(User user) {
		String query = "{call isUserExists(?,?)}";
		boolean result = false;
		try(CallableStatement stmt=BaseRepository.getStatement(query);){
			stmt.setString(1, user.getEmail());
			stmt.registerOutParameter(2, Types.BOOLEAN);
			stmt.execute();
			
			result = stmt.getBoolean(2);
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	/** Insert user to database
	 * @param user - user to be inserted
	 */
	@Override
	public int insertUser(User user) {
		String query = "{call insertUser(?,?,?,?)}";
		int id = -1;
		try(CallableStatement stmt=BaseRepository.getStatement(query);){
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getEmail());
			stmt.setString(3, user.getPassword());
			stmt.registerOutParameter(4, Types.INTEGER);
			
			stmt.execute();
			id = stmt.getInt(4);
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	/** To update user status
	 * This function is use when deactivate or reactivate user
	 * @param user - user to be inserted
	 */
	@Override
	public boolean updateUserStatus(User user) {
		String query = "{call updateUser(?,?)}";
		try(CallableStatement stmt=BaseRepository.getStatement(query);){
			stmt.setInt(1, user.getUserId());
			stmt.setInt(2, user.getStatus().getCode());
			stmt.execute();
			return true;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/** To update user status
	 * This function is use when deactivate or reactivate user account
	 * @param email - email address of the user to retrieve
	 */
	@Override
	public User getUser(String email) {
		User user = null;
		ResultSet rs = null;
		String query = "{call getUserbyEmail(?)}";
		try(CallableStatement stmt=BaseRepository.getStatement(query);){
			stmt.setString(1, email);
			rs = stmt.executeQuery();
			if(rs.next())
			{
				user = new User(rs);
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
}
