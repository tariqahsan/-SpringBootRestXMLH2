package mil.dtic.ured.model;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "Institution")
@Table(name = "INSTITUITION")
public class Institution implements Serializable {
	
	private static final long serialVersionUID = -6790693372846798580L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false)
	private Integer instituitionId;
	
	private String institutionName;
	
	@OneToMany(mappedBy = "institution", cascade=CascadeType.ALL)
	private Set<Teacher> teachers = new HashSet<>();

	public Integer getInstituitionId() {
		return instituitionId;
	}

	public void setInstituitionId(Integer instituitionId) {
		this.instituitionId = instituitionId;
	}

	public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public Set<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(Set<Teacher> teachers) {
		this.teachers = teachers;
	}

}
