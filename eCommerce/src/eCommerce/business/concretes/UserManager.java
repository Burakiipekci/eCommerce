package eCommerce.business.concretes;

import java.util.List;

import eCommerce.business.abstracts.UserService;
import eCommerce.dataAccess.abstracts.UserDao;
import eCommerce.entities.concretes.User;

public class UserManager implements UserService {
	
	private UserDao userDao;

	public UserManager(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	@Override
	public void add(User user) {
		userDao.add(user);
		
	}

	@Override
	public void update(User user) {
		userDao.update(user);
		
	}

	@Override
	public void remove(User user) {
		userDao.remove(user);
		
	}

	@Override
	public User get(int id) {
		return userDao.get(id);
	}

	@Override
	public User getByEmail(String email) {
		return userDao.getByEmail(email);
	}

	@Override
	public User getByEmailAndPassword(String email, String password) {
		return userDao.getByEmailAndPassword(email, password);
	}

	@Override
	public List<User> getAll() {
		return userDao.getAll();
	}

	@Override
	public void verifyUser(int id) {
		User user = userDao.get(id);
		user.setVerified(true);
		System.out.println("ba�ar�yla do�ruland�..");
		
	}

}
