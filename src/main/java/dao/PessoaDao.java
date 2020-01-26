package dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import model.Pessoa;

public class PessoaDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6246523898993213355L;
	
	public static SessionFactory obterSessionFactory() {
		Configuration configuration = new Configuration().configure();
		configuration.addAnnotatedClass(Pessoa.class);
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
		return sessionFactory;
	}
	
	public Pessoa cadastrar(Pessoa pessoa) {
		Session sessionCadastrar = obterSessionFactory().openSession();
		sessionCadastrar.beginTransaction();
		sessionCadastrar.save(pessoa);
		sessionCadastrar.getTransaction().commit();
		sessionCadastrar.close();
		return pessoa;
	}
	
	public Boolean login(String nome, String senha) {
		Pessoa pessoa = new Pessoa();
		
		Session sessionLogin = obterSessionFactory().openSession();
		sessionLogin.beginTransaction();
		
		CriteriaBuilder cb = sessionLogin.getCriteriaBuilder();
		CriteriaQuery<Pessoa> cq = cb.createQuery(Pessoa.class);
		Root<Pessoa> _pessoa = cq.from(Pessoa.class);
		Predicate predicate = cb.equal(_pessoa.get("nome_"), nome);
		cq.select(_pessoa).where(predicate);
		
		Query<Pessoa> query = sessionLogin.createQuery(cq);
		pessoa = query.getSingleResult();
		
		if (pessoa.getLogin() == false) {
			pessoa.setLogin(true);
			sessionLogin.saveOrUpdate(pessoa);
		}
		
		sessionLogin.getTransaction().commit();
		sessionLogin.close();
		return false;
	}
	
	public Boolean logout(Long id) {
		Pessoa pessoa = new Pessoa();
		
		Session sessionPessoa = obterSessionFactory().openSession();
		sessionPessoa.beginTransaction();
		
		pessoa = (Pessoa) sessionPessoa.get(Pessoa.class, id);
		
		if (pessoa.getLogin() == true) {
			pessoa.setLogin(false);
			sessionPessoa.saveOrUpdate(pessoa);
			return true;
		}
		
		sessionPessoa.getTransaction().commit();
		sessionPessoa.close();
		return false;
	}
	
	public Pessoa obterUmaPessoa(Long id) {
		Pessoa pessoa = new Pessoa();
		
		Session sessionPessoa = obterSessionFactory().openSession();
		sessionPessoa.beginTransaction();
		
		pessoa = (Pessoa) sessionPessoa.get(Pessoa.class, id);
		
		if (pessoa.getLogin() == true) {
			return pessoa;
		}
		
		sessionPessoa.getTransaction().commit();
		sessionPessoa.close();
		
		return null;
		
	}
	
	public List<Pessoa> obterPessoas() {
		
		Session sessionPessoas = obterSessionFactory().openSession();
		sessionPessoas.beginTransaction();
		
		List<Pessoa> pessoas = new ArrayList<>();
		CriteriaBuilder cb = sessionPessoas.getCriteriaBuilder();
		CriteriaQuery<Pessoa> cq = cb.createQuery(Pessoa.class);
		Root<Pessoa> pessoa = cq.from(Pessoa.class);
		cq.select(pessoa).orderBy(cb.asc(pessoa.get("id")));
		Query<Pessoa> query = sessionPessoas.createQuery(cq);
		pessoas = query.list();
		
		sessionPessoas.getTransaction().commit();
		sessionPessoas.close();
		
		return pessoas;
	}
	
	public Boolean atualizarPeloId(Long id, Pessoa pessoa) {
		Session sessionAtualizar = obterSessionFactory().openSession();
		sessionAtualizar.beginTransaction();
		
		sessionAtualizar.saveOrUpdate(pessoa);
		
		sessionAtualizar.getTransaction().commit();
		sessionAtualizar.close();
		return false;
	}
	
	public Pessoa deletarPessoaPeloId(Long id) {
		Pessoa pessoa = new Pessoa();
		Session sessionDeletar = obterSessionFactory().openSession();
		sessionDeletar.beginTransaction();
		
		pessoa = sessionDeletar.get(Pessoa.class, id);
		if (pessoa.getId() != 50 && pessoa.getLogin() == true) {
			sessionDeletar.delete(pessoa);
		}
		sessionDeletar.getTransaction().commit();
		sessionDeletar.close();
		return pessoa;
	}

}
