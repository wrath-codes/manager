package codes.wrath.manager.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;

@SuppressWarnings("serial")
@Entity(name = "cart_item")
public class CartItem extends GenericDomain {
	@Column(nullable = false, name = "cart_item_quantity")
	private Integer quantity;
	
	@Column(length = 100, nullable = true, name = "cart_item_observation")
	private String observation;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "dish_id", nullable = false)
	private Dish dish;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "cart_id", nullable = false)
	private Cart cart;
	
	
	
	public CartItem() {
	}
	
	public CartItem(Integer quantity, String observation, Dish dish, Cart cart) {
		this.quantity = quantity;
		this.observation = observation;
		this.dish = dish;
		this.cart = cart;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public String getObservation() {
		return observation;
	}
	
	public void setObservation(String observation) {
		this.observation = observation;
	}
	
	public Dish getDish() {
		return dish;
	}
	
	public void setDish(Dish dish) {
		this.dish = dish;
	}
}
