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
			System.out.println("Ýþlem baþarýsýz lütfen tekrar deneyin");
		}
		if(!BusinessRules.run(checkIfUserExists(email))) {
			System.out.println("Kayýt olma iþlemi baþarýsýz. Bu email ile bir baþka üye mevcut.");
			return;
		}
		System.out.println("Kayýt olundu. Doðrulama kodu e-posta adresinize gönderilmiþtir");
		emailService.send("Doðrulamak için wwww.burakipekci.com adresine gidin",userRegister.getEmail());
		userService.add(userRegister);
		
	}

	@Override
	public void login(String email, String password) {
		if(!BusinessRules.run(checkIfAllFieldsFilled(email, password))) {
			System.out.println("Boþ alanlar doðru doldurulmadý.");
			return;
		}
		
		User userToLogin = userService.getByEmailAndPassword(email, password);
		
		if(userToLogin == null) {
			System.out.println(" E-posta adresiniz veya þifreniz yanlýþ.");
			return;
		}
		
		if(!checkIfUserVerified(userToLogin)) {
			System.out.println("Üyeliðinizi doðrulamadýnýz.");
			return;
		}
		System.out.println("Giriþ baþarýlý. Hoþgeldiniz : " + userToLogin.getFirstName() + " " + userToLogin.getLastName());
		
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
