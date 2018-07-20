package Repositories;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import Model.Comment;
import Model.ImagePost;
import Model.Post;

public class CommentRepository implements ICommentRepository {
	/** To retrieve a list of comment posted by user on post. It allow user to load limitted no. of records at one time
	 * @param postId - post user want to comment upon
	 * @param fromRec- start of the record, for first time loading, can pass 0
	 * @param noOfRec - no. of record, no. of record shown on screen, To show all, set this to 0
	 */
	@Override
	public List<Comment> getComment(int postId, int fromRec, int noOfRec) {
		List<Comment> comment = new ArrayList<Comment>();
		ResultSet rs = null;
		Comment c = null;
		String imgName = null;
		String query = "{call getComment(?,?,?)}";
		
		try(CallableStatement stmt=BaseRepository.getStatement(query);){
			stmt.setInt(1, postId);
			stmt.setInt(2, fromRec);
			stmt.setInt(3, noOfRec);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				c = new Comment(rs);
				comment.add(c);
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return comment;
	}
	/** To insert comment
	 * @param comment - comment to be inserted
	 */
	@Override
	public int insertComment(Comment comment) {
		String query = "{call insertComment(?,?,?,?)}";
		int id = -1;
		try(CallableStatement stmt=BaseRepository.getStatement(query);){
			stmt.setInt(1, comment.getPostId());
			stmt.setString(2, comment.getComment());
			stmt.setInt(3, comment.getCommentBy());
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
