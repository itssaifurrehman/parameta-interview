package com.saif.parameta.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserDTO {

	@Id
	private int id;
	private String firstName;
	private String lastName;
	private String DocumentType;
	private String DocumentNumber;
	private Date dateOfBirth;
	private Date companyJoiningDate;
	private String cargo;
	private Double salary;
	private Date creationTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDocumentType() {
		return DocumentType;
	}

	public void setDocumentType(String documentType) {
		DocumentType = documentType;
	}

	public String getDocumentNumber() {
		return DocumentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		DocumentNumber = documentNumber;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Date getCompanyJoiningDate() {
		return companyJoiningDate;
	}

	public void setCompanyJoiningDate(Date companyJoiningDate) {
		this.companyJoiningDate = companyJoiningDate;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	@Override
	public String toString() {
		return String.format(
				"UserDTO [id=%s, firstName=%s, lastName=%s, DocumentType=%s, DocumentNumber=%s, dateOfBirth=%s, companyJoiningDate=%s, cargo=%s, salary=%s, creationTime=%s]",
				id, firstName, lastName, DocumentType, DocumentNumber, dateOfBirth, companyJoiningDate, cargo, salary,
				creationTime);
	}

}
