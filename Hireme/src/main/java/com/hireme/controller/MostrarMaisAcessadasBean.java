package com.hireme.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.hireme.model.Url;
import com.hireme.repository.Urls;

@Named
@ViewScoped
public class MostrarMaisAcessadasBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Urls urlRepository;
	
	private List<Url> urlsMaisAcessadas;
	
	public MostrarMaisAcessadasBean() {
		urlsMaisAcessadas = new ArrayList<>();
	}
	
	public void inicializar() {
		System.out.println("Inicializando..");
		urlsMaisAcessadas = urlRepository.maisAcessadas();
	}

	public List<Url> getUrlsMaisAcessadas() {
		return urlsMaisAcessadas;
	}

	public void setUrlsMaisAcessadas(List<Url> urlsMaisAcessadas) {
		this.urlsMaisAcessadas = urlsMaisAcessadas;
	}
}