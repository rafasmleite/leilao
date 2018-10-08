package br.com.leilao.test;

import java.util.List;

import org.junit.Test;

import br.com.leilao.dao.CargoDAO;
import br.com.leilao.domain.Cargo;

public class CargoDAOTest {

	@Test
	public void salvar() {
		Cargo cargo = new Cargo();
		cargo.setNome("teste1");
		
		Cargo cargo2 = new Cargo();
		cargo2.setNome("teste2");
		
		CargoDAO dao = new CargoDAO();

		dao.salvar(cargo);
		dao.salvar(cargo2);
	}
	
	@Test
	public void lista() {
		CargoDAO dao = new CargoDAO();
		List<Cargo> lista = dao.listar();
		for (Cargo c : lista) {
			   System.out.println("Dado: " + c.getNome());
		}
	}

	@Test
	public void buscarPorNome() {
		Cargo cargo = new Cargo();
		cargo.setNome("amor");
		CargoDAO dao = new CargoDAO();
//		Cargo cg = dao.buscarPorNome(cargo);
//		System.out.println(cg.getNome());
//		System.out.println(cg.getId());
	}
	
	@Test
	public void buscarPorId() {
		Cargo cargo = new Cargo();
		cargo.setId(2);;
		CargoDAO dao = new CargoDAO();
		Cargo cg = dao.buscarPorID(cargo);
		System.out.println(cg.getNome());
		System.out.println(cg.getId());
	}
	

	@Test
	public void alterar() {
		Cargo cargo = new Cargo();
		cargo.setNome("amor");
		cargo.setId(1);
		
		Cargo cargo2 = new Cargo();
		cargo2.setNome("amor2");
		cargo2.setId(2);
		
		CargoDAO dao = new CargoDAO();

		dao.salvar(cargo);
		dao.salvar(cargo2);
	}
}
