package com.hireme.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.hireme.model.Url;
import com.hireme.repository.Urls;
import com.hireme.service.CadastroUrlService;
import com.hireme.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroUrlBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroUrlService cadastroUrlService;	
	
	@Inject
	private Urls urlRepository;

	private Url url;
	private String finalUrl;
	private Long tempo;

	public CadastroUrlBean() {
		url = new Url();
		finalUrl = "";
	}

	public void salvar() {
		this.tempo = System.currentTimeMillis();
		this.url = cadastroUrlService.salvar(this.url);

		FacesUtil.addInfoMessage("URL salva com sucesso!");
		RequestContext context = RequestContext.getCurrentInstance(); 
        context.execute("urlDialog.show()");
        
        this.tempo = System.currentTimeMillis() - this.tempo;
	}
	
	public void limpar(){
		this.url = new Url();
	}

	public Long getTempo() {
		return tempo;
	}

	public void setTempo(Long tempo) {
		this.tempo = tempo;
	}

	public String getFinalUrl() {
		return finalUrl;
	}

	public void setFinalUrl(String finalUrl) {
		this.finalUrl = finalUrl;
	}

	public Url getUrl() {
		return url;
	}

	public void setUrl(Url url) {
		this.url = url;
	}
}