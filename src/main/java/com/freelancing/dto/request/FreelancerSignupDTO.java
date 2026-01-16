package com.freelancing.dto.request;

public class FreelancerSignupDTO {

    private String name;
    private String email;
    private String password;
    private String skills;
	private double experienceYears;
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

    
}
