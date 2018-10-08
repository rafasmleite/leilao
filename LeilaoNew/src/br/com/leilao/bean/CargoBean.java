package br.com.leilao.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.leilao.dao.CargoDAO;
import br.com.leilao.domain.Cargo;
import br.com.leilao.util.FacesUtil;

@ManagedBean
@ViewScoped
public class CargoBean {
	
	private Cargo cargoCadastro;
	private boolean mostrarTabela = false;
	private List<Cargo> listaCargo;
	private List<Cargo> listaCargoFiltrado;
	private Cargo cargoPesquisa = new Cargo();
	
	public void novo() {
		cargoCadastro = new Cargo();
		mostrarTabela = false;
		listaCargo = null;
		listaCargoFiltrado = null;
		cargoPesquisa = new Cargo();
	}
	
	public void pesquisar() {
		try {
			CargoDAO cargoDAO = new CargoDAO();
			String nome = "%"+cargoPesquisa.getNome()+"%";
			cargoPesquisa.setNome(nome);
			listaCargo = cargoDAO.buscarPorNome(cargoPesquisa);
		} catch (Exception e) {
			FacesUtil.addMsgError("Erro ao tentar pesquisar cargo: " + e.getMessage());
		}
		if (listaCargo.isEmpty()) {
			FacesUtil.addMsgInfo("Não foi encontrado resultado para essa consulta!");
			mostrarTabela = false;
		}else {
			FacesUtil.addMsgInfo("Pesquisa realizada com sucesso!");
			mostrarTabela = true;
		}
		cargoPesquisa.setNome("");
	}
	
	public void salvar() {
		try {
			CargoDAO dao = new CargoDAO();
			dao.salvar(cargoCadastro);
			
			cargoCadastro = new Cargo();
			
			FacesUtil.addMsgInfo("Cargo salvo com sucesso!");
		} catch (RuntimeException e) {
			FacesUtil.addMsgError("Erro ao tentar incluir um Cargo: " + e.getMessage());
		}
	}
	
	public Cargo getCargoCadastro() {
		if (cargoCadastro == null) {
			cargoCadastro = new Cargo();
		}
		return cargoCadastro;
	}

	public void setCargoCadastro(Cargo cargoCadastro) {
		this.cargoCadastro = cargoCadastro;
	}

	public List<Cargo> getListaCargo() {
		return listaCargo;
	}

	public boolean isMostrarTabela() {
		return mostrarTabela;
	}

	public void setMostrarTabela(boolean mostrarTabela) {
		this.mostrarTabela = mostrarTabela;
	}

	public Cargo getCargoPesquisa() {
		return cargoPesquisa;
	}

	public void setCargoPesquisa(Cargo cargoPesquisa) {
		this.cargoPesquisa = cargoPesquisa;
	}

	public void setListaCargo(List<Cargo> listaCargo) {
		this.listaCargo = listaCargo;
	}

	public List<Cargo> getListaCargoFiltrado() {
		return listaCargoFiltrado;
	}

	public void setListaCargoFiltrado(List<Cargo> listaCargoFiltrado) {
		this.listaCargoFiltrado = listaCargoFiltrado;
	}

	

}
