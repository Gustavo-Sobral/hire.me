package com.hireme.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.hireme.model.Url;
import com.hireme.repository.Urls;
import com.hireme.service.NegocioException;

@Named
@ViewScoped
public class PesquisaUrlsBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Urls urlRepository;

	private String urlBuscar;	
	private Url urlFiltrada;

	public PesquisaUrlsBean() {
		urlFiltrada = new Url();
	}

	public void pesquisar() {
		urlFiltrada = urlRepository.porUrlCurta(this.urlBuscar);		
		if(urlFiltrada==null){
			throw new NegocioException("Erro: URL curta não encontrada!");
		} else {
			urlRepository.visitar(urlFiltrada);
		}
		
		RequestContext context = RequestContext.getCurrentInstance(); 
        context.execute("urlDialog.show()");
	}
	
	public void redirecionar() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("https://www.youtube.com/");
		} catch (IOException e) {
			throw new NegocioException("Não possível redirecionar para esta página!");
		}
	}

	public String getUrlBuscar() {
		return urlBuscar;
	}

	public void setUrlBuscar(String urlBuscar) {
		this.urlBuscar = urlBuscar;
	}

	public Url getUrlFiltrada() {
		return urlFiltrada;
	}

	public void setUrlFiltrada(Url urlFiltrada) {
		this.urlFiltrada = urlFiltrada;
	}
}