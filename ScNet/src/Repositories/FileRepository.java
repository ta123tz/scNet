package Repositories;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import Model.ImagePost;

public class FileRepository {
	private final String path = "C:\\Documents";

	private ImagePost post;

	public void setPost(ImagePost post) {
		this.post = post;
	}

	private String getFullPath() {
		return path +"\\"+ post.getImageName();
	}
	/** To save file from image post in directory, This project is using C:\Documents for file location.
	 * Require to initialize post using setPost
	 * @return boolean - To indicate whether saving image is successful
	 * @exception IOException - if any IO error while saving file
	 */
	public boolean SaveImage() throws IOException {
		FileOutputStream stream = null;
		try {
			stream = new FileOutputStream(getFullPath());
			stream.write(post.getImage());
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			stream.close();
		}
		return false;
	}
	/** To retrieve image file from directory, This project is using C:\Documents for file location.
	 * Require to initialize post using setPost
	 * @return byte[] - file retrieve from physical storage and converted to byte[] to return to user
	 * @exception IOException - if any IO error while reading file
	 */
	public byte[] GetImage() throws IOException {
		File file = new File(getFullPath());
		FileInputStream stream = null;

        byte[] b = new byte[(int) file.length()];
        try {
              stream = new FileInputStream(file);
              stream.read(b);
         } catch (FileNotFoundException e) {
                     e.printStackTrace();
         }finally {
        	 stream.close();
         }
        
        return b;
	}
}
