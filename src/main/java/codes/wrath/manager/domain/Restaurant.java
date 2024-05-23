package codes.wrath.manager.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@SuppressWarnings("serial")
@Entity(name = "restaurant")
public class Restaurant extends GenericDomain {
	@Column(length = 100, nullable = false, name = "restaurant_name")
	private String name;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "restaurant_id", nullable = true)
	private User owner;

	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Menu> menus;
	
	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CartOrder> orders;

	public Restaurant() {
		menus = new ArrayList<Menu>();
		orders = new ArrayList<CartOrder>();
	}

	public Restaurant(String name) {
		this.name = name;
		menus = new ArrayList<Menu>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public User getOwner() {
		return owner;
	}
	
	public void setOwner(User owner) {
		this.owner = owner;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	
	public List<CartOrder> getOrders() {
		return orders;
	}
	
	public void setOrders(List<CartOrder> orders) {
		this.orders = orders;
	}

}
