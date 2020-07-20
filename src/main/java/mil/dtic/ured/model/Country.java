package mil.dtic.ured.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "Country")
@Table(name = "COUNTRY")
public class Country {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "COUNTRY_ID")
	private Long countryId;

//    @NotNull
//    @Size(min = 2, max = 2)
    private String iso2;
    
	@OneToOne
    @MapsId
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

    // Getters and setters    
    public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}
  
	public String getIso2() {
		return iso2;
	}

	public void setIso2(String iso2) {
		this.iso2 = iso2;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
