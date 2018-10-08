package br.com.leilao.test;

import org.junit.Test;

import br.com.leilao.dao.CidadeDAO;
import br.com.leilao.domain.Cidade;
import br.com.leilao.domain.Estado;

public class CidadeDAOTest {

//	@Test
//	public void salvar() {
//		CidadeDAO dao = new CidadeDAO();
//		Cidade cidade = new Cidade();
//		Estado estado = new Estado();
//		
//		estado.setId(12);
//		estado.setNome("Parana");
//		
//		cidade.setEstado(estado);
//		cidade.setNome("Cascavel");
//		
//		dao.salvar(cidade);
//	}
	
	@Test
	public void buscarPorNome() {
		CidadeDAO dao = new CidadeDAO();
		Cidade cidade = new Cidade();
		
		cidade.setNome("Cascavel");
		
//		Cidade c = dao.buscarPorNome(cidade);
//		System.out.println(c.getNome());
//		System.out.println(" + ");
//		System.out.println(c.getEstado().getNome());
	}
	
}
