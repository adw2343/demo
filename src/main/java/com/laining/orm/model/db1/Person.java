package com.laining.orm.model.db1;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.laining.orm.model.BaseEntity;

@Entity
@Table(name = "Person")
public class Person extends BaseEntity {

	private String name;

	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Person(String name) {
		super();
		this.name = name;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
