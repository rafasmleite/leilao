package br.com.leilao.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.leilao.domain.Empresa;
import br.com.leilao.util.HibernateUtil;

public class EmpresaDAO {
	
	public void salvar(Empresa empresa) {
		if (empresa != null) {
			Session sessao = HibernateUtil.getSessionFactory().openSession();
			Transaction transacao = null;

			try {
				empresa.setBairro(empresa.getBairro().toUpperCase());
				empresa.setEmail(empresa.getEmail().toUpperCase());
				empresa.setEndereco(empresa.getEndereco().toUpperCase());
				empresa.setFantasia(empresa.getFantasia().toUpperCase());
				empresa.setRazao(empresa.getRazao().toUpperCase());
				transacao = sessao.beginTransaction();
				sessao.saveOrUpdate(empresa);
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

	public List<Empresa> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		try {
			CriteriaQuery<Empresa> criteria = sessao.getCriteriaBuilder().createQuery(Empresa.class);
			criteria.from(Empresa.class);

			return sessao.createQuery(criteria).getResultList();

		} catch (RuntimeException e) {
			System.out.println(e);
			return null;
		} finally {
			sessao.close();
		}
	}

	public List<Empresa> buscarPorNomeFantasia(Empresa empresa) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		try {
			CriteriaQuery<Empresa> criteria = sessao.getCriteriaBuilder().createQuery(Empresa.class);
			Root<Empresa> from = criteria.from(Empresa.class);
			criteria.where(sessao.getCriteriaBuilder().like(from.get("fantasia"), empresa.getFantasia()));

			return sessao.createQuery(criteria).getResultList();

		} catch (RuntimeException e) {
			System.out.println(e);
			return null;
		} finally {
			sessao.close();
		}

	}
	
	public Empresa buscarPorCNPJ(Empresa empresa) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		try {
			CriteriaQuery<Empresa> criteria = sessao.getCriteriaBuilder().createQuery(Empresa.class);
			Root<Empresa> from = criteria.from(Empresa.class);
			criteria.where(sessao.getCriteriaBuilder().equal(from.get("cnpj"), empresa.getCnpj()));

			return sessao.createQuery(criteria).getSingleResult();

		} catch (RuntimeException e) {
			System.out.println(e);
			return null;
		} finally {
			sessao.close();
		}

	}

	public Empresa buscarPorID(Empresa empresa) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		try {
			CriteriaQuery<Empresa> criteria = sessao.getCriteriaBuilder().createQuery(Empresa.class);
			Root<Empresa> from = criteria.from(Empresa.class);
			criteria.where(sessao.getCriteriaBuilder().equal(from.get("id"), empresa.getId()));

			return sessao.createQuery(criteria).getSingleResult();

		} catch (RuntimeException e) {
			System.out.println(e);
			return null;
		} finally {
			sessao.close();
		}

	}

}
