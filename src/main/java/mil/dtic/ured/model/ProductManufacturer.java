package mil.dtic.ured.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
//@Table(name= "PROD_MNFR_RF", catalog = "ESET")
@Table(name= "PROD_MNFR_RF")
public class ProductManufacturer implements Serializable {
	
	private static final long serialVersionUID = 4555106361603383276L;

	@Id
	@SequenceGenerator(name="PROD_MNFR_RF_SEQ_GEN", sequenceName="PROD_MNFR_RF_SEQ", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PROD_MNFR_RF_SEQ_GEN")
	@Column(nullable = false, name = "PROD_MNFR_UID")
	private Long uid;
	
	@Column(nullable = false, name = "MNFR_NM")
	private String name;
	
	@Column(nullable = false, name = "EFF_STDT")
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate effectiveStartDate;
	
	@Column(nullable = false, name = "EFF_ENDT")
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate effectiveEndDate;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "PROD_MNFR_UID", referencedColumnName = "PROD_MNFR_UID", nullable = false)
	@OrderBy("name ASC")
	private List<Product> products;
	
	// The default constructor only exists for the sake of JPA
	public ProductManufacturer() {}
		
	public ProductManufacturer(String name, LocalDate effectiveStartDate) {
		this.name = name;
		this.effectiveStartDate = effectiveStartDate;
	}

	public LocalDate getEffectiveStartDate() {
		return effectiveStartDate;
	}

	public void setEffectiveStartDate(LocalDate effectiveStartDate) {
		this.effectiveStartDate = effectiveStartDate;
	}

	public LocalDate getEffectiveEndDate() {
		return effectiveEndDate;
	}

	public void setEffectiveEndDate(LocalDate effectiveEndDate) {
		this.effectiveEndDate = effectiveEndDate;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
