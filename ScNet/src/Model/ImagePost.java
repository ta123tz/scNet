package Model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ImagePost extends Post {
	private String imageName;
	private byte[] image;
	/**
	 * This constructor is used to create Post from result set data
	 * @param rs - result set from retrieval
	 */
	public ImagePost(ResultSet rs) {
		super(rs);
		try {
			setImageName(rs.getString("imageName"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		return super.toString() + "\r\nImageName: " + getImageName();
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
}
