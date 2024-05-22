package codes.wrath.manager.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.persistence.FetchType;

import codes.wrath.manager.enums.UserRole;

@SuppressWarnings("serial")
@Entity(name = "user")
public class User extends GenericDomain {
	@Column(length = 32, nullable = false, name = "user_password")
	private String password;

	@Transient
	private String passwordWithoutHash;

	@Column(name = "user_role", nullable = false)
	private UserRole role = UserRole.CLIENT;

	@Column(nullable = false, name = "user_active")
	private Boolean active = false;

	@OneToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "person_id", nullable = false)
	private Person person;

	public User() {
	}

	public User(String password, UserRole role, Boolean active, Person person) {
		this.password = password;
		this.role = role;
		this.active = active;
		this.person = person;
	}

	public User(String password, UserRole role, Boolean active) {
		this.password = password;
		this.role = role;
		this.active = active;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public String getPasswordWithoutHash() {
		return passwordWithoutHash;
	}

	public void setPasswordWithoutHash(String passwordWithoutHash) {
		this.passwordWithoutHash = passwordWithoutHash;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}