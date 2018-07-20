package Model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import BusinessLogic.UserManager;

public class User {
	private int userId;
	private String email;
	private String name;
	private String password;
	private Status status;
	private Date joinDate;
	private List<Post> post;
	
	public List<Post> getPost() {
		return post;
	}

	public void setPost(List<Post> post) {
		this.post = post;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int id) {
		this.userId = id;
	}

	public String getJoinDate() {
		return new SimpleDateFormat("yyyy-MM-dd").format(joinDate);
	}

	private void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public void addPost(Post p) {
		post.add(p);
	}
	
	public User(String email, String password) {
		setEmail(email);
		setPassword(password);
		setPost(new ArrayList<Post>());
	}
	
	public User(String email, String name, String password) {
		setEmail(email);
		setName(name);
		setPassword(password);
		setJoinDate(new Date());
		setStatus(Status.Active);
		setPost(new ArrayList<Post>());
	}
	/**
	 * This constructor is used to create User from result set data
	 * @param rs - result set from retrieval
	 */
	public User(ResultSet rs) {
		try {
			setUserId(rs.getInt("userId"));
			setEmail(rs.getString("email"));
			setJoinDate(rs.getDate("joinDate"));
			setName(rs.getString("name"));
			setStatus(Model.Status.fromInt(rs.getInt("status")));
			setPost(new ArrayList<Post>());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * To return user information as String
	 */
	@Override
	public String toString() {
		return "Name: " + getName() + "\r\nEmail: " + getEmail() + "\r\nStatus:" + getStatus() + "\r\nJoin Date:" + getJoinDate();
	}
}