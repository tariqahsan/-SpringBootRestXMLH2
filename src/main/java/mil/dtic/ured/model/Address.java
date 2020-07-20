package mil.dtic.ured.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import mil.dtic.ured.validation.ValidAddress;

@ValidAddress
@Entity(name = "Address")
@Table(name = "ADDRESS")
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ADDRESS_ID")
	private long addressId;

    @NotNull
    @Size(max = 50)
    private String street1;

    @Size(max = 50)
    private String street2;

    @NotNull
    @Size(min = 5, max = 5)
    private String zipCode;

    @NotNull
    @Size(max = 30)
    private String city;

    @Valid
    @NotNull
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinTable(name="ADDRESS_COUNTRY", joinColumns = @JoinColumn(name="ADDRESS_ID"),
//	inverseJoinColumns = @JoinColumn(name="COUNTRY_ID"))
	@OneToOne(mappedBy = "address", cascade = CascadeType.ALL)
    private Country country;
    
    public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
    
}
