package com.hireme.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Table(name = "url")
public class Url implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String urlLonga;
	private String urlCurta; // ou custom alias
	private int visitas;

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotBlank
	@URL
	@Size(max = 2000)
	@Column(name = "url_longa", nullable = false, length = 2000)
	public String getUrlLonga() {
		return urlLonga;
	}

	public void setUrlLonga(String urlLonga) {
		this.urlLonga = urlLonga;
	}

	@Size(max = 2000)
	@Column(name = "url_curta", nullable = false, length = 2000)
	public String getUrlCurta() {
		return urlCurta;
	}

	public void setUrlCurta(String urlCurta) {
		this.urlCurta = urlCurta;
	}
	
	@Column(nullable = false, length = 10)
	public int getVisitas() {
		return visitas;
	}

	public void setVisitas(int visitas) {
		this.visitas = visitas;
	}
}