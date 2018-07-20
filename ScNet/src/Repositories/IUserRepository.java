package Repositories;

import Model.User;

public interface IUserRepository {
	boolean validateUser(User user);
	int insertUser(User user);
	boolean updateUserStatus(User user);
	User getUser(String email);
}
