package BusinessLogic;

import Model.Status;

import java.util.List;

import Model.*;
import Repositories.*;

public class UserManager implements IUserManager {
	private IUserRepository _repository;
	public UserManager() {
		_repository = new UserRepository();
	}
	/**
	 * To register user. Register only if the same email does not exists in the systeme
	 * @param user - user to be register
	 */
	@Override
	public User registerUser(User user) throws Exception {
		int id = -1;
		if(!_repository.validateUser(user)) {
			id = _repository.insertUser(user);
			if(id >0)
				user.setUserId(id);
			else throw new Exception("Error creating user");
		}else
			throw new Exception("User already exists in the system");
		return user;
	}
	/**
	 * To deActivate user
	 * @param user - user to be deActivate
	 */
	@Override
	public boolean deActivateUser(User user) {
		user.setStatus(Status.Disable);
		return _repository.updateUserStatus(user);
	}
	/**
	 * To reActivate user
	 * @param user - user to be reActivate
	 */
	@Override
	public boolean reActivateUser(User user) {
		user.setStatus(Status.Active);
		return _repository.updateUserStatus(user);
	}
	
	@Override
	public User getUser(String email, int noOfRecPage) {
		IPostManager pmgr = new PostManager();
		User user= _repository.getUser(email);
		List<Post> post = pmgr.getPost(user, 1, noOfRecPage);
		user.setPost(post);
		return user;
	}
}
