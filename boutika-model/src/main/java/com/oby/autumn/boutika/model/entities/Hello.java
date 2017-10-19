package com.oby.autumn.boutika.model.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.oby.autumn.boutika.configuration.IgnoreMapping;

@Entity
public class Hello extends BasicEntity {

	@Column
	private String message;

	@Column
	private Double price;

	@Column
	private Long quantity;

	@Column
	private int done;

	@Column
	private Boolean active;

	@ManyToOne
	@JoinColumn(name = "hello_home_id")
	private HelloHome helloHome;

	@IgnoreMapping
	@ManyToMany()
	@JoinTable(name = "meeting", joinColumns = @JoinColumn(name = "hello_id"), inverseJoinColumns = @JoinColumn(name = "helloer_id"))
	private Set<Helloer> helloers;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public int getDone() {
		return done;
	}

	public void setDone(int done) {
		this.done = done;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public HelloHome getHelloHome() {
		return helloHome;
	}

	public void setHelloHome(HelloHome helloHome) {
		this.helloHome = helloHome;
	}

	public Set<Helloer> getHelloers() {
		return helloers;
	}

	public void setHelloers(Set<Helloer> helloers) {
		this.helloers = helloers;
	}

}
