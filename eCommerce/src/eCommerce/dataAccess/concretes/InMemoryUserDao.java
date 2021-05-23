package eCommerce.dataAccess.concretes;

import java.util.List;
import java.util.ArrayList;

import eCommerce.dataAccess.abstracts.UserDao;
import eCommerce.entities.concretes.User;

public class InMemoryUserDao implements UserDao{
	List<User> users = new ArrayList<User>();

	@Override
	public void add(User user) {
		users.add(user);
		
	}

	@Override
	public void update(User user) {
		User userUpdate = get(user.getId());
		userUpdate.setFirstName(user.getFirstName());
		userUpdate.setLastName(user.getLastName());
		
	}

	@Override
	public void remove(User user) {
		users.removeIf(usr->usr.getId() == user.getId());
		
	}

	@Override
	public User get(int id) {
		for(User user : users) {
			if(user.getId()==id)
				return user;
		}
		return null;
	}

	@Override
	public User getByEmail(String email) {
		for(User user : users) {
			if(user.getEmail() == email)
				return user;
		}
		return null;
	}

	@Override
	public User getByEmailAndPassword(String email, String password) {
		for(User user : users) {
			if(user.getEmail()== email && user.getPassword()== password)
				return user;
		}
		return null;
	}

	@Override
	public List<User> getAll() {
		
		return users;
	}

}
