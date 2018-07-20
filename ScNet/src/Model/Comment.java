package Model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Comment {
	private int commentId;
	private int postId;
	private String comment;
	private int commentBy;
	private String commentByName;
	private Date commentDate;
	/**
	 * This constructor is used to create Comment from result set data
	 * @param rs - result set from retrieval
	 */
	public Comment(ResultSet rs) {
		try {
			setCommentId(rs.getInt("commenttId"));
			setPostId(rs.getInt("postId"));
			setCommentBy(rs.getInt("commentBy"));
			setCommentByName(rs.getString("name"));
			setCommentDate(rs.getDate("commentDate"));
			setComment(rs.getString("comment"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Comment(User user, Post post, String commentText) {
		setPostId(post.getPostId());
		setComment(commentText);
		setCommentDate(new Date());
		setCommentBy(user.getUserId());
	}
	/**
	 * To return comment information as String
	 */
	@Override
	public String toString() {
		return "Comment: " + getComment() + "\r\nComment Date: " + getCommentDate();
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getCommentBy() {
		return commentBy;
	}
	public void setCommentBy(int commentBy) {
		this.commentBy = commentBy;
	}
	public String getCommentByName() {
		return commentByName;
	}
	public void setCommentByName(String commentByName) {
		this.commentByName = commentByName;
	}
	public String getCommentDate() {
		return new SimpleDateFormat("yyyy-MM-dd").format(commentDate);
	}
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
}
