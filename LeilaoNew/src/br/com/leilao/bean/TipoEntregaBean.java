package br.com.leilao.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.leilao.dao.TipoEntregaDAO;
import br.com.leilao.domain.TipoEntrega;
import br.com.leilao.util.FacesUtil;

@ManagedBean
@ViewScoped
public class TipoEntregaBean {

	private TipoEntrega tipoEntregaCadastro;
	private boolean mostrarTabela = false;
	private List<TipoEntrega> listaTipoEntrega;
	private List<TipoEntrega> listaTipoEntregaFiltrado;
	private TipoEntrega tipoEntregaPesquisa = new TipoEntrega();

	public void novo() {
		tipoEntregaCadastro = new TipoEntrega();
		mostrarTabela = false;
		listaTipoEntrega = null;
		listaTipoEntregaFiltrado = null;
		tipoEntregaPesquisa = new TipoEntrega();
	}

	public void pesquisar() {
		try {
			TipoEntregaDAO tipoEntregaDAO = new TipoEntregaDAO();
			String nome = "%" + tipoEntregaPesquisa.getNome() + "%";
			tipoEntregaPesquisa.setNome(nome);
			listaTipoEntrega = tipoEntregaDAO.buscarPorNome(tipoEntregaPesquisa);
		} catch (Exception e) {
			FacesUtil.addMsgError("Erro ao tentar pesquisar tipo Entrega: " + e.getMessage());
		}
		if (listaTipoEntrega.isEmpty()) {
			FacesUtil.addMsgInfo("Não foi encontrado resultado para essa consulta!");
			mostrarTabela = false;
		} else {
			FacesUtil.addMsgInfo("Pesquisa realizada com sucesso!");
			mostrarTabela = true;
		}
		tipoEntregaPesquisa.setNome("");
	}

	public void salvar() {
		try {
			TipoEntregaDAO dao = new TipoEntregaDAO();
			dao.salvar(tipoEntregaCadastro);

			tipoEntregaCadastro = new TipoEntrega();

			FacesUtil.addMsgInfo("Tipo Entrega salvo com sucesso!");
		} catch (RuntimeException e) {
			FacesUtil.addMsgError("Erro ao tentar incluir um Tipo Entrega: " + e.getMessage());
		}
	}
	
	public void carregarCadastro() {
		try {
			String valor = FacesUtil.getParam("id");
			if (valor != null) {
				TipoEntregaDAO dao = new TipoEntregaDAO();
				TipoEntrega tipoEntrega = new TipoEntrega();
				tipoEntrega.setId(Integer.parseInt(valor));
				tipoEntregaCadastro = dao.buscarPorID(tipoEntrega);
			}
		} catch (RuntimeException e) {
			FacesUtil.addMsgError("Erro ao tentar obter os dados do Tipo Entrega: " + e.getMessage());
		}
	}

	public TipoEntrega getTipoEntregaCadastro() {
		if (tipoEntregaCadastro == null) {
			tipoEntregaCadastro = new TipoEntrega();
		}
		return tipoEntregaCadastro;
	}

	public void setTipoEntregaCadastro(TipoEntrega tipoEntregaCadastro) {
		this.tipoEntregaCadastro = tipoEntregaCadastro;
	}
	
	public boolean isMostrarTabela() {
		return mostrarTabela;
	}

	public void setMostrarTabela(boolean mostrarTabela) {
		this.mostrarTabela = mostrarTabela;
	}

	public List<TipoEntrega> getListaTipoEntrega() {
		return listaTipoEntrega;
	}

	public void setListaTipoEntrega(List<TipoEntrega> listaTipoEntrega) {
		this.listaTipoEntrega = listaTipoEntrega;
	}

	public List<TipoEntrega> getListaTipoEntregaFiltrado() {
		return listaTipoEntregaFiltrado;
	}

	public void setListaTipoEntregaFiltrado(List<TipoEntrega> listaTipoEntregaFiltrado) {
		this.listaTipoEntregaFiltrado = listaTipoEntregaFiltrado;
	}

	public TipoEntrega getTipoEntregaPesquisa() {
		return tipoEntregaPesquisa;
	}

	public void setTipoEntregaPesquisa(TipoEntrega tipoEntregaPesquisa) {
		this.tipoEntregaPesquisa = tipoEntregaPesquisa;
	}

}
