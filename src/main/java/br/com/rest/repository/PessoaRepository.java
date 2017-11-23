package br.com.rest.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.rest.entity.PessoaEntity;
import br.com.rest.http.Pessoa;

public class PessoaRepository {

	private final EntityManagerFactory entityManagerFactory;
	private final EntityManager entityManager;
	
	public PessoaRepository(){
		this.entityManagerFactory = Persistence.createEntityManagerFactory("persistence_unit_bancorest");
		this.entityManager = this.entityManagerFactory.createEntityManager();
	}

	public List<PessoaEntity> listaPessoas(){

		return entityManager.createQuery("select p from PessoaEntity as p").getResultList();
	}
	
	public PessoaEntity getPessoa(Integer codigo){

		return entityManager.find(PessoaEntity.class, codigo);
	}

	public void salvar(PessoaEntity pessoa){
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(pessoa);
		this.entityManager.getTransaction().commit();
	}

	public void alterar(PessoaEntity pessoa){
		this.entityManager.getTransaction().begin();
		this.entityManager.merge(pessoa);
		this.entityManager.getTransaction().commit();
	}

	public void excluir(Integer codigo){
		PessoaEntity pessoa = this.getPessoa(codigo);

		this.entityManager.getTransaction().begin();
		this.entityManager.remove(pessoa);
		this.entityManager.getTransaction().commit();
	}
}
