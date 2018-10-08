package br.com.leilao.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.leilao.domain.TipoEntrega;
import br.com.leilao.util.HibernateUtil;

public class TipoEntregaDAO {

	public void salvar(TipoEntrega tipoEntrega) {
		if (tipoEntrega != null) {
			if (tipoEntrega.getNome() != null && tipoEntrega.getNome() != "") {
				Session sessao = HibernateUtil.getSessionFactory().openSession();
				Transaction transacao = null;
				try {
					tipoEntrega.setNome(tipoEntrega.getNome().toUpperCase());
					transacao = sessao.beginTransaction();
					sessao.saveOrUpdate(tipoEntrega);
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

	public List<TipoEntrega> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		try {
			CriteriaQuery<TipoEntrega> criteria = sessao.getCriteriaBuilder().createQuery(TipoEntrega.class);
			criteria.from(TipoEntrega.class);

			return sessao.createQuery(criteria).getResultList();

		} catch (RuntimeException e) {
			System.out.println(e);
			return null;
		} finally {
			sessao.close();
		}
	}

	public List<TipoEntrega> buscarPorNome(TipoEntrega tipoEntrega) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		try {
			CriteriaQuery<TipoEntrega> criteria = sessao.getCriteriaBuilder().createQuery(TipoEntrega.class);
			Root<TipoEntrega> from = criteria.from(TipoEntrega.class);
			criteria.where(sessao.getCriteriaBuilder().like(from.get("nome"), tipoEntrega.getNome()));

			return sessao.createQuery(criteria).getResultList();

		} catch (RuntimeException e) {
			System.out.println(e);
			return null;
		} finally {
			sessao.close();
		}

	}

	public TipoEntrega buscarPorID(TipoEntrega tipoEntrega) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		try {
			CriteriaQuery<TipoEntrega> criteria = sessao.getCriteriaBuilder().createQuery(TipoEntrega.class);
			Root<TipoEntrega> from = criteria.from(TipoEntrega.class);
			criteria.where(sessao.getCriteriaBuilder().equal(from.get("id"), tipoEntrega.getId()));

			return sessao.createQuery(criteria).getSingleResult();

		} catch (RuntimeException e) {
			System.out.println(e);
			return null;
		} finally {
			sessao.close();
		}

	}

}
