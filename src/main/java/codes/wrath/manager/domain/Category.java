package codes.wrath.manager.domain;

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
@Entity(name = "menu_category")
public class Category extends GenericDomain{
	@Column(length = 100, nullable = false, name = "category_name")
	private String name;
	
	@Column(length = 100, nullable = true, name = "category_description")
	private String description = name;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "menu_id", nullable = false)
	private Menu menu;
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Dish> dishes;
	
	public Category() {
	}
	
	public Category(String name, String description, Menu menu) {
		this.name = name;
		this.description = description;
		this.menu = menu;
		this.dishes = new ArrayList<Dish>();
	}
	
	public Category(String name, Menu menu) {
		this.name = name;
		this.menu = menu;
		this.dishes = new ArrayList<Dish>();
	}
	
	public Category(String name, String description, Menu menu, List<Dish> dishes) {
		this.name = name;
		this.description = description;
		this.menu = menu;
		this.dishes = new ArrayList<Dish>();
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
	
	public Menu getMenu() {
		return menu;
	}
	
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
	public List<Dish> getDishs() {
		return dishes;
	}
	
	public void setDishs(List<Dish> dishes) {
		this.dishes = dishes;
	}
}
