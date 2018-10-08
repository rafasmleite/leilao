package br.com.leilao.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.leilao.domain.Empresa;
import br.com.leilao.domain.Usuario;
import br.com.leilao.util.HibernateUtil;

public class UsuarioDAO {

	public void salvar(Usuario usuario) {
		if (usuario != null) {
			Session sessao = HibernateUtil.getSessionFactory().openSession();
			Transaction transacao = null;

			try {
				usuario.setNome(usuario.getNome().toUpperCase());
				transacao = sessao.beginTransaction();
				sessao.saveOrUpdate(usuario);
				transacao.commit();
			} catch (RuntimeException e) {
				System.out.println(e);
				if (transacao != null) {
					transacao.rollback();
				}
			} finally {
				sessao.close();
			}
		} else {

		}
	}

	public List<Usuario> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		try {
			CriteriaQuery<Usuario> criteria = sessao.getCriteriaBuilder().createQuery(Usuario.class);
			criteria.from(Usuario.class);

			return sessao.createQuery(criteria).getResultList();

		} catch (RuntimeException e) {
			System.out.println(e);
			return null;
		} finally {
			sessao.close();
		}
	}
	
	public List<Usuario> listarPorEmpresa(Empresa empresa) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		try {
			CriteriaQuery<Usuario> criteria = sessao.getCriteriaBuilder().createQuery(Usuario.class);
			Root<Usuario> from = criteria.from(Usuario.class);
			criteria.where(sessao.getCriteriaBuilder().equal(from.get("empresa"), empresa.getId()));

			return sessao.createQuery(criteria).getResultList();

		} catch (RuntimeException e) {
			System.out.println(e);
			return null;
		} finally {
			sessao.close();
		}
	}

	public List<Usuario> buscarPorNome(Usuario usuario) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		try {
			CriteriaQuery<Usuario> criteria = sessao.getCriteriaBuilder().createQuery(Usuario.class);
			Root<Usuario> from = criteria.from(Usuario.class);
			criteria.where(sessao.getCriteriaBuilder().like(from.get("nome"), usuario.getNome()));

			return sessao.createQuery(criteria).getResultList();

		} catch (RuntimeException e) {
			System.out.println(e);
			return null;
		} finally {
			sessao.close();
		}

	}

	public Usuario buscarPorID(Usuario cargo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		try {
			CriteriaQuery<Usuario> criteria = sessao.getCriteriaBuilder().createQuery(Usuario.class);
			Root<Usuario> from = criteria.from(Usuario.class);
			criteria.where(sessao.getCriteriaBuilder().equal(from.get("id"), cargo.getId()));

			return sessao.createQuery(criteria).getSingleResult();

		} catch (RuntimeException e) {
			System.out.println(e);
			return null;
		} finally {
			sessao.close();
		}

	}
	
	public Usuario autenticar(Long cpf, String senha, Long cnpj) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Usuario usuario = null;

		try {
			CriteriaQuery<Usuario> criteria = sessao.getCriteriaBuilder().createQuery(Usuario.class);
			Root<Usuario> from = criteria.from(Usuario.class);
			criteria.where(sessao.getCriteriaBuilder().equal(from.get("cpf"), cpf));
			
			
			usuario = sessao.createQuery(criteria).getSingleResult();
			if (usuario != null) {
				if (usuario.getSenha().equals(senha)) {
					EmpresaDAO empresaDAO = new EmpresaDAO();
					Empresa empresa = new Empresa();
					empresa.setCnpj(cnpj);
					Empresa retornoEmp = empresaDAO.buscarPorCNPJ(empresa);
					if (retornoEmp != null) {
						if (retornoEmp.getId() != usuario.getEmpresa().getId()) {
							usuario = null;
						}
					}else {
						usuario = null;
					}
				}else {
					usuario = null;
				}
			}
			return usuario;
		} catch (RuntimeException e) {
			System.out.println(e);
			return usuario;
		} finally {
			sessao.close();
		}
	}

}
