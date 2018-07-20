package Model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Post {
	private int postId;
	private int userId;
	private Date postDate;
	private String text;
	private List<Comment> comment;
	/**
	 * This constructor is used to create Post from result set data
	 * @param rs - result set from retrieval
	 */
	public Post(ResultSet rs) {
		try {
			setPostId(rs.getInt("postId"));
			setUserId(rs.getInt("userId"));
			setPostDate(rs.getDate("postDate"));
			setText(rs.getString("text"));
			setComment(new ArrayList<Comment>());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Post(User user, String postText) {
		setUserId(user.getUserId());
		setText(postText);
		setPostDate(new Date());
		setComment(new ArrayList<Comment>());
	}
	/**
	 * To return post information as String
	 */
	@Override
	public String toString() {
		return "Post: " + getText() + "\r\nPost Date: " + getPostDate();
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getPostDate() {
		return new SimpleDateFormat("yyyy-MM-dd").format(postDate);
	}
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<Comment> getComment() {
		return comment;
	}
	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}
	public void addComment(Comment c) {
		comment.add(c);
	}
}
