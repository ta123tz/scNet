package Repositories;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import Model.ImagePost;
import Model.Post;

public class PostRepository extends BaseRepository implements IPostRepository {
	/** To retrieve a list of post posted by user. It allow user to load limitted no. of records at one time
	 * @param userId - current active user
	 * @param fromRec- start of the record, for first time loading, can pass 0
	 * @param noOfRec - no. of record, no. of record shown on screen, To show all, set this to 0
	 */
	@Override
	public List<Post> getPost(int userId, int fromRec, int noOfRec) {
		List<Post> post = new ArrayList<Post>();
		ResultSet rs = null;
		Post p = null;
		String imgName = null;
		String query = "{call getPost(?,?,?)}";
		
		try(CallableStatement stmt=BaseRepository.getStatement(query);){
			stmt.setInt(1, userId);
			stmt.setInt(2, fromRec);
			stmt.setInt(3, noOfRec);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				imgName = rs.getString("imageName");
				if(imgName != null && !imgName.isEmpty())
					p= new ImagePost(rs);
				else
					p = new Post(rs);
				
				post.add(p);
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return post;
	}
	/** To insert post
	 * @param post - post to be inserted
	 */
	@Override
	public int insertPost(Post post) {
		String query = "{call insertPost(?,?,?)}";
		int id = -1;
		try(CallableStatement stmt=BaseRepository.getStatement(query);){
			stmt.setInt(1, post.getUserId());
			stmt.setString(2, post.getText());
			stmt.registerOutParameter(3, Types.INTEGER);
			
			stmt.execute();
			id = stmt.getInt(3);
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	/** To insert image post
	 * @param post - image post to be inserted
	 */
	@Override
	public int insertImage(ImagePost post) {
		String query = "{call insertImage(?,?,?,?)}";
		int id = -1;
		try(CallableStatement stmt=BaseRepository.getStatement(query);){
			stmt.setInt(1, post.getUserId());
			stmt.setString(2, post.getText());
			stmt.setString(3, post.getImageName());
			stmt.registerOutParameter(4, Types.INTEGER);
			
			stmt.execute();
			id = stmt.getInt(4);
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
}
