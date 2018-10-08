package br.com.leilao.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.leilao.dao.CidadeDAO;
import br.com.leilao.dao.EmpresaDAO;
import br.com.leilao.domain.Cidade;
import br.com.leilao.domain.Empresa;
import br.com.leilao.util.FacesUtil;

@ManagedBean
@ViewScoped
public class EmpresaBean {

	private Empresa empresaCadastro;
	private List<Cidade> listaCidade;
	private boolean mostrarTabela = false;
	private List<Empresa> listaEmpresa;
	private List<Empresa> listaEmpresaFiltrado;
	private Empresa empresaPesquisa = new Empresa();

	public void carregarCadastro() {
		novo();
		CidadeDAO cidadeDAO = new CidadeDAO();
		listaCidade = cidadeDAO.listar();
	}

	public void novo() {
		empresaCadastro = new Empresa();
		Cidade cidade = new Cidade();
		empresaCadastro.setCidade(cidade);
		listaEmpresa = null;
		listaEmpresaFiltrado = null;
		empresaPesquisa = new Empresa();
	}

	public void pesquisar() {
		try {
			EmpresaDAO empresaDAO = new EmpresaDAO();
			String nome = "%" + empresaPesquisa.getFantasia() + "%";
			empresaPesquisa.setFantasia(nome);
			listaEmpresa = empresaDAO.buscarPorNomeFantasia(empresaPesquisa);
		} catch (Exception e) {
			FacesUtil.addMsgError("Erro ao tentar pesquisar empresa: " + e.getMessage());
		}
		if (listaEmpresa.isEmpty()) {
			FacesUtil.addMsgInfo("Não foi encontrado resultado para essa consulta!");
			mostrarTabela = false;
		} else {
			FacesUtil.addMsgInfo("Pesquisa realizada com sucesso!");
			mostrarTabela = true;
		}
		empresaPesquisa.setFantasia("");
	}

	public void salvar() {
		try {
			EmpresaDAO dao = new EmpresaDAO();
			dao.salvar(empresaCadastro);

			novo();

			FacesUtil.addMsgInfo("Empresa salva com sucesso!");
		} catch (RuntimeException e) {
			FacesUtil.addMsgError("Erro ao tentar incluir uma Empresa: " + e.getMessage());
		}
	}

	public Empresa getEmpresaCadastro() {
		if (empresaCadastro == null) {
			empresaCadastro = new Empresa();
			Cidade cidade = new Cidade();
			empresaCadastro.setCidade(cidade);
		}
		return empresaCadastro;
	}

	public void setEmpresaCadastro(Empresa empresaCadastro) {
		this.empresaCadastro = empresaCadastro;
	}

	public List<Cidade> getListaCidade() {
		if (listaCidade == null) {
			listaCidade = new ArrayList<Cidade>();
		}
		return listaCidade;
	}

	public void setListaCidade(List<Cidade> listaCidade) {
		this.listaCidade = listaCidade;
	}

	public boolean isMostrarTabela() {
		return mostrarTabela;
	}

	public void setMostrarTabela(boolean mostrarTabela) {
		this.mostrarTabela = mostrarTabela;
	}

	public List<Empresa> getListaEmpresa() {
		return listaEmpresa;
	}

	public void setListaEmpresa(List<Empresa> listaEmpresa) {
		this.listaEmpresa = listaEmpresa;
	}

	public List<Empresa> getListaEmpresaFiltrado() {
		return listaEmpresaFiltrado;
	}

	public void setListaEmpresaFiltrado(List<Empresa> listaEmpresaFiltrado) {
		this.listaEmpresaFiltrado = listaEmpresaFiltrado;
	}

	public Empresa getEmpresaPesquisa() {
		return empresaPesquisa;
	}

	public void setEmpresaPesquisa(Empresa empresaPesquisa) {
		this.empresaPesquisa = empresaPesquisa;
	}

}
