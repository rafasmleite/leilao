package br.com.leilao.test;

import java.util.List;

import org.junit.Test;

import br.com.leilao.dao.UsuarioDAO;
import br.com.leilao.domain.Cargo;
import br.com.leilao.domain.Empresa;
import br.com.leilao.domain.Usuario;

public class UsuarioDAOTest {

//	@Test
//	public void salvar() {
//		
//		UsuarioDAO dao = new UsuarioDAO();
//		Usuario usuario = new Usuario();
//		Usuario usuario2 = new Usuario();
//		Cargo cargo = new Cargo();
//		Empresa empresa = new Empresa();
//		
//		cargo.setId(1);
//		
//		empresa.setId(14);
//		
//		usuario.setCargo(cargo);
////		usuario.setCpf(395383788);
//		usuario.setEmpresa(empresa);
//		usuario.setNome("Rafael");
//		usuario.setSenha("123");
//		
//		usuario2.setCargo(cargo);
////		usuario2.setCpf(395383789);
//		usuario2.setEmpresa(empresa);
//		usuario2.setNome("Jennyfer");
//		usuario2.setSenha("321");
//		
//		dao.salvar(usuario);
//		dao.salvar(usuario2);
//	}
//	
//	@Test
//	public void listarPorEmpresa() {
//		
//		UsuarioDAO dao = new UsuarioDAO();
//		
//		Empresa empresa = new Empresa();
//		
//		empresa.setId(14);
//		
//		List<Usuario> lista = dao.listarPorEmpresa(empresa);
//		for (Usuario c : lista) {
//			   System.out.println("Dado: " + c.getNome());
//		}
//		
//	}
	
	@Test
	public void autenticar() {
		UsuarioDAO dao = new UsuarioDAO();
		Usuario usr = dao.autenticar(395383L, "1234", 9248498L);
		if (usr != null) {
			System.out.println("Logado");
		}else {
			System.out.println("Erro");
		}
	}
	
}
