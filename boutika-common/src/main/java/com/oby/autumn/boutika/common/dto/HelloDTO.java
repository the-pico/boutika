package com.oby.autumn.boutika.common.dto;

import java.io.Serializable;
import java.util.Set;

import com.oby.autumn.boutika.configuration.IgnoreMapping;
import com.oby.autumn.boutika.configuration.logger.Autolog;

@Autolog
public class HelloDTO extends BasicDto implements Serializable {

	/**
	 * 
	 */
	@IgnoreMapping
	private static final long serialVersionUID = -1624511343577738711L;


	private String message;

	private Double price;

	private Long quantity;

	private int done;

	private Boolean active;
	
	private HelloHomeDTO helloHome;
	
	private Set<HelloerDTO> helloers;
	
	
	public HelloHomeDTO getHelloHome() {
		return helloHome;
	}

	public void setHelloHome(HelloHomeDTO helloHome) {
		this.helloHome = helloHome;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Set<HelloerDTO> getHelloers() {
		return helloers;
	}

	public void setHelloers(Set<HelloerDTO> helloers) {
		this.helloers = helloers;
	}
	
	

}
