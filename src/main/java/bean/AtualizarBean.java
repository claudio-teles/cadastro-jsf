package bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import dao.PessoaDao;
import model.Pessoa;

public class AtualizarBean {
	
	private Long id = 50L;
	private String nome;
	private String email;
	private String senha;
	private String numero;
	private String ddd;
	
	private boolean fixo;
	private boolean celular;
	
	private String resulstado = "";
	
	public Long definirIdInicial() {
		this.setId(50L);
		return this.getId();
	}
	
	public Pessoa pegarUmaPessoaPorId(Long id) {
		Pessoa pessoa = new Pessoa();
		PessoaDao pessoaDao = new PessoaDao();
		pessoa = pessoaDao.obterUmaPessoa(this.getId());
		
		this.setNome(pessoa.getNome_());
		this.setEmail(pessoa.getEmail_());
		this.setSenha(pessoa.getSenha_());
		this.setDdd(pessoa.getTelefone().get(0));
		this.setNumero(pessoa.getTelefone().get(1));
		
		if (pessoa.getTelefone().get(2).equals("Fixo")) {
			this.setFixo(true);
		} else if (pessoa.getTelefone().get(2).equals("Celular")) {
			this.setCelular(true);
		}
		
		return pessoa;
	}
	
	public Pessoa atualizarPessoa() {
		
		ddd = numero.substring(1, 3);
		String _numero = numero.substring(4, numero.length());
		String tipo = "";
		
		if (fixo == true) {
			tipo = "Fixo";
		} else if(celular == true) {
			tipo = "Celular";
		}
		
		List<String> telefone = new ArrayList<String>();
		telefone.add(ddd);
		telefone.add(_numero);
		telefone.add(tipo);
		
		Pessoa pessoa = new Pessoa();
		pessoa.setId(id);
		pessoa.setNome_(nome);
		pessoa.setEmail_(email);
		pessoa.setSenha_(senha);
		pessoa.setTelefone(telefone);
		
		System.out.println("Nome: "+nome+", E-mail: "+email+", Senha: "+senha+", Numero: "+_numero+", DDD: "+ddd+", "
				+ "Fixo: "+fixo+", Celular: "+celular+", Logado: "+pessoa.getLogin()+"");
		
		PessoaDao pessoaDao = new PessoaDao();
		pessoaDao.atualizarPeloId(this.getId(), pessoa);
		
		this.setResulstado("");
		this.setResulstado("O cadastro foi atualizado! :)");
		
		return pessoa;
	}
	
	public String limparResultado() {
		this.setResulstado("");
		return "";
	}
	
    public void info() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "O cadastro foi atualizado."));
    }
    
	public String irParaLogout() {
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public boolean isFixo() {
		return fixo;
	}
	public void setFixo(boolean fixo) {
		this.fixo = fixo;
	}
	public boolean isCelular() {
		return celular;
	}
	public void setCelular(boolean celular) {
		this.celular = celular;
	}
	public String getDdd() {
		return ddd;
	}
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}
	public String getResulstado() {
		return resulstado;
	}
	public void setResulstado(String resulstado) {
		this.resulstado = resulstado;
	}

}
