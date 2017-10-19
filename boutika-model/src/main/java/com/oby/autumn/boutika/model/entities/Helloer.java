package com.oby.autumn.boutika.model.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Helloer extends BasicEntity {

	@Column
	private String name;

	@Column
	private String email;

	@ManyToMany(mappedBy = "helloers")
	private Set<Hello> hellos;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Hello)) {
			return false;
		}
		final Hello other = (Hello) obj;
		if (getId() == null) {
			if (other.getId() != null) {
				return false;
			}
		} else if (!getId().equals(other.getId())) {
			return false;
		}
		return true;
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

	public Set<Hello> getHellos() {
		return hellos;
	}

	public void setHellos(Set<Hello> hellos) {
		this.hellos = hellos;
	}

}
