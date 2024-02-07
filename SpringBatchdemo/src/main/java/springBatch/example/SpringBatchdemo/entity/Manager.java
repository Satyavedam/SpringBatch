package springBatch.example.SpringBatchdemo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Managers")
public class Manager {
	@Id
	@Column(name = "ManagerId")
	private int managerId;

	@Column(name = "ManagerName")
	private String managerName;

	@Column(name = "Email")
	private String email;

	public Manager() {
		super();
	}

	public Manager(int managerId, String managerName, String email) {
		super();
		this.managerId = managerId;
		this.managerName = managerName;
		this.email = email;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}