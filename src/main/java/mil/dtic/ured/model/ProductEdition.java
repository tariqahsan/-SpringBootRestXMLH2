package mil.dtic.ured.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
//@Table(name= "PROD_ED", catalog = "ESET")
@Table(name= "PROD_ED")
public class ProductEdition implements Serializable {

	private static final long serialVersionUID = 6431961841619287278L;

	@Id
	@SequenceGenerator(name="PROD_ED_SEQ_GEN", sequenceName="PROD_ED_SEQ", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PROD_ED_SEQ_GEN")
	@Column(nullable = false, name = "PROD_ED_UID")
	private Long uid;
	
	@Column(nullable = true, name = "PROD_ED_NM")
	private String name;
	
	@Column(nullable = true, name = "PROD_VER_NUM")
	private String versionNumber;
	
	/*@Column(nullable = false, name = "EFF_STDT")
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
	private LocalDate effectiveStartDate;*/
	
	@Column(nullable = false, name = "EFF_STDT")
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate effectiveStartDate;
	
	@Column(nullable = false, name = "EFF_ENDT")
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate effectiveEndDate;
	
	/*@ManyToOne
	@JoinColumn(name = "PROD_UID", referencedColumnName = "PROD_UID", nullable = false, insertable = false, updatable = false)
	private Product product; // PARENT
	 */	
	@ManyToOne
	@JoinColumn(name = "PROD_UID", referencedColumnName = "PROD_UID")
	private Product product; // PARENT
	
	public ProductEdition() {}
	
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

	public String getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

}
