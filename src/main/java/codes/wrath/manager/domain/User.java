package codes.wrath.manager.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.apache.shiro.crypto.hash.SimpleHash;

import javax.persistence.FetchType;
import javax.persistence.CascadeType;

import codes.wrath.manager.enums.UserRole;

@SuppressWarnings("serial")
@Entity(name = "user")
public class User extends GenericDomain {
	@Column(length = 32, nullable = false, name = "user_password")
	private String password;

	@Column(name = "user_role", nullable = false)
	private UserRole role = UserRole.CLIENT;

	@Column(nullable = false, name = "user_active")
	private Boolean active = false;

	@OneToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "person_id", nullable = true)
	private Person person;

	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Restaurant> restaurants;

	public void setPassword(String password) {
		SimpleHash hash = new SimpleHash("md5", password);
		this.password = hash.toHex();
		this.password = password;
	}

	public String getPassword() {
		return password;
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

	public List<Restaurant> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}

}