package codes.wrath.manager.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

import codes.wrath.manager.enums.MenuSeason;

import javax.persistence.FetchType;

@SuppressWarnings("serial")
@Entity(name = "menu")
public class Menu extends GenericDomain {
	@Column(length = 100, nullable = false, name = "menu_name")
	private String name;

	@Column(name = "menu_season", nullable = false)
	private MenuSeason season = MenuSeason.ALL;

	@Column(length = 100, nullable = true, name = "menu_description")
	private String description = name;

	@Column(name = "menu_active", nullable = false)
	private Boolean active = true;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "restaurant_id", nullable = false)
	private Restaurant restaurant;

	@OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Dish> dishes;

	@OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Category> categories;

	public String getName() {
		return name;
	}

	public Menu() {
		dishes = new ArrayList<Dish>();
		categories = new ArrayList<Category>();
	}

	public Menu(Restaurant restaurant) {
		this.restaurant = restaurant;
		dishes = new ArrayList<Dish>();
		categories = new ArrayList<Category>();
	}

	public Menu(String name, Restaurant restaurant) {
		this.name = name;
		this.restaurant = restaurant;
		dishes = new ArrayList<Dish>();
		categories = new ArrayList<Category>();
	}

	public Menu(String name, MenuSeason season, Restaurant restaurant) {
		this.name = name;
		this.season = season;
		this.restaurant = restaurant;
		dishes = new ArrayList<Dish>();
		categories = new ArrayList<Category>();
	}

	public Menu(String name, MenuSeason season, String description, Restaurant restaurant) {
		this.name = name;
		this.season = season;
		this.description = description;
		this.restaurant = restaurant;
		dishes = new ArrayList<Dish>();
		categories = new ArrayList<Category>();
	}

	public Menu(String name, MenuSeason season, String description, Boolean active, Restaurant restaurant) {
		this.name = name;
		this.season = season;
		this.description = description;
		this.active = active;
		this.restaurant = restaurant;
		dishes = new ArrayList<Dish>();
		categories = new ArrayList<Category>();
	}

	public Menu(String name, MenuSeason season, String description, Boolean active, Restaurant restaurant,
			List<Category> categories) {
		this.name = name;
		this.season = season;
		this.description = description;
		this.active = active;
		this.restaurant = restaurant;
		this.categories = categories;
		dishes = new ArrayList<Dish>();
	}

	public Menu(String name, MenuSeason season, String description, Boolean active, Restaurant restaurant,
			List<Dish> dishes, List<Category> categories) {
		this.name = name;
		this.season = season;
		this.description = description;
		this.active = active;
		this.restaurant = restaurant;
		this.dishes = dishes;
		this.categories = categories;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public MenuSeason getSeason() {
		return season;
	}

	public void setSeason(MenuSeason season) {
		this.season = season;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public List<Dish> getDishs() {
		return dishes;
	}

	public void setDishs(List<Dish> dishes) {
		this.dishes = dishes;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

}
