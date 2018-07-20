package Repositories;

import java.util.List;
import Model.*;

public interface IPostRepository {
	List<Post> getPost(int userId, int fromRec, int noOfRec);
	int insertPost(Post post);
	int insertImage(ImagePost post);
}
