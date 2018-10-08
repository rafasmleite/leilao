package br.com.leilao.test;

import org.junit.Test;

import br.com.leilao.dao.EmpresaDAO;
import br.com.leilao.domain.Cidade;
import br.com.leilao.domain.Empresa;

public class EmpresaDAOTest {
	
	@Test
	public void salvar() {
		
		EmpresaDAO dao = new EmpresaDAO();
		Empresa empresa = new Empresa();
		Cidade cidade = new Cidade();
		
		cidade.setId(13);
		
		empresa.setBairro("Country");
//		empresa.setCep(85813210);
		empresa.setCidade(cidade);
//		empresa.setCnpj(9085);
		empresa.setEmail("rafaibve@gmail.com");
		empresa.setEndereco("Rua ceu azul");
		empresa.setFantasia("teste");
//		empresa.setInscricaoEstadual(123);
//		empresa.setInscricaoMunicipal(321);
//		empresa.setNumero(10);
		empresa.setRazao("razao");

		dao.salvar(empresa);
	}

}
