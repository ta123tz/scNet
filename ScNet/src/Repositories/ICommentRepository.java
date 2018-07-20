package Repositories;

import java.util.List;
import Model.Comment;

public interface ICommentRepository {
	List<Comment> getComment(int postId, int fromRec, int noOfRec);
	int insertComment(Comment post);
}
