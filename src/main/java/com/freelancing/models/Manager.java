package com.freelancing.models;

import java.util.List;

import com.freelancing.enums.ManagerStatus;
import com.freelancing.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;

@Entity
@Table(name = "managers")
public class Manager {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long managerId;
	
	@Column(nullable=false)
	private String name;
	
	@Email
	@Column(nullable=false, unique = true)
	private String email;
	
	@Column(nullable=false)
	private String password;
	
	@Column(nullable=false)
	private String department;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private Role role = Role.MANAGER;
	
	@OneToMany(mappedBy = "manager")
	List<Project> assignedProjects;

	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private ManagerStatus status = ManagerStatus.ACTIVE;

	public Manager(Long managerId, String name, @Email String email, String password, String department, Role role,
			List<String> assignedProjects, ManagerStatus isActive) {
		super();
		this.managerId = managerId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.department = department;
		this.role = Role.MANAGER;
		this.status = ManagerStatus.ACTIVE;
	}

	public Manager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getManagerId() {
		return managerId;
	}

	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Project> getAssignedProjects() {
		return assignedProjects;
	}

	public void setAssignedProjects(List<Project> assignedProjects) {
		this.assignedProjects = assignedProjects;
	}

	public ManagerStatus getStatus() {
		return status;
	}

	public void setStatus(ManagerStatus status) {
		this.status = status;
	}


	
}

