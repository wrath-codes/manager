package codes.wrath.manager.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import codes.wrath.manager.dao.RestaurantDAO;
import codes.wrath.manager.domain.Restaurant;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class RestaurantControlBean implements Serializable{
	private Restaurant restaurant;
	private List<Restaurant> restaurants;
	
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	public Restaurant getRestaurant() {
		return restaurant;
	}
	
	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}
	
	public List<Restaurant> getRestaurants() {
		return restaurants;
	}
	
	
	@PostConstruct
	public void list() {
		try {
			RestaurantDAO restaurantDAO = new RestaurantDAO();
			restaurants = restaurantDAO.list();
		} catch (RuntimeException error) {
			Messages.addGlobalError("An error occurred while trying to list the restaurants");
			error.printStackTrace();
		}
	}
	
	public void save() {
		try {
			RestaurantDAO restaurantDAO = new RestaurantDAO();
			restaurantDAO.merge(restaurant);

			restaurant = new Restaurant();
			restaurants = restaurantDAO.list();

			Messages.addGlobalInfo("Restaurant saved successfully");
		} catch (RuntimeException error) {
			Messages.addGlobalError("An error occurred while trying to save the restaurant");
			error.printStackTrace();
		}
	}
	
	public void delete(ActionEvent event) {
		try {
			restaurant = (Restaurant) event.getComponent().getAttributes().get("selectedRestaurant");

			RestaurantDAO restaurantDAO = new RestaurantDAO();
			restaurantDAO.delete(restaurant);

			restaurants = restaurantDAO.list();

			Messages.addGlobalInfo("Restaurant removed successfully");
		} catch (RuntimeException error) {
			Messages.addGlobalError("An error occurred while trying to remove the restaurant");
			error.printStackTrace();
		}
	}
	
	public void edit(ActionEvent event) {
		restaurant = (Restaurant) event.getComponent().getAttributes().get("selectedRestaurant");
	}
	
	

}
