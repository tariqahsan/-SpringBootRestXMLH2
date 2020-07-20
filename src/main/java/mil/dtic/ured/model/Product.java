package mil.dtic.ured.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
//@Table(name= "PROD", catalog = "ESET")
@Table(name= "PROD")
//public class Product extends EsetData implements Serializable {
public class Product implements Serializable {

	private static final long serialVersionUID = -6925152486027015274L;

	@Id
	@SequenceGenerator(name="PROD_SEQ_GEN", sequenceName="PROD_SEQ", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PROD_SEQ_GEN")
	@Column(nullable = false, name = "PROD_UID")
	private Long uid;
	
	@Column(nullable = false, name = "PROD_NM")
	private String name;
	
	@Column(nullable = false, name = "PROD_DESC")
	private String description;
	
	@Column(nullable = false, name = "EFF_STDT")
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate effectiveStartDate;

	@Column(nullable = false, name = "EFF_ENDT")
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate effectiveEndDate;
	
	@ManyToOne
	@JoinColumn(name = "PROD_MNFR_UID", referencedColumnName = "PROD_MNFR_UID", nullable = false, insertable = false, updatable = false)
    @JsonBackReference // Jackson JSON infinite recursion problem, to serialize entities with bidirectional relationships
	private ProductManufacturer productManufacturer; // BI-DIRECTIONAL MAPPING BACK TO PARENT
		
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "PROD_UID", referencedColumnName = "PROD_UID")
	private List<ProductEdition> productEditions; // CHILD
	
	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
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

	public List<ProductEdition> getProductEditions() {
		return productEditions;
	}

	public void setProductEditions(List<ProductEdition> productEditions) {
		this.productEditions = productEditions;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public ProductManufacturer getProductManufacturer() {
		return productManufacturer;
	}

	public void setProductManufacturer(ProductManufacturer productManufacturer) {
		this.productManufacturer = productManufacturer;
	}

}
