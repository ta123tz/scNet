package BusinessLogic;

import java.util.List;
import Model.*;

public interface IPostManager {
	List<Post> getPost(User user, int pageNo, int recPerPage);
	Post submitPost(Post post);
}
