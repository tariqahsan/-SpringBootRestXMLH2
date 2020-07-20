package mil.dtic.ured.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "Student")
@Table(name = "student")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "STUDENT_ID")
	private int studentId;
	
	private String name;
	
	private String grade;
	
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY) 
	private List<Laptop> laptops;

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public List<Laptop> getLaptops() {
		return laptops;
	}

	public void setLaptops(List<Laptop> laptops) {
		this.laptops = laptops;
	}

}
