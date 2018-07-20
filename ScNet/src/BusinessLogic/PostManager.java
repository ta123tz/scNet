package BusinessLogic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import Model.*;
import Repositories.FileRepository;
import Repositories.IPostRepository;
import Repositories.IUserRepository;
import Repositories.PostRepository;
import Repositories.UserRepository;

public class PostManager implements IPostManager {
	private IPostRepository _repository;
	FileRepository _frepository;
	public PostManager() {
		_repository = new PostRepository();
		_frepository = new FileRepository();
	}
	/** To retrieve a list of post posted by user. It allow user to load limitted no. of records at one time
	 * If Image file, system will retrieve image file as byte[]
	 * @param user - user who own the post
	 * @param pageNo- page no. user is currently at
	 * @param recPerPage - no. of record shown on screen, To show all, set this to 0
	 */
	@Override
	public List<Post> getPost(User user, int pageNo, int recPerPage) {
		ICommentManager cmgr = new CommentManager();
		ImagePost img = null;
		List<Post> post = _repository.getPost(user.getUserId(), (pageNo == 1 ? 0 : pageNo * recPerPage -1), recPerPage);
		for(Post p : post) {
			if(p instanceof ImagePost) {
				img = (ImagePost)p;
				_frepository.setPost(img);
				try {
					img.setImage(_frepository.GetImage());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			p.setComment(cmgr.getComment(p, 1, recPerPage));
		}
		return post;
	}
	/** To submit post
	 * If Image post, system will save the file to folder and save file name in database
	 * @param post - post to be inserted
	 */
	@Override
	public Post submitPost(Post post) {
		int id = -1;
		if(post instanceof Post)
			id = _repository.insertPost(post);
		else {
			
			ImagePost p = (ImagePost)post;
			_frepository.setPost(p);
			try {
			if(_frepository.SaveImage())
				id= _repository.insertImage(p);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		post.setPostId(id);
		return post;
	}
	
}
