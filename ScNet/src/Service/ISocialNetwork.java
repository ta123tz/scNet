package Service;

import java.util.List;

import Model.Comment;
import Model.Post;
import Model.User;

public interface ISocialNetwork {
	User registerUser(User user) throws Exception;
	boolean deActivateUser(User user);
	boolean reActivateUser(User user);
	Post submitPost(Post post);
	Comment commentPost(Comment comment);
	User getUser(String email, int noOfRecPage);
	Post[] getPost(User user, int pageNo, int recPerPage);
	Comment[] getComment(Post post, int pageNo, int recPerPage);
}
