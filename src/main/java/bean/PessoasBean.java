package bean;

import java.util.ArrayList;
import java.util.List;

import dao.PessoaDao;
import model.Pessoa;

public class PessoasBean {
	
	public List<Pessoa> listarPessoas() {
		List<Pessoa> pessoas = new ArrayList<>();
		PessoaDao pessoaDao = new PessoaDao();
		pessoas.addAll(pessoaDao.obterPessoas());
		return pessoas;
	}
	
	public String deletarPessoa(Long id) {
		PessoaDao pessoaDao = new PessoaDao();
		pessoaDao.deletarPessoaPeloId(id);
		return "pessoas?faces-redirect=true";
	}
	
	public String irParaCadastro() {
		return "index?faces-redirect=true";
	}
	
	public String irParaAtualizar() {
		return "atualizar?faces-redirect=true";
	}
	
	public String irParaLogout() {
		return "logout?faces-redirect=true";
	}

}
