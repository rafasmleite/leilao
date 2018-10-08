package br.com.leilao.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.leilao.dao.CargoDAO;
import br.com.leilao.dao.EmpresaDAO;
import br.com.leilao.dao.UsuarioDAO;
import br.com.leilao.domain.Cargo;
import br.com.leilao.domain.Empresa;
import br.com.leilao.domain.Usuario;
import br.com.leilao.util.FacesUtil;

@ManagedBean
@ViewScoped
public class UsuarioBean {

	private Usuario usuarioCadastro;
	private List<Empresa> listaEmpresa;
	private List<Cargo> listaCargo;
	private boolean mostrarTabela = false;
	private List<Usuario> listaUsuario;
	private List<Usuario> listaUsuarioFiltrado;
	private Usuario usuarioPesquisa = new Usuario();

	public void carregarCadastro() {
		novo();
		EmpresaDAO empresaDAO = new EmpresaDAO();
		CargoDAO cargoDAO = new CargoDAO();

		listaCargo = cargoDAO.listar();
		listaEmpresa = empresaDAO.listar();
	}

	public void novo() {
		usuarioCadastro = new Usuario();

		Empresa empresa = new Empresa();
		Cargo cargo = new Cargo();

		usuarioCadastro.setCargo(cargo);
		usuarioCadastro.setEmpresa(empresa);
	}

	public void pesquisar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			String nome = "%" + usuarioPesquisa.getNome() + "%";
			usuarioPesquisa.setNome(nome);
			listaUsuario = usuarioDAO.buscarPorNome(usuarioPesquisa);
		} catch (Exception e) {
			FacesUtil.addMsgError("Erro ao tentar pesquisar usuario: " + e.getMessage());
		}
		if (listaUsuario.isEmpty()) {
			FacesUtil.addMsgInfo("Não foi encontrado resultado para essa consulta!");
			mostrarTabela = false;
		} else {
			FacesUtil.addMsgInfo("Pesquisa realizada com sucesso!");
			mostrarTabela = true;
		}
		usuarioPesquisa.setNome("");
	}

	public void salvar() {
		try {
			UsuarioDAO dao = new UsuarioDAO();
			dao.salvar(usuarioCadastro);

			novo();

			FacesUtil.addMsgInfo("Usuario salva com sucesso!");
		} catch (RuntimeException e) {
			FacesUtil.addMsgError("Erro ao tentar incluir um Usuario: " + e.getMessage());
		}
	}

	public Usuario getUsuarioCadastro() {
		if (usuarioCadastro == null) {
			usuarioCadastro = new Usuario();
			Empresa empresa = new Empresa();
			usuarioCadastro.setEmpresa(empresa);
		}
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

	public List<Empresa> getListaEmpresa() {
		if (listaEmpresa == null) {
			listaEmpresa = new ArrayList<Empresa>();
		}
		return listaEmpresa;
	}

	public void setListaEmpresa(List<Empresa> listaEmpresa) {
		this.listaEmpresa = listaEmpresa;
	}

	public List<Cargo> getListaCargo() {
		if (listaCargo == null) {
			listaCargo = new ArrayList<Cargo>();
		}
		return listaCargo;
	}

	public void setListaCargo(List<Cargo> listaCargo) {
		this.listaCargo = listaCargo;
	}

	public boolean isMostrarTabela() {
		return mostrarTabela;
	}

	public void setMostrarTabela(boolean mostrarTabela) {
		this.mostrarTabela = mostrarTabela;
	}

	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public List<Usuario> getListaUsuarioFiltrado() {
		return listaUsuarioFiltrado;
	}

	public void setListaUsuarioFiltrado(List<Usuario> listaUsuarioFiltrado) {
		this.listaUsuarioFiltrado = listaUsuarioFiltrado;
	}

	public Usuario getUsuarioPesquisa() {
		return usuarioPesquisa;
	}

	public void setUsuarioPesquisa(Usuario usuarioPesquisa) {
		this.usuarioPesquisa = usuarioPesquisa;
	}

}
