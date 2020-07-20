package mil.dtic.ured.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "Orders")
@Table(name = "ORDERS")
public class Orders implements Serializable {
	
	private static final long serialVersionUID = -6790693372846798580L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ORDER_ID", unique = true, nullable = false)
	private Integer orderId;
	
	private String description;
	
	@OneToOne(mappedBy = "orders", cascade = CascadeType.ALL)
	private Merchandise merchandise;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Merchandise getMerchandise() {
		return merchandise;
	}

	public void setMerchandise(Merchandise merchandise) {
		this.merchandise = merchandise;
	}
	

}
