
package codes.wrath.manager.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;

import codes.wrath.manager.enums.OrderStatus;

@SuppressWarnings("serial")
@Entity(name = "cart_order")
public class CartOrder extends GenericDomain{
	@Column(nullable = false, name = "cart_order_status")
	private OrderStatus status = OrderStatus.PENDING;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "cart_id", nullable = false)
	private Cart cart;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "restaurant_id", nullable = false)
	private Restaurant restaurant;
	
	public CartOrder() {
	}
	
	public CartOrder(OrderStatus status, Cart cart) {
		this.status = status;
		this.cart = cart;
	}
	
	public OrderStatus getStatus() {
		return status;
	}
	
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	
	public Cart getCart() {
		return cart;
	}
	
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	public Restaurant getRestaurant() {
		return restaurant;
	}
	
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
}
