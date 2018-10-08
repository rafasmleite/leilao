package br.com.leilao.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.leilao.dao.EstadoDAO;
import br.com.leilao.domain.Estado;
import br.com.leilao.util.FacesUtil;

@ManagedBean
@ViewScoped
public class EstadoBean {
	
	private Estado estadoCadastro;
	private boolean mostrarTabela = false;
	private List<Estado> listaEstado;
	private List<Estado> listaEstadoFiltrado;
	private Estado estadoPesquisa = new Estado();
	
	public void novo() {
		estadoCadastro = new Estado();
		mostrarTabela = false;
		listaEstado = null;
		listaEstadoFiltrado = null;
		estadoPesquisa = new Estado();
	}
	
	public void pesquisar() {
		try {
			EstadoDAO estadoDAO = new EstadoDAO();
			String nome = "%"+estadoPesquisa.getNome()+"%";
			estadoPesquisa.setNome(nome);
			listaEstado = estadoDAO.buscarPorNome(estadoPesquisa);
		} catch (Exception e) {
			FacesUtil.addMsgError("Erro ao tentar incluir um estado: " + e.getMessage());
		}
		if (listaEstado.isEmpty()) {
			FacesUtil.addMsgInfo("Não foi encontrado resultado para essa consulta!");
			mostrarTabela = false;
		}else {
			FacesUtil.addMsgInfo("Pesquisa realizada com sucesso!");
			mostrarTabela = true;
		}
		estadoPesquisa.setNome("");
	}
	
	public void salvar() {
		try {
			EstadoDAO dao = new EstadoDAO();
			dao.salvar(estadoCadastro);
			
			estadoCadastro = new Estado();
			
			FacesUtil.addMsgInfo("Estado salvo com sucesso!");
		} catch (RuntimeException e) {
			FacesUtil.addMsgError("Erro ao tentar incluir um estado: " + e.getMessage());
		}
	}
	
	public Estado getEstadoCadastro() {
		if (estadoCadastro == null) {
			estadoCadastro = new Estado();
		}
		return estadoCadastro;
	}

	public void setEstadoCadastro(Estado estadoCadastro) {
		this.estadoCadastro = estadoCadastro;
	}

	public boolean isMostrarTabela() {
		return mostrarTabela;
	}

	public void setMostrarTabela(boolean mostrarTabela) {
		this.mostrarTabela = mostrarTabela;
	}

	public List<Estado> getListaEstado() {
		return listaEstado;
	}

	public void setListaEstado(List<Estado> listaEstado) {
		this.listaEstado = listaEstado;
	}

	public List<Estado> getListaEstadoFiltrado() {
		return listaEstadoFiltrado;
	}

	public void setListaEstadoFiltrado(List<Estado> listaEstadoFiltrado) {
		this.listaEstadoFiltrado = listaEstadoFiltrado;
	}

	public Estado getEstadoPesquisa() {
		return estadoPesquisa;
	}

	public void setEstadoPesquisa(Estado estadoPesquisa) {
		this.estadoPesquisa = estadoPesquisa;
	}
	
}
