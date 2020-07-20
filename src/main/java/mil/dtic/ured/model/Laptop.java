package mil.dtic.ured.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "Laptop")
@Table(name = "laptop")
public class Laptop {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LAPTOP_ID")
	private int laptopId;
	
	private String laptopName;
	
	@ManyToOne
	@JoinColumn(name = "STUDENT_ID")
	private Student student;

	public int getLaptopId() {
		return laptopId;
	}

	public void setLaptopId(int laptopId) {
		this.laptopId = laptopId;
	}

	public String getLaptopName() {
		return laptopName;
	}

	public void setLaptopName(String laptopName) {
		this.laptopName = laptopName;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	

}
