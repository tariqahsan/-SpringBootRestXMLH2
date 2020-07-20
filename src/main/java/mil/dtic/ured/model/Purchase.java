package mil.dtic.ured.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import mil.dtic.ured.validation.ValidPurchase;

/*
 * JavaBean Validation - Class level constraints
 * https://www.logicbig.com/tutorials/java-ee-tutorial/bean-validation/class-level-constraints.html
 */

@ValidPurchase
@Entity(name = "Purchase")
@Table(name = "PURCHASE")
public class Purchase {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PURCHASE_ID")
	private long id;
	
	private BigDecimal price;
	private BigDecimal quantity;

	public Purchase(BigDecimal price, BigDecimal quantity) {
		this.price = price;
		this.quantity = quantity;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public BigDecimal getPrice() {
		return price;
	}


	public void setPrice(BigDecimal price) {
		this.price = price;
	}


	public BigDecimal getQuantity() {
		return quantity;
	}


	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}


	public BigDecimal getTotalPrice () {
		return (price != null && quantity != null ?
				price.multiply(quantity) : BigDecimal.ZERO)
				.setScale(2, RoundingMode.CEILING);
	}

}
