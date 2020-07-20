package mil.dtic.ured.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity(name = "Vehicle")
@Table(name = "VEHICLE")
public class Vehicle {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String vehicleName;
	
	@ManyToMany(mappedBy = "vehicles")
	private Set<UserDetail> userDetails = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	public Set<UserDetail> getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(Set<UserDetail> userDetails) {
		this.userDetails = userDetails;
	}
	
	

}
