package eCommerce.business.concretes;

import eCommerce.business.abstracts.EmailService;

public class EmailManager implements EmailService {

	@Override
	public void send(String message, String to) {
		System.out.println("Eposta y�neticisi : "+ message +"mesaj�"+to+"adresine g�nderildi");
		
	}

}
