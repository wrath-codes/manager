package codes.wrath.manager.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.FetchType;

@SuppressWarnings("serial")
@Entity(name = "person")
public class Person extends GenericDomain {

	@Column(length = 32, nullable = false, name = "person_name")
	private String name;

	@Column(length = 32, nullable = false, name = "person_cpf")
	private String cpf;

	@Column(length = 12, nullable = false, name = "person_rg")
	private String rg;

	@Column(length = 14, nullable = false, name = "person_cellphone")
	private String cellphone;

	@Column(length = 100, nullable = false, name = "person_email")
	private String email;
	
	@OneToOne(optional = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;
	
	public Person() {
	}
	
	public Person(String name, String cpf, String rg, String cellphone, String email, User user) {
		this.name = name;
		this.cpf = cpf;
		this.rg = rg;
		this.cellphone = cellphone;
		this.email = email;
		this.user = user;
	}
	
	public Person(String name, String cpf, String rg, String cellphone, String email) {
		this.name = name;
		this.cpf = cpf;
		this.rg = rg;
		this.cellphone = cellphone;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
}
