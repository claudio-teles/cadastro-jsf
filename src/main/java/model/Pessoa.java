package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -20505665646013859L;
	
	@Id
	@GeneratedValue(generator = "gerador_de_id", strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "gerador_de_id", sequenceName = "sequencia_h2", initialValue = 50, allocationSize = 1)
	@Column(nullable = false)
	private Long id;
	@Column(nullable = false, length = 40, name = "nome")
	private String nome_;
	@Column(nullable = false, length = 40, name = "email")
	private String email_;
	@Column(nullable = false, length = 40, name = "senha")
	private String senha_;
	@ElementCollection(fetch = FetchType.EAGER)
	@Column(nullable = false, length = 20)
	private List<String> telefone;
	
	private Boolean login;
	
	
	public Pessoa() {
		super();
	}

	public Pessoa(String nome_, String email_, String senha_, List<String> telefone) {
		super();
		this.nome_ = nome_;
		this.email_ = email_;
		this.senha_ = senha_;
		this.telefone = telefone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome_() {
		return nome_;
	}

	public void setNome_(String nome_) {
		this.nome_ = nome_;
	}

	public String getEmail_() {
		return email_;
	}

	public void setEmail_(String email_) {
		this.email_ = email_;
	}

	public String getSenha_() {
		return senha_;
	}

	public void setSenha_(String senha_) {
		this.senha_ = senha_;
	}

	public List<String> getTelefone() {
		return telefone;
	}

	public void setTelefone(List<String> telefone) {
		this.telefone = telefone;
	}

	public Boolean getLogin() {
		return login;
	}

	public void setLogin(Boolean login) {
		this.login = login;
	}

}