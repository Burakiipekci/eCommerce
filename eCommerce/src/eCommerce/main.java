package eCommerce;

import eCommerce.business.abstracts.UserService;
import eCommerce.business.concretes.AuthManager;
import eCommerce.business.concretes.EmailManager;
import eCommerce.business.concretes.UserManager;
import eCommerce.business.concretes.UserValidationManager;
import eCommerce.core.auth.AuthService;
import eCommerce.core.auth.GoogleAuthManagerAdapter;
import eCommerce.dataAccess.concretes.InMemoryUserDao;

public class main {

	public static void main(String[] args) {
		UserService userService= new UserManager(new InMemoryUserDao());
		AuthService authService = new AuthManager(userService, new UserValidationManager(), new EmailManager());
		authService.register(1, "Burak ", "Ýpekci","burakipekci@gmail.com", "123burak");
		authService.register(2, "Berkan ", "celik","berkancelik@gmail.com", "12berkan12");
		authService.register(3, "Berk ", "gültekin","berk_gültekin@gmail.com", "berkk123");
		authService.register(4, "Can ", "demir","candmr@gmail.com", "1111can1111");
		
		authService.login("burakipekci@gmail.com", "123burak");
		userService.verifyUser(1);
		authService.login("burakipekci@gmail.com", "123burak1"); 
		authService.login("burakipekci@gmail.com", "123burak"); 
		authService.login("", ""); 
		
		AuthService googleAuthService = new GoogleAuthManagerAdapter();
		googleAuthService.register(6, "Mert", "Eren", "merteren@gmail.com", "mert123Eren");
		googleAuthService.login("merteren@gmail.com", "mert123Eren");

	}

}
