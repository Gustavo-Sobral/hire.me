package com.hireme.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.hireme.model.Url;
import com.hireme.repository.Urls;
import com.hireme.util.jpa.Transactional;

public class CadastroUrlService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Urls urls;

	@Transactional
	public Url salvar(Url url) {
		String urlCurta = url.getUrlCurta().trim();
		Url urlCurstaExistente = urls.porUrlCurta(urlCurta);
		if(urlCurta.equals("")){
			url = construirUrlCurta(url);
		} else if(urlCurstaExistente != null) {
			throw new NegocioException("Já existe um Custom Alias com este formato!");			
		} 
		
		url.setVisitas(0);
		return urls.guardar(url);
	}
	
	public Url construirUrlCurta(Url url) {
		url.setUrlCurta("http://shortUrl.com/" + (urls.ultimoId()+1));
		
		return url;
	}
}