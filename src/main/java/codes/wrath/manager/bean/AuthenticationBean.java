package codes.wrath.manager.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import codes.wrath.manager.dao.PersonDAO;
import codes.wrath.manager.dao.UserDAO;
import codes.wrath.manager.domain.Person;
import codes.wrath.manager.domain.User;
import codes.wrath.manager.enums.UserRole;

@ManagedBean
@SessionScoped
@SuppressWarnings("serial")
public class AuthenticationBean implements Serializable {
	private User user;
	private User loggedUser;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
	}
	
	public void init() {
		addUser();
	}

	public void login() throws IOException {
		try {
			UserDAO userDAO = new UserDAO();
			loggedUser = userDAO.authenticate(user.getPerson().getEmail(), user.getPassword());

			if (loggedUser == null) {
				Messages.addGlobalError("Invalid email and/or password");
				return;
			}

			if (loggedUser.getRole().equals(UserRole.ADMIN) || loggedUser.getRole().equals(UserRole.MANAGER)) {
				Messages.addGlobalInfo("Welcome, " + loggedUser.getPerson().getEmail());
				Faces.redirect("/Manager/pages/admim/restaurants.xhtml");
			}

			if (loggedUser.getRole().equals(UserRole.CLIENT)) {
				Messages.addGlobalInfo("Welcome, " + loggedUser.getPerson().getEmail());
				Faces.redirect("/Manager/pages/user/restaurants.xhtml");
			}
		} catch (RuntimeException error) {
			Messages.addGlobalError("An error occurred while trying to authenticate the user");
			error.printStackTrace();
		}
	}

	public boolean hasPermission(List<String> roles) {
		if (loggedUser == null) {
			return false;
		}

		for (String role : roles) {
			if (loggedUser.getRole().toString().equals(role)) {
				return true;
			}
		}

		return false;
	}

	public void logout() throws IOException {
		loggedUser = null;
		Faces.redirect("/Manager/pages/authentication/login.xhtml");
	}

	public void addUser() {
		user = new User();
		user.setPerson(new Person());
	}

	public void saveUser() {
		try {
			// Ensure that the person object is fully initialized
			if (user.getPerson() == null) {
				user.setPerson(new Person());
			}

			// Save the person entity first
			PersonDAO personDAO = new PersonDAO();
			personDAO.save(user.getPerson());

			// Then merge the user entity
			UserDAO userDAO = new UserDAO();
			
			SimpleHash hash = new SimpleHash("md5", user.getPassword());
			user.setPassword(hash.toHex());
			userDAO.merge(user);


			// Display success message
			Messages.addGlobalInfo("User saved successfully");
		} catch (RuntimeException error) {
			Messages.addGlobalError("An error occurred while trying to save the user");
			error.printStackTrace();
		}
	}

}
