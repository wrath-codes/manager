package codes.wrath.manager.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;

import codes.wrath.manager.enums.CartStatus;

import javax.persistence.Column;

import java.math.BigDecimal;
import java.util.List;

@SuppressWarnings("serial")
@Entity(name = "cart")
public class Cart extends GenericDomain {
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CartItem> items;
	
	@Column(nullable = false, name = "cart_status")
	private CartStatus status = CartStatus.OPEN;

	@Column(nullable = false, precision = 6, scale = 2, name = "cart_total")
	private BigDecimal total;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	public Cart() {
	}
	
	public Cart(User user) {
		this.user = user;
	}
	
	public Cart(List<CartItem> items, CartStatus status, BigDecimal total, User user) {
		this.items = items;
		this.status = status;
		this.total = total;
		this.user = user;
	}
	
	public List<CartItem> getItems() {
		return items;
	}
	
	public void setItems(List<CartItem> items) {
		this.items = items;
	}
	
	public CartStatus getStatus() {
		return status;
	}
	
	public void setStatus(CartStatus status) {
		this.status = status;
	}
	
	public BigDecimal getTotal() {
		return total;
	}
	
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
}
