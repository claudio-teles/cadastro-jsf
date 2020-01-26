package bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import dao.PessoaDao;
import model.Pessoa;

public class CadastroBean {
	
	private String nome;
	private String email;
	private String senha;
	private String numero;
	private String ddd;
	
	private String resulstado = "";
	
	private boolean fixo;
	private boolean celular;
	
	public Pessoa cadastrarPessoa() {
		
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
		pessoa.setNome_(nome);
		pessoa.setEmail_(email);
		pessoa.setSenha_(senha);
		pessoa.setTelefone(telefone);
		pessoa.setLogin(false);
		
		System.out.println("Nome: "+nome+", E-mail: "+email+", Senha: "+senha+", Numero: "+_numero+", DDD: "+ddd+", "
				+ "Fixo: "+fixo+", Celular: "+celular+", Logado: "+pessoa.getLogin()+"");
		
		PessoaDao pessoaDao = new PessoaDao();
		pessoaDao.cadastrar(pessoa);
		
		this.setResulstado("");
		this.setResulstado("O cadastro foi feito! :)");
		
		return pessoa;
	}
	
	public String limparResultado() {
		this.setResulstado("");
		return "";
	}
	
    public void info() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "O cadastro foi feito."));
    }
    
	public String irParaLogin() {
		return "login?faces-redirect=true";
	}
	
	public String irParaLogout() {
		return "logout?faces-redirect=true";
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
