package codes.wrath.manager.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;

@SuppressWarnings("serial")
@Entity(name = "menu_dish")
public class Dish extends GenericDomain {
	@Column(length = 100, nullable = false, name = "dish_name")
	private String name;

	@Column(length = 100, nullable = true, name = "dish_description")
	private String description = name;

	@Column(nullable = false, precision = 6, scale = 2)
	private BigDecimal price;

	@Column(name = "dish_active", nullable = false)
	private Boolean active = true;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "menu_id", nullable = false)
	private Menu menu;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;
	
	@OneToMany(mappedBy = "dish", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CartItem> cartItems;
	
	public Dish() {
	}
	
	public Dish(String name, String description, BigDecimal price, Boolean active, Menu menu, Category category) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.active = active;
		this.menu = menu;
		this.category = category;
		this.cartItems = new ArrayList<CartItem>();
	}

	public Dish(String name, String description, BigDecimal price, Boolean active, Menu menu, Category category, List<CartItem> cartItems) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.active = active;
		this.menu = menu;
		this.category = category;
		this.cartItems = cartItems;
	}
	
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Boolean getActive() {
		return active;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	public List<CartItem> getCartItems() {
		return cartItems;
	}
	
	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}
}
