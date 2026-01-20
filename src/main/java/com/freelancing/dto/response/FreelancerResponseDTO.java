package com.freelancing.dto.response;

import com.freelancing.enums.FreelancerStatus;

public class FreelancerResponseDTO {

		private Long freelancerId;
		
		private String name;
		
	    private String email;
	    
	    private String skills;
	    
		private double experienceYears;
		
	    private double rating;
	    
	    private FreelancerStatus status;
	    
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
		public String getSkills() {
			return skills;
		}
		public void setSkills(String skills) {
			this.skills = skills;
		}
		
		public double getExperienceYears() {
			return experienceYears;
		}
		public void setExperienceYears(double experienceYears) {
			this.experienceYears = experienceYears;
		}
		public FreelancerStatus getStatus() {
			return status;
		}
		public void setStatus(FreelancerStatus status) {
			this.status = status;
		}
		public Long getFreelancerId() {
			return freelancerId;
		}
		public void setFreelancerId(Long freelancerId) {
			this.freelancerId = freelancerId;
		}
		public double getRating() {
			return rating;
		}
		public void setRating(double rating) {
			this.rating = rating;
		}
	    
}
