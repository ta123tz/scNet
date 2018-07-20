package BusinessLogic;

import java.util.List;
import Model.*;

public interface ICommentManager {
	List<Comment> getComment(Post post, int pageNo, int recPerPage);
	Comment submitComment(Comment comment);
}
