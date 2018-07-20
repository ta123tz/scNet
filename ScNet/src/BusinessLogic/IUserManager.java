package BusinessLogic;

import Model.User;

public interface IUserManager {
	
	User registerUser(User user) throws Exception;
	boolean deActivateUser(User user);
	boolean reActivateUser(User user);
	User getUser(String email, int noOfRecPage);
}
