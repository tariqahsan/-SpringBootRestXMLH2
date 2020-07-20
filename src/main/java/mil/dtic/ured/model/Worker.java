package mil.dtic.ured.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "Worker")
@Table(name = "WORKER")
public class Worker implements Serializable {
	
	private static final long serialVersionUID = -6790693372846798580L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "WORKER_ID", unique = true, nullable = false)
	private Integer workerId;
	
	private String name;
	
	@OneToOne(mappedBy = "worker", cascade = CascadeType.ALL,
    fetch = FetchType.LAZY, optional = false)
	private Job job;

	public Integer getWorkerId() {
		return workerId;
	}

	public void setWorkerId(Integer workerId) {
		this.workerId = workerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

}
