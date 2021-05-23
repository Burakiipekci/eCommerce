package eCommerce.business.abstracts;

import java.util.List;

import eCommerce.entities.concretes.User;

public interface UserService {
	void add(User user);
	void update (User user);
	void remove(User user);
	 void verifyUser(int id);
	User get(int id );
	User getByEmail(String email);
	User getByEmailAndPassword(String email , String password);
	List<User> getAll();
	
}
