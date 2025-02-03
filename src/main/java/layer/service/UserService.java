package layer.service;

import org.mindrot.jbcrypt.BCrypt;

import layer.dao.UserDAO;
import layer.entity.User;

public class UserService {
	UserDAO dao = new UserDAO();
	//Register Validataion
	public String registerValidation(String name, String email, String password, String cpassword) {
		String result = null;
		//checking if the email is already in the DB
		User user = dao.getUserByEmail(email);
		if(user!=null) {
			result = "User already exist";
		}else if(!password.equals(cpassword)) {
			result = "Passwords do not match";
		}else {
			result = "success";
		}
		//if validation is valid, now insert into DB
		if(result.equals("success")) {
			//hash password
			String hashedpw = BCrypt.hashpw(password, BCrypt.gensalt());
			//create a new user object
			User newUser = new User(name,email,hashedpw);
			dao.createUser(newUser);
		}
		return result;
	}
}
