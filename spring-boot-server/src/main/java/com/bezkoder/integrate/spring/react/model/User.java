package com.bezkoder.integrate.spring.react.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.Instant;


@Entity
@Table(name = "users")
public class User {
	private static final String ALPHABETS= "^[a-zA-Z]{4,6}$";
	private static final String COUNTRY= "^[a-zA-Z]{1,20}$";
	private static final String NAME = "^[A-Za-z][A-Za-z0-9_]{3,29}$";
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotEmpty
	@Column(name = "name")
	@Pattern(regexp = NAME, message = "name is not valid")
	private String name;

	@Max(value = 200, message = "age is not valid")
	@Min(value = 1, message = "age is not valid")
	@Column(name = "age")
	private Integer age;

	@NotEmpty
	@Pattern(regexp = ALPHABETS, message = "sex is not valid")
	@Column(name = "sex")
	private String sex;

	@NotEmpty
	@Column(name = "country")
	@Pattern(regexp = COUNTRY, message = "country is not valid")
	private String country;

	@Column(name = "dateCreated")
	private Instant dateCreated;

	public User() {

	}

	public User(String name, String sex, int age, String country) {
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.country = country;
		this.dateCreated = Instant.now();
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Instant getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Instant dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + ", sex=" + sex + "]";
	}

}
