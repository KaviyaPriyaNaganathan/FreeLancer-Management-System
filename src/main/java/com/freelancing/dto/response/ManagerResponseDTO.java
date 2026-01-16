package com.freelancing.dto.response;

import com.freelancing.enums.ManagerStatus;
import com.freelancing.enums.Role;

public class ManagerResponseDTO {

	 	private Long managerId;
	    private String name;
	    private String email;
	    private String department;
	    private Role role;
	    private ManagerStatus status;
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
		public ManagerStatus getStatus() {
			return status;
		}
		public void setStatus(ManagerStatus status) {
			this.status = status;
		}
	    
	    
	    
}
