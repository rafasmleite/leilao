package br.com.leilao.test;

import org.junit.Test;

import br.com.leilao.dao.EstadoDAO;
import br.com.leilao.domain.Estado;

public class EstadoDAOTest {

	@Test
	public void salvar() {
		EstadoDAO dao = new EstadoDAO();
		Estado estado = new Estado();
		
		estado.setNome("Parana");
		
		dao.salvar(estado);
	}
	
}
