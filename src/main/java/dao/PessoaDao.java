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

import bean.AtualizarBean;
import model.Pessoa;

public class PessoaDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6246523898993213355L;
	private Boolean login = false;
	
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
		
		if (this.getLogin() == false) {
			pessoa.setLogin(true);
			this.setLogin(true);
			
			AtualizarBean atualizarBean = new AtualizarBean();
			atualizarBean.setId(pessoa.getId());
			atualizarBean.setNome(pessoa.getNome_());
			atualizarBean.setEmail(pessoa.getEmail_());
			atualizarBean.setSenha(pessoa.getSenha_());
			atualizarBean.setNumero(pessoa.getTelefone().get(0));
			atualizarBean.setDdd(pessoa.getTelefone().get(1));
			
			if (pessoa.getTelefone().get(2) == "Fixo") {
				atualizarBean.setFixo(true);
			}
			
			if (pessoa.getTelefone().get(2) == "Celular") {
				atualizarBean.setCelular(true);
			}
			
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
		
		if (this.getLogin() == true) {
			pessoa.setLogin(false);
			this.setLogin(false);
			sessionPessoa.saveOrUpdate(pessoa);
			return true;
		}
		
		sessionPessoa.getTransaction().commit();
		sessionPessoa.close();
		return false;
	}
	
	public Pessoa obterUmaPessoa(Long id) {
		if (this.getLogin() == true) {
			Pessoa pessoa = new Pessoa();
			Session sessionPessoa = obterSessionFactory().openSession();
			sessionPessoa.beginTransaction();
			
			pessoa = (Pessoa) sessionPessoa.get(Pessoa.class, id);
			
			sessionPessoa.getTransaction().commit();
			sessionPessoa.close();
			return pessoa;
		}
		return null;
	}
	
	public List<Pessoa> obterPessoas() {
		List<Pessoa> pessoas = new ArrayList<>();
		Session sessionPessoas = obterSessionFactory().openSession();
		sessionPessoas.beginTransaction();
		
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
		return true;
	}
	
	public Pessoa deletarPessoaPeloId(Long id) {
		Pessoa pessoa = new Pessoa();
		Session sessionDeletar = obterSessionFactory().openSession();
		sessionDeletar.beginTransaction();
		
		pessoa = sessionDeletar.get(Pessoa.class, id);
		sessionDeletar.delete(pessoa);
		
		sessionDeletar.getTransaction().commit();
		sessionDeletar.close();
		return pessoa;
	}

	public Boolean getLogin() {
		return login;
	}

	public void setLogin(Boolean login) {
		this.login = login;
	}

}
