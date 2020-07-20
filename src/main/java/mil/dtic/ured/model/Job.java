package mil.dtic.ured.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "Job")
@Table(name = "JOB")
public class Job implements Serializable {
	
	private static final long serialVersionUID = -6790693372846798580L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "JOB_ID", unique = true, nullable = false)
	private Integer jobId;
	
	private String jobName;
	
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "WORKER_ID")
	private Worker worker;

	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}


}
