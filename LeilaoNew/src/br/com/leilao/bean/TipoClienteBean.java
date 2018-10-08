package br.com.leilao.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.leilao.dao.TipoClienteDAO;
import br.com.leilao.domain.TipoCliente;
import br.com.leilao.util.FacesUtil;

@ManagedBean
@ViewScoped
public class TipoClienteBean {

	private TipoCliente tipoClienteCadastro;
	private boolean mostrarTabela = false;
	private List<TipoCliente> listaTipoCliente;
	private List<TipoCliente> listaTipoClienteFiltrado;
	private TipoCliente tipoClientePesquisa = new TipoCliente();

	public void novo() {
		tipoClienteCadastro = new TipoCliente();
		mostrarTabela = false;
		listaTipoCliente = null;
		listaTipoClienteFiltrado = null;
		tipoClientePesquisa = new TipoCliente();
	}

	public void pesquisar() {
		try {
			TipoClienteDAO tipoClienteDAO = new TipoClienteDAO();
			String nome = "%" + tipoClientePesquisa.getNome() + "%";
			tipoClientePesquisa.setNome(nome);
			listaTipoCliente = tipoClienteDAO.buscarPorNome(tipoClientePesquisa);
		} catch (Exception e) {
			FacesUtil.addMsgError("Erro ao tentar pesquisar tipo Cliente: " + e.getMessage());
		}
		if (listaTipoCliente.isEmpty()) {
			FacesUtil.addMsgInfo("Não foi encontrado resultado para essa consulta!");
			mostrarTabela = false;
		} else {
			FacesUtil.addMsgInfo("Pesquisa realizada com sucesso!");
			mostrarTabela = true;
		}
		tipoClientePesquisa.setNome("");
	}

	public void salvar() {
		try {
			TipoClienteDAO dao = new TipoClienteDAO();
			dao.salvar(tipoClienteCadastro);

			tipoClienteCadastro = new TipoCliente();

			FacesUtil.addMsgInfo("Tipo Cliente salvo com sucesso!");
		} catch (RuntimeException e) {
			FacesUtil.addMsgError("Erro ao tentar incluir um Tipo Cliente: " + e.getMessage());
		}
	}

	public TipoCliente getTipoClienteCadastro() {
		if (tipoClienteCadastro == null) {
			tipoClienteCadastro = new TipoCliente();
		}
		return tipoClienteCadastro;
	}

	public void setTipoClienteCadastro(TipoCliente tipoClienteCadastro) {
		this.tipoClienteCadastro = tipoClienteCadastro;
	}

	public boolean isMostrarTabela() {
		return mostrarTabela;
	}

	public void setMostrarTabela(boolean mostrarTabela) {
		this.mostrarTabela = mostrarTabela;
	}

	public List<TipoCliente> getListaTipoCliente() {
		return listaTipoCliente;
	}

	public void setListaTipoCliente(List<TipoCliente> listaTipoCliente) {
		this.listaTipoCliente = listaTipoCliente;
	}

	public List<TipoCliente> getListaTipoClienteFiltrado() {
		return listaTipoClienteFiltrado;
	}

	public void setListaTipoClienteFiltrado(List<TipoCliente> listaTipoClienteFiltrado) {
		this.listaTipoClienteFiltrado = listaTipoClienteFiltrado;
	}

	public TipoCliente getTipoClientePesquisa() {
		return tipoClientePesquisa;
	}

	public void setTipoClientePesquisa(TipoCliente tipoClientePesquisa) {
		this.tipoClientePesquisa = tipoClientePesquisa;
	}
}
