package br.com.leilao.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.leilao.domain.TipoCliente;
import br.com.leilao.util.HibernateUtil;

public class TipoClienteDAO {

	public void salvar(TipoCliente tipoCliente) {
		if (tipoCliente != null) {
			if (tipoCliente.getNome() != null && tipoCliente.getNome() != "") {
				Session sessao = HibernateUtil.getSessionFactory().openSession();
				Transaction transacao = null;
				try {
					tipoCliente.setNome(tipoCliente.getNome().toUpperCase());
					transacao = sessao.beginTransaction();
					sessao.saveOrUpdate(tipoCliente);
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
		} else {

		}
	}

	public List<TipoCliente> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		try {
			CriteriaQuery<TipoCliente> criteria = sessao.getCriteriaBuilder().createQuery(TipoCliente.class);
			criteria.from(TipoCliente.class);

			return sessao.createQuery(criteria).getResultList();

		} catch (RuntimeException e) {
			System.out.println(e);
			return null;
		} finally {
			sessao.close();
		}
	}

	public List<TipoCliente> buscarPorNome(TipoCliente tipoCliente) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		try {
			CriteriaQuery<TipoCliente> criteria = sessao.getCriteriaBuilder().createQuery(TipoCliente.class);
			Root<TipoCliente> from = criteria.from(TipoCliente.class);
			criteria.where(sessao.getCriteriaBuilder().like(from.get("nome"), tipoCliente.getNome()));

			return sessao.createQuery(criteria).getResultList();

		} catch (RuntimeException e) {
			System.out.println(e);
			return null;
		} finally {
			sessao.close();
		}

	}

	public TipoCliente buscarPorID(TipoCliente tipoCliente) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		try {
			CriteriaQuery<TipoCliente> criteria = sessao.getCriteriaBuilder().createQuery(TipoCliente.class);
			Root<TipoCliente> from = criteria.from(TipoCliente.class);
			criteria.where(sessao.getCriteriaBuilder().equal(from.get("id"), tipoCliente.getId()));

			return sessao.createQuery(criteria).getSingleResult();

		} catch (RuntimeException e) {
			System.out.println(e);
			return null;
		} finally {
			sessao.close();
		}

	}

}
