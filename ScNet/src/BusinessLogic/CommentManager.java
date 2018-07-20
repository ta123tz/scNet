package BusinessLogic;

import java.util.List;
import Model.*;
import Repositories.*;

public class CommentManager implements ICommentManager {
	private ICommentRepository _repository;
	public CommentManager() {
		_repository = new CommentRepository();
	}
	/** To retrieve a list of comment posted by user on post. It allow user to load limitted no. of records at one time
	 * @param post - post user want to comment upon
	 * @param pageNo- page no. user is currently at
	 * @param recPerPage - no. of record shown on screen, To show all, set this to 0
	 */
	@Override
	public List<Comment> getComment(Post post, int pageNo, int recPerPage) {
		return _repository.getComment(post.getUserId(), (pageNo == 1 ? 0 : pageNo * recPerPage -1), recPerPage);
	}

	/** To submit comment
	 * @param comment - comment to be submitted
	 */
	@Override
	public Comment submitComment(Comment comment) {
		int id = _repository.insertComment(comment);
		comment.setCommentId(id);
		return comment;
	}

}
