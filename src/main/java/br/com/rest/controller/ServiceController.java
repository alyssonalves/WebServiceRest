package br.com.rest.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import br.com.rest.entity.PessoaEntity;
import br.com.rest.http.Pessoa;
import br.com.rest.repository.PessoaRepository;

@Path("/service")
public class ServiceController {
	
	PessoaRepository rep = new PessoaRepository();
	
	private final Gson gson = new Gson();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/listaPessoas")
	public List<Pessoa> listaPessoas(){
		
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		
		List<PessoaEntity> listaEnt = rep.listaPessoas();
		
		for(PessoaEntity p : listaEnt) {
			pessoas.add(new Pessoa(p.getCodigo(), p.getNome(), p.getSexo()));
		}
		return pessoas;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getPessoa/{codigo}")
	public Pessoa getPessoa(@PathParam("codigo") Integer codigo) {
		PessoaEntity p = rep.getPessoa(codigo);
		
		if(p != null) {
			return new Pessoa(p.getCodigo(), p.getNome(), p.getSexo());
		}else {
			return null;
		}
	}
	
	
}
