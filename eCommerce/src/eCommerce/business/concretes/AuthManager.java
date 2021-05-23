package eCommerce.business.concretes;

import eCommerce.business.abstracts.EmailService;
import eCommerce.business.abstracts.UserService;
import eCommerce.business.abstracts.UserValidationService;
import eCommerce.core.auth.AuthService;
import eCommerce.core.utils.BusinessRules;
import eCommerce.entities.concretes.User;

public class AuthManager implements AuthService {
	UserService userService;
	UserValidationService userValidationService;
	EmailService emailService;

	public AuthManager(UserService userservice, UserValidationService userValidationService,
			EmailService emailService) {
		super();
		this.userService = userservice;
		this.userValidationService = userValidationService;
		this.emailService = emailService;
	}

	@Override
	public void register(int id, String firstName, String lastName, String email, String password) {
		User userRegister = new User(id,email,password,firstName,lastName,false);
		if(!userValidationService.validate(userRegister)) {
			System.out.println("��lem ba�ar�s�z l�tfen tekrar deneyin");
		}
		if(!BusinessRules.run(checkIfUserExists(email))) {
			System.out.println("Kay�t olma i�lemi ba�ar�s�z. Bu email ile bir ba�ka �ye mevcut.");
			return;
		}
		System.out.println("Kay�t olundu. Do�rulama kodu e-posta adresinize g�nderilmi�tir");
		emailService.send("Do�rulamak i�in wwww.burakipekci.com adresine gidin",userRegister.getEmail());
		userService.add(userRegister);
		
	}

	@Override
	public void login(String email, String password) {
		if(!BusinessRules.run(checkIfAllFieldsFilled(email, password))) {
			System.out.println("Bo� alanlar do�ru doldurulmad�.");
			return;
		}
		
		User userToLogin = userService.getByEmailAndPassword(email, password);
		
		if(userToLogin == null) {
			System.out.println(" E-posta adresiniz veya �ifreniz yanl��.");
			return;
		}
		
		if(!checkIfUserVerified(userToLogin)) {
			System.out.println("�yeli�inizi do�rulamad�n�z.");
			return;
		}
		System.out.println("Giri� ba�ar�l�. Ho�geldiniz : " + userToLogin.getFirstName() + " " + userToLogin.getLastName());
		
	}
	private boolean checkIfUserExists(String email) {
		return userService.getByEmail(email) == null;
	}
	private boolean checkIfUserVerified(User user) {
		return user.isVerified();
	}
	private boolean checkIfAllFieldsFilled(String email,String password) {
		if(email.length()<=0 || password.length() <=0) {
			return false;
		}
		return true;
	}

}
