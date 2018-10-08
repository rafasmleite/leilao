package br.com.leilao.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.leilao.dao.CidadeDAO;
import br.com.leilao.dao.EstadoDAO;
import br.com.leilao.domain.Cidade;
import br.com.leilao.domain.Estado;
import br.com.leilao.util.FacesUtil;

@ManagedBean
@ViewScoped
public class CidadeBean {
	
	private Cidade cidadeCadastro;
	private List<Estado> listaEstado;
	private boolean mostrarTabela = false;
	private List<Cidade> listaCidade;
	private List<Cidade> listaCidadeFiltrado;
	private Cidade cidadePesquisa = new Cidade();
	
	public void carregarCadastro() {
		novo();
		EstadoDAO estadoDAO = new EstadoDAO();
		listaEstado = estadoDAO.listar();
	}
	
	public void novo() {
		cidadeCadastro = new Cidade();
		Estado estado = new Estado();
		cidadeCadastro.setEstado(estado);
		listaEstado = null;
		mostrarTabela = false;
		listaCidade = null;
		listaCidadeFiltrado = null;
		cidadePesquisa = new Cidade();
	}

	public void pesquisar() {
		try {
			CidadeDAO cidadeDAO = new CidadeDAO();
			String nome = "%"+cidadePesquisa.getNome()+"%";
			cidadePesquisa.setNome(nome);
			listaCidade = cidadeDAO.buscarPorNome(cidadePesquisa);
		} catch (Exception e) {
			FacesUtil.addMsgError("Erro ao tentar pesquisar cidade: " + e.getMessage());
		}
		if (listaCidade.isEmpty()) {
			FacesUtil.addMsgInfo("Não foi encontrado resultado para essa consulta!");
			mostrarTabela = false;
		}else {
			FacesUtil.addMsgInfo("Pesquisa realizada com sucesso!");
			mostrarTabela = true;
		}
		cidadePesquisa.setNome("");
	}
	
	public void salvar() {
		try {
			CidadeDAO dao = new CidadeDAO();
			dao.salvar(cidadeCadastro);
			
			novo();
			
			FacesUtil.addMsgInfo("Cidade salva com sucesso!");
		} catch (RuntimeException e) {
			FacesUtil.addMsgError("Erro ao tentar incluir uma Cidade: " + e.getMessage());
		}
	}
	
	public Cidade getCidadeCadastro() {
		if (cidadeCadastro == null) {
			cidadeCadastro = new Cidade();
			Estado estado = new Estado();
			cidadeCadastro.setEstado(estado);
		}
		return cidadeCadastro;
	}

	public void setCidadeCadastro(Cidade cidadeCadastro) {
		this.cidadeCadastro = cidadeCadastro;
	}

	public List<Estado> getListaEstado() {
		if (listaEstado == null) {
			listaEstado = new ArrayList<Estado>();
		}
		return listaEstado;
	}

	public void setListaEstado(List<Estado> listaEstado) {
		this.listaEstado = listaEstado;
	}

	public boolean isMostrarTabela() {
		return mostrarTabela;
	}

	public void setMostrarTabela(boolean mostrarTabela) {
		this.mostrarTabela = mostrarTabela;
	}

	public List<Cidade> getListaCidade() {
		return listaCidade;
	}

	public void setListaCidade(List<Cidade> listaCidade) {
		this.listaCidade = listaCidade;
	}

	public List<Cidade> getListaCidadeFiltrado() {
		return listaCidadeFiltrado;
	}

	public void setListaCidadeFiltrado(List<Cidade> listaCidadeFiltrado) {
		this.listaCidadeFiltrado = listaCidadeFiltrado;
	}

	public Cidade getCidadePesquisa() {
		return cidadePesquisa;
	}

	public void setCidadePesquisa(Cidade cidadePesquisa) {
		this.cidadePesquisa = cidadePesquisa;
	}

}
