package Service;

import java.util.ArrayList;
import java.util.List;

import BusinessLogic.CommentManager;
import BusinessLogic.ICommentManager;
import BusinessLogic.IPostManager;
import BusinessLogic.IUserManager;
import BusinessLogic.PostManager;
import BusinessLogic.UserManager;
import Model.Comment;
import Model.Post;
import Model.User;
/**
 * 
 * @author Thuzar
 * REST/SOAP API is not included in this project
 */

public class SocialNetworkService implements ISocialNetwork {
	IUserManager uMgr = null;
	IPostManager pMgr = null;
	ICommentManager cMgr = null;
	
	public SocialNetworkService(){
		uMgr = new UserManager();
		pMgr = new PostManager();
		cMgr = new CommentManager();
	}
	/**
	 * To register user for using Social Network
	 * Upon registration, email validation will be performed
	 * TODO: To add sendEmail upon successful registration, not in this phase
	 * @param user = new User(email, name, password);
	 * @return user - created account with Id. Any error will throw Exception
	 * @exception Exception - Error creating user
	 * 						- User already exists in the system
	 */
	@Override
	public User registerUser(User user) throws Exception {
		User u = null;
		try {
			u = uMgr.registerUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return u;
	}
	/**
	 * To deactivate user from Social Network
	 * TODO: To add sendEmail for confirmation, not in this phase
	 * @param user - current login user;
	 */
	@Override
	public boolean deActivateUser(User user) {
		return uMgr.deActivateUser(user);
	}
	/**
	 * To reactivate user from Social Network
	 * TODO: To add sendEmail for confirmation, not in this phase
	 * @param user - user to reactivate;
	 */
	@Override
	public boolean reActivateUser(User user) {
		return uMgr.reActivateUser(user);
	}
	/**
	 * To submit post on Social Network
	 * @param post - post user want to submit;
	 */
	@Override
	public Post submitPost(Post post) {
		return pMgr.submitPost(post);
	}
	/**
	 * To submit comment on post on Social Network
	 * @param comment - comment user want to submit for a post;
	 */
	@Override
	public Comment commentPost(Comment comment) {
		return cMgr.submitComment(comment);
	}
	/**
	 * To get user after submit post
	 * TODO: To call after login, not in this module
	 * @param email - user email address;
	 * @return User - load User Info, 1st page of Post and 1st page of comment for the post loaded
	 */
	@Override
	public User getUser(String email, int noOfRecPage) {
		return uMgr.getUser(email, noOfRecPage);
	}
	/**
	 * To view more posts while browsing
	 * TODO: To call after login, not in this module
	 * @param user - current login user
	 * @param pageNo - for lazy load (to call upon next scroll or click on next page) start with 1
	 * @param recPerPage - for lazy load (no. of post shown in one load) if no pagination set to 0
	 * @return Post[] - post loaded according to the provided pageNo and no. of record
	 */
	@Override
	public Post[] getPost(User user, int pageNo, int recPerPage){
		List<Post> post = pMgr.getPost(user, pageNo, recPerPage);
		return post.toArray(new Post[post.size()]);
	}
	/**
	 * To view comments while browsing posts
	 * @param post - current post user is viewing
	 * @param pageNo - for lazy load (to call upon next scroll or click on next page) start with 1
	 * @param recPerPage - for lazy load (no. of post shown in one load) if no pagination set to 0
	 * @return Comment[] - comment loaded according to the provided pageNo and no. of record
	 */
	@Override
	public Comment[] getComment(Post post, int pageNo, int recPerPage){
		List<Comment> comment = cMgr.getComment(post, pageNo, recPerPage);
		return comment.toArray(new Comment[comment.size()]);
	}
}
