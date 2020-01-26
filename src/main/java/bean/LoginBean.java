package bean;

import dao.PessoaDao;

public class LoginBean {
	
	private Long id;
	private String nome;
	private String senha;
	
	public String irParaPessoas() {
		PessoaDao pessoaDao = new PessoaDao();
		pessoaDao.login(this.getNome(), this.getSenha());
		return "pessoas?faces-redirect=true";
	}
	
	public String irParaLogout(Long id) {
		PessoaDao pessoaDao = new PessoaDao();
		pessoaDao.logout(id);
		return "logout?faces-redirect=true";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
